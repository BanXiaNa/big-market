package com.banxia.infrastructure.persistent.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author BanXia
 * @description: 策略奖品明细
 * @Date 2025/10/16 15:53
 */
@Data
public class StrategyAward {

    /** 自增ID */
    private Long id;
    /** 策略ID */
    private Long strategyId;
    /** 奖品ID */
    private Integer awardId;
    /** 奖品标题 */
    private String awardTitle;
    /** 奖品副标题 */
    private String awardSubtitle;
    /** 奖品总量 */
    private Integer awardCount;
    /** 奖品剩余库存 */
    private Integer awardCountSurplus;
    /** 奖品中奖概率 */
    private BigDecimal awardRate;
    /** 规则模型 */
    private String ruleModels;
    /** 排序 */
    private Integer sort;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
