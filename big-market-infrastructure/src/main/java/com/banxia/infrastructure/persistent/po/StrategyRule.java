package com.banxia.infrastructure.persistent.po;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author BanXia
 * @description: 策略规则
 * @Date 2025/10/16 16:02
 */
@Data
@Builder
public class StrategyRule {

    /** 自增ID */
    private Long id;
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
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date updateTime;
}
