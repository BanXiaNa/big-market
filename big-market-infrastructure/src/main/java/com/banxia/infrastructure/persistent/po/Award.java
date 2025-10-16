package com.banxia.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author BanXia
 * @description: 奖品表
 * @Date 2025/10/16 16:32
 */
@Data
public class Award {

    /** 自增ID */
    private Long id;
    /** 奖品ID */
    private Integer awardId;
    /** 发奖策略 */
    private String awardKey;
    /** 奖品配置信息 */
    private String awardConfig;
    /** 奖品描述 */
    private String awardDesc;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
