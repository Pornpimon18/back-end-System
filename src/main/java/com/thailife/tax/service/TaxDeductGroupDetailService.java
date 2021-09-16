package com.thailife.tax.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
import com.thailife.tax.entity.TaxDeduct;
import com.thailife.tax.entity.TaxDeductGroupDetail;
import com.thailife.tax.entity.TaxDeductGroupDetailPK;
import com.thailife.tax.entity.UserRole;
import com.thailife.tax.object.MenuObj;
import com.thailife.tax.object.RoleMenuObj;
import com.thailife.tax.object.RoleObj;
import com.thailife.tax.object.TaxDeductGroupDetailObj;
import com.thailife.tax.object.TaxDeductObj;
import com.thailife.tax.object.criteria.RoleObjC;
import com.thailife.tax.object.criteria.TaxDeductGroupDetailObjC;
import com.thailife.tax.repository.RoleRepository;
import com.thailife.tax.repository.TaxDeductGroupDetailRepository;
import com.thailife.tax.repository.TaxDeductRepository;
import com.thailife.tax.repository.custom.RoleCustomRepository;
import com.thailife.tax.repository.custom.RoleMenuCustomRepository;
import com.thailife.tax.repository.custom.TaxDeductGroupDetailCustomRepository;
import com.thailife.tax.utils.IdGenerator;
import com.thailife.tax.utils.SecurityUtils;
import com.thailife.tax.utils.Status;

@Service
public class TaxDeductGroupDetailService extends ServiceBase {

	@Autowired
	private TaxDeductGroupDetailRepository taxDeductGroupDetailRepository;
	
	@Autowired
	private TaxDeductRepository taxDeductRepository;

	@Autowired
	private TaxDeductGroupDetailCustomRepository taxDeductGroupDetailCustomRepository;

	private EntityManager entityManager;

	@Autowired
	public TaxDeductGroupDetailService(TaxDeductGroupDetailRepository taxDeductGroupDetailRepository) {
		this.taxDeductGroupDetailRepository = taxDeductGroupDetailRepository;
	}
	
	public String addTaxDeductGroupDetail(TaxDeductGroupDetailObjC taxDeductGroupDetailObjC) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start add TaxDeductGroupDetail .........");
			ModelMapper modelMapper = new ModelMapper();
			List<TaxDeductGroupDetail> listTaxDeductGroupDetailEntity = new ArrayList<TaxDeductGroupDetail>();
			
			if(taxDeductGroupDetailObjC.getListTaxDeductGroupDetailObj().size() > 0){
				listTaxDeductGroupDetailEntity = taxDeductGroupDetailRepository.searchDataByYearAndEffectiveDate(taxDeductGroupDetailObjC.getListTaxDeductGroupDetailObj().get(0).getYear(),taxDeductGroupDetailObjC.getListTaxDeductGroupDetailObj().get(0).getEffectiveDate());
				if(listTaxDeductGroupDetailEntity.size() > 0){
					taxDeductGroupDetailCustomRepository.deleteEntityList(listTaxDeductGroupDetailEntity);
				}
				listTaxDeductGroupDetailEntity = taxDeductGroupDetailRepository.searchDataMoreCurrentDate(taxDeductGroupDetailObjC.getListTaxDeductGroupDetailObj().get(0).getYear());
				if(listTaxDeductGroupDetailEntity.size() > 0){
					taxDeductGroupDetailCustomRepository.deleteEntityList(listTaxDeductGroupDetailEntity);
				}
				listTaxDeductGroupDetailEntity = new ArrayList<TaxDeductGroupDetail>();
					for(int y = 0 ; y < taxDeductGroupDetailObjC.getListTaxDeductGroupDetailObj().size() ; y++){
						TaxDeductGroupDetail taxDeductGroupDetailEntity = modelMapper.map(taxDeductGroupDetailObjC.getListTaxDeductGroupDetailObj().get(y), TaxDeductGroupDetail.class);
						taxDeductGroupDetailEntity.setTaxDeductGroupDetailPK(new TaxDeductGroupDetailPK());
						taxDeductGroupDetailEntity.getTaxDeductGroupDetailPK().setYear(taxDeductGroupDetailObjC.getListTaxDeductGroupDetailObj().get(y).getYear());
						taxDeductGroupDetailEntity.getTaxDeductGroupDetailPK().setTaxDeductId(taxDeductGroupDetailObjC.getListTaxDeductGroupDetailObj().get(y).getTaxDeductId());
						taxDeductGroupDetailEntity.getTaxDeductGroupDetailPK().setEffectiveDate(taxDeductGroupDetailObjC.getListTaxDeductGroupDetailObj().get(y).getEffectiveDate());
						taxDeductGroupDetailEntity.setCreateTime(new Date());
						taxDeductGroupDetailEntity.setCreateUser(SecurityUtils.getUserName());
						taxDeductGroupDetailEntity.setUpdateUser(SecurityUtils.getUserName());
						taxDeductGroupDetailEntity.setUpdateTime(new Date());
						listTaxDeductGroupDetailEntity.add(taxDeductGroupDetailEntity);
					}
						listTaxDeductGroupDetailEntity = taxDeductGroupDetailCustomRepository.saveList(listTaxDeductGroupDetailEntity);
					
				
			
			}
			
