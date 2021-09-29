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
import com.thailife.tax.entity.TaxOpcode;
import com.thailife.tax.object.TaxOpcodeObj;
import com.thailife.tax.object.criteria.TaxOpcodeObjC;
import com.thailife.tax.repository.TaxOpcodeRepository;
import com.thailife.tax.repository.custom.TaxOpcodeCustomRepository;
import com.thailife.tax.utils.IdGenerator;
import com.thailife.tax.utils.SecurityUtils;
import com.thailife.tax.utils.Status;

@Service
public class TaxOpcodeService extends ServiceBase {

	@Autowired
	private TaxOpcodeRepository taxOpcodeRepository;

	@Autowired
	private TaxOpcodeCustomRepository taxOpcodeCustomRepository;

	private EntityManager entityManager;

	@Autowired
	public TaxOpcodeService(TaxOpcodeRepository taxOpcodeRepository) {
		this.taxOpcodeRepository = taxOpcodeRepository;
	}

	public TaxOpcodeObjC searchDataAll() {
		ModelMapper modelMapper = new ModelMapper();
		TaxOpcodeObjC taxOpcodeObjC = new TaxOpcodeObjC();
		List<TaxOpcodeObj> listTaxOpcodeObj = new ArrayList<>();
		List<TaxOpcode> listTaxOpcodeEntity = taxOpcodeRepository.searchDataAll();
		for (int i = 0; i < listTaxOpcodeEntity.size(); i++) {
			TaxOpcodeObj taxOpcodeObj = modelMapper.map(listTaxOpcodeEntity.get(i), TaxOpcodeObj.class);
			listTaxOpcodeObj.add(taxOpcodeObj);
		}
		taxOpcodeObjC.setListTaxOpcodeObj(listTaxOpcodeObj);
		return taxOpcodeObjC;
	}

	public String addTaxOpcode(TaxOpcodeObj taxOpcodeObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start add TaxOpcode .........");
			ModelMapper modelMapper = new ModelMapper();
			result = checkDup(taxOpcodeObj,ApplicationConstant.ADD);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				TaxOpcode taxOpcodeEntity = modelMapper.map(taxOpcodeObj, TaxOpcode.class);
				taxOpcodeEntity.setCreateUser(SecurityUtils.getUserName());
				taxOpcodeEntity.setCreateTime(new Date());
				taxOpcodeEntity.setStatus(Status.active);
				taxOpcodeEntity = taxOpcodeCustomRepository.saveEntity(taxOpcodeEntity);
			}
			logger.info("End add TaxOpcode .........");
		} catch (Exception e) {
			logger.error("add TaxOpcode Error", e);
		}
		return result;
	}

	public String updateTaxOpcode(TaxOpcodeObj taxOpcodeObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start update TaxOpcode .........");

			result = checkDup(taxOpcodeObj,ApplicationConstant.EDIT);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				ModelMapper modelMapper = new ModelMapper();
				TaxOpcode taxOpcodeEntity = modelMapper.map(taxOpcodeObj, TaxOpcode.class);
				taxOpcodeEntity.setUpdateUser(SecurityUtils.getUserName());
				taxOpcodeEntity.setUpdateTime(new Date());
				taxOpcodeEntity = taxOpcodeCustomRepository.updateEntity(taxOpcodeEntity);

			}
			logger.info("End update TaxOpcode .........");
		} catch (Exception e) {
			logger.error("update TaxOpcode Error", e);
		}
		return result;
	}

	public TaxOpcodeObj findById(TaxOpcodeObj taxOpcodeObj) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			TaxOpcode taxOpcodeEntity = new TaxOpcode();
			taxOpcodeEntity = taxOpcodeRepository.searchDataById(taxOpcodeObj.getOpcode());
			if(taxOpcodeEntity.getOpcode() !=null){
				taxOpcodeObj = modelMapper.map(taxOpcodeEntity, TaxOpcodeObj.class);
			}
		} catch (Exception e) {
			logger.error("findById TaxOpcode Error", e);
		}
		return taxOpcodeObj;

	}

	public TaxOpcodeObjC searchTaxOpcode(TaxOpcodeObjC taxOpcodeObjC) {
		ModelMapper modelMapper = new ModelMapper();
		List<TaxOpcode> listTaxOpcodeEntity = new ArrayList<TaxOpcode>();
		List<TaxOpcodeObj> listTaxOpcodeObj = new ArrayList<>();
		String status = "";
		try {
			if(("all").equals(taxOpcodeObjC.getStatus().name())){

				listTaxOpcodeEntity = taxOpcodeRepository.searchDataByNameAndStatusAll(taxOpcodeObjC.getName());
			}else{

				listTaxOpcodeEntity = taxOpcodeRepository.searchDataByNameAndStatus(taxOpcodeObjC.getName(), taxOpcodeObjC.getStatus());
			}
			
			for (int i = 0; i < listTaxOpcodeEntity.size(); i++) {
				TaxOpcodeObj taxOpcodeObj = modelMapper.map(listTaxOpcodeEntity.get(i), TaxOpcodeObj.class);
				listTaxOpcodeObj.add(taxOpcodeObj);
			}
			taxOpcodeObjC.setListTaxOpcodeObj(listTaxOpcodeObj);
		} catch (Exception e) {
			logger.error("search TaxOpcode Error", e);
		}
		return taxOpcodeObjC;

	}

	public String deleteTaxOpcode(TaxOpcodeObjC taxOpcodeObjC) {
		TaxOpcode taxOpcodeEntity = null;
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start delete TaxOpcode .........");
			for (int d = 0; d < taxOpcodeObjC.getListTaxOpcodeObj().size(); d++) {
				taxOpcodeEntity = taxOpcodeRepository.searchDataById(taxOpcodeObjC.getListTaxOpcodeObj().get(d).getOpcode());
				taxOpcodeEntity.setStatus(Status.inactive);
				taxOpcodeEntity = taxOpcodeCustomRepository.updateEntity(taxOpcodeEntity);
			}

			logger.info("End delete TaxOpcode .........");
		} catch (Exception e) {
			logger.error("delete TaxOpcode Error", e);
		}
		return result;
	}

	public String checkDup(TaxOpcodeObj taxOpcodeObj,String action) {
		List<TaxOpcode> listTaxOpcodeEntity = new ArrayList<TaxOpcode>();
		String result = ApplicationConstant.DUPLICATE;

		try {
			if(ApplicationConstant.ADD.equals(action)){
				listTaxOpcodeEntity = taxOpcodeRepository.checkDupDataByNameAndStatus(taxOpcodeObj.getName());
				if (listTaxOpcodeEntity.size() == 0) {
					result = ApplicationConstant.SUCCESS;
				}
			}else if(ApplicationConstant.EDIT.equals(action)){
				listTaxOpcodeEntity = taxOpcodeRepository.checkDupDataByNameAndStatus(taxOpcodeObj.getName());
				if (listTaxOpcodeEntity.size() > 0) {
					if(taxOpcodeObj.getOpcode().equals(listTaxOpcodeEntity.get(0).getOpcode())){
						result = ApplicationConstant.SUCCESS;
					}
				}else{
						result = ApplicationConstant.SUCCESS;
				}
			}
			
		} catch (Exception e) {
			logger.error("TaxOpcode checkDup error", e);
		}
		return result;
	}

}
