package com.banxia.domain.strategy.service.raffle;

import com.banxia.domain.strategy.model.entity.RaffleFactorEntity;
import com.banxia.domain.strategy.model.entity.RuleActionEntity;
import com.banxia.domain.strategy.model.entity.RuleMatterEntity;
import com.banxia.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.banxia.domain.strategy.service.rule.ILogicFilter;
import com.banxia.domain.strategy.service.rule.factory.DefaultLogicFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author BanXia
 * @description: 默认抽奖策略
 * @Date 2025/10/18 03:58
 */
@Slf4j
@Service
public class DefaultRaffleStrategy extends AbstractRaffleStrategy {

    @Resource
    private DefaultLogicFactory logicFactory;

    private Map<String, ILogicFilter<RuleActionEntity.RaffleBeforeEntity>> logicFilterMap;

    @Override
    protected RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffleBeforeLogic(RaffleFactorEntity raffleFactorEntity, String... logics) {

        // 初始化映射
        logicFilterMap = logicFactory.openLogicFilter();

        // 拆分规则
        String ruleBlack = null;
        List<String> ruleList = new ArrayList<>();

        for (String logic : logics) {
            if (DefaultLogicFactory.LogicModel.RULE_BLACKLIST.getCode().equals(logic)) {
                ruleBlack = logic;
            }else {
                ruleList.add(logic);
            }
        }

        // 如果有黑名单，优先执行黑名单
        if (StringUtils.isNotBlank(ruleBlack)) {

            RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter = doCheckRaffleCenterLogic(raffleFactorEntity, ruleBlack);
            if(filter.getCode().equals(RuleLogicCheckTypeVO.TAKE_OVER.getCode())) {
                return filter;
            }
        }

        // 过滤其他规则
        for (String rule : ruleList) {

            RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter = doCheckRaffleCenterLogic(raffleFactorEntity, rule);
            if(filter.getCode().equals(RuleLogicCheckTypeVO.TAKE_OVER.getCode())) {
                return filter;
            }
        }

        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();
    }

    private RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffleCenterLogic(RaffleFactorEntity raffleFactorEntity, String ruleModel) {
        RuleMatterEntity ruleMatterEntity = RuleMatterEntity.builder()
                .userId(raffleFactorEntity.getUserId())
                .strategyId(raffleFactorEntity.getStrategyId())
                .ruleModel(ruleModel)
                .build();
        return logicFilterMap.get(ruleModel).filter(ruleMatterEntity);
    }

}