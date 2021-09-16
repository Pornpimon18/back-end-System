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
import com.thailife.tax.entity.TaxDeductDetail;
import com.thailife.tax.entity.TaxDeductDetailPK;
import com.thailife.tax.entity.UserRole;
import com.thailife.tax.object.MenuObj;
import com.thailife.tax.object.RoleMenuObj;
import com.thailife.tax.object.RoleObj;
import com.thailife.tax.object.TaxDeductDetailObj;
import com.thailife.tax.object.TaxDeductObj;
import com.thailife.tax.object.criteria.RoleObjC;
import com.thailife.tax.object.criteria.TaxDeductDetailObjC;
import com.thailife.tax.repository.RoleRepository;
import com.thailife.tax.repository.TaxDeductDetailRepository;
import com.thailife.tax.repository.TaxDeductRepository;
import com.thailife.tax.repository.custom.RoleCustomRepository;
import com.thailife.tax.repository.custom.RoleMenuCustomRepository;
import com.thailife.tax.repository.custom.TaxDeductDetailCustomRepository;
import com.thailife.tax.utils.IdGenerator;
import com.thailife.tax.utils.SecurityUtils;
import com.thailife.tax.utils.Status;

@Service
public class TaxDeductDetailService extends ServiceBase {

	@Autowired
	private TaxDeductDetailRepository taxDeductDetailRepository;
	
	@Autowired
	private TaxDeductRepository taxDeductRepository;

	@Autowired
	private TaxDeductDetailCustomRepository taxDeductDetailCustomRepository;

	private EntityManager entityManager;

	@Autowired
	public TaxDeductDetailService(TaxDeductDetailRepository taxDeductDetailRepository) {
		this.taxDeductDetailRepository = taxDeductDetailRepository;
	}
	
	public String addTaxDeductDetail(TaxDeductDetailObjC taxDeductDetailObjC) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start add TaxDeductDetail .........");
			ModelMapper modelMapper = new ModelMapper();
			List<TaxDeductDetail> listTaxDeductDetailEntity = new ArrayList<TaxDeductDetail>();
			
			if(taxDeductDetailObjC.getListTaxDeductDetailObj().size() > 0){
				listTaxDeductDetailEntity = taxDeductDetailRepository.searchDataByYearAndEffectiveDate(taxDeductDetailObjC.getListTaxDeductDetailObj().get(0).getYear(),taxDeductDetailObjC.getListTaxDeductDetailObj().get(0).getEffectiveDate());
				if(listTaxDeductDetailEntity.size() > 0){
					taxDeductDetailCustomRepository.deleteEntityList(listTaxDeductDetailEntity);
				}
				listTaxDeductDetailEntity = taxDeductDetailRepository.searchDataMoreCurrentDate(taxDeductDetailObjC.getListTaxDeductDetailObj().get(0).getYear());
				if(listTaxDeductDetailEntity.size() > 0){
					taxDeductDetailCustomRepository.deleteEntityList(listTaxDeductDetailEntity);
				}
				listTaxDeductDetailEntity = new ArrayList<TaxDeductDetail>();
					for(int y = 0 ; y < taxDeductDetailObjC.getListTaxDeductDetailObj().size() ; y++){
						TaxDeductDetail taxDeductDetailEntity = modelMapper.map(taxDeductDetailObjC.getListTaxDeductDetailObj().get(y), TaxDeductDetail.class);
						taxDeductDetailEntity.setTaxDeductDetailPK(new TaxDeductDetailPK());
						taxDeductDetailEntity.getTaxDeductDetailPK().setYear(taxDeductDetailObjC.getListTaxDeductDetailObj().get(y).getYear());
						taxDeductDetailEntity.getTaxDeductDetailPK().setTaxDeductId(taxDeductDetailObjC.getListTaxDeductDetailObj().get(y).getTaxDeductId());
						taxDeductDetailEntity.getTaxDeductDetailPK().setEffectiveDate(taxDeductDetailObjC.getListTaxDeductDetailObj().get(y).getEffectiveDate());
						taxDeductDetailEntity.setCreateTime(new Date());
						taxDeductDetailEntity.setCreateUser(SecurityUtils.getUserName());
						taxDeductDetailEntity.setUpdateUser(SecurityUtils.getUserName());
						taxDeductDetailEntity.setUpdateTime(new Date());
						listTaxDeductDetailEntity.add(taxDeductDetailEntity);
					}
						listTaxDeductDetailEntity = taxDeductDetailCustomRepository.saveList(listTaxDeductDetailEntity);
					
				
			
			}
			
