package com.demo.model.user;

import com.demo.commen.Page;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ai_user")
public class UserInfo extends Page {

    /**主键*/
    @Id
    @GeneratedValue
    private Integer id;

    /**用户名*/
    @Column(name = "userName",  unique = true, nullable = true, length = 255)
    private String userName;

    /**用户密码*/
    @Column(name = "userPassword",  unique = true, nullable = true, length = 255)
    private String userPassword;

    /**创建时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Column(name = "createTime",  unique = true, nullable = true, length = 255)
    private Date createTime;

    /**修改时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Column(name = "updateTime",  unique = true, nullable = true, length = 255)
    private Date updateTime;

    /**登录时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Column(name = "loginTime",  unique = true, nullable = true, length = 255)
    private Date loginTime;

    /**描述*/
    @Column(name = "userDescribe",  unique = true, nullable = true, length = 255)
    private String userDescribe;

    /**图片路径*/
    @Column(name = "userPath", unique = true, nullable = true, length = 255)
    private String userPath;

    public String getUserPath() {
        return userPath;
    }

    public void setUserPath(String userPath) {
        this.userPath = userPath;
    }

    public String getUserDescribe() {
        return userDescribe;
    }

    public void setUserDescribe(String userDescribe) {
        this.userDescribe = userDescribe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
