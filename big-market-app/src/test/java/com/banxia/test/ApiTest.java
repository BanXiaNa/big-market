package com.banxia.test;

import com.banxia.domain.strategy.model.entity.RaffleAwardEntity;
import com.banxia.domain.strategy.model.entity.RaffleFactorEntity;
import com.banxia.domain.strategy.service.armory.IStrategyArmory;
import com.banxia.domain.strategy.service.dispatch.IStrategyDispatch;
import com.banxia.domain.strategy.service.raffle.IRaffleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private IRaffleStrategy raffleStrategy;

    @Test
    public void test() {

        RaffleFactorEntity req = RaffleFactorEntity.builder()
                .userId("userId")
                .strategyId(10001L)
                .build();

        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(req);
        System.out.println(raffleAwardEntity.getAwardId());
    }
}
