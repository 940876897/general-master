@echo off

:: 读取配置文件database.properties, 设置数据库变量
for /f "tokens=1,2 delims==" %%i in (versiondatabase.properties) do (
set %%i=%%j
)

::设置脚本路径
@set scriptPath109=1.0.9\


@set currentScript=001_table_vc_software_version_alter.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath109%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...



pause
