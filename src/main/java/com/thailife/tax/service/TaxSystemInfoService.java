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
import com.thailife.tax.entity.TaxSystemInfo;
import com.thailife.tax.object.TaxSystemInfoObj;
import com.thailife.tax.object.criteria.TaxSystemInfoObjC;
import com.thailife.tax.repository.TaxSystemInfoRepository;
import com.thailife.tax.repository.custom.TaxSystemInfoCustomRepository;
import com.thailife.tax.utils.IdGenerator;
import com.thailife.tax.utils.SecurityUtils;
import com.thailife.tax.utils.Status;

@Service
public class TaxSystemInfoService extends ServiceBase {

	@Autowired
	private TaxSystemInfoRepository taxSystemInfoRepository;

	@Autowired
	private TaxSystemInfoCustomRepository taxSystemInfoCustomRepository;

	private EntityManager entityManager;

	@Autowired
	public TaxSystemInfoService(TaxSystemInfoRepository taxSystemInfoRepository) {
		this.taxSystemInfoRepository = taxSystemInfoRepository;
	}

	public TaxSystemInfoObjC searchDataAll() {
		ModelMapper modelMapper = new ModelMapper();
		TaxSystemInfoObjC taxSystemInfoObjC = new TaxSystemInfoObjC();
		List<TaxSystemInfoObj> listTaxSystemInfoObj = new ArrayList<>();
		List<TaxSystemInfo> listTaxSystemInfoEntity = taxSystemInfoRepository.searchDataAll();
		for (int i = 0; i < listTaxSystemInfoEntity.size(); i++) {
			TaxSystemInfoObj taxSystemInfoObj = modelMapper.map(listTaxSystemInfoEntity.get(i), TaxSystemInfoObj.class);
			listTaxSystemInfoObj.add(taxSystemInfoObj);
		}
		taxSystemInfoObjC.setListTaxSystemInfoObj(listTaxSystemInfoObj);
		return taxSystemInfoObjC;
	}

	public String addTaxSystemInfo(TaxSystemInfoObj taxSystemInfoObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start add TaxSystemInfo .........");
			ModelMapper modelMapper = new ModelMapper();
			result = checkDup(taxSystemInfoObj,ApplicationConstant.ADD);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				TaxSystemInfo taxSystemInfoEntity = modelMapper.map(taxSystemInfoObj, TaxSystemInfo.class);
				taxSystemInfoEntity.setCreateUser(SecurityUtils.getUserName());
				taxSystemInfoEntity.setCreateTime(new Date());
				taxSystemInfoEntity.setStatus(Status.active);
				taxSystemInfoEntity = taxSystemInfoCustomRepository.saveEntity(taxSystemInfoEntity);
			}
			logger.info("End add TaxSystemInfo .........");
		} catch (Exception e) {
			logger.error("add TaxSystemInfo Error", e);
		}
		return result;
	}

	public String updateTaxSystemInfo(TaxSystemInfoObj taxSystemInfoObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start update TaxSystemInfo .........");

			result = checkDup(taxSystemInfoObj,ApplicationConstant.EDIT);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				ModelMapper modelMapper = new ModelMapper();
				TaxSystemInfo taxSystemInfoEntity = modelMapper.map(taxSystemInfoObj, TaxSystemInfo.class);
				taxSystemInfoEntity.setUpdateUser(SecurityUtils.getUserName());
				taxSystemInfoEntity.setUpdateTime(new Date());
				taxSystemInfoEntity = taxSystemInfoCustomRepository.updateEntity(taxSystemInfoEntity);

			}
			logger.info("End update TaxSystemInfo .........");
		} catch (Exception e) {
			logger.error("update TaxSystemInfo Error", e);
		}
		return result;
	}

	public TaxSystemInfoObj findById(TaxSystemInfoObj taxSystemInfoObj) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			TaxSystemInfo taxSystemInfoEntity = new TaxSystemInfo();
			taxSystemInfoEntity = taxSystemInfoRepository.searchDataById(taxSystemInfoObj.getSystemId());
			if(taxSystemInfoEntity.getSystemId() !=null){
				taxSystemInfoObj = modelMapper.map(taxSystemInfoEntity, TaxSystemInfoObj.class);
			}
		} catch (Exception e) {
			logger.error("findById TaxSystemInfo Error", e);
		}
		return taxSystemInfoObj;

	}

	public TaxSystemInfoObjC searchTaxSystemInfo(TaxSystemInfoObjC taxSystemInfoObjC) {
		ModelMapper modelMapper = new ModelMapper();
		List<TaxSystemInfo> listTaxSystemInfoEntity = new ArrayList<TaxSystemInfo>();
		List<TaxSystemInfoObj> listTaxSystemInfoObj = new ArrayList<>();
		String status = "";
		try {
			
			listTaxSystemInfoEntity = taxSystemInfoRepository.searchDataByNameAndStatus(taxSystemInfoObjC.getName(), taxSystemInfoObjC.getStatus());
			for (int i = 0; i < listTaxSystemInfoEntity.size(); i++) {
				TaxSystemInfoObj taxSystemInfoObj = modelMapper.map(listTaxSystemInfoEntity.get(i), TaxSystemInfoObj.class);
				listTaxSystemInfoObj.add(taxSystemInfoObj);
			}
			taxSystemInfoObjC.setListTaxSystemInfoObj(listTaxSystemInfoObj);
		} catch (Exception e) {
			logger.error("search TaxSystemInfo Error", e);
		}
		return taxSystemInfoObjC;

	}

	public String deleteTaxSystemInfo(TaxSystemInfoObjC taxSystemInfoObjC) {
		TaxSystemInfo taxSystemInfoEntity = null;
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start delete TaxSystemInfo .........");
			for (int d = 0; d < taxSystemInfoObjC.getListTaxSystemInfoObj().size(); d++) {
				taxSystemInfoEntity = taxSystemInfoRepository.searchDataById(taxSystemInfoObjC.getListTaxSystemInfoObj().get(d).getSystemId());
				taxSystemInfoEntity.setStatus(Status.inactive);
				taxSystemInfoEntity = taxSystemInfoCustomRepository.updateEntity(taxSystemInfoEntity);
			}

			logger.info("End delete TaxSystemInfo .........");
		} catch (Exception e) {
			logger.error("delete TaxSystemInfo Error", e);
		}
		return result;
	}

	public String checkDup(TaxSystemInfoObj taxSystemInfoObj,String action) {
		List<TaxSystemInfo> listTaxSystemInfoEntity = new ArrayList<TaxSystemInfo>();
		String result = ApplicationConstant.DUPLICATE;

		try {
			if(ApplicationConstant.ADD.equals(action)){
				listTaxSystemInfoEntity = taxSystemInfoRepository.checkDupDataByNameAndStatus(taxSystemInfoObj.getName());
				if (listTaxSystemInfoEntity.size() == 0) {
					result = ApplicationConstant.SUCCESS;
				}
			}else if(ApplicationConstant.EDIT.equals(action)){
				listTaxSystemInfoEntity = taxSystemInfoRepository.checkDupDataByNameAndStatus(taxSystemInfoObj.getName());
				if (listTaxSystemInfoEntity.size() > 0) {
					if(taxSystemInfoObj.getSystemId().equals(listTaxSystemInfoEntity.get(0).getSystemId())){
						result = ApplicationConstant.SUCCESS;
					}
				}else{
						result = ApplicationConstant.SUCCESS;
				}
			}
			
		} catch (Exception e) {
			logger.error("TaxSystemInfo checkDup error", e);
		}
		return result;
	}

}
