package com.banxia.domain.strategy.model.entity;

import com.banxia.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author BanXia
 * @description: 策略规则实体类
 * @Date 2025/10/17 15:41
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyRuleEntity {

    /** 策略ID */
    private Long strategyId;
    /** 奖品ID */
    private Integer awardId;
    /** 规则类型【1-策略规则、2-奖品规则】 */
    private Integer ruleType;
    /** 规则类型【rule_lock...】 */
    private String ruleModel;
    /** 规则比值 */
    private String ruleValue;
    /** 规则描述 */
    private String ruleDesc;

    public Map<String, List<Integer>> getRuleWeightValues() throws IllegalAccessException {
        if(!"rule_weight".equals(ruleModel)) {
            return null;
        }

        Map<String, List<Integer>> ruleWeightValues = new HashMap<>();

        String[] ruleWeights = ruleValue.split(Constants.SPACE);
        for (String ruleWeight : ruleWeights) {
            if (null == ruleWeight && ruleWeight.isEmpty()) {
                return ruleWeightValues;
            }

            String[] split = ruleWeight.split(Constants.COLON);
            if(split.length != 2){
                throw new IllegalArgumentException("rule_weight格式错误" + ruleWeight);
            }

            String[] awardIds = split[1].split(Constants.SPLIT);
            List<Integer> values = new ArrayList<>();
            for (String awardId : awardIds) {
                values.add(Integer.parseInt(awardId));
            }
            ruleWeightValues.put(ruleWeight, values);
        }
        return ruleWeightValues;
    }
}
