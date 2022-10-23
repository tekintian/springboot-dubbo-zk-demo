# SpringBoot v2.3.12 + Dubbo + zk demo

## pom坐标

### 主模块pom
~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <!--添加SpringBoot parent支持-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.12.RELEASE</version>
    </parent>
    <groupId>cn.tekin.dubbo</groupId>
    <artifactId>tekin-dubbo-demo</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>tekin-dubbo-service</module>
        <module>tekin-dubbo-consumer</module>
        <module>tekin-dubbo-api</module>
    </modules>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <dependencies>
<!--  springboot dubbo 公共依赖    -->

        <!--springboot依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <!--添加dubbo的springboot依赖-->
        <!-- https://mvnrepository.com/artifact/com.alibaba.boot/dubbo-spring-boot-starter -->
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>0.2.1.RELEASE</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.alibaba/dubbo -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
            <version>2.6.12</version>
        </dependency>

        <!--SpringBoot测试-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>
    <build>
        <plugins>
            <!--添加springboot的maven插件-->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
~~~

### api接口定义模块 pom
注意，这个模块不需要其他任何的依赖，仅定义API，和POJO 供provider和consumer调用。 直接使用IDEA新建maven模块默认的即可

### Provider模块 pom
~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    
    <parent>
        <artifactId>tekin-dubbo-demo</artifactId>
        <groupId>cn.tekin.dubbo</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <modelVersion>4.0.0</modelVersion>

    <artifactId>tekin-dubbo-service</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <dependencies>

        <!-- https://mvnrepository.com/artifact/org.apache.zookeeper/zookeeper -->
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.5.10</version>
        </dependency>
        <dependency>
            <groupId>com.github.sgroschupf</groupId>
            <artifactId>zkclient</artifactId>
            <version>0.1</version>
        </dependency>

        <!--引入api依赖-->
        <dependency>
            <groupId>cn.tekin.dubbo</groupId>
            <artifactId>tekin-dubbo-api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

    </dependencies>
</project>
~~~

### Consumer模块 pom
~~~xml
<!-- 这个仅坐标artifactId和Provider不同，其他依赖Provider一样，相同部分省略 -->
    <artifactId>tekin-dubbo-consumer</artifactId>
~~~


## zk注册中心部署
~~~sh
# 部署ZK  -p 2888:2888 -p 3888:3888 -p 8080:8080
docker run -itd --name zk -p 2181:2181  zookeeper:3.5
~~~

## Dubbo Admin部署
Production Setup
~~~sh
# Clone source code on develop branch
git clone https://github.com/apache/dubbo-admin.git
# Specify registry address in dubbo-admin-server/src/main/resources/application.properties
# Build
mvn clean package -Dmaven.test.skip=true
# Start
mvn --projects dubbo-admin-server spring-boot:run

# 或者
# cd dubbo-admin-distribution/target
# java -jar dubbo-admin-0.1.jar

# Visit http://localhost:8080
# Default username and password is root

~~~

## tips

- 在同一个工程中启动多个实例
IDEA默认一个项目只能Run一个实例， 如果需要多次 Run,需要再 Run/Debug Configurations -> Allow parallel run这里的勾选上， 然后再到 application.yml 中修改应用运行端口号即可再次点击 Run运行多个实例。

- @Reference注入说明
1. 测试类中，如果当前模块无@SpringBootApplication启动类无法注入；
2. 所在包名称和@SpringBootApplication不一致无法注入；
3. 引用的包必须是 import com.alibaba.dubbo.config.annotation.Reference;

- 启动时需要先启动Provider服务提供模块，在启动consumer





