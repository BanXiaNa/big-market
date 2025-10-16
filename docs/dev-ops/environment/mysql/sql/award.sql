create database big_market;

use big_market;

create table award
(
    id           int auto_increment comment '自增ID'
        primary key,
    award_id     int                                not null comment '奖品ID',
    award_key    varchar(32)                        not null comment '发奖策略',
    award_config varchar(32)                        not null comment '奖品配置信息',
    award_desc   varchar(128)                       not null comment '奖品描述',
    create_time  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime default CURRENT_TIMESTAMP not null comment '更新时间'
);

INSERT INTO big_market.award (id, award_id, award_key, award_config, award_desc, create_time, update_time) VALUES (1, 101, 'user_credit_random', '1,100', '用户积分', '2025-10-16 16:31:58.0', '2025-10-16 16:31:58.0');
INSERT INTO big_market.award (id, award_id, award_key, award_config, award_desc, create_time, update_time) VALUES (2, 102, 'openai_use_count', '5', '使用次数', '2025-10-16 16:31:58.0', '2025-10-16 16:31:58.0');
INSERT INTO big_market.award (id, award_id, award_key, award_config, award_desc, create_time, update_time) VALUES (3, 103, 'openai_use_count', '10', '使用次数', '2025-10-16 16:31:58.0', '2025-10-16 16:31:58.0');
INSERT INTO big_market.award (id, award_id, award_key, award_config, award_desc, create_time, update_time) VALUES (4, 104, 'openai_use_count', '20', '使用次数', '2025-10-16 16:31:58.0', '2025-10-16 16:31:58.0');
INSERT INTO big_market.award (id, award_id, award_key, award_config, award_desc, create_time, update_time) VALUES (5, 105, 'openai_model', 'GPT-4', '模型', '2025-10-16 16:31:58.0', '2025-10-16 16:31:58.0');
INSERT INTO big_market.award (id, award_id, award_key, award_config, award_desc, create_time, update_time) VALUES (6, 106, 'openai_model', 'dall-e-2', '模型', '2025-10-16 16:31:58.0', '2025-10-16 16:31:58.0');
INSERT INTO big_market.award (id, award_id, award_key, award_config, award_desc, create_time, update_time) VALUES (7, 107, 'openai_model', 'dall-e-3', '模型', '2025-10-16 16:31:58.0', '2025-10-16 16:31:58.0');
INSERT INTO big_market.award (id, award_id, award_key, award_config, award_desc, create_time, update_time) VALUES (8, 108, 'openai_model', 'GPT-4,dall-e-2,dall-e-3', '模型', '2025-10-16 16:31:58.0', '2025-10-16 16:31:58.0');
