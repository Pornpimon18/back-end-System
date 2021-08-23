package com.thailife.tax.entity;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author parach
 */
@Entity
@Table(name = "role_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleMenu.findAll", query = "SELECT r FROM RoleMenu r")
    , @NamedQuery(name = "RoleMenu.findByRoleRight", query = "SELECT r FROM RoleMenu r WHERE r.roleRight = :roleRight")
    , @NamedQuery(name = "RoleMenu.findByRoleId", query = "SELECT r FROM RoleMenu r WHERE r.roleId = :roleId")
    , @NamedQuery(name = "RoleMenu.findByMenuId", query = "SELECT r FROM RoleMenu r WHERE r.menuId = :menuId")
    , @NamedQuery(name = "RoleMenu.findById", query = "SELECT r FROM RoleMenu r WHERE r.id = :id")})
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "role_right")
    private String roleRight;
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "menu_id")
    private String menuId;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;

    public RoleMenu() {
    }

    public RoleMenu(String id) {
        this.id = id;
    }

    public String getRoleRight() {
        return roleRight;
    }

    public void setRoleRight(String roleRight) {
        this.roleRight = roleRight;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (!(object instanceof RoleMenu)) {
            return false;
        }
        RoleMenu other = (RoleMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.RoleMenu[ id=" + id + " ]";
    }
    
}
