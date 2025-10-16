package com.banxia.infrastructure.persistent.repository;

import com.banxia.domain.strategy.model.entity.StrategyAwardEntity;
import com.banxia.domain.strategy.repository.IStrategyAwardRepository;
import com.banxia.infrastructure.converter.StrategyAwardMapping;
import com.banxia.infrastructure.persistent.dao.IStrategyAwardDao;
import com.banxia.infrastructure.persistent.po.StrategyAward;
import com.banxia.infrastructure.persistent.redis.IRedisService;
import com.banxia.types.common.Constants;
import org.redisson.api.RMap;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author BanXia
 * @description: 策略奖品仓储实现类
 * @Date 2025/10/17 02:32
 */
@Repository
public class StrategyAwardRepository implements IStrategyAwardRepository {

    @Resource
    private IStrategyAwardDao strategyAwardDao;
    @Resource
    private IRedisService redisService;
    @Resource
    private StrategyAwardMapping strategyAwardMapping;


    @Override
    public List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId) {

        String cacheKay = Constants.RedisKey.STRATEGY_AWARD_KEY + strategyId;
        List<StrategyAwardEntity> strategyAwardEntityList = redisService.getValue(cacheKay);
        if (null != strategyAwardEntityList && !strategyAwardEntityList.isEmpty()) {
            return strategyAwardEntityList;
        }

        List<StrategyAward> strategyAwardList = strategyAwardDao.queryStrategyAwardListByStrategyId(strategyId);
        strategyAwardEntityList = strategyAwardMapping.sourceToTarget(strategyAwardList);

        redisService.setValue(cacheKay, strategyAwardEntityList);

        return strategyAwardEntityList;
    }

    @Override
    public void saveStrategyAwardMap(Long strategyId, Map<Integer, Integer> awardIdCountMap) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_LIST_KEY + strategyId;

        RMap<Object, Object> map = redisService.getMap(cacheKey);
        map.putAll(awardIdCountMap);

    }

    @Override
    public Integer queryRandomStrategyAwardId(Long strategyId, Integer randomKey) {
        return redisService.getFromMap(Constants.RedisKey.STRATEGY_AWARD_LIST_KEY + strategyId, randomKey);
    }
}
