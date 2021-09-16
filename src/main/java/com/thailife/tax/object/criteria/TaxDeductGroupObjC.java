package com.thailife.tax.object.criteria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thailife.tax.base.DataObjBaseTax;
import com.thailife.tax.object.TaxDeductGroupObj;
import com.thailife.tax.object.TaxDeductObj;

public class TaxDeductGroupObjC extends DataObjBaseTax{
	
	    private String name;
	    private String nameTh;
	    private String nameEn;
	    private String description;
	    private List<TaxDeductGroupObj> listTaxDeductGroupObj= new ArrayList<TaxDeductGroupObj>();
	    
		
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
		public List<TaxDeductGroupObj> getListTaxDeductGroupObj() {
			return listTaxDeductGroupObj;
		}
		public void setListTaxDeductGroupObj(List<TaxDeductGroupObj> listTaxDeductGroupObj) {
			this.listTaxDeductGroupObj = listTaxDeductGroupObj;
		}
	    

}
