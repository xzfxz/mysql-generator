
### 通过mybateis逆向工程构建

* 构建maven项目
* 下载依赖
<!-- https://mvnrepository.com/artifact/org.mybatis.generator/mybatis-generator-core -->
    <dependency>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-core</artifactId>
        <version>1.3.5</version>
    </dependency>
* 修改generatorConfig.xml 文件，主要是数据库连接信息 jdbcConnection 标签以及对应的表 table 标签。

* 执行maven 
    mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
    
* 注意：jdbc的依赖在pom文件中插件的位置。每次更新表，都需要重新生成对应的文件。
    
        
        