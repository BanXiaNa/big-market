package com.banxia.domain.strategy.repository;

import java.util.Map;

/**
 * @Author BanXia
 * @description: 策略仓储接口
 * @Date 2025/10/17 02:26
 */
public interface IStrategyRepository {

    /**
     * 保存策略范围
     * @param strategyId 策略ID
     * @param range 策略范围
     */
    void saveStrategyRange(Long strategyId, Integer range);

    /**
     * 获取策略范围
     * @param strategyId 策略ID
     * @return 策略范围
     */
    Integer queryStrategyRange(Long strategyId);

}
