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
import com.thailife.tax.entity.TaxRate;
import com.thailife.tax.object.TaxRateObj;
import com.thailife.tax.object.criteria.TaxRateObjC;
import com.thailife.tax.repository.TaxRateRepository;
import com.thailife.tax.repository.custom.TaxRateCustomRepository;
import com.thailife.tax.utils.IdGenerator;
import com.thailife.tax.utils.SecurityUtils;
import com.thailife.tax.utils.Status;

@Service
public class TaxRateService extends ServiceBase {

	@Autowired
	private TaxRateRepository taxRateRepository;

	@Autowired
	private TaxRateCustomRepository taxRateCustomRepository;

	private EntityManager entityManager;

	@Autowired
	public TaxRateService(TaxRateRepository taxRateRepository) {
		this.taxRateRepository = taxRateRepository;
	}

	public TaxRateObjC searchDataAll() {
		ModelMapper modelMapper = new ModelMapper();
		TaxRateObjC taxRateObjC = new TaxRateObjC();
		List<TaxRateObj> listTaxRateObj = new ArrayList<>();
		List<TaxRate> listTaxRateEntity = taxRateRepository.searchDataAll();
		for (int i = 0; i < listTaxRateEntity.size(); i++) {
			TaxRateObj taxRateObj = modelMapper.map(listTaxRateEntity.get(i), TaxRateObj.class);
			listTaxRateObj.add(taxRateObj);
		}
		taxRateObjC.setListTaxRateObj(listTaxRateObj);
		return taxRateObjC;
	}

	public String addTaxRate(TaxRateObj taxRateObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start add TaxRate .........");
			ModelMapper modelMapper = new ModelMapper();
			result = checkDup(taxRateObj,ApplicationConstant.ADD);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				TaxRate taxRateEntity = modelMapper.map(taxRateObj, TaxRate.class);
				taxRateEntity.setUpdateUser(SecurityUtils.getUserName());
				taxRateEntity.setUpdateTime(new Date());
				taxRateEntity.setStatus(Status.active);
				taxRateEntity = taxRateCustomRepository.saveEntity(taxRateEntity);
			}
			logger.info("End add TaxRate .........");
		} catch (Exception e) {
			logger.error("add TaxRate Error", e);
		}
		return result;
	}

	public String updateTaxRate(TaxRateObj taxRateObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start update TaxRate .........");

			result = checkDup(taxRateObj,ApplicationConstant.EDIT);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				ModelMapper modelMapper = new ModelMapper();
				TaxRate taxRateEntity = modelMapper.map(taxRateObj, TaxRate.class);
				taxRateEntity.setUpdateUser(SecurityUtils.getUserName());
				taxRateEntity.setUpdateTime(new Date());
				taxRateEntity = taxRateCustomRepository.updateEntity(taxRateEntity);

			}
			logger.info("End update TaxRate .........");
		} catch (Exception e) {
			logger.error("update TaxRate Error", e);
		}
		return result;
	}

	public TaxRateObj findById(TaxRateObj taxRateObj) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			TaxRate taxRateEntity = new TaxRate();
			taxRateEntity = taxRateRepository.searchDataById(taxRateObj.getTaxRateId());
			if(taxRateEntity.getTaxRateId() !=null){
				taxRateObj = modelMapper.map(taxRateEntity, TaxRateObj.class);
			}
		} catch (Exception e) {
			logger.error("findById TaxRate Error", e);
		}
		return taxRateObj;

	}

	public TaxRateObjC searchTaxRate(TaxRateObjC taxRateObjC) {
		ModelMapper modelMapper = new ModelMapper();
		List<TaxRate> listTaxRateEntity = new ArrayList<TaxRate>();
		List<TaxRateObj> listTaxRateObj = new ArrayList<>();
		String status = "";
		try {
			
			listTaxRateEntity = taxRateRepository.searchDataByNameAndStatus(taxRateObjC.getName(), taxRateObjC.getStatus());
			for (int i = 0; i < listTaxRateEntity.size(); i++) {
				TaxRateObj taxRateObj = modelMapper.map(listTaxRateEntity.get(i), TaxRateObj.class);
				listTaxRateObj.add(taxRateObj);
			}
			taxRateObjC.setListTaxRateObj(listTaxRateObj);
		} catch (Exception e) {
			logger.error("search TaxRate Error", e);
		}
		return taxRateObjC;

	}

	public String deleteTaxRate(TaxRateObjC taxRateObjC) {
		TaxRate taxRateEntity = null;
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start delete TaxRate .........");
			for (int d = 0; d < taxRateObjC.getListTaxRateObj().size(); d++) {
				taxRateEntity = taxRateRepository.searchDataById(taxRateObjC.getListTaxRateObj().get(d).getTaxRateId());
				taxRateEntity.setStatus(Status.inactive);
				taxRateEntity = taxRateCustomRepository.updateEntity(taxRateEntity);
			}
			logger.info("End delete TaxRate .........");
		} catch (Exception e) {
			logger.error("delete TaxRate Error", e);
		}
		return result;
	}

	public String checkDup(TaxRateObj taxRateObj,String action) {
		List<TaxRate> listTaxRateEntity = new ArrayList<TaxRate>();
		String result = ApplicationConstant.DUPLICATE;

		try {
			if(ApplicationConstant.ADD.equals(action)){
				listTaxRateEntity = taxRateRepository.checkDupDataByNameAndStatus(taxRateObj.getName());
				if (listTaxRateEntity.size() == 0) {
					result = ApplicationConstant.SUCCESS;
				}
			}else if(ApplicationConstant.EDIT.equals(action)){
				listTaxRateEntity = taxRateRepository.checkDupDataByNameAndStatus(taxRateObj.getName());
				if (listTaxRateEntity.size() > 0) {
					if(taxRateObj.getTaxRateId().equals(listTaxRateEntity.get(0).getTaxRateId())){
						result = ApplicationConstant.SUCCESS;
					}
				}else{
						result = ApplicationConstant.SUCCESS;
				}
			}
			
		} catch (Exception e) {
			logger.error("TaxRate checkDup error", e);
		}
		return result;
	}

}
