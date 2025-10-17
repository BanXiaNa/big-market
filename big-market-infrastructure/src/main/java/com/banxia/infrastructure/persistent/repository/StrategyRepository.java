package com.banxia.infrastructure.persistent.repository;

import com.banxia.domain.strategy.repository.IStrategyRepository;
import com.banxia.infrastructure.persistent.redis.IRedisService;
import com.banxia.types.common.Constants;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author BanXia
 * @description: 策略仓储实现类
 * @Date 2025/10/17 02:28
 */
@Repository
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IRedisService redisService;

    @Override
    public void saveStrategyRange(Long strategyId, Integer range) {
        String key = Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + strategyId;
        redisService.setValue(key, range);
    }

    @Override
    public Integer queryStrategyRange(Long strategyId) {
        String key = Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + strategyId;
        return redisService.getValue(key);
    }
}
