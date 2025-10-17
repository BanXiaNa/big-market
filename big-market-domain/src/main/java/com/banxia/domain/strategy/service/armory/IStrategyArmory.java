package com.banxia.domain.strategy.service.armory;

/**
 * @Author BanXia
 * @description: 策略装配接口
 * @Date 2025/10/17 02:21
 */
public interface IStrategyArmory {

    /**
     * 装配策略
     * @param strategyId 策略ID
     */
    void assembleLotteryStrategy(Long strategyId) throws IllegalAccessException;

}
