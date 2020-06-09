package com.demo.model.user;

import com.demo.commen.Page;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ai_role")
public class Roleinfo extends Page {

    /**主键*/
    @Id
    @GeneratedValue
    private Integer id;

    /**角色类型 1 普通用户 2 会员 3 管理员*/
    @Column(name = "roleTypeName",  unique = true, nullable = true, length = 255)
    private String roleTypeName;

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
    @Column(name = "roleDescribe",  unique = true, nullable = true, length = 255)
    private String roleDescribe;

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

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleTypeName() {
        return roleTypeName;
    }

    public void setRoleTypeName(String roleTypeName) {
        this.roleTypeName = roleTypeName;
    }
}
