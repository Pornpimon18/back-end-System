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

public class TaxDeductGroupDetailObj extends DataObjBaseTax {

	private String year;
	private String deductGroupId;
	private String no;
	private String taxDeductId;
	private Date effectiveDate;
	private TaxDeductObj taxDeductObj;
	private TaxDeductGroupObj taxDeductGroupObj;

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

	public TaxDeductObj getTaxDeductObj() {
		return taxDeductObj;
	}

	public void setTaxDeductObj(TaxDeductObj taxDeductObj) {
		this.taxDeductObj = taxDeductObj;
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

	public TaxDeductGroupObj getTaxDeductGroupObj() {
		return taxDeductGroupObj;
	}

	public void setTaxDeductGroupObj(TaxDeductGroupObj taxDeductGroupObj) {
		this.taxDeductGroupObj = taxDeductGroupObj;
	}

}
