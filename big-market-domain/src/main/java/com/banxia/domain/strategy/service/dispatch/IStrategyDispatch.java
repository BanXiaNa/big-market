package com.banxia.domain.strategy.service.dispatch;

/**
 * @Author BanXia
 * @description: 策略调度接口
 * @Date 2025/10/17 14:43
 */
public interface IStrategyDispatch {

    /**
     * 获取策略奖品
     * @param strategyId 策略ID
     * @return 奖品ID
     */
    Integer getRandomAwardId(Long strategyId);
}
