package com.thailife.tax.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thailife.tax.App;
import com.thailife.tax.base.ServiceBase;
import com.thailife.tax.constant.ApplicationConstant;
import com.thailife.tax.entity.Menu;
import com.thailife.tax.entity.Role;
import com.thailife.tax.entity.RoleMenu;
import com.thailife.tax.object.MenuObj;
import com.thailife.tax.object.RoleMenuObj;
import com.thailife.tax.object.RoleObj;
import com.thailife.tax.object.criteria.RoleObjC;
import com.thailife.tax.repository.RoleRepository;
import com.thailife.tax.repository.custom.RoleCustomRepository;
import com.thailife.tax.repository.custom.RoleMenuCustomRepository;
import com.thailife.tax.utils.IdGenerator;
import com.thailife.tax.utils.SecurityUtils;

@Service
public class RoleService extends ServiceBase {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleCustomRepository roleRepositoryCustom;

	@Autowired
	private RoleMenuCustomRepository roleMenuRepositoryCustom;

	@Autowired
	private RoleMenuService roleMenuService;

	@Autowired
	private MenuService menuService;

	private EntityManager entityManager;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public RoleObjC searchDataAll() {
		ModelMapper modelMapper = new ModelMapper();
		RoleObjC roleObjC = new RoleObjC();
		List<RoleObj> listRoleObj = new ArrayList<>();
		List<Role> listRoleEntity = roleRepository.searchDataAll();
		for (int i = 0; i < listRoleEntity.size(); i++) {
			RoleObj roleObj = modelMapper.map(listRoleEntity.get(i), RoleObj.class);
			listRoleObj.add(roleObj);
		}
		roleObjC.setListRoleObj(listRoleObj);
		return roleObjC;
	}

