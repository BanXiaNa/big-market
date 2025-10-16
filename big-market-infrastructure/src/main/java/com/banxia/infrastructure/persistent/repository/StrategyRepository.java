package com.banxia.infrastructure.persistent.repository;

import com.banxia.domain.strategy.repository.IStrategyRepository;
import com.banxia.infrastructure.persistent.redis.IRedisService;
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

}
