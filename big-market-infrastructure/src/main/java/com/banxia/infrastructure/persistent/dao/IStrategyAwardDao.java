package com.banxia.infrastructure.persistent.dao;

import com.banxia.infrastructure.persistent.po.StrategyAward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author BanXia
 * @description: 策略奖品 DAO
 * @Date 2025/10/16 16:37
 */
@Mapper
public interface IStrategyAwardDao {

    /**
     * 根据策略ID查询策略奖品列表
     * @param strategyId 策略ID
     * @return 策略奖品列表
     */
    List<StrategyAward> queryStrategyAwardListByStrategyId(Long strategyId);
}
