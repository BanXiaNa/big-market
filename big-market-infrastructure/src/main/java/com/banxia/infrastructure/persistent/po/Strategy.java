package com.banxia.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author BanXia
 * @description: 策略
 * @Date 2025/10/16 15:42
 */
@Data
public class Strategy {

    /** 自增ID */
    private Long id;
    /** 策略ID */
    private Long strategyId;
    /** 策略描述 */
    private String strategyDesc;
    /** 规则模型 */
    private String ruleModels;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
