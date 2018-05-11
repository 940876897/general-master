START TRANSACTION;

DELIMITER //
drop procedure if exists add_col_homework; 
create procedure add_col_homework()
BEGIN
DECLARE  CurrentDatabase VARCHAR(100);
SELECT DATABASE() INTO CurrentDatabase;

IF EXISTS 
(select column_name from information_schema.columns 
where TABLE_SCHEMA = CurrentDatabase and TABLE_NAME = 'u_users' and COLUMN_NAME = 'id_card')
THEN  
   ALTER TABLE u_users  DROP COLUMN id_card; 
END IF;
   ALTER TABLE u_users ADD COLUMN id_card VARCHAR(20) comment '身份证号码';

IF EXISTS 
(select column_name from information_schema.columns 
where TABLE_SCHEMA = CurrentDatabase  and TABLE_NAME = 'u_users' and COLUMN_NAME = 'employee_id')
THEN  
   ALTER TABLE u_users  DROP COLUMN employee_id; 
END IF;
   ALTER TABLE u_users ADD COLUMN employee_id varchar(40) comment '工号';  

IF EXISTS 
(select column_name from information_schema.columns 
where TABLE_SCHEMA = CurrentDatabase  and TABLE_NAME = 'u_users' and COLUMN_NAME = 'type')
THEN  
   ALTER TABLE u_users  DROP COLUMN type; 
END IF;
   ALTER TABLE u_users ADD COLUMN type varchar(2) comment '用户类型';  
  
END;
//

 call add_col_homework();

 drop procedure if exists add_col_homework; 
 
COMMIT;