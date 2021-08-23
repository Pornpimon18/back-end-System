package com.thailife.tax.service;

import com.thailife.tax.base.ServiceBase;
import com.thailife.tax.config.ApiConfigProperties;
import com.thailife.tax.entity.GroupMenu;
import com.thailife.tax.entity.Menu;
import com.thailife.tax.entity.RoleMenu;
import com.thailife.tax.entity.User;
import com.thailife.tax.entity.UserRole;
import com.thailife.tax.object.GroupMenuObj;
import com.thailife.tax.object.MenuObj;
import com.thailife.tax.object.UserObj;
import com.thailife.tax.object.criteria.GroupMenuObjC;
import com.thailife.tax.object.criteria.MenuObjC;
import com.thailife.tax.repository.MenuRepository;
import com.thailife.tax.repository.RoleMenuRepository;
import com.thailife.tax.repository.RoleRepository;
import com.thailife.tax.repository.UserRepository;
import com.thailife.tax.repository.UserRoleRepository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuManageService extends ServiceBase {

	@Autowired
	private MenuService menuService;

	@Autowired
	private GroupMenuService groupMenuService;

	@Autowired
	private ApiConfigProperties configProperties;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private RoleMenuRepository roleMenuRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserRepository userRepository;



	public GroupMenuObjC userRoleLogin(UserObj userObj) throws Exception {
		MenuObj menuObj = new MenuObj();
		List<MenuObj> listMenuObj = new ArrayList<MenuObj>();
		List<RoleMenu> listRoleMenu = null;
		List<RoleMenu> listRoleMenuAll = new ArrayList<RoleMenu>();
		List<UserRole> listUserRole = new ArrayList<UserRole>();
		List<GroupMenu> listGroupMenu = new ArrayList<GroupMenu>();
		User user = new User();
		ModelMapper modelMapper = new ModelMapper();
		GroupMenuObjC groupMenuObjC = new GroupMenuObjC();

		try {
			user = userRepository.findUserName(userObj.getUserName());
			if (user.getId() != null) {
				listUserRole = userRoleRepository.findByUserName(userObj.getUserName());
				listUserRole = listUserRole.stream().filter(distinctByKeys(UserRole::getRoleId))
						.collect(Collectors.toList());

				for (int a = 0; a < listUserRole.size(); a++) {
					listRoleMenu = new ArrayList<RoleMenu>();
					listRoleMenu = roleMenuRepository.searchDataByRoleId(listUserRole.get(a).getRoleId());
					for (int f = 0; f < listRoleMenu.size(); f++) {
						listRoleMenuAll.add(listRoleMenu.get(f));
					}
				}

				for (int d = 0; d < listRoleMenuAll.size(); d++) {
					if (listMenuObj.size() > 0) {
						boolean checkDup = false;
						for (int n = 0; n < listMenuObj.size(); n++) {
							if (listRoleMenuAll.get(d).getMenuId().equals(listMenuObj.get(n).getId())
									&& !listRoleMenuAll.get(d).getRoleRight()
											.equals(listMenuObj.get(n).getRoleRight())) {
								String roleRight = listMenuObj.get(n).getRoleRight();
								if (listRoleMenuAll.get(d).getRoleRight().indexOf("A") != -1
										&& roleRight.indexOf("A") == -1) {
									roleRight = roleRight + "A";
								}
								if (listRoleMenuAll.get(d).getRoleRight().indexOf("E") != -1
										&& roleRight.indexOf("E") == -1) {
									roleRight = roleRight + "E";
								}
								if (listRoleMenuAll.get(d).getRoleRight().indexOf("D") != -1
										&& roleRight.indexOf("D") == -1) {
									roleRight = roleRight + "D";
								}
								if (listRoleMenuAll.get(d).getRoleRight().indexOf("V") != -1
										&& roleRight.indexOf("V") == -1) {
									roleRight = roleRight + "V";
								}
								listMenuObj.get(n).setRoleRight(roleRight);
							}
							if (listRoleMenuAll.get(d).getMenuId().equals(listMenuObj.get(n).getId())) {
								checkDup = true;
							}
						}
						if (!checkDup) {
							menuObj = new MenuObj();
							menuObj.setId(listRoleMenuAll.get(d).getMenuId());
							menuObj.setRoleRight(listRoleMenuAll.get(d).getRoleRight());
							listMenuObj.add(menuObj);
						}

					} else {
						menuObj = new MenuObj();
						menuObj.setId(listRoleMenuAll.get(d).getMenuId());
						menuObj.setRoleRight(listRoleMenuAll.get(d).getRoleRight());
						listMenuObj.add(menuObj);
					}
				}

				listGroupMenu = groupMenuService.searchGroupMenuAll();
				groupMenuObjC.setListGroupMenu(new ArrayList<GroupMenuObj>());
				//
				for (int n = 0; n < listGroupMenu.size(); n++) {
					GroupMenuObj menuGroupObj = modelMapper.map(listGroupMenu.get(n), GroupMenuObj.class);
					if (listMenuObj.size() > 0) {
						for (int i = 0; i < listMenuObj.size(); i++) {
							Menu menu = menuRepository.menuFindById(listMenuObj.get(i).getId());
							listMenuObj.get(i).setGroupMenuId(menu.getGroupMenuId());
							listMenuObj.get(i).setMenuName(menu.getMenuName());
							listMenuObj.get(i).setPath(menu.getPath());
							listMenuObj.get(i).setSeq(menu.getSeq());
							listMenuObj.get(i).setIcon(menu.getIcon());
							menuObj = modelMapper.map(listMenuObj.get(i), MenuObj.class);
							if (null != menu.getGroupMenuId()) {
								if (menuGroupObj.getId().equals(menu.getGroupMenuId())) {
									if (null == menuGroupObj.getListMenu()) {
										menuGroupObj.setListMenu(new ArrayList<MenuObj>());
										menuGroupObj.getListMenu().add(menuObj);
									} else {
										menuGroupObj.getListMenu().add(menuObj);
									}
								}
							} else {
								if (menu.getMenuName().equals(menuGroupObj.getGroupMenuName())) {
									menuGroupObj.setPath(menu.getPath());
								}
							}
						}
						if(null != menuGroupObj.getListMenu() && menuGroupObj.getListMenu().size() > 0){
							groupMenuObjC.getListGroupMenu().add(menuGroupObj);
						}
						
					}

				}
			}
		} catch (Exception e) {
			logger.error("Error ", e);
			throw e;
		}
		return groupMenuObjC;
	}

	public GroupMenuObjC listMenu() throws Exception {
		String result = "Success";
		List<Menu> list = new ArrayList<Menu>();
		List<MenuObj> listMenuObj = new ArrayList<MenuObj>();
		List<GroupMenu> listGroupMenu = new ArrayList<GroupMenu>();
		List<GroupMenuObj> listGroupMenuObj = new ArrayList<GroupMenuObj>();
		ModelMapper modelMapper = new ModelMapper();
		MenuObjC menuObjC = new MenuObjC();
		GroupMenuObjC groupMenuObjC = new GroupMenuObjC();

		try {
			listGroupMenu = groupMenuService.searchGroupMenuAll();
			list = menuService.searchDataAllMenu();
			groupMenuObjC.setListGroupMenu(new ArrayList<GroupMenuObj>());
			for (int n = 0; n < listGroupMenu.size(); n++) {
				GroupMenuObj menuGroupObj = modelMapper.map(listGroupMenu.get(n), GroupMenuObj.class);
				for (int i = 0; i < list.size(); i++) {
					MenuObj menu = modelMapper.map(list.get(i), MenuObj.class);
					if (null != menu.getGroupMenuId()) {
						if (menuGroupObj.getId().equals(menu.getGroupMenuId())) {
							if (null == menuGroupObj.getListMenu()) {
								menuGroupObj.setListMenu(new ArrayList<MenuObj>());
								menuGroupObj.getListMenu().add(menu);
							} else {
								menuGroupObj.getListMenu().add(menu);
							}
						}
					} else {
						if (menu.getMenuName().equals(menuGroupObj.getGroupMenuName())) {
							menuGroupObj.setPath(menu.getPath());
						}
					}

				}
				if(null != menuGroupObj.getListMenu() && menuGroupObj.getListMenu().size() > 0){
					groupMenuObjC.getListGroupMenu().add(menuGroupObj);
				}
			}
		} catch (Exception e) {
			logger.error("Error ", e);
			throw e;
		}
		return groupMenuObjC;
	}

	public GroupMenuObjC permissionMenu(List<Menu> list) throws Exception {
		String result = "Success";
		// List<Menu> list = new ArrayList<Menu>();
		List<MenuObj> listMenuObj = new ArrayList<MenuObj>();
		List<GroupMenu> listGroupMenu = new ArrayList<GroupMenu>();
		List<GroupMenuObj> listGroupMenuObj = new ArrayList<GroupMenuObj>();
		ModelMapper modelMapper = new ModelMapper();
		MenuObjC menuObjC = new MenuObjC();
		GroupMenuObjC groupMenuObjC = new GroupMenuObjC();

		try {
			listGroupMenu = groupMenuService.searchGroupMenuAll();
			// list = menuService.searchDataAllMenu();
			groupMenuObjC.setListGroupMenu(new ArrayList<GroupMenuObj>());
			for (int n = 0; n < listGroupMenu.size(); n++) {
				GroupMenuObj menuGroupObj = modelMapper.map(listGroupMenu.get(n), GroupMenuObj.class);
				for (int i = 0; i < list.size(); i++) {
					MenuObj menu = modelMapper.map(list.get(i), MenuObj.class);
					if (null != menu.getGroupMenuId()) {
						if (menuGroupObj.getId().equals(menu.getGroupMenuId())) {
							if (null == menuGroupObj.getListMenu()) {
								menuGroupObj.setListMenu(new ArrayList<MenuObj>());
								menuGroupObj.getListMenu().add(menu);
							} else {
								menuGroupObj.getListMenu().add(menu);
							}
							groupMenuObjC.getListGroupMenu().add(menuGroupObj);
						}
					} else {
						if (menu.getMenuName().equals(menuGroupObj.getGroupMenuName())) {
							menuGroupObj.setPath(menu.getPath());
						}
					}

				}

			}
		} catch (Exception e) {
			logger.error("Error ", e);
			throw e;
		}
		return groupMenuObjC;
	}

	private static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) {
		final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

		return t -> {
			final List<?> keys = Arrays.stream(keyExtractors).map(ke -> ke.apply(t)).collect(Collectors.toList());

			return seen.putIfAbsent(keys, Boolean.TRUE) == null;
		};
	}

	public long startTime() {
		long start = System.currentTimeMillis();
		start = System.currentTimeMillis();
		return start;
	}

	public long endTime() {
		long end = System.currentTimeMillis();
		end = System.currentTimeMillis();
		return end;
	}

}
