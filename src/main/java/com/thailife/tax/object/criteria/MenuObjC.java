package com.thailife.tax.object.criteria;

import java.util.List;

import com.thailife.tax.base.DataObjBase;
import com.thailife.tax.object.MenuObj;

public class MenuObjC extends DataObjBase{
	    private String menuName;
	    private String path;
	    private String icon;
	    private int seq;
	    private List<MenuObj> listMenu = null;
	    
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
		public List<MenuObj> getListMenu() {
			return listMenu;
		}
		public void setListMenu(List<MenuObj> listMenu) {
			this.listMenu = listMenu;
		}
		public void setSeq(int seq) {
			this.seq = seq;
		}
	    
	    
}
