/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afi.jx.lib.common.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import org.hibernate.envers.Audited;

/**
 *
 * @author achmad.ha
 */
@MappedSuperclass
@Audited
public class BaseAuditVersioning extends BaseAuditImpl implements Serializable{
    
    @Version
    @Column(name="IVER",nullable=false)
    private Integer iver;

    public Integer getIver() {
        return iver;
    }

    public void setIver(Integer iver) {
        this.iver = iver;
    }
}
