/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afi.jx.lib.common.model;

import java.util.Date;

/**
 *
 * @author AFI
 */
public interface BaseAudit {
    
    public String getCreateBy();

    public void setCreateBy(String createBy);

    public Date getCreateDate() ;

    public void setCreateDate(Date createDate);

    public String getLastModBy();

    public void setLastModBy(String lastModBy);

    public Date getLastModDate();

    public void setLastModDate(Date lastModDate);
    
}
