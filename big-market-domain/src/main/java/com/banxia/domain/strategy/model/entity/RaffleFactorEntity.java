package com.banxia.domain.strategy.model.entity;

/**
 * @Author BanXia
 * @description: 抽奖因子实体类
 * @Date 2025/10/18 01:51
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaffleFactorEntity {

    /** 用户ID */
    private String userId;
    /** 策略ID */
    private Long strategyId;
}
