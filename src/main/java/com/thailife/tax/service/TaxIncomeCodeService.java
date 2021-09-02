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
import com.thailife.tax.entity.TaxIncomeCode;
import com.thailife.tax.object.MenuObj;
import com.thailife.tax.object.RoleMenuObj;
import com.thailife.tax.object.RoleObj;
import com.thailife.tax.object.TaxIncomeCodeObj;
import com.thailife.tax.object.criteria.RoleObjC;
import com.thailife.tax.object.criteria.TaxIncomeCodeObjC;
import com.thailife.tax.repository.RoleRepository;
import com.thailife.tax.repository.TaxIncomeCodeRepository;
import com.thailife.tax.repository.custom.RoleCustomRepository;
import com.thailife.tax.repository.custom.RoleMenuCustomRepository;
import com.thailife.tax.repository.custom.TaxIncomeCodeCustomRepository;
import com.thailife.tax.utils.IdGenerator;
import com.thailife.tax.utils.SecurityUtils;

@Service
public class TaxIncomeCodeService extends ServiceBase {

	@Autowired
	private TaxIncomeCodeRepository taxIncomeCodeRepository;

	@Autowired
	private TaxIncomeCodeCustomRepository taxIncomeCodeCustomRepository;

	private EntityManager entityManager;

	@Autowired
	public TaxIncomeCodeService(TaxIncomeCodeRepository taxIncomeCodeRepository) {
		this.taxIncomeCodeRepository = taxIncomeCodeRepository;
	}

	public TaxIncomeCodeObjC searchDataAll() {
		ModelMapper modelMapper = new ModelMapper();
		TaxIncomeCodeObjC taxIncomeCodeObjC = new TaxIncomeCodeObjC();
		List<TaxIncomeCodeObj> listTaxIncomeCodeObj = new ArrayList<>();
		List<TaxIncomeCode> listTaxIncomeCodeEntity = taxIncomeCodeRepository.searchDataAll();
		for (int i = 0; i < listTaxIncomeCodeEntity.size(); i++) {
			TaxIncomeCodeObj taxIncomeCodeObj = modelMapper.map(listTaxIncomeCodeEntity.get(i), TaxIncomeCodeObj.class);
			listTaxIncomeCodeObj.add(taxIncomeCodeObj);
		}
		taxIncomeCodeObjC.setListTaxIncomeObj(listTaxIncomeCodeObj);
		return taxIncomeCodeObjC;
	}

