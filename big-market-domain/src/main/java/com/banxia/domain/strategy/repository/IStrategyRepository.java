package com.banxia.domain.strategy.repository;

import com.banxia.domain.strategy.model.entity.StrategyEntity;

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
    void saveStrategyRange(String key, Integer range);

    /**
     * 获取策略范围
     * @param key 策略key
     * @return 策略范围
     */
    Integer queryStrategyRange(String key);

    /**
     * 获取策略实体
     * @param strategyId 策略ID
     * @return 策略实体
     */
    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);
}
