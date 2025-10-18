package com.banxia.domain.strategy.service.rule.impl;

import com.banxia.domain.strategy.model.entity.RuleActionEntity;
import com.banxia.domain.strategy.model.entity.RuleMatterEntity;
import com.banxia.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.banxia.domain.strategy.repository.IStrategyRuleRepository;
import com.banxia.domain.strategy.service.annotation.LogicStrategy;
import com.banxia.domain.strategy.service.rule.ILogicFilter;
import com.banxia.domain.strategy.service.rule.factory.DefaultLogicFactory;
import com.banxia.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author BanXia
 * @description: 黑名单过滤器
 * @Date 2025/10/18 02:56
 */
@Slf4j
@Service
@LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_BLACKLIST)
public class RuleBackListLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {

    @Resource
    private IStrategyRuleRepository strategyRuleRepository;

    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("过滤黑名单规则：{}", ruleMatterEntity);

        // 查找黑名单
        String ruleValue = strategyRuleRepository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(), ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());
        String[] split = ruleValue.split(Constants.COLON);
        Integer awardId = Integer.parseInt(split[0]);
        if(split.length != 2){
            throw new IllegalArgumentException("rule_value格式错误" + ruleValue);
        }

        String[] blackUserList = split[1].split(Constants.SPLIT);
        for (String blackUser : blackUserList) {
            if(ruleMatterEntity.getUserId().equals(blackUser)){
                return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                        .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                        .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                        .ruleModel(DefaultLogicFactory.LogicModel.RULE_BLACKLIST.getCode())
                        .data(RuleActionEntity.RaffleBeforeEntity.builder()
                                .strategyId(ruleMatterEntity.getStrategyId())
                                .awardId(awardId)
                                .build())
                        .build();
            }
        }
        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();
    }
}
