package com.demo.model.shop;

import javax.persistence.*;

@Entity
@Table(name = "ai_shop")
public class ShopInfo {

    //主键
    @Id
    @GeneratedValue
    private Integer id;

    /**用户名*/
    @Column(name = "name",  unique = true, nullable = true, length = 255)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
