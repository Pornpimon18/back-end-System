package com.thailife.tax.object;

import java.util.Date;
import java.util.List;

import com.thailife.tax.base.DataObjBase;

public class UserRoleObj extends DataObjBase{
	
	private String roleId;
	private String userId;
	private String groupId;
	private List<String> listUserId = null;
    private RoleObj roleObj;
    private UserObj userObj;
    
	public RoleObj getRoleObj() {
		return roleObj;
	}
	public void setRoleObj(RoleObj roleObj) {
		this.roleObj = roleObj;
	}
	public UserObj getUserObj() {
		return userObj;
	}
	public void setUserObj(UserObj userObj) {
		this.userObj = userObj;
	}
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
	public List<String> getListUserId() {
		return listUserId;
	}
	public void setListUserId(List<String> listUserId) {
		this.listUserId = listUserId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
    
    

}
