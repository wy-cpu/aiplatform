package com.demo.util;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class WebMessage {

    // 响应状态 表示执行成功或失败  success| error
    String state = "success";
    // 原因
    String message;
    final String time = new Date().toString();

    public String getTime() {
        return time;
    }
    // 数据
    final Map<String, Object> data = new TreeMap<>();

    public WebMessage(){
    }

    public WebMessage(final String state, final String message) {
        this.state = state;
        this.message = message;
    }

    public WebMessage put(final String key, final Object value) {
        data.put(key, value);
        return this;
    }

    public WebMessage putAll(final Map<String,Object> map) {
        data.putAll(map);
        return this;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }
    public static WebMessage create() {
        return new WebMessage();
    }

}
