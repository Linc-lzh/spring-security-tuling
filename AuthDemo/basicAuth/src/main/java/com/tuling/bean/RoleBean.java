package com.tuling.bean;

import java.util.List;

public class RoleBean {

    private String roleId;

    public RoleBean(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public RoleBean(){

    }

    private String roleName;
    private List<ResourceBean> resources;

    public List<ResourceBean> getResources() {
        return resources;
    }

    public void setResources(List<ResourceBean> resources) {
        this.resources = resources;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
