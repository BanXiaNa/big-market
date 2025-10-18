package com.banxia.infrastructure.persistent.repository;

import com.banxia.domain.strategy.model.entity.StrategyRuleEntity;
import com.banxia.domain.strategy.repository.IStrategyRuleRepository;
import com.banxia.infrastructure.converter.StrategyRuleMapping;
import com.banxia.infrastructure.persistent.dao.IStrategyRuleDao;
import com.banxia.infrastructure.persistent.po.Strategy;
import com.banxia.infrastructure.persistent.po.StrategyRule;
import com.banxia.infrastructure.persistent.redis.IRedisService;
import com.banxia.types.common.Constants;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Author BanXia
 * @description: 策略规则仓储实现类
 * @Date 2025/10/17 15:43
 */
@Repository
public class StrategyRuleRepository implements IStrategyRuleRepository {

    @Resource
    private IRedisService redisService;

    @Resource
    private StrategyRuleMapping strategyRuleMapping;

    @Resource
    private IStrategyRuleDao strategyRuleDao;

    @Override
    public StrategyRuleEntity queryStrategyRuleEntityByStrategyIdAndRuleModel(Long strategyId, String ruleModel) {

        String redisKey = Constants.RedisKey.STRATEGY_RULE_MODEL_KEY + strategyId + "_" + ruleModel;
        StrategyRuleEntity strategyRuleEntity = redisService.getValue(redisKey);
        if(null != strategyRuleEntity) {
            return strategyRuleEntity;
        }

        StrategyRule req = StrategyRule.builder()
                .strategyId(strategyId)
                .ruleModel(ruleModel)
                .build();
        StrategyRule strategyRule = strategyRuleDao.queryStrategyRule(req);

        strategyRuleEntity = strategyRuleMapping.sourceToTarget(strategyRule);
        redisService.setValue(redisKey, strategyRuleEntity);

        return strategyRuleEntity;
    }

    @Override
    public String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel) {
        String redisKey = Constants.RedisKey.STRATEGY_RULE_WEIGHT_KEY + strategyId + "_" + (awardId == null ? "" : (awardId + "_")) + ruleModel;
        String ruleValue = redisService.getValue(redisKey);
        if(null != ruleValue) {
            return ruleValue;
        }

        StrategyRule req = StrategyRule.builder()
                .strategyId(strategyId)
                .awardId(awardId)
                .ruleModel(ruleModel)
                .build();
        ruleValue = strategyRuleDao.queryStrategyRuleValue(req);
        redisService.setValue(redisKey, ruleValue);
        return ruleValue;
    }
}
