package com.banxia.infrastructure.converter;

import com.banxia.domain.strategy.model.entity.StrategyAwardEntity;
import com.banxia.infrastructure.persistent.po.StrategyAward;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @Author BanXia
 * @description:
 * @Date 2025/10/17 02:54
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StrategyAwardMapping extends IMapping<StrategyAward, StrategyAwardEntity> {


    @Override
    StrategyAwardEntity sourceToTarget(StrategyAward var1);

    @Override
    StrategyAward targetToSource(StrategyAwardEntity var1);

    @Override
    List<StrategyAwardEntity> sourceToTarget(List<StrategyAward> var1);

    @Override
    List<StrategyAward> targetToSource(List<StrategyAwardEntity> var1);

}