package com.thailife.tax.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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

import org.hibernate.annotations.TypeDef;

import com.thailife.tax.utils.PostgreSQLEnumType;

/**
 *
 * @author parach
 */
@Entity
@Table(name = "tax_deduct_detail")
@XmlRootElement
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
	)
public class TaxDeductDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TaxDeductDetailPK taxDeductDetailPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "min_amt")
    private BigDecimal minAmt;
    @Basic(optional = false)
    @Column(name = "max_amt")
    private BigDecimal maxAmt;
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "excess")
    private BigDecimal excess;
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

    public TaxDeductDetail() {
    }

    public TaxDeductDetail(TaxDeductDetailPK taxDeductDetailPK) {
        this.taxDeductDetailPK = taxDeductDetailPK;
    }

    public TaxDeductDetail(TaxDeductDetailPK taxDeductDetailPK, BigDecimal minAmt, BigDecimal maxAmt, String createUser, Date createTime) {
        this.taxDeductDetailPK = taxDeductDetailPK;
        this.minAmt = minAmt;
        this.maxAmt = maxAmt;
        this.createUser = createUser;
        this.createTime = createTime;
    }

    public TaxDeductDetail(String year, String taxDeductId, Date effectiveDate) {
        this.taxDeductDetailPK = new TaxDeductDetailPK(year, taxDeductId, effectiveDate);
    }

    public TaxDeductDetailPK getTaxDeductDetailPK() {
        return taxDeductDetailPK;
    }
    public Date getEffectiveDate() {
        return taxDeductDetailPK.getEffectiveDate();
    }

    public void setTaxDeductDetailPK(TaxDeductDetailPK taxDeductDetailPK) {
        this.taxDeductDetailPK = taxDeductDetailPK;
    }

    public BigDecimal getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(BigDecimal minAmt) {
        this.minAmt = minAmt;
    }

    public BigDecimal getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(BigDecimal maxAmt) {
        this.maxAmt = maxAmt;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getExcess() {
        return excess;
    }

    public void setExcess(BigDecimal excess) {
        this.excess = excess;
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
        hash += (taxDeductDetailPK != null ? taxDeductDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxDeductDetail)) {
            return false;
        }
        TaxDeductDetail other = (TaxDeductDetail) object;
        if ((this.taxDeductDetailPK == null && other.taxDeductDetailPK != null) || (this.taxDeductDetailPK != null && !this.taxDeductDetailPK.equals(other.taxDeductDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.TaxDeductDetail[ taxDeductDetailPK=" + taxDeductDetailPK + " ]";
    }
    
}
