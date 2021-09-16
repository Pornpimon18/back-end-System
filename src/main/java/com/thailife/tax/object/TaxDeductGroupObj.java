package com.thailife.tax.object;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thailife.tax.base.DataObjBaseTax;

public class TaxDeductGroupObj extends DataObjBaseTax{
	
	 private String deductGroupId;
	    private String name;
	    private String nameTh;
	    private String nameEn;
	    private String description;
	    private String descriptionTh;
	    private String descriptionEn;
	    private BigDecimal amount;
	    private Date effectiveDate;
	    
		public String getDeductGroupId() {
			return deductGroupId;
		}
		public void setDeductGroupId(String deductGroupId) {
			this.deductGroupId = deductGroupId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNameTh() {
			return nameTh;
		}
		public void setNameTh(String nameTh) {
			this.nameTh = nameTh;
		}
		public String getNameEn() {
			return nameEn;
		}
		public void setNameEn(String nameEn) {
			this.nameEn = nameEn;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getDescriptionTh() {
			return descriptionTh;
		}
		public void setDescriptionTh(String descriptionTh) {
			this.descriptionTh = descriptionTh;
		}
		public String getDescriptionEn() {
			return descriptionEn;
		}
		public void setDescriptionEn(String descriptionEn) {
			this.descriptionEn = descriptionEn;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		public void setEffectiveDate(Date effectiveDate) {
			this.effectiveDate = effectiveDate;
		}
	    
	    

}
