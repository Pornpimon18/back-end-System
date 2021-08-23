package com.thailife.tax.object;

import com.thailife.tax.base.DataObjBase;

public class RoleMenuObj extends DataObjBase{
	
	    private String menuId;
	    private String roleId;
	    private String roleRight;
	    private MenuObj menuObj;
	    
		public String getRoleRight() {
			return roleRight;
		}
		public void setRoleRight(String roleRight) {
			this.roleRight = roleRight;
		}
		public String getMenuId() {
			return menuId;
		}
		public void setMenuId(String menuId) {
			this.menuId = menuId;
		}
		public String getRoleId() {
			return roleId;
		}
		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}
		public MenuObj getMenuObj() {
			return menuObj;
		}
		public void setMenuObj(MenuObj menuObj) {
			this.menuObj = menuObj;
		}
	    

}
