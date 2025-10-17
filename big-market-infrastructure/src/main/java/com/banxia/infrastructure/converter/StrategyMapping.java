package com.banxia.infrastructure.converter;

import com.banxia.domain.strategy.model.entity.StrategyAwardEntity;
import com.banxia.domain.strategy.model.entity.StrategyEntity;
import com.banxia.infrastructure.persistent.po.Strategy;
import com.banxia.infrastructure.persistent.po.StrategyAward;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @Author BanXia
 * @description:
 * @Date 2025/10/17 02:54
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StrategyMapping extends IMapping<Strategy, StrategyEntity> {


    @Override
    StrategyEntity sourceToTarget(Strategy var1);

    @Override
    Strategy targetToSource(StrategyEntity var1);

    @Override
    List<StrategyEntity> sourceToTarget(List<Strategy> var1);

    @Override
    List<Strategy> targetToSource(List<StrategyEntity> var1);

}