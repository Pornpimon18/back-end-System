package com.thailife.tax.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thailife.tax.base.ServiceBase;
import com.thailife.tax.constant.ApplicationConstant;
import com.thailife.tax.entity.Role;
import com.thailife.tax.entity.RoleMenu;
import com.thailife.tax.entity.User;
import com.thailife.tax.entity.UserRole;
import com.thailife.tax.object.RoleObj;
import com.thailife.tax.object.UserObj;
import com.thailife.tax.object.UserRoleObj;
import com.thailife.tax.object.criteria.RoleObjC;
import com.thailife.tax.object.criteria.UserRoleObjC;
import com.thailife.tax.repository.RoleRepository;
import com.thailife.tax.repository.UserRepository;
import com.thailife.tax.repository.UserRoleRepository;
import com.thailife.tax.repository.custom.UserRoleCustomRepository;
import com.thailife.tax.utils.IdGenerator;
import com.thailife.tax.utils.SecurityUtils;

@Service
public class UserRoleService extends ServiceBase {

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserRoleCustomRepository userRoleCustomRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public UserRoleService(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}

	public void addUserRole(UserRoleObj userRoleObj) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			List<UserRole> listUserRoleEntity = new ArrayList<UserRole>();
			String groupId = IdGenerator.getId();
			for (int i = 0; i < userRoleObj.getListUserId().size(); i++) {
				UserRole userRole = new UserRole();
				userRole.setId(IdGenerator.getId());
				userRole.setGroupId(groupId);
				userRole.setRoleId(userRoleObj.getRoleId());
				userRole.setUserId(userRoleObj.getListUserId().get(i));
				userRole.setCreateBy(SecurityUtils.getUserName());
				userRole.setCreateDate(new Date());
				userRole.setStatus(ApplicationConstant.STATUS_ACTIVE);
				listUserRoleEntity.add(userRole);
			}
			userRoleCustomRepository.saveEntityList(listUserRoleEntity);

		} catch (Exception e) {
			logger.error("addUserRole Error", e);
		}

	}
	
	public void updateUserRole(UserRoleObj userRoleObj) {
		try {
			List<UserRole> listUserRoleEntity = new ArrayList<UserRole>();
			List<UserRole> listDeleteUserRoleEntity = userRoleRepository.findByGroupIdUserRole(userRoleObj.getGroupId());
			
			if(null != listDeleteUserRoleEntity && listDeleteUserRoleEntity.size() > 0){
				String groupId = IdGenerator.getId();
				for (int i = 0; i < userRoleObj.getListUserId().size(); i++) {
					UserRole userRole = new UserRole();
					userRole.setId(IdGenerator.getId());
					userRole.setGroupId(groupId);
					userRole.setRoleId(userRoleObj.getRoleId());
					userRole.setUserId(userRoleObj.getListUserId().get(i));
					userRole.setCreateBy(listDeleteUserRoleEntity.get(0).getCreateBy());;
					userRole.setCreateDate(listDeleteUserRoleEntity.get(0).getCreateDate());;
					userRole.setUpdateBy(SecurityUtils.getUserName());
					userRole.setUpdateDate(new Date());
					userRole.setStatus(ApplicationConstant.STATUS_ACTIVE);
					listUserRoleEntity.add(userRole);
				}
				userRoleCustomRepository.deleteEntityList(listDeleteUserRoleEntity);
				userRoleCustomRepository.saveEntityList(listUserRoleEntity);
			}
			

		} catch (Exception e) {
			logger.error("addUserRole Error", e);
		}

	}

	public UserRoleObjC findByGroupIdUserRole(UserRoleObj userRoleObj) {
		ModelMapper modelMapper = new ModelMapper();
		UserRoleObjC userRoleObjC = new UserRoleObjC();
		List<UserRoleObj> listUserRoleObj = new ArrayList<>();
		List<UserRole> listUserRoleEntity = userRoleRepository.findByGroupIdUserRole(userRoleObj.getGroupId());
		try {

			for (int i = 0; i < listUserRoleEntity.size(); i++) {
				Role role = new Role();
				User user = new User();
				role = roleRepository.searchDataById(listUserRoleEntity.get(i).getRoleId());
				user = userRepository.findByUserId(listUserRoleEntity.get(i).getUserId());
				RoleObj roleObj = modelMapper.map(role, RoleObj.class);
				UserObj userObj = modelMapper.map(user, UserObj.class);
				UserRoleObj userRole = new UserRoleObj();
				userRole.setRoleId(roleObj.getId());
				userRole.setRoleObj(roleObj);
				userRole.setUserId(userObj.getId());
				userRole.setUserObj(userObj);
				userRole.setCreateBy(listUserRoleEntity.get(i).getCreateBy());
				userRole.setCreateDate(listUserRoleEntity.get(i).getCreateDate());
				userRole.setUpdateBy(listUserRoleEntity.get(i).getUpdateBy());
				userRole.setUpdateDate(listUserRoleEntity.get(i).getUpdateDate());
				userRole.setStatus(listUserRoleEntity.get(i).getStatus());
				listUserRoleObj.add(userRole);
			}
			userRoleObjC.setListUserRoleObj(listUserRoleObj);
		} catch (Exception e) {
			logger.error("addRole Error", e);
		}
		return userRoleObjC;

	}

	public UserRoleObjC searchDataAll() {
		ModelMapper modelMapper = new ModelMapper();
		UserRoleObjC userRoleObjC = new UserRoleObjC();
		List<UserRoleObj> listUserRoleObj = new ArrayList<>();
		List<UserRole> listUserRoleEntity = userRoleRepository.searchDataAll();
		try {
			for (int i = 0; i < listUserRoleEntity.size(); i++) {
				Role role = new Role();
				User user = new User();
				UserRoleObj userRoleObj = new UserRoleObj();
				role = roleRepository.searchDataById(listUserRoleEntity.get(i).getRoleId());
				user = userRepository.findByUserId(listUserRoleEntity.get(i).getUserId());
				RoleObj roleObj = modelMapper.map(role, RoleObj.class);
				UserObj userObj = modelMapper.map(user, UserObj.class);
				userRoleObj.setId(listUserRoleEntity.get(i).getId());
				userRoleObj.setRoleId(roleObj.getId());
				userRoleObj.setRoleObj(roleObj);
				userRoleObj.setUserId(userObj.getId());
				userRoleObj.setUserObj(userObj);
				userRoleObj.setGroupId(listUserRoleEntity.get(i).getGroupId());
				userRoleObj.setCreateBy(listUserRoleEntity.get(i).getCreateBy());
				userRoleObj.setCreateDate(listUserRoleEntity.get(i).getCreateDate());
				userRoleObj.setUpdateBy(listUserRoleEntity.get(i).getUpdateBy());
				userRoleObj.setUpdateDate(listUserRoleEntity.get(i).getUpdateDate());
				userRoleObj.setStatus(listUserRoleEntity.get(i).getStatus());
				listUserRoleObj.add(userRoleObj);
			}
			userRoleObjC.setListUserRoleObj(listUserRoleObj);
		} catch (Exception e) {
			logger.error("addRole Error", e);
		}
		return userRoleObjC;
	}
	
	
	public UserRoleObjC searchByUserAndRole(UserRoleObjC userRoleObjC) {
		ModelMapper modelMapper = new ModelMapper();
		List<UserRoleObj> listUserRoleObj = new ArrayList<>();
		List<UserRole> listUserRoleEntity = userRoleRepository.searchDataAll();
		try {
			for (int i = 0; i < listUserRoleEntity.size(); i++) {
				Role role = new Role();
				User user = new User();
				UserRoleObj userRoleObj = new UserRoleObj();
				role = roleRepository.searchDataByRoleName(userRoleObjC.getRoleName());
				user = userRepository.findByUserName(userRoleObjC.getUserName());
				RoleObj roleObj = modelMapper.map(role, RoleObj.class);
				UserObj userObj = modelMapper.map(user, UserObj.class);
				userRoleObj.setId(listUserRoleEntity.get(i).getId());
				userRoleObj.setRoleId(roleObj.getId());
				userRoleObj.setRoleObj(roleObj);
				userRoleObj.setUserId(userObj.getId());
				userRoleObj.setUserObj(userObj);
				userRoleObj.setGroupId(listUserRoleEntity.get(i).getGroupId());
				userRoleObj.setCreateBy(listUserRoleEntity.get(i).getCreateBy());
				userRoleObj.setCreateDate(listUserRoleEntity.get(i).getCreateDate());
				userRoleObj.setUpdateBy(listUserRoleEntity.get(i).getUpdateBy());
				userRoleObj.setUpdateDate(listUserRoleEntity.get(i).getUpdateDate());
				userRoleObj.setStatus(listUserRoleEntity.get(i).getStatus());
				listUserRoleObj.add(userRoleObj);
			}
			userRoleObjC.setListUserRoleObj(listUserRoleObj);
		} catch (Exception e) {
			logger.error("addRole Error", e);
		}
		return userRoleObjC;
	}
	
	public UserRoleObjC searchUserRole(UserRoleObjC userRoleObjC){
		ModelMapper modelMapper = new ModelMapper();
		List<UserRole> listUserRoleEntity = new ArrayList<UserRole>();
		List<UserRoleObj> listUserRoleObj = new ArrayList<>();
		String status = "";
		try {
			if(userRoleObjC.getStatus() != null){
				status = userRoleObjC.getStatus();
				if(("").equals(status)){
					status = "ST";
				}
			}
			if(!("").equals(userRoleObjC.getUserName()) && ("").equals(userRoleObjC.getRoleName())){
				listUserRoleEntity = userRoleRepository.searchUserName(userRoleObjC.getUserName(),status);
			}else if(("").equals(userRoleObjC.getUserName()) && !("").equals(userRoleObjC.getRoleName())){
				listUserRoleEntity = userRoleRepository.searchRoleName(userRoleObjC.getRoleName(),status);
			}else{
				listUserRoleEntity = userRoleRepository.searchUserNameAndRoleName(userRoleObjC.getUserName(),userRoleObjC.getRoleName(),status);
			}
			for(int i=0 ; i < listUserRoleEntity.size();i++){
				Role role = new Role();
				User user = new User();
				UserRoleObj userRole = modelMapper.map(listUserRoleEntity.get(i),UserRoleObj.class);
				role = roleRepository.searchDataById(listUserRoleEntity.get(i).getRoleId());
				user = userRepository.findByUserId(listUserRoleEntity.get(i).getUserId());
				RoleObj roleObj = modelMapper.map(role, RoleObj.class);
				UserObj userObj = modelMapper.map(user, UserObj.class);
				userRole.setId(listUserRoleEntity.get(i).getId());
				userRole.setRoleId(roleObj.getId());
				userRole.setRoleObj(roleObj);
				userRole.setUserId(userObj.getId());
				userRole.setUserObj(userObj);
				userRole.setGroupId(listUserRoleEntity.get(i).getGroupId());
				userRole.setCreateBy(listUserRoleEntity.get(i).getCreateBy());
				userRole.setCreateDate(listUserRoleEntity.get(i).getCreateDate());
				userRole.setUpdateBy(listUserRoleEntity.get(i).getUpdateBy());
				userRole.setUpdateDate(listUserRoleEntity.get(i).getUpdateDate());
				userRole.setStatus(listUserRoleEntity.get(i).getStatus());
				listUserRoleObj.add(userRole);
			}
			userRoleObjC.setListUserRoleObj(listUserRoleObj); 
		}catch(Exception e){
			logger.error("addRole Error",e);
		}
		return userRoleObjC;
		
	}
	
	public String deleteUserRole(UserRoleObjC userRoleObjC){
		UserRole userRole = null;
		String result = "Success";
		try {
			logger.error("Start service deleteUserRole .........");
			List<UserRole> listUserRoleEntity = new ArrayList<UserRole>();
			
				for(int h= 0 ; h <userRoleObjC.getListUserRoleObj().size();h++){
					userRole = new UserRole();
					userRole = userRoleRepository.findByUserRoleId(userRoleObjC.getListUserRoleObj().get(h).getId());
					if(null != userRole.getGroupId()){
						listUserRoleEntity = userRoleRepository.findByGroupIdUserRole(userRole.getGroupId());
						for(int f =0 ; f < listUserRoleEntity.size();f++ ){
							
							listUserRoleEntity.get(f).setUpdateBy(SecurityUtils.getUserName());
							listUserRoleEntity.get(f).setUpdateDate(new Date());
							listUserRoleEntity.get(f).setStatus(ApplicationConstant.STATUS_INACTIVE);
						}
						userRoleCustomRepository.updateEntityList(listUserRoleEntity);
						
					}else{
						result = "fail";
					}
				
				}
			
			logger.error("End service deleteUserRole .........");
		}catch(Exception e){
			logger.error("deleteUserRole service Error",e);
		}
		return result;
	}
	
	public String deleteUserAllRole(UserRoleObjC userRoleObjC){
		String result = "Success";
		ModelMapper modelMapper = new ModelMapper();
		List<UserRole> listUserRoleEntity = new ArrayList<>();
		try {
			logger.error("Start service deleteUserAllRole .........");
				for(int h= 0 ; h <userRoleObjC.getListUserRoleObj().size();h++){
					UserRole userRole = modelMapper.map(userRoleObjC.getListUserRoleObj().get(h),UserRole.class);
					userRole.setStatus(ApplicationConstant.STATUS_INACTIVE);
					userRole.setUpdateBy(SecurityUtils.getUserName());
					userRole.setUpdateDate(new Date());
					listUserRoleEntity.add(userRole);
						
				}
				userRoleCustomRepository.updateEntityList(listUserRoleEntity);
			logger.error("End service deleteUserAllRole .........");
		}catch(Exception e){
			logger.error("deleteUserAllRole service Error",e);
		}
		return result;
	}
	
	public UserRoleObjC searchByUserName(UserRoleObjC userRoleObjC){
		List<UserRole> listUserRole = null;
		String result = "Success";
		ModelMapper modelMapper = new ModelMapper();
		List<UserRoleObj> listUserRoleObj = new ArrayList<>();
		try {
			logger.error("Start service searchByUserName .........");
			List<UserRole> listUserRoleEntity = new ArrayList<UserRole>();
			
			listUserRole = new ArrayList<>();
			listUserRole = userRoleRepository.findByUserRoleByUserName(userRoleObjC.getUserName());
			if(null != listUserRole && listUserRole.size() > 0){
				for(int i=0 ; i < listUserRole.size();i++){
					Role role = new Role();
					User user = new User();
					UserRoleObj userRole = modelMapper.map(listUserRole.get(i),UserRoleObj.class);
					role = roleRepository.searchDataById(listUserRole.get(i).getRoleId());
					user = userRepository.findByUserId(listUserRole.get(i).getUserId());
					RoleObj roleObj = modelMapper.map(role, RoleObj.class);
					UserObj userObj = modelMapper.map(user, UserObj.class);
					userRole.setId(listUserRole.get(i).getId());
					userRole.setRoleId(roleObj.getId());
					userRole.setRoleObj(roleObj);
					userRole.setUserId(userObj.getId());
					userRole.setUserObj(userObj);
					userRole.setGroupId(listUserRole.get(i).getGroupId());
					userRole.setCreateBy(listUserRole.get(i).getCreateBy());
					userRole.setCreateDate(listUserRole.get(i).getCreateDate());
					userRole.setUpdateBy(listUserRole.get(i).getUpdateBy());
					userRole.setUpdateDate(listUserRole.get(i).getUpdateDate());
					userRole.setStatus(listUserRole.get(i).getStatus());
					listUserRoleObj.add(userRole);
				}
				
				userRoleObjC.setListUserRoleObj(listUserRoleObj);
			}
				
			
			logger.error("End service searchByUserName .........");
		}catch(Exception e){
			logger.error("searchByUserName service Error",e);
		}
		return userRoleObjC;
	}

}
