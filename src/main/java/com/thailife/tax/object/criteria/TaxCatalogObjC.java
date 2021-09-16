package com.thailife.tax.object.criteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thailife.tax.base.DataObjBaseTax;
import com.thailife.tax.object.TaxCatalogObj;

public class TaxCatalogObjC extends DataObjBaseTax {
	
    private String name;
    private String nameTh;
    private String nameEn;
    private List<TaxCatalogObj> listTaxCatalogObj= new ArrayList<TaxCatalogObj>();
    
	
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
	public List<TaxCatalogObj> getListTaxCatalogObj() {
		return listTaxCatalogObj;
	}
	public void setListTaxCatalogObj(List<TaxCatalogObj> listTaxCatalogObj) {
		this.listTaxCatalogObj = listTaxCatalogObj;
	}

	
    
}
