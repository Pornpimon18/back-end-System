package com.thailife.tax.object;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;

import com.thailife.tax.base.DataObjBaseTax;

public class TaxOpcodeObj extends DataObjBaseTax{
	
    private String opcode;
    private String name;
    private String nameTh;
    private String nameEn;
    private String groupType;
    private String opcodeType;
    private String netType;
    private String incomeCatalogId;
    private BigDecimal minRate;
    private BigDecimal maxRate;
    private BigDecimal minBaht;
    private BigDecimal maxBaht;
    private boolean calSoc;
    private boolean calTax;
    private Boolean requireDocNo;
	private Date effectiveDate;
	
	
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
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getOpcodeType() {
		return opcodeType;
	}
	public void setOpcodeType(String opcodeType) {
		this.opcodeType = opcodeType;
	}
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
	public String getIncomeCatalogId() {
		return incomeCatalogId;
	}
	public void setIncomeCatalogId(String incomeCatalogId) {
		this.incomeCatalogId = incomeCatalogId;
	}
	public BigDecimal getMinRate() {
		return minRate;
	}
	public void setMinRate(BigDecimal minRate) {
		this.minRate = minRate;
	}
	public BigDecimal getMaxRate() {
		return maxRate;
	}
	public void setMaxRate(BigDecimal maxRate) {
		this.maxRate = maxRate;
	}
	public BigDecimal getMinBaht() {
		return minBaht;
	}
	public void setMinBaht(BigDecimal minBaht) {
		this.minBaht = minBaht;
	}
	public BigDecimal getMaxBaht() {
		return maxBaht;
	}
	public void setMaxBaht(BigDecimal maxBaht) {
		this.maxBaht = maxBaht;
	}
	public boolean isCalSoc() {
		return calSoc;
	}
	public void setCalSoc(boolean calSoc) {
		this.calSoc = calSoc;
	}
	public boolean isCalTax() {
		return calTax;
	}
	public void setCalTax(boolean calTax) {
		this.calTax = calTax;
	}
	public Boolean getRequireDocNo() {
		return requireDocNo;
	}
	public void setRequireDocNo(Boolean requireDocNo) {
		this.requireDocNo = requireDocNo;
	}
	
	
}
