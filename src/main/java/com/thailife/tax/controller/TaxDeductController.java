package com.thailife.tax.controller;

import com.thailife.tax.object.RoleObj;
import com.thailife.tax.object.TaxDeductObj;
import com.thailife.tax.object.criteria.RoleObjC;
import com.thailife.tax.object.criteria.TaxDeductObjC;
import com.thailife.tax.service.RoleService;
import com.thailife.tax.service.TaxDeductService;

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
@RequestMapping("/taxDeduct")
public class TaxDeductController {

	@Autowired
	private TaxDeductService taxDeductService;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PostMapping(value = "/addTaxDeduct", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addTaxDeduct(@RequestBody TaxDeductObj taxDeductObj) {
		String result = "fail";
		logger.info("name is ....."+taxDeductObj.getName());
		try {
			result = taxDeductService.addTaxDeduct(taxDeductObj);
		} catch (Exception e) {
			result = "Add TaxDeduct Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/editTaxDeduct", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> editTaxDeduct(@RequestBody TaxDeductObj taxDeductObj) {
		String result = "fail";
		logger.info("name is ....."+taxDeductObj.getName());
		try {
			result =taxDeductService.updateTaxDeduct(taxDeductObj);
		} catch (Exception e) {
			result = "Update TaxDeduct Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listTaxDeduct", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxDeductObjC> listTaxDeduct() {
		TaxDeductObjC taxDeductObjC = null;
		logger.info("list TaxDeduct Start .....");
		try {
			taxDeductObjC = taxDeductService.searchDataAll();
			
		} catch (Exception e) {
			logger.error("list TaxDeduct Error ...",e);
		}
		return new ResponseEntity<>(taxDeductObjC, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findById", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxDeductObj> findById(@RequestParam("taxDeductId") String taxDeductId) {
		logger.info("findById find by id TaxDeduct Start .....");
		TaxDeductObj taxDeductObj = new TaxDeductObj();
		try {
			taxDeductObj.setTaxDeductId(taxDeductId);
			taxDeductObj = taxDeductService.findById(taxDeductObj);
			
		} catch (Exception e) {
			logger.error("findById find by id TaxDeduct Error ...",e);
		}
		return new ResponseEntity<>(taxDeductObj, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchTaxDeduct", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxDeductObjC> searchTaxDeduct(@RequestBody TaxDeductObjC taxDeductObjC) {
		logger.info("search TaxDeduct find by id Start .....");
		try {
			taxDeductObjC = taxDeductService.searchTaxDeduct(taxDeductObjC);
			
		} catch (Exception e) {
			logger.error("searchRole find by id Error ...",e);
		}
		return new ResponseEntity<>(taxDeductObjC, HttpStatus.OK);
	}
	
	@PostMapping(value = "/deleteTaxDeduct", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteTaxDeduct(@RequestBody TaxDeductObjC taxDeductObjC) {
		logger.info("delete TaxDeduct find by id Start .....");
		String result = "Success";
		try {
			result = taxDeductService.deleteTaxDeduct(taxDeductObjC);
			
		} catch (Exception e) {
			result = "fail";
			logger.error("delete TaxDeduct find by id Error ...",e);
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
