delete from t_user;
insert into t_user(
    user_id ,password ,
    first_name,last_name,
    deleted,locked,
    remark,
    last_update_date,last_update_user
)values(
  'admin','admin',
  'システム管理者','管理者',
  true,true,
  'test user',
  null,'test'
);
