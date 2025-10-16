use big_market;

create table strategy_rule
(
    id          int auto_increment comment '自增ID'
        primary key,
    strategy_id int                                not null comment '策略ID',
    award_id    int                                null comment '奖品ID',
    rule_type   int                                not null comment '规则类型【1-策略规则、2-奖品规则】',
    rule_model  varchar(16)                        not null comment '规则类型【rule_lock...】',
    rule_value  varchar(128)                       not null comment '规则比值',
    rule_desc   varchar(128)                       not null comment '规则描述',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null comment '修改时间'
);

INSERT INTO big_market.strategy_rule (id, strategy_id, award_id, rule_type, rule_model, rule_value, rule_desc, create_time, update_time) VALUES (1, 10001, 101, 2, 'rule_random', '1,100', '随机积分规则', '2025-10-16 01:59:12.0', '2025-10-16 01:59:12.0');
INSERT INTO big_market.strategy_rule (id, strategy_id, award_id, rule_type, rule_model, rule_value, rule_desc, create_time, update_time) VALUES (2, 10001, 105, 2, 'rule_lock', '1', '次数解锁规则', '2025-10-16 02:02:30.0', '2025-10-16 02:02:30.0');
INSERT INTO big_market.strategy_rule (id, strategy_id, award_id, rule_type, rule_model, rule_value, rule_desc, create_time, update_time) VALUES (3, 10001, 106, 2, 'rule_lock', '3', '次数解锁规则', '2025-10-16 02:02:30.0', '2025-10-16 02:02:30.0');
INSERT INTO big_market.strategy_rule (id, strategy_id, award_id, rule_type, rule_model, rule_value, rule_desc, create_time, update_time) VALUES (4, 10001, 107, 2, 'rule_lock', '5', '次数解锁规则', '2025-10-16 02:02:30.0', '2025-10-16 02:02:30.0');
INSERT INTO big_market.strategy_rule (id, strategy_id, award_id, rule_type, rule_model, rule_value, rule_desc, create_time, update_time) VALUES (5, 10001, 108, 2, 'rule_lock', '10', '次数解锁规则', '2025-10-16 02:02:30.0', '2025-10-16 02:02:30.0');
INSERT INTO big_market.strategy_rule (id, strategy_id, award_id, rule_type, rule_model, rule_value, rule_desc, create_time, update_time) VALUES (6, 10001, null, 1, 'rule_luck_award', '20', '兜底规则', '2025-10-16 02:04:11.0', '2025-10-16 02:04:11.0');
INSERT INTO big_market.strategy_rule (id, strategy_id, award_id, rule_type, rule_model, rule_value, rule_desc, create_time, update_time) VALUES (7, 10001, null, 1, 'rule_weight', '10000:101,102,103,104', '分数过高限制规则', '2025-10-16 02:07:54.0', '2025-10-16 02:07:54.0');
INSERT INTO big_market.strategy_rule (id, strategy_id, award_id, rule_type, rule_model, rule_value, rule_desc, create_time, update_time) VALUES (8, 10001, null, 1, 'rule_blacklist', '1', '黑名单一积分规则', '2025-10-16 02:09:03.0', '2025-10-16 02:09:03.0');
