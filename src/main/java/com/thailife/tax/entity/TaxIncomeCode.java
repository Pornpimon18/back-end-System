package com.thailife.tax.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author parach
 */
@Entity
@Table(name = "tax_income_code")
@XmlRootElement
public class TaxIncomeCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "name_th")
    private String nameTh;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "description")
    private String description;
    @Column(name = "description_th")
    private String descriptionTh;
    @Column(name = "description_en")
    private String descriptionEn;
    @Basic(optional = false)
    @Column(name = "tax_catalog")
    private String taxCatalog;
    @Basic(optional = false)
    @Column(name = "tax_rate")
    private String taxRate;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;
    @Column(name = "create_user")
    private String createUser;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "update_user")
    private String updateUser;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public TaxIncomeCode() {
    }

    public TaxIncomeCode(String id) {
        this.id = id;
    }

    public TaxIncomeCode(String id, String code, String name, String taxCatalog, String taxRate, String status, Date effectiveDate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.taxCatalog = taxCatalog;
        this.taxRate = taxRate;
        this.status = status;
        this.effectiveDate = effectiveDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getTaxCatalog() {
        return taxCatalog;
    }

    public void setTaxCatalog(String taxCatalog) {
        this.taxCatalog = taxCatalog;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxIncomeCode)) {
            return false;
        }
        TaxIncomeCode other = (TaxIncomeCode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.TaxIncomeCode[ id=" + id + " ]";
    }
    
}