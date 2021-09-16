package com.thailife.tax.object.criteria;

import java.util.ArrayList;
import java.util.List;

import com.thailife.tax.base.DataObjBaseTax;
import com.thailife.tax.object.TaxDeductDetailObj;

public class TaxDeductDetailObjC extends DataObjBaseTax {
	
	private String year;
	private String taxDeductId;
	private List<TaxDeductDetailObj> listTaxDeductDetailObj= new ArrayList<TaxDeductDetailObj>();
	
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
	public List<TaxDeductDetailObj> getListTaxDeductDetailObj() {
		return listTaxDeductDetailObj;
	}
	public void setListTaxDeductDetailObj(List<TaxDeductDetailObj> listTaxDeductDetailObj) {
		this.listTaxDeductDetailObj = listTaxDeductDetailObj;
	}
	

}
