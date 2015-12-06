/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afi.jx.lib.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author AFI
 */
@MappedSuperclass
public class BaseAuditEffective extends BaseAuditImpl implements Serializable{
    
    @Column(name="DBGNEFF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dBgnEff;

    @Column(name="DLASTEFF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dLastEff;

    public Date getdBgnEff() {
        return dBgnEff;
    }

    public void setdBgnEff(Date dBgnEff) {
        this.dBgnEff = dBgnEff;
    }

    public Date getdLastEff() {
        return dLastEff;
    }

    public void setdLastEff(Date dLastEff) {
        this.dLastEff = dLastEff;
    }    
}
