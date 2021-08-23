package com.thailife.tax.object;

import java.util.ArrayList;
import java.util.List;

import com.thailife.tax.base.DataObjBase;

public class GroupMenuObj  extends DataObjBase{
	
    private String groupMenuName;
    private String icon;
    private String path;
    private List<MenuObj> listMenu = null;
    
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
	public List<MenuObj> getListMenu() {
		return listMenu;
	}
	public void setListMenu(List<MenuObj> listMenu) {
		this.listMenu = listMenu;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
    
    
}
