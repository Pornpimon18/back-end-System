package com.thailife.tax.object;

import com.thailife.tax.base.DataObjBase;
import com.thailife.tax.entity.GroupMenu;

public class MenuObj extends DataObjBase{
	    private String menuName;
	    private String path;
	    private String icon;
	    private int seq;
	    private String action;
	    private String groupMenuId;
	    private String roleRight;
	    
		public String getMenuName() {
			return menuName;
		}
		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public Integer getSeq() {
			return seq;
		}
		public void setSeq(Integer seq) {
			this.seq = seq;
		}
		public String getGroupMenuId() {
			return groupMenuId;
		}
		public void setGroupMenuId(String groupMenuId) {
			this.groupMenuId = groupMenuId;
		}
		public void setSeq(int seq) {
			this.seq = seq;
		}
		public String getAction() {
			return action;
		}
		public void setAction(String action) {
			this.action = action;
		}
		public String getRoleRight() {
			return roleRight;
		}
		public void setRoleRight(String roleRight) {
			this.roleRight = roleRight;
		}
	    
	    
}
