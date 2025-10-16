package com.banxia.test.DaoTest;

import com.banxia.infrastructure.persistent.dao.IAwardDao;
import com.banxia.infrastructure.persistent.dao.IStrategyAwardDao;
import com.banxia.infrastructure.persistent.dao.IStrategyDao;
import com.banxia.infrastructure.persistent.dao.IStrategyRuleDao;
import com.banxia.infrastructure.persistent.po.Award;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author BanXia
 * @description:
 * @Date 2025/10/16 16:54
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DaoTest {

    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyRuleDao strategyRuleDao;
    @Resource
    private IStrategyAwardDao strategyAwardDao;
    @Resource
    private IAwardDao awardDao;

    @org.junit.Test
    public void test() {

        List<Award> awards = awardDao.queryList();
        System.out.println(awards);

    }
}
