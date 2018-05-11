@echo off

:: 读取配置文件database.properties, 设置数据库变量
for /f "tokens=1,2 delims==" %%i in (versiondatabase.properties) do (
set %%i=%%j
)

::设置脚本路径
@set scriptPath101=1.0.1\


@set currentScript=001_table_version_info_create.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath101%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

@set currentScript=002_table_vc_project_software_alter.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath101%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

@set currentScript=003_table_vc_software_version_alter.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath101%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

@set currentScript=004_table_version_info_alter.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath101%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

pause
