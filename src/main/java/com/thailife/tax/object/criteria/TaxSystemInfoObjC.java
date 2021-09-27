package com.thailife.tax.object.criteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;

import com.thailife.tax.base.DataObjBaseTax;
import com.thailife.tax.object.TaxCatalogObj;
import com.thailife.tax.object.TaxSystemInfoObj;

public class TaxSystemInfoObjC extends DataObjBaseTax{
	
	private String systemId;
    private String name;
    private String nameTh;
    private String nameEn;
    private List<TaxSystemInfoObj> listTaxSystemInfoObj= new ArrayList<TaxSystemInfoObj>();
	
	
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
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
	public List<TaxSystemInfoObj> getListTaxSystemInfoObj() {
		return listTaxSystemInfoObj;
	}
	public void setListTaxSystemInfoObj(List<TaxSystemInfoObj> listTaxSystemInfoObj) {
		this.listTaxSystemInfoObj = listTaxSystemInfoObj;
	}
	
}