	public String addTaxIncomeCode(TaxIncomeCodeObj taxIncomeCodeObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start add TaxIncomeCode .........");
			ModelMapper modelMapper = new ModelMapper();
			result = checkDup(taxIncomeCodeObj,ApplicationConstant.ADD);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				taxIncomeCodeObj.setId(IdGenerator.getId());
				TaxIncomeCode taxIncomeCodeEntity = modelMapper.map(taxIncomeCodeObj, TaxIncomeCode.class);
				taxIncomeCodeEntity.setCreateUser("User01");
				taxIncomeCodeEntity.setCreateTime(new Date());
				taxIncomeCodeEntity.setStatus(ApplicationConstant.STATUS_ACTIVE);
				taxIncomeCodeEntity = taxIncomeCodeCustomRepository.saveEntity(taxIncomeCodeEntity);
			}
			logger.info("End add TaxIncomeCode .........");
		} catch (Exception e) {
			logger.error("add TaxIncomeCode Error", e);
		}
		return result;
	}

	public String updateTaxIncomeCode(TaxIncomeCodeObj taxIncomeCodeObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start update TaxIncomeCode .........");

			result = checkDup(taxIncomeCodeObj,ApplicationConstant.EDIT);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				ModelMapper modelMapper = new ModelMapper();
				TaxIncomeCode taxIncomeCodeEntity = modelMapper.map(taxIncomeCodeObj, TaxIncomeCode.class);
				taxIncomeCodeEntity.setUpdateUser("test01");
				taxIncomeCodeEntity.setUpdateTime(new Date());
				taxIncomeCodeEntity = taxIncomeCodeCustomRepository.updateEntity(taxIncomeCodeEntity);

			}
			logger.info("End update TaxIncomeCode .........");
		} catch (Exception e) {
			logger.error("update TaxIncomeCode Error", e);
		}
		return result;
	}

	public TaxIncomeCodeObj findById(TaxIncomeCodeObj taxIncomeCodeObj) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			TaxIncomeCode taxIncomeCodeEntity = new TaxIncomeCode();
			taxIncomeCodeEntity = taxIncomeCodeRepository.searchDataById(taxIncomeCodeObj.getId());
			taxIncomeCodeObj = modelMapper.map(taxIncomeCodeEntity, TaxIncomeCodeObj.class);
		} catch (Exception e) {
			logger.error("findById TaxIncomeCode Error", e);
		}
		return taxIncomeCodeObj;

	}

	public TaxIncomeCodeObjC searchTaxIncomeCode(TaxIncomeCodeObjC taxIncomeCodeObjC) {
		ModelMapper modelMapper = new ModelMapper();
		List<TaxIncomeCode> listTaxIncomeCodeEntity = new ArrayList<TaxIncomeCode>();
		List<TaxIncomeCodeObj> listTaxIncomeCodeObj = new ArrayList<>();
		String status = "";
		try {
			if (taxIncomeCodeObjC.getStatus() != null) {
				status = taxIncomeCodeObjC.getStatus();
				if (("").equals(status)) {
					status = "ST";
				}
			}
			listTaxIncomeCodeEntity = taxIncomeCodeRepository.searchDataByNameAndStatus(taxIncomeCodeObjC.getName(), status);
			for (int i = 0; i < listTaxIncomeCodeEntity.size(); i++) {
				TaxIncomeCodeObj taxIncomeCodeObj = modelMapper.map(listTaxIncomeCodeEntity.get(i), TaxIncomeCodeObj.class);
				listTaxIncomeCodeObj.add(taxIncomeCodeObj);
			}
			taxIncomeCodeObjC.setListTaxIncomeObj(listTaxIncomeCodeObj);
		} catch (Exception e) {
			logger.error("search TaxIncomeCode Error", e);
		}
		return taxIncomeCodeObjC;

	}

	public String deleteTaxIncomeCode(TaxIncomeCodeObjC taxIncomeCodeObjC) {
		TaxIncomeCode taxIncomeCodeEntity = null;
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start delete TaxIncomeCode .........");
			ModelMapper modelMapper = new ModelMapper();
//			List<RoleMenu> listRoleMenuEntity = new ArrayList<RoleMenu>();
//			List<Role> listRoleEntity = new ArrayList<Role>();

//			for (int d = 0; d < taxIncomeCodeObjC.getListTaxIncomeObj().size(); d++) {
//				taxIncomeCodeEntity = new TaxIncomeCode();
//				listRoleEntity = taxIncomeCodeRepository.checkUserUseRole(taxIncomeCodeObjC.getListTaxIncomeObj().get(d).getId(),
//						ApplicationConstant.STATUS_ACTIVE);
//				if (listRoleEntity.size() > 0) {
//					result = ApplicationConstant.FAIL;
//				}
//			}

//			if (!result.equals(ApplicationConstant.FAIL)) {
//				for (int h = 0; h < roleObjC.getListRoleObj().size(); h++) {
//					role = new Role();
//					listRoleEntity = roleRepository.checkUserUseRole(roleObjC.getListRoleObj().get(h).getId(),
//							ApplicationConstant.STATUS_ACTIVE);
//					if (listRoleEntity.size() == 0) {
//						role = roleRepository.searchDataById(roleObjC.getListRoleObj().get(h).getId());
//						listRoleMenuEntity = roleMenuService.searchDataByRoleId(role.getId());
//						roleMenuRepositoryCustom.deleteEntityList(listRoleMenuEntity);
//						role.setStatus(ApplicationConstant.STATUS_INACTIVE);
//						roleRepositoryCustom.updateEntity(role);
//						result = ApplicationConstant.SUCCESS;
//					}
//
//				}
//			}

			logger.info("End delete TaxIncomeCode .........");
		} catch (Exception e) {
			logger.error("delete TaxIncomeCode Error", e);
		}
		return result;
	}

	public String checkDup(TaxIncomeCodeObj taxIncomeCodeObj,String action) {
		List<TaxIncomeCode> listTaxIncomeCodeEntity = new ArrayList<TaxIncomeCode>();
		String result = ApplicationConstant.DUPLICATE;

		try {
			if(ApplicationConstant.ADD.equals(action)){
				listTaxIncomeCodeEntity = taxIncomeCodeRepository.checkDupDataByNameAndStatus(taxIncomeCodeObj.getName(),
						ApplicationConstant.STATUS_ACTIVE);
				if (listTaxIncomeCodeEntity.size() == 0) {
					result = ApplicationConstant.SUCCESS;
				}
			}else if(ApplicationConstant.EDIT.equals(action)){
				listTaxIncomeCodeEntity = taxIncomeCodeRepository.checkDupDataByNameAndStatus(taxIncomeCodeObj.getName(),
						ApplicationConstant.STATUS_ACTIVE);
				if (listTaxIncomeCodeEntity.size() > 0) {
					if(taxIncomeCodeObj.getId().equals(listTaxIncomeCodeEntity.get(0).getId())){
						result = ApplicationConstant.SUCCESS;
					}
				}else{
						result = ApplicationConstant.SUCCESS;
				}
			}
			
		} catch (Exception e) {
			logger.error("TaxIncomeCode checkDup error", e);
		}
		return result;
	}

}