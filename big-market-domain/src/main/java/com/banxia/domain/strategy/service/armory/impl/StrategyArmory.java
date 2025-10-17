package com.banxia.domain.strategy.service.armory.impl;

import com.banxia.domain.strategy.model.entity.StrategyAwardEntity;
import com.banxia.domain.strategy.model.entity.StrategyEntity;
import com.banxia.domain.strategy.model.entity.StrategyRuleEntity;
import com.banxia.domain.strategy.repository.IStrategyAwardRepository;
import com.banxia.domain.strategy.repository.IStrategyRepository;
import com.banxia.domain.strategy.repository.IStrategyRuleRepository;
import com.banxia.domain.strategy.service.armory.IStrategyArmory;
import com.banxia.types.enums.ResponseCode;
import com.banxia.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    @Resource
    private IStrategyRuleRepository strategyRuleRepository;

    @Override
    public void assembleLotteryStrategy(Long strategyId) throws IllegalAccessException {

        // 获取策略奖品信息
        List<StrategyAwardEntity> awardList = strategyAwardRepository.queryStrategyAwardList(strategyId);
        // 缓存全量策略
        assembleLotteryStrategy(strategyId.toString(), awardList);

        // 根据规则模型缓存策略
        StrategyEntity strategyEntity = strategyRepository.queryStrategyEntityByStrategyId(strategyId);
        String ruleWeight = strategyEntity.getRuleWeight();
        if(null == ruleWeight) {
            return;
        }

        StrategyRuleEntity strategyRuleEntity = strategyRuleRepository.queryStrategyRuleEntityByStrategyIdAndRuleModel(strategyId, ruleWeight);
        if(null == strategyRuleEntity){
            throw new AppException(ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getCode(), ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getInfo());
        }

        Map<String, List<Integer>> ruleWeightValues = strategyRuleEntity.getRuleWeightValues();
        Set<String> keys = ruleWeightValues.keySet();
        for (String key : keys) {
            List<Integer> awardIds = ruleWeightValues.get(key);
            List<StrategyAwardEntity> strategyAwardEntityList = new ArrayList<>();
            for (StrategyAwardEntity strategyAwardEntity : awardList) {
                if(awardIds.contains(strategyAwardEntity.getAwardId())){
                    strategyAwardEntityList.add(strategyAwardEntity);
                }
            }
            assembleLotteryStrategy(strategyId + "_" + key, strategyAwardEntityList);
        }


    }

    private void assembleLotteryStrategy(String key, List<StrategyAwardEntity> awardList){
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
        strategyRepository.saveStrategyRange(key, range);
        strategyAwardRepository.saveStrategyAwardMap(key, awardIdCountMap);
    }

    /**
     * 获取奖品总概率
     * @param awardList 奖品列表
     * @return 总概率
     */
    private Integer getTotalAwardRate(List<StrategyAwardEntity> awardList) {
        Integer total = 0;
        for (StrategyAwardEntity strategyAwardEntity : awardList) {
            total += strategyAwardEntity.getAwardRate().multiply(BigDecimal.valueOf(10000)).intValue();
        }
        return total;
    }

    /**
     * 获取奖品最大公因数
     * @param awardList 奖品列表
     * @return 最大公因数
     */
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
