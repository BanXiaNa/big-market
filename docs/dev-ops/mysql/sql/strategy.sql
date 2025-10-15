create table strategy
(
    id            int auto_increment comment '自增ID'
        primary key,
    strategy_id   int                      not null comment '策略ID',
    strategy_desc varchar(128)             not null comment '策略描述',
    create_time   datetime default (now()) not null comment '创建时间',
    update_time   datetime default (now()) not null comment '更新时间'
);

INSERT INTO big_market.strategy (id, strategy_id, strategy_desc, create_time, update_time) VALUES (1, 10001, '抽奖策略A', '2025-10-16 01:47:29.0', '2025-10-16 01:47:29.0');
