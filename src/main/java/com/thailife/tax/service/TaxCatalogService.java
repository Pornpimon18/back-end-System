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
import com.thailife.tax.entity.TaxCatalog;
import com.thailife.tax.object.TaxCatalogObj;
import com.thailife.tax.object.criteria.TaxCatalogObjC;
import com.thailife.tax.repository.TaxCatalogRepository;
import com.thailife.tax.repository.custom.TaxCatalogCustomRepository;
import com.thailife.tax.utils.IdGenerator;
import com.thailife.tax.utils.SecurityUtils;
import com.thailife.tax.utils.Status;

@Service
public class TaxCatalogService extends ServiceBase {

	@Autowired
	private TaxCatalogRepository taxCatalogRepository;

	@Autowired
	private TaxCatalogCustomRepository taxCatalogCustomRepository;

	private EntityManager entityManager;

	@Autowired
	public TaxCatalogService(TaxCatalogRepository taxCatalogRepository) {
		this.taxCatalogRepository = taxCatalogRepository;
	}

	public TaxCatalogObjC searchDataAll() {
		ModelMapper modelMapper = new ModelMapper();
		TaxCatalogObjC taxCatalogObjC = new TaxCatalogObjC();
		List<TaxCatalogObj> listTaxCatalogObj = new ArrayList<>();
		List<TaxCatalog> listTaxCatalogEntity = taxCatalogRepository.searchDataAll();
		for (int i = 0; i < listTaxCatalogEntity.size(); i++) {
			TaxCatalogObj taxCatalogObj = modelMapper.map(listTaxCatalogEntity.get(i), TaxCatalogObj.class);
			listTaxCatalogObj.add(taxCatalogObj);
		}
		taxCatalogObjC.setListTaxCatalogObj(listTaxCatalogObj);
		return taxCatalogObjC;
	}

	public String addTaxCatalog(TaxCatalogObj taxCatalogObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start add TaxCatalog .........");
			ModelMapper modelMapper = new ModelMapper();
			result = checkDup(taxCatalogObj,ApplicationConstant.ADD);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				taxCatalogObj.setTaxCatalogId(IdGenerator.getId());
				TaxCatalog taxCatalogEntity = modelMapper.map(taxCatalogObj, TaxCatalog.class);
				taxCatalogEntity.setUpdateUser(SecurityUtils.getUserName());
				taxCatalogEntity.setUpdateTime(new Date());
				taxCatalogEntity.setStatus(Status.active);
				taxCatalogEntity = taxCatalogCustomRepository.saveEntity(taxCatalogEntity);
			}
			logger.info("End add TaxCatalog .........");
		} catch (Exception e) {
			logger.error("add TaxCatalog Error", e);
		}
		return result;
	}

	public String updateTaxCatalog(TaxCatalogObj taxCatalogObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start update TaxCatalog .........");

			result = checkDup(taxCatalogObj,ApplicationConstant.EDIT);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				ModelMapper modelMapper = new ModelMapper();
				TaxCatalog taxCatalogEntity = modelMapper.map(taxCatalogObj, TaxCatalog.class);
				taxCatalogEntity.setUpdateUser(SecurityUtils.getUserName());
				taxCatalogEntity.setUpdateTime(new Date());
				taxCatalogEntity = taxCatalogCustomRepository.updateEntity(taxCatalogEntity);

			}
			logger.info("End update TaxCatalog .........");
		} catch (Exception e) {
			logger.error("update TaxCatalog Error", e);
		}
		return result;
	}

	public TaxCatalogObj findById(TaxCatalogObj taxCatalogObj) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			TaxCatalog taxCatalogEntity = new TaxCatalog();
			taxCatalogEntity = taxCatalogRepository.searchDataById(taxCatalogObj.getTaxCatalogId());
			if(taxCatalogEntity.getTaxCatalogId() !=null){
				taxCatalogObj = modelMapper.map(taxCatalogEntity, TaxCatalogObj.class);
			}
		} catch (Exception e) {
			logger.error("findById TaxCatalog Error", e);
		}
		return taxCatalogObj;

	}

	public TaxCatalogObjC searchTaxCatalog(TaxCatalogObjC taxCatalogObjC) {
		ModelMapper modelMapper = new ModelMapper();
		List<TaxCatalog> listTaxCatalogEntity = new ArrayList<TaxCatalog>();
		List<TaxCatalogObj> listTaxCatalogObj = new ArrayList<>();
		String status = "";
		try {
			if(("all").equals(taxCatalogObjC.getStatus())){
				listTaxCatalogEntity = taxCatalogRepository.searchDataByNameAndStatusAll(taxCatalogObjC.getName());
			}else{
				listTaxCatalogEntity = taxCatalogRepository.searchDataByNameAndStatus(taxCatalogObjC.getName(), taxCatalogObjC.getStatus());
			}
			for (int i = 0; i < listTaxCatalogEntity.size(); i++) {
				TaxCatalogObj taxCatalogObj = modelMapper.map(listTaxCatalogEntity.get(i), TaxCatalogObj.class);
				listTaxCatalogObj.add(taxCatalogObj);
			}
			taxCatalogObjC.setListTaxCatalogObj(listTaxCatalogObj);
		} catch (Exception e) {
			logger.error("search TaxCatalog Error", e);
		}
		return taxCatalogObjC;

	}

	public String deleteTaxCatalog(TaxCatalogObjC taxCatalogObjC) {
		TaxCatalog taxCatalogEntity = null;
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start delete TaxCatalog .........");
			for (int d = 0; d < taxCatalogObjC.getListTaxCatalogObj().size(); d++) {
				taxCatalogEntity = taxCatalogRepository.searchDataById(taxCatalogObjC.getListTaxCatalogObj().get(d).getTaxCatalogId());
				taxCatalogEntity.setStatus(Status.inactive);
				taxCatalogEntity = taxCatalogCustomRepository.updateEntity(taxCatalogEntity);
			}
			logger.info("End delete TaxCatalog .........");
		} catch (Exception e) {
			logger.error("delete TaxCatalog Error", e);
		}
		return result;
	}

	public String checkDup(TaxCatalogObj taxCatalogObj,String action) {
		List<TaxCatalog> listTaxCatalogEntity = new ArrayList<TaxCatalog>();
		String result = ApplicationConstant.DUPLICATE;

		try {
			if(ApplicationConstant.ADD.equals(action)){
				listTaxCatalogEntity = taxCatalogRepository.checkDupDataByNameAndStatus(taxCatalogObj.getName());
				if (listTaxCatalogEntity.size() == 0) {
					result = ApplicationConstant.SUCCESS;
				}
			}else if(ApplicationConstant.EDIT.equals(action)){
				listTaxCatalogEntity = taxCatalogRepository.checkDupDataByNameAndStatus(taxCatalogObj.getName());
				if (listTaxCatalogEntity.size() > 0) {
					if(taxCatalogObj.getTaxCatalogId().equals(listTaxCatalogEntity.get(0).getTaxCatalogId())){
						result = ApplicationConstant.SUCCESS;
					}
				}else{
						result = ApplicationConstant.SUCCESS;
				}
			}
			
		} catch (Exception e) {
			logger.error("TaxCatalog checkDup error", e);
		}
		return result;
	}

}
