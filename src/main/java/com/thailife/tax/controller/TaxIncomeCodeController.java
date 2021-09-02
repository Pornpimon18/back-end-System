package com.thailife.tax.controller;

import com.thailife.tax.object.RoleObj;
import com.thailife.tax.object.TaxIncomeCodeObj;
import com.thailife.tax.object.criteria.RoleObjC;
import com.thailife.tax.object.criteria.TaxIncomeCodeObjC;
import com.thailife.tax.service.RoleService;
import com.thailife.tax.service.TaxIncomeCodeService;

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
@RequestMapping("/taxIncomeCode")
public class TaxIncomeCodeController {

	@Autowired
	private TaxIncomeCodeService taxIncomeCodeService;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PostMapping(value = "/addTaxIncomeCode", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addTaxIncomeCode(@RequestBody TaxIncomeCodeObj taxIncomeCodeObj) {
		String result = "fail";
		logger.info("name is ....."+taxIncomeCodeObj.getName());
		try {
			result = taxIncomeCodeService.addTaxIncomeCode(taxIncomeCodeObj);
		} catch (Exception e) {
			result = "Add TaxIncomeCode Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/editTaxIncomeCode", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> editTaxIncomeCode(@RequestBody TaxIncomeCodeObj taxIncomeCodeObj) {
		String result = "fail";
		logger.info("name is ....."+taxIncomeCodeObj.getName());
		try {
			result =taxIncomeCodeService.updateTaxIncomeCode(taxIncomeCodeObj);
		} catch (Exception e) {
			result = "Update TaxIncomeCode Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listTaxIncomeCode", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxIncomeCodeObjC> listTaxIncomeCode() {
		TaxIncomeCodeObjC taxIncomeCodeObjC = null;
		logger.info("list TaxIncomeCode Start .....");
		try {
			taxIncomeCodeObjC = taxIncomeCodeService.searchDataAll();
			
		} catch (Exception e) {
			logger.error("list TaxIncomeCode Error ...",e);
		}
		return new ResponseEntity<>(taxIncomeCodeObjC, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findById", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxIncomeCodeObj> findById(@RequestParam("id") String id) {
		logger.info("findById find by id TaxIncomeCode Start .....");
		TaxIncomeCodeObj taxIncomeCodeObj = new TaxIncomeCodeObj();
		try {
			taxIncomeCodeObj.setId(id);
			taxIncomeCodeObj = taxIncomeCodeService.findById(taxIncomeCodeObj);
			
		} catch (Exception e) {
			logger.error("findById find by id TaxIncomeCode Error ...",e);
		}
		return new ResponseEntity<>(taxIncomeCodeObj, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchTaxIncomeCode", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxIncomeCodeObjC> searchTaxIncomeCode(@RequestBody TaxIncomeCodeObjC taxIncomeCodeObjC) {
		logger.info("search TaxIncomeCode find by id Start .....");
		try {
			taxIncomeCodeObjC = taxIncomeCodeService.searchTaxIncomeCode(taxIncomeCodeObjC);
			
		} catch (Exception e) {
			logger.error("searchRole find by id Error ...",e);
		}
		return new ResponseEntity<>(taxIncomeCodeObjC, HttpStatus.OK);
	}
	
	@PostMapping(value = "/deleteTaxIncomeCode", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteTaxIncomeCode(@RequestBody TaxIncomeCodeObjC taxIncomeCodeObjC) {
		logger.info("delete TaxIncomeCode find by id Start .....");
		String result = "Success";
		try {
			result = taxIncomeCodeService.deleteTaxIncomeCode(taxIncomeCodeObjC);
			
		} catch (Exception e) {
			result = "fail";
			logger.error("delete TaxIncomeCode find by id Error ...",e);
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
