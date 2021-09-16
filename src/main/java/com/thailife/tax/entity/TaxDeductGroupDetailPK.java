package com.thailife.tax.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author parach
 */
@Embeddable
public class TaxDeductGroupDetailPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "year")
    private String year;
    @Basic(optional = false)
    @Column(name = "deduct_group_id")
    private String deductGroupId;
    @Basic(optional = false)
    @Column(name = "no")
    private String no;
    @Basic(optional = false)
    @Column(name = "tax_deduct_id")
    private String taxDeductId;
    @Basic(optional = false)
    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;

    public TaxDeductGroupDetailPK() {
    }

    public TaxDeductGroupDetailPK(String year, String deductGroupId, String no, String taxDeductId, Date effectiveDate) {
        this.year = year;
        this.deductGroupId = deductGroupId;
        this.no = no;
        this.taxDeductId = taxDeductId;
        this.effectiveDate = effectiveDate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDeductGroupId() {
        return deductGroupId;
    }

    public void setDeductGroupId(String deductGroupId) {
        this.deductGroupId = deductGroupId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTaxDeductId() {
        return taxDeductId;
    }

    public void setTaxDeductId(String taxDeductId) {
        this.taxDeductId = taxDeductId;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (year != null ? year.hashCode() : 0);
        hash += (deductGroupId != null ? deductGroupId.hashCode() : 0);
        hash += (no != null ? no.hashCode() : 0);
        hash += (taxDeductId != null ? taxDeductId.hashCode() : 0);
        hash += (effectiveDate != null ? effectiveDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxDeductGroupDetailPK)) {
            return false;
        }
        TaxDeductGroupDetailPK other = (TaxDeductGroupDetailPK) object;
        if ((this.year == null && other.year != null) || (this.year != null && !this.year.equals(other.year))) {
            return false;
        }
        if ((this.deductGroupId == null && other.deductGroupId != null) || (this.deductGroupId != null && !this.deductGroupId.equals(other.deductGroupId))) {
            return false;
        }
        if ((this.no == null && other.no != null) || (this.no != null && !this.no.equals(other.no))) {
            return false;
        }
        if ((this.taxDeductId == null && other.taxDeductId != null) || (this.taxDeductId != null && !this.taxDeductId.equals(other.taxDeductId))) {
            return false;
        }
        if ((this.effectiveDate == null && other.effectiveDate != null) || (this.effectiveDate != null && !this.effectiveDate.equals(other.effectiveDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.TaxDeductGroupDetailPK[ year=" + year + ", deductGroupId=" + deductGroupId + ", no=" + no + ", taxDeductId=" + taxDeductId + ", effectiveDate=" + effectiveDate + " ]";
    }
    
}
