package com.banxia.domain.strategy.service.dispatch;

/**
 * @Author BanXia
 * @description: 策略调度接口
 * @Date 2025/10/17 14:43
 */
public interface IStrategyDispatch {

    /**
     * 根据策略ID获取策略奖品
     * @param strategyId 策略ID
     * @return 奖品ID
     */
    Integer getRandomAwardId(Long strategyId);

    /**
     * 根据策略ID和权重值获取策略奖品
     * @param strategyId 策略ID
     * @param ruleWeightValue 策略权重值
     * @return 奖品ID
     */
    Integer getRandomAwardId(Long strategyId, String ruleWeightValue);
}
