package com.banxia.domain.strategy.model.entity;

import com.banxia.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import lombok.*;

/**
 * @Author BanXia
 * @description: 规则动作实体
 * @Date 2025/10/18 02:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleActionEntity <T extends RuleActionEntity.RaffleEntity> {

    private String code = RuleLogicCheckTypeVO.ALLOW.getCode();
    private String info = RuleLogicCheckTypeVO.ALLOW.getInfo();
    private String ruleModel;
    private T data;

    public static class RaffleEntity {

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RaffleBeforeEntity extends RaffleEntity {
        /** 策略ID */
        private Long strategyId;
        /** 权重Key */
        private String ruleWeightValueKey;
        /** 奖品ID */
        private Integer awardId;
    }
    public static class RaffleAfterEntity extends RaffleEntity {

    }
    public static class RaffleCenterEntity extends RaffleEntity {

    }

}
