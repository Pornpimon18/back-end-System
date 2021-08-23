/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thailife.tax.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author parach
 */
@Entity
@Table(name = "group_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupMenu.findAll", query = "SELECT g FROM GroupMenu g")
    , @NamedQuery(name = "GroupMenu.findById", query = "SELECT g FROM GroupMenu g WHERE g.id = :id")
    , @NamedQuery(name = "GroupMenu.findByGroupMenuName", query = "SELECT g FROM GroupMenu g WHERE g.groupMenuName = :groupMenuName")
    , @NamedQuery(name = "GroupMenu.findByIcon", query = "SELECT g FROM GroupMenu g WHERE g.icon = :icon")})
public class GroupMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "group_menu_name")
    private String groupMenuName;
    @Column(name = "icon")
    private String icon;
    @Column(name = "seq")
    private int seq;

    public GroupMenu() {
    }

    public GroupMenu(String id) {
        this.id = id;
    }

    public GroupMenu(String id, String groupMenuName) {
        this.id = id;
        this.groupMenuName = groupMenuName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupMenuName() {
        return groupMenuName;
    }

    public void setGroupMenuName(String groupMenuName) {
        this.groupMenuName = groupMenuName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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
        if (!(object instanceof GroupMenu)) {
            return false;
        }
        GroupMenu other = (GroupMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.GroupMenu[ id=" + id + " ]";
    }
    
}
