package com.fuyang.bean;

import java.io.Serializable;

/**
 * (Position)实体类
 *
 * @author makejava
 * @since 2020-03-23 20:02:45
 */
public class Position implements Serializable {
    private static final long serialVersionUID = 483786611557998761L;
    
    private Integer id;
    
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