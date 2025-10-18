package com.banxia.domain.strategy.service.rule;

import com.banxia.domain.strategy.model.entity.RuleActionEntity;
import com.banxia.domain.strategy.model.entity.RuleMatterEntity;

/**
 * @Author BanXia
 * @description: 过滤器接口
 * @Date 2025/10/18 01:48
 */
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {

    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);
}
