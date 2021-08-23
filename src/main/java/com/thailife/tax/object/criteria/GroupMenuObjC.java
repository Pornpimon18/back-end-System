package com.thailife.tax.object.criteria;

import java.util.List;

import com.thailife.tax.base.DataObjBase;
import com.thailife.tax.object.GroupMenuObj;

public class GroupMenuObjC extends DataObjBase {
	private String groupMenuName;
	private String icon;
	private List<GroupMenuObj> listGroupMenu = null;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getGroupMenuName() {
		return groupMenuName;
	}

	public void setGroupMenuName(String groupMenuName) {
		this.groupMenuName = groupMenuName;
	}

	public List<GroupMenuObj> getListGroupMenu() {
		return listGroupMenu;
	}

	public void setListGroupMenu(List<GroupMenuObj> listGroupMenu) {
		this.listGroupMenu = listGroupMenu;
	}

}
