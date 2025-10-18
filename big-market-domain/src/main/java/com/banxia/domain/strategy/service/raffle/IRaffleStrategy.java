package com.banxia.domain.strategy.service.raffle;

import com.banxia.domain.strategy.model.entity.RaffleAwardEntity;
import com.banxia.domain.strategy.model.entity.RaffleFactorEntity;

/**
 * @Author BanXia
 * @description: 抽奖策略接口
 * @Date 2025/10/17 18:17
 */
public interface IRaffleStrategy {


    RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity);

}