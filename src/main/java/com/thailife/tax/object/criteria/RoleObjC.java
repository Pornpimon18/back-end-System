package com.thailife.tax.object.criteria;

import java.util.List;

import com.thailife.tax.base.DataObjBase;
import com.thailife.tax.object.RoleObj;

public class RoleObjC extends DataObjBase{
	    private String roleName;
	    private String roleType;
	    private boolean checkDup;
	    private List<RoleObj> listRoleObj = null;
	    
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
		public List<RoleObj> getListRoleObj() {
			return listRoleObj;
		}
		public void setListRoleObj(List<RoleObj> listRoleObj) {
			this.listRoleObj = listRoleObj;
		}
		public boolean isCheckDup() {
			return checkDup;
		}
		public void setCheckDup(boolean checkDup) {
			this.checkDup = checkDup;
		}
	    
	    
	
	    
	    
	    
}
