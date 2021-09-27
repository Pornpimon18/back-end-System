package com.thailife.tax.object.criteria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;

import com.thailife.tax.base.DataObjBaseTax;
import com.thailife.tax.object.TaxDeductDetailObj;
import com.thailife.tax.object.TaxOpcodeObj;

public class TaxOpcodeObjC extends DataObjBaseTax{
	
    private String opcode;
    private String name;
	private Date effectiveDate;
	private List<TaxOpcodeObj> listTaxOpcodeObj= new ArrayList<TaxOpcodeObj>();
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getOpcode() {
		return opcode;
	}
	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}
	public List<TaxOpcodeObj> getListTaxOpcodeObj() {
		return listTaxOpcodeObj;
	}
	public void setListTaxOpcodeObj(List<TaxOpcodeObj> listTaxOpcodeObj) {
		this.listTaxOpcodeObj = listTaxOpcodeObj;
	}
	
	
	
}
