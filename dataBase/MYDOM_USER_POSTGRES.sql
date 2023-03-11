SHOW TIMEZONE;


insert into tbl_person(
  ID
, CREATED_BY
, CREATED_DATE
, DOCUMENTO
, EMAIL
, LAST_MODIFIED_BY
, LAST_MODIFIED_DATE
, NAME
)
select
  gen_random_uuid()
, 'MYDOM'
, CURRENT_TIMESTAMP
, '47883125060'
, 'mydom@email.com'
, 'MYDOM'
, CURRENT_TIMESTAMP
, 'MyDom System'
;

select * from tbl_person;


insert into tbl_user(
  ID
, CREATED_BY
, CREATED_DATE
, LAST_MODIFIED_BY
, LAST_MODIFIED_DATE
, PASSWORD
, USER_NAME
, PERSON_ID
)
select
  gen_random_uuid()
, 'MYDOM'
, CURRENT_TIMESTAMP
, 'MYDOM'
, CURRENT_TIMESTAMP
, '$2a$10$Zs5QU.ibV9Be7vTd5JzvWeF/tFijRI0O4BOUPw0pM4xckkXnJbMVa'
, 'mydom'
, (select id from tbl_person where name = 'MyDom System')
;
select * from tbl_user;


insert into tbl_role(
  ID
, CREATED_BY
, CREATED_DATE
, LAST_MODIFIED_BY
, LAST_MODIFIED_DATE
, NAME
)
select 
  gen_random_uuid()
, 'MYDOM'
, CURRENT_TIMESTAMP
, 'MYDOM'
, CURRENT_TIMESTAMP
, 'ROLE_ADMIN'
UNION ALL
select 
  gen_random_uuid()
, 'MYDOM'
, CURRENT_TIMESTAMP
, 'MYDOM'
, CURRENT_TIMESTAMP
, 'ROLE_USER'
UNION ALL
select 
  gen_random_uuid()
, 'MYDOM'
, CURRENT_TIMESTAMP
, 'MYDOM'
, CURRENT_TIMESTAMP
, 'ROLE_MODERATOR'
;
select * from tbl_role;


insert into tbl_user_role(
  USER_ID
, ROLE_ID
)
select
  (select id from tbl_user where USER_NAME = 'mydom')
, (select id from tbl_role where name = 'ROLE_ADMIN')
;
select * from tbl_user_role;