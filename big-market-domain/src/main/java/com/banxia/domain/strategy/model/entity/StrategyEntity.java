package com.banxia.domain.strategy.model.entity;

import com.banxia.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author BanXia
 * @description: 策略实体类
 * @Date 2025/10/17 15:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyEntity {

    /** 策略ID */
    private Long strategyId;
    /** 策略描述 */
    private String strategyDesc;
    /** 规则模型 */
    private String ruleModels;

    /** 返回规则模型列表 */
    public String[] getRuleModelList(){
        return ruleModels.split(Constants.SPLIT);
    }

    /** 返回是否有rule_weight */
    public String getRuleWeight(){
        for (String ruleModel : getRuleModelList()) {
            if("rule_weight".equals(ruleModel)){
                return ruleModel;
            }
        }
        return null;
    }

}
