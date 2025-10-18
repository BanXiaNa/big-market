# 25.10.16：

## 持久化数据

将数据库中的 奖品表-**award** 策略表-**strategy** 策略奖品表-**strategy_award** 策略规则表-**strategy_rule** 分别创建了他们的 **PO** 和 **DTO** 并做了映射

## 策略概率装配处理

在 **domain** 创建 **strategy-service-armory-IStrategyArmory** 用来实现概率装配工作

在 **domain** 创建 **strategy-service-dispatch-IStrategyDispatch** 用来实现简单的抽奖工作

- **StrategyArmory** 作为 **IStrategyArmory** 的实现类，**assembleLotteryStrategy** 方法负责装配概率
  * 通过仓储获取对应策略的奖品列表，仓储部分使用 **STRATEGY_AWARD_KEY + StrategyId** 来在缓存中拉取，没有就去Mysql拉取并缓存

  * 奖品是按照万分位设置的概率，所以概率映射有一万长度，K是随机值，V是奖品的ID，V存在个个数和棋概率严格对应，按照其概率乘以一万，通过 for 来装配概率映射，目的是通过随机数抽奖的时候，以 **O(1)** 的复杂度迅速查找到奖品ID
  * 通过仓储将概率映射存储到缓存中，K是**STRATEGY_RATE_TABLE_KEY + strategyId**
- **StrategyDispatch** 作为 **IStrategyDispatch** 的实现类，**getRandomAwardId** 方法负责简单抽奖
  * 首先生成一个在[0,10000)的随机值，用于抽奖
  * 通过仓储获得在缓存中的奖品映射，然后获取奖品ID

---

# 25.10.17：

## 策略装配算法优化

之前采用的方法不是很好，可以采用一个寻找所有概率的最大公因数作为所有概率的除数，商作为在映射中K-V的个数，可以缩小映射的大小（大概？

**StrategyArmory** 新增 **getTotalAwardRate** **getGcdAwardRate** 方法，二者用于获取总概率和奖品概率的最大公因数

**StrategyArmory** 修改 **assembleLotteryStrategy** 方法

- 使其先通过上述的两个方法获取到概率的范围，也就是 total / gcd，用于后续的抽奖的随机数生成
- 后续的装配策略修改为通过奖品概率除以最大公因数来获取K-V的数量，来缩小范围
- 将概率范围以 K = **STRATEGY_RATE_RANGE_KEY + strategyId** 的方式缓存

**StrategyDispatch** 修改 **getRandomAwardId** 方法，增加先从缓存中获取概率范围的操作用于计算随机值

## 策略权重概率装配

所谓策略权重就是用户在消耗多少积分之后能够定向从一部分奖池中抽取奖品，而不是这个策略的全部奖品

规则值大概长这个样子：5000:102,103,104,105,06,107,108 8000:104,105,106,107,108
也就是说，如果在5000积分以下，我们不做任何处理，还是从原来的抽奖策略中抽取奖品，五千到八千分，也就是要剔除掉101奖品，八千分以上，要剔除101,102,103奖品，也就是用户投入越大，奖品质量越好

原来的抽奖方式是通过 **StrategyId** 获取的数据，现在权重抽奖改为通过 **StrategyId + 积分:AwardId1,AwardId2...** 作为K获取数据

**StrategyArmory** 重载 **assembleLotteryStrategy** 方法

- 重载的方法用于装配K和对应的奖品数组，奖品的概率范围，K就是 **StrategyId + RuleValue** 奖品数组由原来的 **assembleLotteryStrategy** 提供
- 原来的 **assembleLotteryStrategy** 方法用于根据 **StrategyId** 查询到对应的策略规则，解析策略规则，根据权重中的奖品ID装配好对应的奖品数组，加工好新的RedisKEY，发配到重载方法中用于装配到缓存

**IStrategyDispatch** 重载 **getRandomAwardId** 方法 使其能另外的接收一个策略权重字段

**StrategyDispatch** 重载 **getRandomAwardId** 方法，将抽奖部分单独拉出来，原来的方法负责对传入的字段进行加工成RedisKey

## 抽奖前置规则过滤

前置规则就是抽奖之前进行一定的规则判断，通常是黑名单过滤 **rule_blacklist** 和权重过滤 **rule_weight**，这些规则在抽奖前判断

黑名单规则：被风控的用户只能抽到固定的奖品
权重规则：上面有

很明显：黑名单规则和权重规则产生的结果并不一样，黑名单规则产生的是一个奖品ID，权重规则产生的是一个权重K，需要再进行抽奖才能产生最后的奖品ID

所以我们就需要同时处理不同的规则返回的不同结果

在 **domain-strategy-model-entity** 创建 **RuleActionEntity**

- 这个玩意的定义是规则过滤后的结果，它适用于所有规则的过滤，包括抽奖前判断，抽奖中判断，抽奖后的规则过滤结果，要完成这么牛逼的工作，不加小操作是不行的，我们在里面声明一个泛型，这个泛型必须继承于他规定的内部类 **RaffleEntity**，我们在 **RuleActionEntity** 创建三个类继承于 **RaffleEntity**，分别对应三个判定阶段，这样我们在创建返回值的时候通过指定这个内部类的状态就能使用者一个类完成三个阶段的返回值接收工作

在 **domain-strategy-service** 创建 **rule-ILogicFilter** 用于进行规则过滤

- 我们希望 **ILogicFilter** 接口可以处理所有的规则过滤情况，通过对结果类创建内部类来可以实现这个操作
- 其次，希望通过工厂模式管理实现 **ILogicFilter** 的类，我们创建 **LogicStrategy** 注解来标记这些方法，最后加载到工厂类创建的映射中
- 创建 **RuleBackListLogicFilter** 方法用于进行黑名单过滤，首先查询到对应的黑名单的 **RuleValue**，然后解析这个字符串找到黑名单用户列表，挨个比对，如果找到的话，就返回一个对应的结果，没有的话就返回对应的结果
- 创建 **RuleWeightLogicFilter** 方法用于权重过滤，这里直接先虚拟一个用户积分出来，因为还没写关于用户积分的东西，随后查询到 **RuleValue** 用于字段解析，然后挨个匹配寻找对应的分数区间，找到的话就把K返回，找不到就返回对应结果

在这里使用工厂模式管理每种过滤器，方便初始化和调用

在 在 **domain-strategy-service-rule-factory** 创建 **DefaultLogicFactory** 用于加载所有的规则类到映射中便于后续调用

- 先得规定映射的 K V 

  * K：每种规则的RuleModel
  * V：实现了 **ILogicFilter** 接口的过滤器

  这样的设计可以通过获取 **RuleModel** 直接获取到对应的过滤器

- 里面声明 **LogicModel** 枚举类用来枚举每种 **RuleModel** 作为 字典的K

- 创建 **LogicStrategy** 注解，里面是上面说的 **LogicModel** 枚举类，**DefaultLogicFactory** 可以扫描每种被这个注解修饰的类，并把他们放在映射中，所以上述的两种过滤器都要被这个注解修饰

在 **domain-strategy-service** 创建 **raffle-IRaffleStrategy** 用于抽奖

创建抽象类 **AbstractRaffleStrategy** 实现 **performRaffle** 方法

- 首先进行必要的参数校验，
- 创建抽象方法 **doCheckRaffleBeforeLogic** 进行抽奖前的规则判断

创建 **DefaultRaffleStrategy** 类实现 **AbstractRaffleStrategy** 类的 **doCheckRaffleBeforeLogic** 方法

- 加载从工厂获取的过滤器映射
- 首先过滤黑名单规则，其次过滤其他规则

































