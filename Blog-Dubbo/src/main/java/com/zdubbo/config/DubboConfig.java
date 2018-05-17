package com.zdubbo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description: dubbo配置
 * @Author: liubin
 * @Date: Created in 8:12 2018/5/3
 * @Modified by:
 */
@Configuration
@ImportResource("classpath:dubbo_provider.xml")
public class DubboConfig {


}
