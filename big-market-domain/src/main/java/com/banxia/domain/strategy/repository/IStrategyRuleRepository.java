package com.banxia.domain.strategy.repository;

import com.banxia.domain.strategy.model.entity.StrategyRuleEntity;

/**
 * @Author BanXia
 * @description: 策略规则仓储接口
 * @Date 2025/10/17 15:43
 */
public interface IStrategyRuleRepository {

    /**
     * 根据策略ID和规则模型查询策略规则
     * @param strategyId 策略ID
     * @param ruleModel 规则模型
     * @return 策略规则
     */
    StrategyRuleEntity queryStrategyRuleEntityByStrategyIdAndRuleModel(Long strategyId, String ruleModel);

    /**
     * 根据策略ID、奖品ID和规则模型查询策略规则值
     * @param strategyId 策略ID
     * @param awardId 奖品ID
     * @param ruleModel 策略模型
     * @return 策略规则值
     */
    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);
}
