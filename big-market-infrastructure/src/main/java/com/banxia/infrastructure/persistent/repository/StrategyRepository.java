package com.banxia.infrastructure.persistent.repository;

import com.banxia.domain.strategy.model.entity.StrategyEntity;
import com.banxia.domain.strategy.repository.IStrategyRepository;
import com.banxia.infrastructure.converter.StrategyMapping;
import com.banxia.infrastructure.persistent.dao.IStrategyDao;
import com.banxia.infrastructure.persistent.po.Strategy;
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

    @Resource
    private StrategyMapping strategyMapping;

    @Resource
    private IStrategyDao strategyDao;

    @Override
    public void saveStrategyRange(String key, Integer range) {
        String redisKey = Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + key;
        redisService.setValue(redisKey, range);
    }

    @Override
    public Integer queryStrategyRange(String key) {
        String redisKey = Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + key;
        return redisService.getValue(redisKey);
    }

    @Override
    public StrategyEntity queryStrategyEntityByStrategyId(Long strategyId) {

        String redisKey = Constants.RedisKey.STRATEGY_KEY + strategyId;
        StrategyEntity strategyEntity = redisService.getValue(redisKey);
        if(null != strategyEntity){
            return strategyEntity;
        }

        Strategy strategy = strategyDao.queryStrategyEntityByStrategyId(strategyId);
        strategyEntity = strategyMapping.sourceToTarget(strategy);
        redisService.setValue(redisKey, strategyEntity);

        return strategyEntity;
    }
}
