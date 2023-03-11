insert into tbl_mydom(
	id
,	created_by
,	created_date
,	last_modified_by
,	last_modified_date
,	description
,	name
)
select
  gen_random_uuid()
, 'MYDOM'
, CURRENT_TIMESTAMP
, 'MYDOM'
, CURRENT_TIMESTAMP
, 'My First Insert to my_dom table by flyway'
, 'MyDom System'
;