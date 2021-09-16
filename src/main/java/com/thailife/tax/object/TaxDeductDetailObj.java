package com.thailife.tax.object;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.thailife.tax.base.DataObjBaseTax;
import com.thailife.tax.entity.TaxDeductDetailPK;
import com.thailife.tax.utils.Status;

public class TaxDeductDetailObj extends DataObjBaseTax {

	private String year;
	private String taxDeductId;
	private Date effectiveDate;
	private BigDecimal minAmt;
	private BigDecimal maxAmt;
	private BigDecimal rate;
	private BigDecimal excess;
	private TaxDeductObj taxDeductObj;
	
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
	public TaxDeductObj getTaxDeductObj() {
		return taxDeductObj;
	}
	public void setTaxDeductObj(TaxDeductObj taxDeductObj) {
		this.taxDeductObj = taxDeductObj;
	}
	
	
	
}
