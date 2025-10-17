package com.banxia.infrastructure.persistent.dao;

import com.banxia.infrastructure.persistent.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author BanXia
 * @description: 策略规则 DAO
 * @Date 2025/10/16 16:37
 */
@Mapper
public interface IStrategyRuleDao {
    /**
     * 查询策略规则
     * @param req 查询参数
     * @return 策略规则
     */
    StrategyRule queryStrategyRule(StrategyRule req);
}
