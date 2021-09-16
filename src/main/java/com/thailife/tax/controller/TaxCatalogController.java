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

import com.thailife.tax.object.TaxCatalogObj;
import com.thailife.tax.object.criteria.TaxCatalogObjC;
import com.thailife.tax.service.TaxCatalogService;

@RestController
@CrossOrigin(origins = "/**", allowedHeaders = "/**")
@RequestMapping("/taxCatalog")
public class TaxCatalogController {

	@Autowired
	private TaxCatalogService taxCatalogService;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PostMapping(value = "/addTaxCatalog", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addTaxCatalog(@RequestBody TaxCatalogObj taxCatalogObj) {
		String result = "fail";
		logger.info("name is ....."+taxCatalogObj.getName());
		try {
			result = taxCatalogService.addTaxCatalog(taxCatalogObj);
		} catch (Exception e) {
			result = "Add TaxCatalog Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/editTaxCatalog", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> editTaxCatalog(@RequestBody TaxCatalogObj taxCatalogObj) {
		String result = "fail";
		logger.info("name is ....."+taxCatalogObj.getName());
		try {
			result =taxCatalogService.updateTaxCatalog(taxCatalogObj);
		} catch (Exception e) {
			result = "Update TaxCatalog Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listTaxCatalog", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxCatalogObjC> listTaxCatalog() {
		TaxCatalogObjC taxCatalogObjC = null;
		logger.info("list TaxCatalog Start .....");
		try {
			taxCatalogObjC = taxCatalogService.searchDataAll();
			
		} catch (Exception e) {
			logger.error("list TaxCatalog Error ...",e);
		}
		return new ResponseEntity<>(taxCatalogObjC, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findById", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxCatalogObj> findById(@RequestParam("taxCatalogId") String taxCatalogId) {
		logger.info("findById find by id TaxCatalog Start .....");
		TaxCatalogObj taxCatalogObj = new TaxCatalogObj();
		try {
			taxCatalogObj.setTaxCatalogId(taxCatalogId);
			taxCatalogObj = taxCatalogService.findById(taxCatalogObj);
			
		} catch (Exception e) {
			logger.error("findById find by id TaxCatalog Error ...",e);
		}
		return new ResponseEntity<>(taxCatalogObj, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchTaxCatalog", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxCatalogObjC> searchTaxCatalog(@RequestBody TaxCatalogObjC taxCatalogObjC) {
		logger.info("search TaxCatalog find by id Start .....");
		try {
			taxCatalogObjC = taxCatalogService.searchTaxCatalog(taxCatalogObjC);
			
		} catch (Exception e) {
			logger.error("searchRole find by id Error ...",e);
		}
		return new ResponseEntity<>(taxCatalogObjC, HttpStatus.OK);
	}
	
	@PostMapping(value = "/deleteTaxCatalog", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteTaxCatalog(@RequestBody TaxCatalogObjC taxCatalogObjC) {
		logger.info("delete TaxCatalog find by id Start .....");
		String result = "Success";
		try {
			result = taxCatalogService.deleteTaxCatalog(taxCatalogObjC);
			
		} catch (Exception e) {
			result = "fail";
			logger.error("delete TaxCatalog find by id Error ...",e);
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
