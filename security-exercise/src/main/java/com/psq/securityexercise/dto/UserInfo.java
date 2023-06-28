package com.psq.securityexercise.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class UserInfo implements Serializable {
    private Integer userId;

    private String loginName;

    private Set<String> roles;

    private Set<String> permissions;

    private Set<String> originalPermissions;

    private List<String> appIds;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<String> getOriginalPermissions() {
        return originalPermissions;
    }

    public void setOriginalPermissions(Set<String> originalPermissions) {
        this.originalPermissions = originalPermissions;
    }

    public List<String> getAppIds() {
        return appIds;
    }

    public void setAppIds(List<String> appIds) {
        this.appIds = appIds;
    }
}
