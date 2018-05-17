package com.zclient.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Transient;
import java.io.Serializable;

/**
 * @Description: api接口返回的响应数据
 * @Author: liubin
 * @Date: Created in 16:20 2018/3/2
 * @Modified by:
 */
@JSONType(asm = false)
public class ApiResult<T> implements Serializable {
    private static final Logger logger=LoggerFactory.getLogger(ApiResult.class);

    /**
     * 表示成功的code，对应{@link #code}
     */
    public static final int SUCCESS_CODE = 0;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    @JSONField(serialize=false)
    private String ver="1.0";
    private T data;

    public ApiResult() {
        code = 0;
        ver = "1.0";
    }

    public ApiResult(int error, String msg) {
        this.code = error;
        this.msg = msg;
    }

    public ApiResult(T data) {
        this.data = data;
    }

    /**
     * 状态码 状态码为字符串类型 (String). eg. "0" (注意有双引号) 0 ~ 100 为系统保留状态 "-100" - session
     * timeout (登陆验证) "0" - api success (default value) "1" - internal error
     * (后端接口未知错误) "2" - redirect url (页面跳转响应) 业务状态码建议使用 100以后的数值 (>100). eg:
     * 0x101F, 0x1013
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    public void setCode(int error) {
        this.code = error;
    }

    /**
     * api status message api状态信息
     *
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @see {@link #getMsg()}
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * version identify, defaults to '1.0' 接口版本
     *
     * @return
     */
    public String getVer() {
        return ver;
    }

    /**
     * @see {@link #getVer()}
     * @param ver
     */
    public void setVer(String ver) {
        this.ver = ver;
    }

    /**
     * Generic type for api data response, can be null, empty "", 0, {}, []
     * etc,. 响应数据
     *
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     * @see {@link #getData()}
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * is successful.
     *
     * @return
     */
    @Transient
    public boolean isSuccessful() {
        return code == SUCCESS_CODE;
    }
}