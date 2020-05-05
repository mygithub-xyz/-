package com.fuyang.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(value = {"handler"})
public class RoleExtends extends Role{
    private List<SourcesExtends> sourcesExtendsList;
    private List<RoleSources> roleSources;

    public List<RoleSources> getRoleSources() {
        return roleSources;
    }

    public void setRoleSources(List<RoleSources> roleSources) {
        this.roleSources = roleSources;
    }

    public List<SourcesExtends> getSourcesExtendsList() {
        return sourcesExtendsList;
    }

    public void setSourcesExtendsList(List<SourcesExtends> sourcesExtendsList) {
        this.sourcesExtendsList = sourcesExtendsList;
    }
}
