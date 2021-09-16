package com.thailife.tax.entity;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.thailife.tax.utils.PostgreSQLEnumType;
import com.thailife.tax.utils.Status;

/**
 *
 * @author parach
 */
@Entity
@Table(name = "tax_catalog")
@XmlRootElement
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
	)
public class TaxCatalog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tax_catalog_id")
    private String taxCatalogId;
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
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enu_statu")
    @Type( type = "pgsql_enum" )
    private Status status;
    @Basic(optional = false)
    @Column(name = "effective_date")
    @Temporal(TemporalType.DATE)
    private Date effectiveDate;
    @Basic(optional = false)
    @Column(name = "create_user")
    private String createUser;
    @Basic(optional = false)
    @Column(name = "create_time")
    @Temporal(TemporalType.TIME)
    private Date createTime;
    @Basic(optional = false)
    @Column(name = "update_user")
    private String updateUser;
    @Basic(optional = false)
    @Column(name = "update_time")
    @Temporal(TemporalType.TIME)
    private Date updateTime;

    public TaxCatalog() {
    }

    public TaxCatalog(String taxCatalogId) {
        this.taxCatalogId = taxCatalogId;
    }

    public TaxCatalog(String taxCatalogId, String name, Status status, Date effectiveDate, String createUser, Date createTime, String updateUser, Date updateTime) {
        this.taxCatalogId = taxCatalogId;
        this.name = name;
        this.status = status;
        this.effectiveDate = effectiveDate;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
    }

    public String getTaxCatalogId() {
        return taxCatalogId;
    }

    public void setTaxCatalogId(String taxCatalogId) {
        this.taxCatalogId = taxCatalogId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
        hash += (taxCatalogId != null ? taxCatalogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxCatalog)) {
            return false;
        }
        TaxCatalog other = (TaxCatalog) object;
        if ((this.taxCatalogId == null && other.taxCatalogId != null) || (this.taxCatalogId != null && !this.taxCatalogId.equals(other.taxCatalogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.TaxCatalog[ taxCatalogId=" + taxCatalogId + " ]";
    }
    
}
