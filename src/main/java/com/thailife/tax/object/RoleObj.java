package com.thailife.tax.object;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thailife.tax.base.DataObjBase;

public class RoleObj extends DataObjBase{
	
	    private String roleName;
	    private String roleType;
	    private List<RoleMenuObj> listRoleMenuObj = null;
	    
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		public String getRoleType() {
			return roleType;
		}
		public void setRoleType(String roleType) {
			this.roleType = roleType;
		}
		public List<RoleMenuObj> getListRoleMenuObj() {
			return listRoleMenuObj;
		}
		public void setListRoleMenuObj(List<RoleMenuObj> listRoleMenuObj) {
			this.listRoleMenuObj = listRoleMenuObj;
		}
	    
	    

}
