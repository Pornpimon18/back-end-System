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
public class TaxDeductDetailPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "year")
    private String year;
    @Basic(optional = false)
    @Column(name = "tax_deduct_id")
    private String taxDeductId;
    @Basic(optional = false)
    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;

    public TaxDeductDetailPK() {
    }

    public TaxDeductDetailPK(String year, String taxDeductId, Date effectiveDate) {
        this.year = year;
        this.taxDeductId = taxDeductId;
        this.effectiveDate = effectiveDate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
        hash += (taxDeductId != null ? taxDeductId.hashCode() : 0);
        hash += (effectiveDate != null ? effectiveDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxDeductDetailPK)) {
            return false;
        }
        TaxDeductDetailPK other = (TaxDeductDetailPK) object;
        if ((this.year == null && other.year != null) || (this.year != null && !this.year.equals(other.year))) {
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
        return "javaapplication1.TaxDeductDetailPK[ year=" + year + ", taxDeductId=" + taxDeductId + ", effectiveDate=" + effectiveDate + " ]";
    }
    
}