	public String addRole(RoleObj roleObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.error("Start addRole .........");
			ModelMapper modelMapper = new ModelMapper();
			List<RoleMenu> listRoleMenuEntity = new ArrayList<RoleMenu>();

			result = checkDup(roleObj,ApplicationConstant.ADD);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				roleObj.setId(IdGenerator.getId());
				Role roleEntity = modelMapper.map(roleObj, Role.class);
				roleEntity.setCreateBy(SecurityUtils.getUserName());
				roleEntity.setCreateDate(new Date());
				roleEntity.setStatus(ApplicationConstant.STATUS_ACTIVE);
				roleEntity = roleRepositoryCustom.saveEntity(roleEntity);
				for (int i = 0; i < roleObj.getListRoleMenuObj().size(); i++) {
					// roleObj.getListRoleMenuObj().get(i).getRoleObj().setId(roleObj.getId());
					// roleObj.getListRoleMenuObj().get(i).setRoleObj(roleObj.getId());
					roleObj.getListRoleMenuObj().get(i).setId(IdGenerator.getId());
					RoleMenu roleMenuEntity = modelMapper.map(roleObj.getListRoleMenuObj().get(i), RoleMenu.class);
					roleMenuEntity.setRoleId(roleEntity.getId());
					listRoleMenuEntity.add(roleMenuEntity);
				}

				roleMenuRepositoryCustom.saveEntityList(listRoleMenuEntity);
			}
			logger.error("End addRole .........");
		} catch (Exception e) {
			logger.error("addRole Error", e);
		}
		return result;
	}

	public String updateRole(RoleObj roleObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.error("Start updateRole .........");

			result = checkDup(roleObj,ApplicationConstant.EDIT);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				ModelMapper modelMapper = new ModelMapper();
				List<RoleMenu> listRoleMenuEntity = new ArrayList<RoleMenu>();
				List<RoleMenu> listRoleMenuDeleteEntity = new ArrayList<RoleMenu>();
				Role roleEntity = modelMapper.map(roleObj, Role.class);
				roleEntity.setUpdateBy(SecurityUtils.getUserName());
				roleEntity.setUpdateDate(new Date());
				roleEntity = roleRepositoryCustom.updateEntity(roleEntity);
				listRoleMenuDeleteEntity = roleMenuService.searchDataByRoleId(roleObj.getId());
				roleMenuRepositoryCustom.deleteEntityList(listRoleMenuDeleteEntity);
				for (int i = 0; i < roleObj.getListRoleMenuObj().size(); i++) {
					RoleMenu roleMenuEntity = modelMapper.map(roleObj.getListRoleMenuObj().get(i), RoleMenu.class);
					roleMenuEntity.setRoleId(roleEntity.getId());
					roleMenuEntity.setId(IdGenerator.getId());
					listRoleMenuEntity.add(roleMenuEntity);
				}

				roleMenuRepositoryCustom.saveEntityList(listRoleMenuEntity);
			}
			logger.error("End updateRole .........");
		} catch (Exception e) {
			logger.error("updateRole Error", e);
		}
		return result;
	}

	public RoleObj findById(RoleObj roleObj) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			List<RoleMenu> listRoleMenuEntity = new ArrayList<RoleMenu>();
			Role role = new Role();
			Menu menu = null;
			role = roleRepository.searchDataById(roleObj.getId());
			roleObj = modelMapper.map(role, RoleObj.class);
			listRoleMenuEntity = roleMenuService.searchDataByRoleId(roleObj.getId());
			roleObj.setListRoleMenuObj(new ArrayList<>());
			for (int i = 0; i < listRoleMenuEntity.size(); i++) {
				menu = new Menu();
				menu = menuService.menuFindById(listRoleMenuEntity.get(i).getMenuId());
				MenuObj menuObj = modelMapper.map(menu, MenuObj.class);
				RoleMenuObj roleMenuObj = modelMapper.map(listRoleMenuEntity.get(i), RoleMenuObj.class);
				roleMenuObj.setMenuObj(menuObj);
				roleObj.getListRoleMenuObj().add(roleMenuObj);
			}
			;
		} catch (Exception e) {
			logger.error("findById Error", e);
		}
		return roleObj;

	}

	public RoleObjC searchRole(RoleObjC roleObjC) {
		ModelMapper modelMapper = new ModelMapper();
		List<Role> listRoleEntity = new ArrayList<Role>();
		List<RoleObj> listRoleObj = new ArrayList<>();
		String status = "";
		try {
			if (roleObjC.getStatus() != null) {
				status = roleObjC.getStatus();
				if (("").equals(status)) {
					status = "ST";
				}
			}
			listRoleEntity = roleRepository.searchDataByRoleNameAndStatus(roleObjC.getRoleName(), status);
			for (int i = 0; i < listRoleEntity.size(); i++) {
				RoleObj role = modelMapper.map(listRoleEntity.get(i), RoleObj.class);
				listRoleObj.add(role);
			}
			roleObjC.setListRoleObj(listRoleObj);
		} catch (Exception e) {
			logger.error("searchRole Error", e);
		}
		return roleObjC;

	}

	public String deleteRole(RoleObjC roleObjC) {
		Role role = null;
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.error("Start deleteRole .........");
			ModelMapper modelMapper = new ModelMapper();
			List<RoleMenu> listRoleMenuEntity = new ArrayList<RoleMenu>();
			List<Role> listRoleEntity = new ArrayList<Role>();

			for (int d = 0; d < roleObjC.getListRoleObj().size(); d++) {
				role = new Role();
				listRoleEntity = roleRepository.checkUserUseRole(roleObjC.getListRoleObj().get(d).getId(),
						ApplicationConstant.STATUS_ACTIVE);
				if (listRoleEntity.size() > 0) {
					result = ApplicationConstant.FAIL;
				}
			}

			if (!result.equals(ApplicationConstant.FAIL)) {
				for (int h = 0; h < roleObjC.getListRoleObj().size(); h++) {
					role = new Role();
					listRoleEntity = roleRepository.checkUserUseRole(roleObjC.getListRoleObj().get(h).getId(),
							ApplicationConstant.STATUS_ACTIVE);
					if (listRoleEntity.size() == 0) {
						role = roleRepository.searchDataById(roleObjC.getListRoleObj().get(h).getId());
						listRoleMenuEntity = roleMenuService.searchDataByRoleId(role.getId());
						roleMenuRepositoryCustom.deleteEntityList(listRoleMenuEntity);
						role.setUpdateBy(SecurityUtils.getUserName());
						role.setUpdateDate(new Date());
						role.setStatus(ApplicationConstant.STATUS_INACTIVE);
						roleRepositoryCustom.updateEntity(role);
						result = ApplicationConstant.SUCCESS;
					}

				}
			}

			logger.error("End deleteRole .........");
		} catch (Exception e) {
			logger.error("deleteRole Error", e);
		}
		return result;
	}

	public String checkDup(RoleObj roleObj,String action) {
		List<Role> listRoleEntity = new ArrayList<Role>();
		String result = ApplicationConstant.DUPLICATE;

		try {
			if(ApplicationConstant.ADD.equals(action)){
				listRoleEntity = roleRepository.checkDupDataByRoleNameAndStatus(roleObj.getRoleName(),
						ApplicationConstant.STATUS_ACTIVE);
				if (listRoleEntity.size() == 0) {
					result = ApplicationConstant.SUCCESS;
				}
			}else if(ApplicationConstant.EDIT.equals(action)){
				listRoleEntity = roleRepository.checkDupDataByRoleNameAndStatus(roleObj.getRoleName(),
						ApplicationConstant.STATUS_ACTIVE);
				if (listRoleEntity.size() > 0) {
					if(roleObj.getId().equals(listRoleEntity.get(0).getId())){
						result = ApplicationConstant.SUCCESS;
					}
				}else{
						result = ApplicationConstant.SUCCESS;
				}
			}
			
		} catch (Exception e) {
			logger.error("Role checkDup error", e);
		}
		return result;
	}

}
