package com.thailife.tax.controller;

import com.thailife.tax.object.RoleObj;
import com.thailife.tax.object.TaxDeductGroupObj;
import com.thailife.tax.object.criteria.RoleObjC;
import com.thailife.tax.object.criteria.TaxDeductGroupObjC;
import com.thailife.tax.service.RoleService;
import com.thailife.tax.service.TaxDeductGroupService;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "/**", allowedHeaders = "/**")
@RequestMapping("/taxDeductGroup")
public class TaxDeductGroupController {

	@Autowired
	private TaxDeductGroupService taxDeductGroupService;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PostMapping(value = "/addTaxDeductGroup", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addTaxDeductGroup(@RequestBody TaxDeductGroupObj taxDeductGroupObj) {
		String result = "fail";
		logger.info("name is ....."+taxDeductGroupObj.getName());
		try {
			result = taxDeductGroupService.addTaxDeductGroup(taxDeductGroupObj);
		} catch (Exception e) {
			result = "Add TaxDeductGroup Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/editTaxDeductGroup", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> editTaxDeductGroup(@RequestBody TaxDeductGroupObj taxDeductGroupObj) {
		String result = "fail";
		logger.info("name is ....."+taxDeductGroupObj.getName());
		try {
			result =taxDeductGroupService.updateTaxDeductGroup(taxDeductGroupObj);
		} catch (Exception e) {
			result = "Update TaxDeductGroup Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listTaxDeductGroup", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxDeductGroupObjC> listTaxDeductGroup() {
		TaxDeductGroupObjC taxDeductObjC = null;
		logger.info("list TaxDeductGroup Start .....");
		try {
			taxDeductObjC = taxDeductGroupService.searchDataAll();
			
		} catch (Exception e) {
			logger.error("list TaxDeductGroup Error ...",e);
		}
		return new ResponseEntity<>(taxDeductObjC, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findById", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxDeductGroupObj> findById(@RequestParam("deductGroupId") String deductGroupId) {
		logger.info("findById find by id TaxDeductGroup Start .....");
		TaxDeductGroupObj taxDeductObj = new TaxDeductGroupObj();
		try {
			taxDeductObj.setDeductGroupId(deductGroupId);
			taxDeductObj = taxDeductGroupService.findById(taxDeductObj);
			
		} catch (Exception e) {
			logger.error("findById find by id TaxDeductGroup Error ...",e);
		}
		return new ResponseEntity<>(taxDeductObj, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchTaxDeductGroup", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxDeductGroupObjC> searchTaxDeductGroup(@RequestBody TaxDeductGroupObjC taxDeductObjC) {
		logger.info("search TaxDeductGroup find by id Start .....");
		try {
			taxDeductObjC = taxDeductGroupService.searchTaxDeductGroup(taxDeductObjC);
			
		} catch (Exception e) {
			logger.error("searchRole find by id Error ...",e);
		}
		return new ResponseEntity<>(taxDeductObjC, HttpStatus.OK);
	}
	
	@PostMapping(value = "/deleteTaxDeductGroup", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteTaxDeductGroup(@RequestBody TaxDeductGroupObjC taxDeductObjC) {
		logger.info("delete TaxDeductGroup find by id Start .....");
		String result = "Success";
		try {
			result = taxDeductGroupService.deleteTaxDeductGroup(taxDeductObjC);
			
		} catch (Exception e) {
			result = "fail";
			logger.error("delete TaxDeductGroup find by id Error ...",e);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
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
