/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afi.jx.lib.common.rest.dto;

/**
 *
 * @author achmad.ha
 */
public class DtoRespondPaging extends DtoRespond{
    
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
