@echo off

:: 读取配置文件database.properties, 设置数据库变量
for /f "tokens=1,2 delims==" %%i in (shirodatabase.properties) do (
set %%i=%%j
)

::设置脚本路径
@set scriptPath102=1.0.4\


@set currentScript=001_table_p_permissions_alter.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath102%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

@set currentScript=002_table_r_roles_alter.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath102%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

@set currentScript=003_table_u_users_alter.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath102%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...



@set currentScript=data\001_table_p_permission.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath102%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

@set currentScript=data\002_table_r_roles.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath102%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

@set currentScript=data\003_rp_roles_permissions.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath102%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

@set currentScript=data\004_u_users.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath102%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

@set currentScript=data\005_ur_user_roles.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath102%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...


pause
