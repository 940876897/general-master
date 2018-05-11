# 之前存在数据时转liquibase需要备份已有数据，原理是通过如下命令将数据存放在db/ddl/1.0.x/1.0.10_base_db/Base_Data.xml中,在update进库中
mvn liquibase:generateChangeLog -Dliquibase.diffTypes=data -Dliquibase.outputChangeLogFile=db/ddl/1.0.x/1.0.10_base_db/Base_Data.xml

#更新
mvn liquibase:update -Pdev

