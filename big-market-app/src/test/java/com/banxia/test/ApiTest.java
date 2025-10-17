package com.banxia.test;

import com.banxia.domain.strategy.service.armory.IStrategyArmory;
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
    private IStrategyArmory strategyArmory;

    @Test
    public void test1() {

        strategyArmory.assembleLotteryStrategy(10001L);

    }


    @Test
    public void test2() {

        for(int i = 0; i < 100; i++){
            System.out.println(strategyArmory.getRandomAwardId(10001L));
        }

    }
}
