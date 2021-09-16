package com.thailife.tax.controller;


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

import com.thailife.tax.object.TaxRateObj;
import com.thailife.tax.object.criteria.TaxRateObjC;
import com.thailife.tax.service.TaxRateService;

@RestController
@CrossOrigin(origins = "/**", allowedHeaders = "/**")
@RequestMapping("/taxRate")
public class TaxRateController {

	@Autowired
	private TaxRateService taxRateService;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PostMapping(value = "/addTaxRate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addTaxRate(@RequestBody TaxRateObj taxRateObj) {
		String result = "fail";
		logger.info("name is ....."+taxRateObj.getName());
		try {
			result = taxRateService.addTaxRate(taxRateObj);
		} catch (Exception e) {
			result = "Add TaxRate Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/editTaxRate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> editTaxRate(@RequestBody TaxRateObj taxRateObj) {
		String result = "fail";
		logger.info("name is ....."+taxRateObj.getName());
		try {
			result =taxRateService.updateTaxRate(taxRateObj);
		} catch (Exception e) {
			result = "Update TaxRate Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listTaxRate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxRateObjC> listTaxRate() {
		TaxRateObjC taxRateObjC = null;
		logger.info("list TaxRate Start .....");
		try {
			taxRateObjC = taxRateService.searchDataAll();
			
		} catch (Exception e) {
			logger.error("list TaxRate Error ...",e);
		}
		return new ResponseEntity<>(taxRateObjC, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findById", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxRateObj> findById(@RequestParam("taxRateId") String taxRateId) {
		logger.info("findById find by id TaxRate Start .....");
		TaxRateObj taxRateObj = new TaxRateObj();
		try {
			taxRateObj.setTaxRateId(taxRateId);
			taxRateObj = taxRateService.findById(taxRateObj);
			
		} catch (Exception e) {
			logger.error("findById find by id TaxRate Error ...",e);
		}
		return new ResponseEntity<>(taxRateObj, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchTaxRate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxRateObjC> searchTaxRate(@RequestBody TaxRateObjC taxRateObjC) {
		logger.info("search TaxRate find by id Start .....");
		try {
			taxRateObjC = taxRateService.searchTaxRate(taxRateObjC);
			
		} catch (Exception e) {
			logger.error("searchRole find by id Error ...",e);
		}
		return new ResponseEntity<>(taxRateObjC, HttpStatus.OK);
	}
	
	@PostMapping(value = "/deleteTaxRate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteTaxRate(@RequestBody TaxRateObjC taxRateObjC) {
		logger.info("delete TaxRate find by id Start .....");
		String result = "Success";
		try {
			result = taxRateService.deleteTaxRate(taxRateObjC);
			
		} catch (Exception e) {
			result = "fail";
			logger.error("delete TaxRate find by id Error ...",e);
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
