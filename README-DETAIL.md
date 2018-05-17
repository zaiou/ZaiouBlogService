--------------------------------------------------------------------------------------------------------------
更新时间：2018-03-02
变更人：刘彬
变更内容：
	
* 1、完成基本配置，实现简单的增删改查
* 2、加入logback日志
    - a)添加 logback.xml文件
    - b）内容中添加 eg： private final static Logger logger = LoggerFactory.getLogger(TestController.class)。
   
* 3、集成restful风格
    - a）pom.xml中添加
      ~~~
        <dependency>
              <groupId>com.fasterxml.jackson.core</groupId>
              <artifactId>jackson-databind</artifactId>
              <version>2.9.0</version>
            </dependency>
            <dependency>
              <groupId>com.fasterxml.jackson.core</groupId>
              <artifactId>jackson-core</artifactId>
              <version>2.9.0</version>
            </dependency>
            <dependency>
              <groupId>com.fasterxml.jackson.core</groupId>
              <artifactId>jackson-annotations</artifactId>
              <version>2.9.0</version>
            </dependency>
      ~~~
    - b）web.xml中添加
        ~~~
            <!--集成Restful,对所有的Action请求路径开放-->
              <servlet-mapping>
                <servlet-name>springMVC</servlet-name>
                <url-pattern>/</url-pattern>
              </servlet-mapping>
       ~~~
* 4、添加reids
    application中添加：
        ~~~
        <!-- redis -->
            <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
                <property name="maxTotal" value="${maxTotal}"/>
                <property name="maxIdle" value="${maxIdle}"/>
                <property name="minIdle" value="${minIdle}"/>
                <property name="maxWaitMillis" value="15000"/>
                <property name="minEvictableIdleTimeMillis" value="300000"/>
                <property name="numTestsPerEvictionRun" value="3"/>
                <property name="timeBetweenEvictionRunsMillis" value="60000"/>
                <property name="testOnBorrow" value="true"/>
                <property name="testOnReturn" value="true"/>
                <property name="testWhileIdle" value="true"/>
            </bean>
        
            <bean id="jedisConnectionFactory"
                  class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory">
                <property name="hostName" value="${host}"/>
                <property name="port" value="${port}"/>
                <property name="password" value="${password}"/>
                <property name="database" value="${redis.default.db}"/>
                <property name="timeout" value="${timeout}"/>
                <property name="poolConfig" ref="jedisPoolConfig"/>
                <property name="usePool" value="true"/>
            </bean>
        
            <bean id="redisTemplate" class="org.springframework.data.redis.core.StringRedisTemplate">
                <property name="connectionFactory" ref="jedisConnectionFactory"/>
                <!-- 序列化方式 建议key/hashKey采用StringRedisSerializer。 -->
                <property name="keySerializer">
                    <bean
                            class="org.springframework.data.redis.serializer.StringRedisSerializer"/>
                </property>
                <property name="hashKeySerializer">
                    <bean
                            class="org.springframework.data.redis.serializer.StringRedisSerializer"/>
                </property>
                <property name="valueSerializer">
                    <bean
                            class="org.springframework.data.redis.serializer.JdkSerializationRedisSerializer"/>
                </property>
                <property name="hashValueSerializer">
                    <bean
                            class="org.springframework.data.redis.serializer.JdkSerializationRedisSerializer"/>
                </property>
            </bean>
        ~~~
 * 5、配置事物
    ~~~
        <!-- 事务管理管理器（transaction manager） -->
            <bean id="transactionManager"
                  class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
                <property name="dataSource" ref="dataSource"/>
            </bean>
        
            <!-- 配置事务通知属性 对@Transactional这个注解进行的驱动-->
            <tx:advice id="txAdvice" transaction-manager="transactionManager">
                <!-- 定义事务传播属性 -->
                <tx:attributes>
                    <tx:method name="insert*" propagation="REQUIRED"/>
                    <tx:method name="update*" propagation="REQUIRED"/>
                    <tx:method name="edit*" propagation="REQUIRED"/>
                    <tx:method name="save*" propagation="REQUIRED"/>
                    <tx:method name="add*" propagation="REQUIRED"/>
                    <tx:method name="new*" propagation="REQUIRED"/>
                    <tx:method name="set*" propagation="REQUIRED"/>
                    <tx:method name="remove*" propagation="REQUIRED"/>
                    <tx:method name="delete*" propagation="REQUIRED"/>
                    <tx:method name="change*" propagation="REQUIRED"/>
                    <tx:method name="check*" propagation="REQUIRED"/>
                    <tx:method name="get*" propagation="REQUIRED" read-only="true"/>
                    <tx:method name="find*" propagation="REQUIRED" read-only="true"/>
                    <tx:method name="load*" propagation="REQUIRED" read-only="true"/>
                    <tx:method name="*" propagation="REQUIRED" read-only="true"/>
                </tx:attributes>
            </tx:advice>
        
            <!-- 配置事务切面 配置事务影响的类-->
            <aop:config proxy-target-class="true">
                <aop:pointcut id="pointCut" expression="execution(* com.lb.service.*.*(..))"/>
                <aop:advisor advice-ref="txAdvice" pointcut-ref="pointCut"/>
            </aop:config>
    ~~~
--------------------------------------------------------------------------------------------------------------
更新时间：2018-03-04
变更人：刘彬
变更内容：
* 1、封装json返回工具 实体类：ApiResult
* 2、添加mybatis自动生成工具
    ~~~
       - a) pom.xml中添加：
            <!-- mybatis-generator自动生成代码插件 -->
                  <plugin>
                    <groupId>org.mybatis.generator</groupId>
                    <artifactId>mybatis-generator-maven-plugin</artifactId>
                    <version>1.3.2</version>
                    <dependencies>
                      <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>5.1.36</version>
                      </dependency>
                    </dependencies>
                    <configuration>
                      <configurationFile>src/main/resources/generatorConfig.xml</configurationFile>
                      <verbose>true</verbose>
                      <overwrite>true</overwrite>
                    </configuration>
                  </plugin>
       -b) 添加 generatorConfig.xml文件
    ~~~
    
 ------------------------------------------------------------------------------
    更新时间：2018-03-04
    变更人：刘彬
    变更内容：
    * 添加部分业务逻辑
    * mybati之parameterType传递多个参数 参考文档 https://www.cnblogs.com/wangchuanfu/p/5877169.html
   
   
 -----------------------------------------------------------------------------------
    
    更新时间：2018-05-08
    变更人：刘彬
        变更内容：
        *集成 elasticsearch 用 rest client 客户端
        *集成shiro
        *拆分项目为多模块，集成dubbo
            Blog_Client 为dubbo消费者和对外提供接口模块
            Blog-Dubbo 为dubbo提供者
        