create table if not exists t_console_cluster
(
    id           bigint auto_increment comment '主键id'
        constraint `PRIMARY`
        primary key,
    cn_name      varchar(64)                          not null comment '集群中文名称',
    gmt_create   datetime   default CURRENT_TIMESTAMP null comment '数据创建时间',
    gmt_modified datetime   default CURRENT_TIMESTAMP null comment '数据修改时间',
    deleted      tinyint(1) default 0                 null comment '数据是否逻辑删除',
    tenant_id    bigint                               not null comment '租户id',
    en_name      varchar(64)                          not null comment '集群英文名称'
)
    comment '集群表';

create table if not exists t_console_tenant
(
    id           bigint auto_increment comment '主键id'
        constraint `PRIMARY`
        primary key,
    en_name      varchar(64)                          not null comment '租户英文名称',
    cn_name      varchar(64)                          not null comment '租户中文名称',
    user_id      bigint                               not null comment '所属用户id',
    gmt_create   datetime   default CURRENT_TIMESTAMP null comment '数据创建时间',
    gmt_modified datetime   default CURRENT_TIMESTAMP null comment '数据修改时间',
    deleted      tinyint(1) default 0                 null comment '数据是否逻辑删除'
)
    comment '租户表';

create table if not exists t_console_user
(
    id           bigint auto_increment comment '主键id'
        constraint `PRIMARY`
        primary key,
    account      varchar(64)                          not null comment '用户登录账号',
    username     varchar(128)                         not null comment '用户名称',
    password     varchar(16)                          not null comment '登录密码',
    gmt_create   datetime   default CURRENT_TIMESTAMP null comment '数据创建时间',
    gmt_modified datetime   default CURRENT_TIMESTAMP null comment '数据修改时间',
    deleted      tinyint(1) default 0                 null comment '数据是否逻辑删除'
)
    comment '用户表';

