package com.fuyang.bean;

import java.util.List;

public class SourcesExtends extends Sources{
    private Boolean open;
    private List<SourcesExtends> children;
    private String target = "_self";

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<SourcesExtends> getChildren() {
        return children;
    }

    public void setChildren(List<SourcesExtends> children) {
        this.children = children;
    }
}
