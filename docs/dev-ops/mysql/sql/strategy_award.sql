create table strategy_award
(
    id                  int auto_increment comment '自增ID'
        primary key,
    strategy_id         int                      not null comment '策略ID',
    award_id            int                      not null comment '奖品ID',
    award_title         varchar(128)             not null comment '奖品标题',
    award_subtitle       varchar(128)             null comment '奖品副标题',
    award_count         int                      not null comment '奖品总量',
    award_count_surplus int                      not null comment '奖品剩余库存',
    award_rate          double(6, 4)             not null comment '奖品中奖概率',
    rule_models         varchar(256)             null comment '规则模型',
    sort                int                      not null comment '排序',
    create_time         datetime default (now()) not null comment '创建时间',
    update_time         datetime default (now()) not null comment '更新时间'
);

INSERT INTO big_market.strategy_award (id, strategy_id, award_id, award_title, award_subtitle, award_count, award_count_surplus, award_rate, rule_models, sort, create_time, update_time) VALUES (1, 10001, 101, '随机积分', null, 80000, 80000, 80, 'rule_random', 1, '2025-10-16 01:54:12.0', '2025-10-16 01:54:12.0');
INSERT INTO big_market.strategy_award (id, strategy_id, award_id, award_title, award_subtitle, award_count, award_count_surplus, award_rate, rule_models, sort, create_time, update_time) VALUES (2, 10001, 102, '五次抽奖机会', null, 10000, 10000, 10, null, 2, '2025-10-16 01:54:12.0', '2025-10-16 01:54:12.0');
INSERT INTO big_market.strategy_award (id, strategy_id, award_id, award_title, award_subtitle, award_count, award_count_surplus, award_rate, rule_models, sort, create_time, update_time) VALUES (3, 10001, 103, '十次抽奖机会', null, 5000, 5000, 5, null, 3, '2025-10-16 01:54:12.0', '2025-10-16 01:54:12.0');
INSERT INTO big_market.strategy_award (id, strategy_id, award_id, award_title, award_subtitle, award_count, award_count_surplus, award_rate, rule_models, sort, create_time, update_time) VALUES (4, 10001, 104, '二十次使用机会', null, 4000, 4000, 4, null, 4, '2025-10-16 01:54:12.0', '2025-10-16 01:54:12.0');
INSERT INTO big_market.strategy_award (id, strategy_id, award_id, award_title, award_subtitle, award_count, award_count_surplus, award_rate, rule_models, sort, create_time, update_time) VALUES (5, 10001, 105, '增加GDP-4对话模型', null, 600, 600, 0.6, null, 5, '2025-10-16 01:54:12.0', '2025-10-16 01:54:12.0');
INSERT INTO big_market.strategy_award (id, strategy_id, award_id, award_title, award_subtitle, award_count, award_count_surplus, award_rate, rule_models, sort, create_time, update_time) VALUES (6, 10001, 106, '增加dall-e-2画图模型', null, 200, 200, 0.2, null, 6, '2025-10-16 01:54:12.0', '2025-10-16 01:54:12.0');
INSERT INTO big_market.strategy_award (id, strategy_id, award_id, award_title, award_subtitle, award_count, award_count_surplus, award_rate, rule_models, sort, create_time, update_time) VALUES (7, 10001, 107, '增加dall-e-3画图模型', null, 199, 199, 0.1999, null, 7, '2025-10-16 01:54:12.0', '2025-10-16 01:54:12.0');
INSERT INTO big_market.strategy_award (id, strategy_id, award_id, award_title, award_subtitle, award_count, award_count_surplus, award_rate, rule_models, sort, create_time, update_time) VALUES (8, 10001, 108, '解锁全部模型', null, 1, 1, 0.0001, null, 8, '2025-10-16 01:54:12.0', '2025-10-16 01:54:12.0');
