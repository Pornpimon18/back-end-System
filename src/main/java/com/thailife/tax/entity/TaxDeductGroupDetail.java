package com.thailife.tax.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author parach
 */
@Entity
@Table(name = "tax_deduct_group_detail")
@XmlRootElement
public class TaxDeductGroupDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TaxDeductGroupDetailPK taxDeductGroupDetailPK;
    @Basic(optional = false)
    @Column(name = "create_user")
    private String createUser;
    @Basic(optional = false)
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "update_user")
    private String updateUser;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public TaxDeductGroupDetail() {
    }

    public TaxDeductGroupDetail(TaxDeductGroupDetailPK taxDeductGroupDetailPK) {
        this.taxDeductGroupDetailPK = taxDeductGroupDetailPK;
    }

    public TaxDeductGroupDetail(TaxDeductGroupDetailPK taxDeductGroupDetailPK, String createUser, Date createTime) {
        this.taxDeductGroupDetailPK = taxDeductGroupDetailPK;
        this.createUser = createUser;
        this.createTime = createTime;
    }

    public TaxDeductGroupDetail(String year, String deductGroupId, String no, String taxDeductId, Date effectiveDate) {
        this.taxDeductGroupDetailPK = new TaxDeductGroupDetailPK(year, deductGroupId, no, taxDeductId, effectiveDate);
    }

    public TaxDeductGroupDetailPK getTaxDeductGroupDetailPK() {
        return taxDeductGroupDetailPK;
    }

    public void setTaxDeductGroupDetailPK(TaxDeductGroupDetailPK taxDeductGroupDetailPK) {
        this.taxDeductGroupDetailPK = taxDeductGroupDetailPK;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxDeductGroupDetailPK != null ? taxDeductGroupDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxDeductGroupDetail)) {
            return false;
        }
        TaxDeductGroupDetail other = (TaxDeductGroupDetail) object;
        if ((this.taxDeductGroupDetailPK == null && other.taxDeductGroupDetailPK != null) || (this.taxDeductGroupDetailPK != null && !this.taxDeductGroupDetailPK.equals(other.taxDeductGroupDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.TaxDeductGroupDetail[ taxDeductGroupDetailPK=" + taxDeductGroupDetailPK + " ]";
    }
    
}
