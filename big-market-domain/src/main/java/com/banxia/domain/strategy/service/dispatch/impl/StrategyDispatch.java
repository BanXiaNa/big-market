package com.banxia.domain.strategy.service.dispatch.impl;

import com.banxia.domain.strategy.repository.IStrategyAwardRepository;
import com.banxia.domain.strategy.repository.IStrategyRepository;
import com.banxia.domain.strategy.service.dispatch.IStrategyDispatch;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.SecureRandom;

/**
 * @Author BanXia
 * @description:
 * @Date 2025/10/17 14:44
 */
@Service
public class StrategyDispatch implements IStrategyDispatch {

    @Resource
    private IStrategyRepository strategyRepository;
    @Resource
    private IStrategyAwardRepository strategyAwardRepository;

    @Override
    public Integer getRandomAwardId(Long strategyId) {
        // 获取概率范围
        Integer range = strategyRepository.queryStrategyRange(String.valueOf(strategyId));
        // 生成随机值
        Integer randomKey = new SecureRandom().nextInt(range);
        // 返回结果
        return strategyAwardRepository.queryRandomStrategyAwardId(String.valueOf(strategyId), randomKey);
    }

    @Override
    public Integer getRandomAwardId(Long strategyId, String ruleWeightValue) {

        String redisKey = strategyId + "_" + ruleWeightValue;
        // 获取概率范围
        Integer range = strategyRepository.queryStrategyRange(redisKey);
        // 生成随机值
        Integer randomKey = new SecureRandom().nextInt(range);
        // 返回结果
        return strategyAwardRepository.queryRandomStrategyAwardId(redisKey, randomKey);
    }
}
