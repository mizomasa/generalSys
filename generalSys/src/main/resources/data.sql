delete from t_user;
insert into t_user(
    user_id ,password ,
    first_name,last_name,
    deleted,locked,
    remark,
    last_update_date,last_update_user
)values(
  'admin','$2a$10$ANRqjCXUPyL5vMkdssPgEOCdFUt181BfrWI0wY.sAOySk/cacY3/W',
  'システム管理者','管理者',
  true,true,
  'test user',
  '2019-01-01','test'
);
delete from m_role;
insert into m_role(role_id,name,description) values(0,'unknown','unknownユーザ');
insert into m_role(role_id,name,description) values(1,'role1','');
insert into m_role(role_id,name,description) values(2,'role2','');
insert into m_role(role_id,name,description) values(3,'role3','');
insert into m_role(role_id,name,description) values(4,'role4','');
insert into m_role(role_id,name,description) values(5,'role5','');
insert into m_role(role_id,name,description) values(6,'role6','');
insert into m_role(role_id,name,description) values(7,'role7','');
insert into m_role(role_id,name,description) values(8,'role8','');
insert into m_role(role_id,name,description) values(9,'role9','');
insert into m_role(role_id,name,description) values(10,'role10','');
insert into m_role(role_id,name,description) values(11,'role11','');
insert into m_role(role_id,name,description) values(12,'role12','');
insert into m_role(role_id,name,description) values(13,'role13','');
insert into m_role(role_id,name,description) values(14,'role14','');
insert into m_role(role_id,name,description) values(15,'role15','');
insert into m_role(role_id,name,description) values(999,'システム管理者','フル権限');