			logger.info("End add TaxDeductDetail .........");
		} catch (Exception e) {
			logger.error("add TaxDeductDetail Error", e);
		}
		return result;
	}

	public String updateTaxDeductDetail(TaxDeductDetailObj taxDeductDetailObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start update TaxDeductDetail .........");

			result = checkDup(taxDeductDetailObj, ApplicationConstant.EDIT);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				ModelMapper modelMapper = new ModelMapper();
				TaxDeductDetail taxDeductDetailEntity = modelMapper.map(taxDeductDetailObj, TaxDeductDetail.class);
				taxDeductDetailEntity.setUpdateUser(SecurityUtils.getUserName());
				taxDeductDetailEntity.setUpdateTime(new Date());
				taxDeductDetailEntity = taxDeductDetailCustomRepository.updateEntity(taxDeductDetailEntity);

			}
			logger.info("End update TaxDeductDetail .........");
		} catch (Exception e) {
			logger.error("update TaxDeductDetail Error", e);
		}
		return result;
	}

	public TaxDeductDetailObjC findById(TaxDeductDetailObjC taxDeductDetailObjC) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			TaxDeduct taxDeductEntity = null;
			TaxDeductObj taxDeductObj = null;
			TaxDeductDetailObj taxDeductDetailObj = null;
			List<TaxDeductDetail> listTaxDeductDetailEntity = new ArrayList<TaxDeductDetail>();
			List<TaxDeductDetailObj> listTaxDeductDetailObj = new ArrayList<TaxDeductDetailObj>();
			listTaxDeductDetailEntity = taxDeductDetailRepository.searchDataByYear(taxDeductDetailObjC.getYear());
			checkEffectiveDate(listTaxDeductDetailEntity);
			if (listTaxDeductDetailEntity.size() > 0) {
				for(int i =0 ; i < listTaxDeductDetailEntity.size() ; i++){
					taxDeductDetailObj = new TaxDeductDetailObj();
					taxDeductEntity = new TaxDeduct();
					taxDeductObj = new TaxDeductObj();
					taxDeductDetailObj = modelMapper.map(listTaxDeductDetailEntity.get(i), TaxDeductDetailObj.class);
					taxDeductDetailObj.setEffectiveDate(listTaxDeductDetailEntity.get(i).getTaxDeductDetailPK().getEffectiveDate());
					taxDeductDetailObj.setYear(listTaxDeductDetailEntity.get(i).getTaxDeductDetailPK().getYear());
					taxDeductDetailObj.setTaxDeductId(listTaxDeductDetailEntity.get(i).getTaxDeductDetailPK().getTaxDeductId());
					taxDeductEntity = taxDeductRepository.searchDataById(listTaxDeductDetailEntity.get(i).getTaxDeductDetailPK().getTaxDeductId());
					taxDeductObj = modelMapper.map(taxDeductEntity, TaxDeductObj.class);
					taxDeductDetailObj.setTaxDeductObj(taxDeductObj);
					listTaxDeductDetailObj.add(taxDeductDetailObj);
				}
			}
			taxDeductDetailObjC.setListTaxDeductDetailObj(listTaxDeductDetailObj);
		} catch (Exception e) {
			logger.error("findById TaxDeductDetail Error", e);
		}
		return taxDeductDetailObjC;

	}
	
	public String findDataMoreCurrentDate(TaxDeductDetailObjC taxDeductDetailObjC) {
		String result = ApplicationConstant.FAIL;
		List<TaxDeductDetail> listTaxDeductDetailEntity = new ArrayList<TaxDeductDetail>();
		try {
			
			listTaxDeductDetailEntity = taxDeductDetailRepository.searchDataMoreCurrentDate(taxDeductDetailObjC.getYear());
			if(listTaxDeductDetailEntity.size() == 0){
				result = ApplicationConstant.SUCCESS;
			}
		} catch (Exception e) {
			logger.error("findById TaxDeductDetail Error", e);
		}
		return result;

	}

	public String checkDup(TaxDeductDetailObj taxDeductDetailObj, String action) {
		List<TaxDeductDetail> listTaxDeductDetailEntity = new ArrayList<TaxDeductDetail>();
		String result = ApplicationConstant.DUPLICATE;

		try {
			if (ApplicationConstant.ADD.equals(action)) {
				listTaxDeductDetailEntity = taxDeductDetailRepository.checkDupData(taxDeductDetailObj.getYear(),
						taxDeductDetailObj.getTaxDeductId(), taxDeductDetailObj.getEffectiveDate());
				if (listTaxDeductDetailEntity.size() == 0) {
					result = ApplicationConstant.SUCCESS;
				}
			} else if (ApplicationConstant.EDIT.equals(action)) {
				listTaxDeductDetailEntity = taxDeductDetailRepository.checkDupData(taxDeductDetailObj.getYear(),
						taxDeductDetailObj.getTaxDeductId(), taxDeductDetailObj.getEffectiveDate());
				if (listTaxDeductDetailEntity.size() > 0) {
					if (taxDeductDetailObj.getYear()
							.equals(listTaxDeductDetailEntity.get(0).getTaxDeductDetailPK().getYear())
							&& taxDeductDetailObj.getTaxDeductId()
									.equals(listTaxDeductDetailEntity.get(0).getTaxDeductDetailPK().getTaxDeductId())
							&& taxDeductDetailObj.getEffectiveDate().equals(
									listTaxDeductDetailEntity.get(0).getTaxDeductDetailPK().getEffectiveDate())) {
						result = ApplicationConstant.SUCCESS;
					}
				} else {
					result = ApplicationConstant.SUCCESS;
				}
			}

		} catch (Exception e) {
			logger.error("TaxDeductDetail checkDup error", e);
		}
		return result;
	}
	
	public String checkEffectiveDate(List<TaxDeductDetail> listTaxDeductDetailEntity) {
		List<TaxDeductDetail> listTaxDeductDetailByEffectiveDate = new ArrayList<TaxDeductDetail>();
		String result = ApplicationConstant.DUPLICATE;
		try {
			listTaxDeductDetailByEffectiveDate = listTaxDeductDetailEntity.stream().filter(distinctByKeys(TaxDeductDetail::getEffectiveDate))
					.collect(Collectors.toList());
//			if(listTaxDeductDetailByEffectiveDate.size() > 0){
//				for(listTaxDeductDetailByEffectiveDate){
//					
//				}
//				for(int v=0 ; v < listTaxDeductDetailEntity.size() ;v++){
//					listTaxDeductDetailEntity
//				}
//				
//			}
		} catch (Exception e) {
			logger.error("TaxDeductDetail checkDup error", e);
		}
		return result;
	}
	
	private static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) {
		final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

		return t -> {
			final List<?> keys = Arrays.stream(keyExtractors).map(ke -> ke.apply(t)).collect(Collectors.toList());

			return seen.putIfAbsent(keys, Boolean.TRUE) == null;
		};
	}

}
