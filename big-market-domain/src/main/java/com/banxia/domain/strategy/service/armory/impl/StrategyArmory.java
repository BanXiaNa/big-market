package com.banxia.domain.strategy.service.armory.impl;

import com.banxia.domain.strategy.model.entity.StrategyAwardEntity;
import com.banxia.domain.strategy.repository.IStrategyAwardRepository;
import com.banxia.domain.strategy.repository.IStrategyRepository;
import com.banxia.domain.strategy.service.armory.IStrategyArmory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.*;

/**
 * @Author BanXia
 * @description: 策略服务实现类
 * @Date 2025/10/17 02:23
 */
@Service
@Slf4j
public class StrategyArmory implements IStrategyArmory {

    @Resource
    private IStrategyRepository strategyRepository;
    @Resource
    private IStrategyAwardRepository strategyAwardRepository;

    @Override
    public void assembleLotteryStrategy(Long strategyId) {

        // 获取策略奖品信息
        List<StrategyAwardEntity> awardList = strategyAwardRepository.queryStrategyAwardList(strategyId);

        List<Integer> awardIdList = new ArrayList<>();
        for (StrategyAwardEntity strategyAwardEntity : awardList) {
            BigDecimal awardRate = strategyAwardEntity.getAwardRate();
            int intValue = awardRate.multiply(new BigDecimal(10000)).intValue();
            for(int i = 0; i < intValue; i++) {
                awardIdList.add(strategyAwardEntity.getAwardId());
            }
        }

        Map<Integer, Integer> awardIdCountMap = new HashMap<>();
        for(int i = 0; i < 10000; i++) {
            awardIdCountMap.put(i, awardIdList.get(i));
        }

        strategyAwardRepository.saveStrategyAwardMap(strategyId, awardIdCountMap);
    }

    @Override
    public Integer getRandomAwardId(Long strategyId) {
        SecureRandom random = new SecureRandom();
        Integer randomKey = random.nextInt(10000);

        return strategyAwardRepository.queryRandomStrategyAwardId(strategyId, randomKey);
    }
}
