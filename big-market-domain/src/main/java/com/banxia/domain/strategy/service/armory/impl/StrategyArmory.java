package com.banxia.domain.strategy.service.armory.impl;

import com.banxia.domain.strategy.model.entity.StrategyAwardEntity;
import com.banxia.domain.strategy.repository.IStrategyAwardRepository;
import com.banxia.domain.strategy.repository.IStrategyRepository;
import com.banxia.domain.strategy.service.armory.IStrategyArmory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.*;

import static com.google.common.math.IntMath.gcd;

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

        // 最大公因数用于缩短概率映射长度
        Integer gcdAwardRate = getGcdAwardRate(awardList);
        Integer totalAwardRate = getTotalAwardRate(awardList);

        // 获取概率范围
        Integer range = totalAwardRate / gcdAwardRate;
        // 计算概率映射
        Map<Integer, Integer> awardIdCountMap = new HashMap<>();
        Integer start = 0;
        for (StrategyAwardEntity strategyAwardEntity : awardList) {
            Integer awardRate = strategyAwardEntity.getAwardRate().multiply(BigDecimal.valueOf(10000)).intValue();
            for(int i = start;i < start + (awardRate / gcdAwardRate); i++){
                awardIdCountMap.put(i, strategyAwardEntity.getAwardId());
            }
            start += (awardRate / gcdAwardRate);
        }
        // 存储
        strategyRepository.saveStrategyRange(strategyId, range);
        strategyAwardRepository.saveStrategyAwardMap(strategyId, awardIdCountMap);
    }

    private Integer getTotalAwardRate(List<StrategyAwardEntity> awardList) {
        Integer total = 0;
        for (StrategyAwardEntity strategyAwardEntity : awardList) {
            total += strategyAwardEntity.getAwardRate().multiply(BigDecimal.valueOf(10000)).intValue();
        }
        return total;
    }

    private Integer getGcdAwardRate(List<StrategyAwardEntity> awardList) {

        // 最大公因数用于缩短概率映射长度
        List<Integer> gcdList = new ArrayList<>();
        for (StrategyAwardEntity strategyAwardEntity : awardList) {
            gcdList.add(strategyAwardEntity.getAwardRate().multiply(BigDecimal.valueOf(10000)).intValue());
        }

        Integer gcd = gcdList.get(0);
        for (int i = 1; i < gcdList.size(); i++) {
            gcd = gcd(gcd, gcdList.get(i));
        }
        return gcd;
    }

}
