package com.thailife.tax.object;

import java.util.List;

import com.thailife.tax.base.DataObjBase;
import com.thailife.tax.object.MenuObj;

public class UserLoginObj extends DataObjBase{
	private List<UserRoleObj> listRole;
	
	    
	public List<UserRoleObj> getListRole() {
		return listRole;
	}
	public void setListRole(List<UserRoleObj> listRole) {
		this.listRole = listRole;
	}
    
	    
	    
}
