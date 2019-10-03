/*M_USER*/
create table if not exists t_user(
    user_id varchar(100) ,
    password varchar(100),
    first_name varchar(100),
    last_name varchar(100),
    deleted boolean,
    locked boolean,
    remark varchar(500),
    last_update_date datetime,
    last_update_user varchar(100),
    PRIMARY KEY(user_id)

);
/*M_USER*/
create table if not exists t_user_role(
    user_id varchar(100) ,
    role_id varchar(100),
    last_update_date datetime,
    last_update_user varchar(100),
    PRIMARY KEY(user_id,role_id)

);

create table if not exists M_ROLE(
    role_id varchar(100) ,
    name varchar(100),
    description varchar(100),
    last_update_date datetime,
    last_update_user varchar(100),
    PRIMARY KEY(role_id)
);