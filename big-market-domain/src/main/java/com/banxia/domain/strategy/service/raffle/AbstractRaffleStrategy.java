package com.banxia.domain.strategy.service.raffle;

import com.banxia.domain.strategy.model.entity.*;
import com.banxia.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.banxia.domain.strategy.repository.IStrategyRepository;
import com.banxia.domain.strategy.service.dispatch.IStrategyDispatch;
import com.banxia.domain.strategy.service.rule.factory.DefaultLogicFactory;
import com.banxia.types.enums.ResponseCode;
import com.banxia.types.exception.AppException;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * @Author BanXia
 * @description: 抽奖策略抽象类
 * @Date 2025/10/18 02:02
 */
public abstract class AbstractRaffleStrategy implements IRaffleStrategy {

    @Resource
    protected IStrategyRepository strategyRepository;

    @Resource
    protected IStrategyDispatch strategyDispatch;

    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity){
        // 1. 参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (null == strategyId || StringUtils.isBlank(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 2. 策略查询
        StrategyEntity strategy = strategyRepository.queryStrategyEntityByStrategyId(strategyId);

        // 3. 抽奖前 - 规则过滤
        RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> ruleActionEntity =
                this.doCheckRaffleBeforeLogic(
                        RaffleFactorEntity.builder()
                                .userId(userId)
                                .strategyId(strategyId)
                                .build(),
                        strategy.getRuleModelList()
                );

        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(ruleActionEntity.getCode())) {
            if (DefaultLogicFactory.LogicModel.RULE_BLACKLIST.getCode().equals(ruleActionEntity.getRuleModel())) {
                // 黑名单返回固定的奖品ID
                return RaffleAwardEntity.builder()
                        .awardId(ruleActionEntity.getData().getAwardId())
                        .build();
            } else if (DefaultLogicFactory.LogicModel.RULE_WIGHT.getCode().equals(ruleActionEntity.getRuleModel())) {
                // 权重根据返回的信息进行抽奖
                RuleActionEntity.RaffleBeforeEntity raffleBeforeEntity = ruleActionEntity.getData();
                String ruleWeightValueKey = raffleBeforeEntity.getRuleWeightValueKey();
                Integer awardId = strategyDispatch.getRandomAwardId(strategyId, ruleWeightValueKey);
                return RaffleAwardEntity.builder()
                        .awardId(awardId)
                        .build();
            }
        }

        // 4. 默认抽奖流程
        Integer awardId = strategyDispatch.getRandomAwardId(strategyId);

        return RaffleAwardEntity.builder()
                .awardId(awardId)
                .build();
    }

    protected abstract RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffleBeforeLogic(RaffleFactorEntity raffleFactorEntity, String... logics);

}