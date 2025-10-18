package com.banxia.domain.strategy.model.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @Author BanXia
 * @description: 过滤物料
 * @Date 2025/10/18 02:06
 */
@Data
@Builder
public class RuleMatterEntity {

    /** 用户ID */
    private String userId;
    /** 策略ID */
    private Long strategyId;
    /** 奖品ID */
    private Integer awardId;
    /** 规则类型 */
    private String ruleModel;
}
