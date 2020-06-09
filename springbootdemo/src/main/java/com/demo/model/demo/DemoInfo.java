package com.demo.model.demo;

import javax.persistence.*;

@Entity
@Table(name = "t_demo")
public class DemoInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    /**电影名称*/
    @Column(columnDefinition = "MEDIUMTEXT")
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
