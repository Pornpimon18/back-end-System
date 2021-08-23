package com.thailife.tax.object.criteria;

import java.util.List;

import com.thailife.tax.base.DataObjBase;
import com.thailife.tax.object.UserRoleObj;

public class UserRoleObjC extends DataObjBase{
	
	private String roleId;
	private String userId;
	private String roleName;
	private String userName;
	private boolean checkDup;
	private List<UserRoleObj> listUserRoleObj = null;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<UserRoleObj> getListUserRoleObj() {
		return listUserRoleObj;
	}
	public void setListUserRoleObj(List<UserRoleObj> listUserRoleObj) {
		this.listUserRoleObj = listUserRoleObj;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isCheckDup() {
		return checkDup;
	}
	public void setCheckDup(boolean checkDup) {
		this.checkDup = checkDup;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    

}