			logger.info("End add TaxDeductGroupDetail .........");
		} catch (Exception e) {
			logger.error("add TaxDeductGroupDetail Error", e);
		}
		return result;
	}

	public String updateTaxDeductGroupDetail(TaxDeductGroupDetailObj taxDeductGroupDetailObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start update TaxDeductGroupDetail .........");

			result = checkDup(taxDeductGroupDetailObj, ApplicationConstant.EDIT);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				ModelMapper modelMapper = new ModelMapper();
				TaxDeductGroupDetail taxDeductGroupDetailEntity = modelMapper.map(taxDeductGroupDetailObj, TaxDeductGroupDetail.class);
				taxDeductGroupDetailEntity.setUpdateUser(SecurityUtils.getUserName());
				taxDeductGroupDetailEntity.setUpdateTime(new Date());
				taxDeductGroupDetailEntity = taxDeductGroupDetailCustomRepository.updateEntity(taxDeductGroupDetailEntity);

			}
			logger.info("End update TaxDeductGroupDetail .........");
		} catch (Exception e) {
			logger.error("update TaxDeductGroupDetail Error", e);
		}
		return result;
	}

	public TaxDeductGroupDetailObjC findById(TaxDeductGroupDetailObjC taxDeductGroupDetailObjC) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			TaxDeduct taxDeductEntity = null;
			TaxDeductObj taxDeductObj = null;
			TaxDeductGroupDetailObj taxDeductGroupDetailObj = null;
			List<TaxDeductGroupDetail> listTaxDeductGroupDetailEntity = new ArrayList<TaxDeductGroupDetail>();
			List<TaxDeductGroupDetailObj> listTaxDeductGroupDetailObj = new ArrayList<TaxDeductGroupDetailObj>();
			listTaxDeductGroupDetailEntity = taxDeductGroupDetailRepository.searchDataByYearAndDeductGroupId(taxDeductGroupDetailObjC.getYear(),taxDeductGroupDetailObjC.getDeductGroupId());
