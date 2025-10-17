package com.banxia.test;

import com.banxia.domain.strategy.service.armory.IStrategyArmory;
import com.banxia.domain.strategy.service.dispatch.IStrategyDispatch;
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
    @Resource
    private IStrategyDispatch strategyDispatch;

    @Test
    public void test1() throws IllegalAccessException {

        strategyArmory.assembleLotteryStrategy(10001L);

    }


    @Test
    public void test2() {

        for(int i = 0; i < 100; i++){
            System.out.println(strategyDispatch.getRandomAwardId(10001L));
        }

    }
}
