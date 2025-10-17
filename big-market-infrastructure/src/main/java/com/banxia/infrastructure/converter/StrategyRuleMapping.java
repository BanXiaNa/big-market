package com.banxia.infrastructure.converter;

import com.banxia.domain.strategy.model.entity.StrategyRuleEntity;
import com.banxia.infrastructure.persistent.po.StrategyRule;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @Author BanXia
 * @description:
 * @Date 2025/10/17 02:54
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StrategyRuleMapping extends IMapping<StrategyRule, StrategyRuleEntity> {


    @Override
    StrategyRuleEntity sourceToTarget(StrategyRule var1);

    @Override
    StrategyRule targetToSource(StrategyRuleEntity var1);

    @Override
    List<StrategyRuleEntity> sourceToTarget(List<StrategyRule> var1);

    @Override
    List<StrategyRule> targetToSource(List<StrategyRuleEntity> var1);

}