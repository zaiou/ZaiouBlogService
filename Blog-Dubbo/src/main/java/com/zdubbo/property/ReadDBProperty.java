package com.zdubbo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Description: 读数据库属性
 * @Author: liubin
 * @Date: Created in 20:44 2018/5/2
 * @Modified by:
 */
@ConfigurationProperties("dubboservice.datasource.read")
@EnableConfigurationProperties
public class ReadDBProperty {
    private String url;
    private String driver;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
