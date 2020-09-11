drop table if exists t_user;
create table t_user
(
    user_id     int primary key auto_increment,
    user_name   varchar(20)  not null,
    password    varchar(100) not null,
    salt        varchar(20),
    user_status varchar(10)  not null,
    delete_flag smallint     not null,
    create_date datetime     not null,
    update_date datetime,
    creator     long         not null,
    updater     long
);
drop table if exists t_role;
create table t_role
(
    role_id     int primary key auto_increment,
    role_name   varchar(20) not null,
    delete_flag smallint    not null,
    create_date datetime    not null,
    update_date datetime,
    creator     long        not null,
    updater     long
);
drop table if exists t_permission;
create table t_permission
(
    permission_id     int primary key auto_increment,
    permission_name   varchar(20) not null,
    delete_flag smallint    not null,
    create_date datetime    not null,
    update_date datetime,
    creator     long        not null,
    updater     long
);