//			checkEffectiveDate(listTaxDeductGroupDetailEntity);
			if (listTaxDeductGroupDetailEntity.size() > 0) {
				for(int i =0 ; i < listTaxDeductGroupDetailEntity.size() ; i++){
					taxDeductGroupDetailObj = new TaxDeductGroupDetailObj();
					taxDeductEntity = new TaxDeduct();
					taxDeductObj = new TaxDeductObj();
					taxDeductGroupDetailObj.setCreateTime(listTaxDeductGroupDetailEntity.get(i).getCreateTime());
					taxDeductGroupDetailObj.setCreateUser(listTaxDeductGroupDetailEntity.get(i).getCreateUser());
					taxDeductGroupDetailObj.setUpdateTime(listTaxDeductGroupDetailEntity.get(i).getUpdateTime());
					taxDeductGroupDetailObj.setUpdateUser(listTaxDeductGroupDetailEntity.get(i).getUpdateUser());
					taxDeductGroupDetailObj.setNo(listTaxDeductGroupDetailEntity.get(i).getTaxDeductGroupDetailPK().getNo());
					taxDeductGroupDetailObj.setEffectiveDate(listTaxDeductGroupDetailEntity.get(i).getTaxDeductGroupDetailPK().getEffectiveDate());
					taxDeductGroupDetailObj.setYear(listTaxDeductGroupDetailEntity.get(i).getTaxDeductGroupDetailPK().getYear());
					taxDeductGroupDetailObj.setTaxDeductId(listTaxDeductGroupDetailEntity.get(i).getTaxDeductGroupDetailPK().getTaxDeductId());
					taxDeductGroupDetailObj.setDeductGroupId(listTaxDeductGroupDetailEntity.get(i).getTaxDeductGroupDetailPK().getDeductGroupId());
					taxDeductEntity = taxDeductRepository.searchDataById(listTaxDeductGroupDetailEntity.get(i).getTaxDeductGroupDetailPK().getTaxDeductId());
					taxDeductObj = modelMapper.map(taxDeductEntity, TaxDeductObj.class);
					taxDeductGroupDetailObj.setTaxDeductObj(taxDeductObj);
					listTaxDeductGroupDetailObj.add(taxDeductGroupDetailObj);
				}
			}
			taxDeductGroupDetailObjC.setListTaxDeductGroupDetailObj(listTaxDeductGroupDetailObj);
		} catch (Exception e) {
			logger.error("findById TaxDeductGroupDetail Error", e);
		}
		return taxDeductGroupDetailObjC;

	}
	
	public String findDataMoreCurrentDate(TaxDeductGroupDetailObjC taxDeductGroupDetailObjC) {
		String result = ApplicationConstant.FAIL;
		List<TaxDeductGroupDetail> listTaxDeductGroupDetailEntity = new ArrayList<TaxDeductGroupDetail>();
		try {
			
			listTaxDeductGroupDetailEntity = taxDeductGroupDetailRepository.searchDataMoreCurrentDate(taxDeductGroupDetailObjC.getYear());
			if(listTaxDeductGroupDetailEntity.size() == 0){
				result = ApplicationConstant.SUCCESS;
			}
		} catch (Exception e) {
			logger.error("findById TaxDeductGroupDetail Error", e);
		}
		return result;

	}

	public String checkDup(TaxDeductGroupDetailObj taxDeductGroupDetailObj, String action) {
		List<TaxDeductGroupDetail> listTaxDeductGroupDetailEntity = new ArrayList<TaxDeductGroupDetail>();
		String result = ApplicationConstant.DUPLICATE;

		try {
			if (ApplicationConstant.ADD.equals(action)) {
				listTaxDeductGroupDetailEntity = taxDeductGroupDetailRepository.checkDupData(taxDeductGroupDetailObj.getYear(),
						taxDeductGroupDetailObj.getTaxDeductId(), taxDeductGroupDetailObj.getEffectiveDate());
				if (listTaxDeductGroupDetailEntity.size() == 0) {
					result = ApplicationConstant.SUCCESS;
				}
			} else if (ApplicationConstant.EDIT.equals(action)) {
				listTaxDeductGroupDetailEntity = taxDeductGroupDetailRepository.checkDupData(taxDeductGroupDetailObj.getYear(),
						taxDeductGroupDetailObj.getTaxDeductId(), taxDeductGroupDetailObj.getEffectiveDate());
				if (listTaxDeductGroupDetailEntity.size() > 0) {
					if (taxDeductGroupDetailObj.getYear()
							.equals(listTaxDeductGroupDetailEntity.get(0).getTaxDeductGroupDetailPK().getYear())
							&& taxDeductGroupDetailObj.getTaxDeductId()
									.equals(listTaxDeductGroupDetailEntity.get(0).getTaxDeductGroupDetailPK().getTaxDeductId())
							&& taxDeductGroupDetailObj.getEffectiveDate().equals(
									listTaxDeductGroupDetailEntity.get(0).getTaxDeductGroupDetailPK().getEffectiveDate())) {
						result = ApplicationConstant.SUCCESS;
					}
				} else {
					result = ApplicationConstant.SUCCESS;
				}
			}

		} catch (Exception e) {
			logger.error("TaxDeductGroupDetail checkDup error", e);
		}
		return result;
	}
	
//	public String checkEffectiveDate(List<TaxDeductGroupDetail> listTaxDeductGroupDetailEntity) {
//		List<TaxDeductGroupDetail> listTaxDeductGroupDetailByEffectiveDate = new ArrayList<TaxDeductGroupDetail>();
//		String result = ApplicationConstant.DUPLICATE;
//		try {
//			listTaxDeductGroupDetailByEffectiveDate = listTaxDeductGroupDetailEntity.stream().filter(distinctByKeys(TaxDeductGroupDetail::getEffectiveDate))
//					.collect(Collectors.toList());
////			if(listTaxDeductGroupDetailByEffectiveDate.size() > 0){
////				for(listTaxDeductGroupDetailByEffectiveDate){
////					
////				}
////				for(int v=0 ; v < listTaxDeductGroupDetailEntity.size() ;v++){
////					listTaxDeductGroupDetailEntity
////				}
////				
////			}
//		} catch (Exception e) {
//			logger.error("TaxDeductGroupDetail checkDup error", e);
//		}
//		return result;
//	}
	
	private static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) {
		final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

		return t -> {
			final List<?> keys = Arrays.stream(keyExtractors).map(ke -> ke.apply(t)).collect(Collectors.toList());

			return seen.putIfAbsent(keys, Boolean.TRUE) == null;
		};
	}

}
