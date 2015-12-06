/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afi.jx.lib.common.rest.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author achmad.ha
 */
public class DtoRespond {   
    
    private String stat;
    private String msg;    
    private String detailMsg;
    private List rows; 
    private List<String> dtlMsg;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        if(this.dtlMsg==null){
            this.dtlMsg=new ArrayList<String>();
        }
        this.dtlMsg.add(detailMsg);
        //this.detailMsg = detailMsg;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public List<String> getDtlMsg() {
        return dtlMsg;
    }

    public void setDtlMsg(List<String> dtlMsg) {
        this.dtlMsg = dtlMsg;
    }
}
