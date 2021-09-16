package com.thailife.tax.object.criteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thailife.tax.base.DataObjBaseTax;
import com.thailife.tax.object.TaxRateObj;

public class TaxRateObjC extends DataObjBaseTax {
	
    private String name;
    private String nameTh;
    private String nameEn;
    private List<TaxRateObj> listTaxRateObj= new ArrayList<TaxRateObj>();
    
	
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
	public List<TaxRateObj> getListTaxRateObj() {
		return listTaxRateObj;
	}
	public void setListTaxRateObj(List<TaxRateObj> listTaxRateObj) {
		this.listTaxRateObj = listTaxRateObj;
	}
	
    
}
