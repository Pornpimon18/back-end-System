package com.thailife.tax.controller;

import com.thailife.tax.object.TaxSystemInfoObj;
import com.thailife.tax.object.criteria.TaxSystemInfoObjC;
import com.thailife.tax.service.TaxSystemInfoService;

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
@RequestMapping("/taxSystemInfo")
public class TaxSystemInfoController {

	@Autowired
	private TaxSystemInfoService taxSystemInfoService;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PostMapping(value = "/addTaxSystemInfo", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addTaxSystemInfo(@RequestBody TaxSystemInfoObj taxSystemInfoObj) {
		String result = "fail";
		logger.info("name is ....."+taxSystemInfoObj.getName());
		try {
			result = taxSystemInfoService.addTaxSystemInfo(taxSystemInfoObj);
		} catch (Exception e) {
			result = "Add TaxSystemInfo Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/editTaxSystemInfo", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> editTaxSystemInfo(@RequestBody TaxSystemInfoObj taxSystemInfoObj) {
		String result = "fail";
		logger.info("name is ....."+taxSystemInfoObj.getName());
		try {
			result =taxSystemInfoService.updateTaxSystemInfo(taxSystemInfoObj);
		} catch (Exception e) {
			result = "Update TaxSystemInfo Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listTaxSystemInfo", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxSystemInfoObjC> listTaxSystemInfo() {
		TaxSystemInfoObjC taxSystemInfoObjC = null;
		logger.info("list TaxSystemInfo Start .....");
		try {
			taxSystemInfoObjC = taxSystemInfoService.searchDataAll();
			
		} catch (Exception e) {
			logger.error("list TaxSystemInfo Error ...",e);
		}
		return new ResponseEntity<>(taxSystemInfoObjC, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findById", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxSystemInfoObj> findById(@RequestParam("systemId") String systemId) {
		logger.info("findById find by id TaxSystemInfo Start .....");
		TaxSystemInfoObj taxSystemInfoObj = new TaxSystemInfoObj();
		try {
			taxSystemInfoObj.setSystemId(systemId);
			taxSystemInfoObj = taxSystemInfoService.findById(taxSystemInfoObj);
			
		} catch (Exception e) {
			logger.error("findById find by id TaxSystemInfo Error ...",e);
		}
		return new ResponseEntity<>(taxSystemInfoObj, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchTaxSystemInfo", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxSystemInfoObjC> searchTaxSystemInfo(@RequestBody TaxSystemInfoObjC taxSystemInfoObjC) {
		logger.info("search TaxSystemInfo find by id Start .....");
		try {
			taxSystemInfoObjC = taxSystemInfoService.searchTaxSystemInfo(taxSystemInfoObjC);
			
		} catch (Exception e) {
			logger.error("searchRole find by id Error ...",e);
		}
		return new ResponseEntity<>(taxSystemInfoObjC, HttpStatus.OK);
	}
	
	@PostMapping(value = "/deleteTaxSystemInfo", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteTaxSystemInfo(@RequestBody TaxSystemInfoObjC taxSystemInfoObjC) {
		logger.info("delete TaxSystemInfo find by id Start .....");
		String result = "Success";
		try {
			result = taxSystemInfoService.deleteTaxSystemInfo(taxSystemInfoObjC);
			
		} catch (Exception e) {
			result = "fail";
			logger.error("delete TaxSystemInfo find by id Error ...",e);
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
