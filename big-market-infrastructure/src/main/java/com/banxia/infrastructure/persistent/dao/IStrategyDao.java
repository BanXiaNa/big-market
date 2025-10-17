package com.banxia.infrastructure.persistent.dao;

import com.banxia.infrastructure.persistent.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author BanXia
 * @description: 策略 DAO
 * @Date 2025/10/16 16:36
 */
@Mapper
public interface IStrategyDao {
    /**
     * 根据策略ID查询策略
     * @param strategyId 策略ID
     * @return 策略
     */
    Strategy queryStrategyEntityByStrategyId(Long strategyId);
}
