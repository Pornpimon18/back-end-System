package com.thailife.tax.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.thailife.tax.utils.GroupType;
import com.thailife.tax.utils.NetType;
import com.thailife.tax.utils.OpcodeType;
import com.thailife.tax.utils.PostgreSQLEnumType;
import com.thailife.tax.utils.Status;

/**
 *
 * @author parach
 */
@Entity
@Table(name = "tax_opcode")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
	)
public class TaxOpcode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "opcode")
    private String opcode;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "name_th")
    private String nameTh;
    @Column(name = "name_en")
    private String nameEn;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enu_group_type")
    @Type( type = "pgsql_enum" )
    private GroupType groupType;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enu_opcode_type")
    @Type( type = "pgsql_enum" )
    private OpcodeType opcodeType;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enu_net_type")
    @Type( type = "pgsql_enum" )
    private NetType netType;
    @Basic(optional = false)
    @Column(name = "income_catalog_id")
    private String incomeCatalogId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "min_rate")
    private BigDecimal minRate;
    @Basic(optional = false)
    @Column(name = "max_rate")
    private BigDecimal maxRate;
    @Basic(optional = false)
    @Column(name = "min_baht")
    private BigDecimal minBaht;
    @Basic(optional = false)
    @Column(name = "max_baht")
    private BigDecimal maxBaht;
    @Basic(optional = false)
    @Column(name = "cal_soc")
    private boolean calSoc;
    @Basic(optional = false)
    @Column(name = "cal_tax")
    private boolean calTax;
    @Column(name = "require_doc_no")
    private Boolean requireDocNo;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enu_statu")
    @Type( type = "pgsql_enum" )
    private Status status;
    @Basic(optional = false)
    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;
    @Basic(optional = false)
    @Column(name = "create_user")
    private String createUser;
    @Basic(optional = false)
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "update_user")
    private String updateUser;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public TaxOpcode() {
    }

    public TaxOpcode(String opcode) {
        this.opcode = opcode;
    }

    public TaxOpcode(String opcode, String name, GroupType groupType, OpcodeType opcodeType, NetType netType, String incomeCatalogId, BigDecimal minRate, BigDecimal maxRate, BigDecimal minBaht, BigDecimal maxBaht, boolean calSoc, boolean calTax, Status status, Date effectiveDate, String createUser, Date createTime) {
        this.opcode = opcode;
        this.name = name;
        this.groupType = groupType;
        this.opcodeType = opcodeType;
        this.netType = netType;
        this.incomeCatalogId = incomeCatalogId;
        this.minRate = minRate;
        this.maxRate = maxRate;
        this.minBaht = minBaht;
        this.maxBaht = maxBaht;
        this.calSoc = calSoc;
        this.calTax = calTax;
        this.status = status;
        this.effectiveDate = effectiveDate;
        this.createUser = createUser;
        this.createTime = createTime;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
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

    public GroupType getGroupType() {
		return groupType;
	}

	public void setGroupType(GroupType groupType) {
		this.groupType = groupType;
	}

	public OpcodeType getOpcodeType() {
		return opcodeType;
	}

	public void setOpcodeType(OpcodeType opcodeType) {
		this.opcodeType = opcodeType;
	}

	public NetType getNetType() {
		return netType;
	}

	public void setNetType(NetType netType) {
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

    public boolean getCalSoc() {
        return calSoc;
    }

    public void setCalSoc(boolean calSoc) {
        this.calSoc = calSoc;
    }

    public boolean getCalTax() {
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
        hash += (opcode != null ? opcode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxOpcode)) {
            return false;
        }
        TaxOpcode other = (TaxOpcode) object;
        if ((this.opcode == null && other.opcode != null) || (this.opcode != null && !this.opcode.equals(other.opcode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.TaxOpcode[ opcode=" + opcode + " ]";
    }
    
}