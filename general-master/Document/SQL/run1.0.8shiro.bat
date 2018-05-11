@echo off

::database.properties
for /f "tokens=1,2 delims==" %%i in (shirodatabase.properties) do (
set %%i=%%j
)

::set screpts
@set scriptPath108=1.0.8\


@set currentScript=001_table_u_users_alter.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath108%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

@set currentScript=data\001_table_r_roles.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath108%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

@set currentScript=data\002_table_u_users.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath108%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...


@set currentScript=data\003_table_ur_user_roles.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath108%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

@set currentScript=data\004_table_p_permissions.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath108%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

pause
