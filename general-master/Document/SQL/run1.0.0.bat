@echo off

:: 读取配置文件database.properties, 设置数据库变量
for /f "tokens=1,2 delims==" %%i in (versiondatabase.properties) do (
set %%i=%%j
)

::设置脚本路径
@set scriptPath101="1.0.0 base db"\


@set currentScript=table_structure.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath101%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

@set currentScript=base_data.sql
echo Start script %currentScript%...
mysql -f -h %server% -u %userName% --password=%password% %dbname% < %scriptPath101%%currentScript% --default-character-set=%defaultcharset%
echo End script %currentScript%...

pause
