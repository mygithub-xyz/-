package com.fuyang.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"})
public class EmployeeExtends extends Employee{
    private Position position;
    private RoleExtends roleExtends;

    public RoleExtends getRoleExtends() {
        return roleExtends;
    }

    public void setRoleExtends(RoleExtends roleExtends) {
        this.roleExtends = roleExtends;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
