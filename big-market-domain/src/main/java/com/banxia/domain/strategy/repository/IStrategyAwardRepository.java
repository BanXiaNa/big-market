package com.banxia.domain.strategy.repository;

import com.banxia.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author BanXia
 * @description: 策略奖品仓储接口
 * @Date 2025/10/17 02:32
 */
public interface IStrategyAwardRepository {

    /**
     * 获取策略奖品列表
     * @param strategyId 策略ID
     * @return 策略奖品列表
     */
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    /**
     * 保存策略奖品
     * @param strategyId 策略ID
     * @param awardIdCountMap 奖品ID-奖品数量
     */
    void saveStrategyAwardMap(Long strategyId, Map<Integer, Integer> awardIdCountMap);


    /**
     * 获取策略奖品ID
     * @param strategyId 策略ID
     * @param randomKey 随机数
     * @return 策略奖品ID
     */
    Integer queryRandomStrategyAwardId(Long strategyId, Integer randomKey);
}
