package com.banxia.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author BanXia
 * @description: 抽奖奖品信息
 * @Date 2025/10/18 01:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaffleAwardEntity {

    /** 策略ID */
    private Long strategyId;
    /** 奖品ID */
    private Integer awardId;
    /** 发奖策略 */
    private String awardKey;
    /** 奖品配置信息 */
    private String awardConfig;
    /** 奖品描述 */
    private String awardDesc;
}
