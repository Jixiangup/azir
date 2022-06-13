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

create table if not exists t_console_competence
(
    id           bigint auto_increment comment '主键id'
        constraint `PRIMARY`
        primary key,
    menu_id      bigint                               not null comment '菜单id',
    gmt_create   datetime   default CURRENT_TIMESTAMP null comment '数据创建时间',
    gmt_modified datetime   default CURRENT_TIMESTAMP null comment '数据修改时间',
    deleted      tinyint(1) default 0                 null comment '数据是否逻辑删除',
    remove       tinyint(1) default 0                 not null comment '是否有删除权限',
    edit         tinyint(1) default 0                 not null comment '是否有编辑权限'
)
    comment '权限表';

create table if not exists t_console_menu
(
    id           bigint auto_increment comment '主键id'
        constraint `PRIMARY`
        primary key,
    parent_id    bigint     default -1                not null comment '父菜单id,如果为根父菜单则为-1',
    name         varchar(128)                         not null comment '菜单名称',
    icon         varchar(128)                         null comment '菜单id',
    gmt_create   datetime   default CURRENT_TIMESTAMP null comment '数据创建时间',
    gmt_modified datetime   default CURRENT_TIMESTAMP null comment '数据修改时间',
    deleted      tinyint(1) default 0                 null comment '数据是否逻辑删除',
    path         varchar(256)                         not null comment '当前路由请求地址',
    weights      int        default 3                 not null comment '当前路由权重,数字越小权重越高'
)
    comment '路由菜单';

create table if not exists t_console_role
(
    id           bigint auto_increment comment '主键id'
        constraint `PRIMARY`
        primary key,
    name         varchar(128)                         not null comment '角色名称',
    gmt_create   datetime   default CURRENT_TIMESTAMP null comment '数据创建时间',
    gmt_modified datetime   default CURRENT_TIMESTAMP null comment '数据修改时间',
    deleted      tinyint(1) default 0                 null comment '数据是否逻辑删除'
)
    comment '角色表';

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
    account      varchar(64)                                                            not null comment '用户登录账号',
    username     varchar(128)                                                           not null comment '用户名称',
    password     varchar(32)                                                            not null comment '登录密码',
    gmt_create   datetime     default CURRENT_TIMESTAMP                                 null comment '数据创建时间',
    gmt_modified datetime     default CURRENT_TIMESTAMP                                 null comment '数据修改时间',
    deleted      tinyint(1)   default 0                                                 null comment '数据是否逻辑删除',
    avatar       varchar(256) default 'https://blogimg.bytestroll.com/avatar/bnyte.jpg' not null comment '用户头像',
    admin        tinyint(1)   default 0                                                 not null comment '是否为admin用户(拥有最高权限)'
)
    comment '用户表';

create table if not exists t_role_competence
(
    id            bigint auto_increment comment '主键id'
        constraint `PRIMARY`
        primary key,
    role_id       bigint                               not null comment '角色id',
    competence_id bigint                               not null comment '权限id',
    gmt_create    datetime   default CURRENT_TIMESTAMP null comment '数据创建时间',
    gmt_modified  datetime   default CURRENT_TIMESTAMP null comment '数据修改时间',
    deleted       tinyint(1) default 0                 null comment '数据是否逻辑删除'
)
    comment '角色权限映射字典表';

create table if not exists t_role_user
(
    id           bigint auto_increment comment '主键id'
        constraint `PRIMARY`
        primary key,
    role_id      bigint                               not null comment '角色id',
    user_id      bigint                               not null comment '用户id',
    gmt_create   datetime   default CURRENT_TIMESTAMP null comment '数据创建时间',
    gmt_modified datetime   default CURRENT_TIMESTAMP null comment '数据修改时间',
    deleted      tinyint(1) default 0                 null comment '数据是否逻辑删除'
)
    comment '角色用户映射字典表';
