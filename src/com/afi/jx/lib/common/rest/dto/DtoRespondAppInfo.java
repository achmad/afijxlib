/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afi.jx.lib.common.rest.dto;

/**
 *
 * @author achmad.ha
 */
public class DtoRespondAppInfo extends DtoRespond{
    
    private String[] role;
    private String[] customRoleAccess;

    public String[] getRole() {
        return role;
    }

    public void setRole(String[] role) {
        this.role = role;
    }

    public String[] getCustomRoleAccess() {
        return customRoleAccess;
    }

    public void setCustomRoleAccess(String[] customRoleAccess) {
        this.customRoleAccess = customRoleAccess;
    }
}
