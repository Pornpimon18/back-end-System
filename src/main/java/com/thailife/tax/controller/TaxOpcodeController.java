package com.thailife.tax.controller;

import com.thailife.tax.object.TaxOpcodeObj;
import com.thailife.tax.object.criteria.TaxOpcodeObjC;
import com.thailife.tax.service.TaxOpcodeService;

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
@RequestMapping("/taxOpcode")
public class TaxOpcodeController {

	@Autowired
	private TaxOpcodeService taxOpcodeService;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PostMapping(value = "/addTaxOpcode", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addTaxOpcode(@RequestBody TaxOpcodeObj taxOpcodeObj) {
		String result = "fail";
		logger.info("name is ....."+taxOpcodeObj.getName());
		try {
			result = taxOpcodeService.addTaxOpcode(taxOpcodeObj);
		} catch (Exception e) {
			result = "Add TaxOpcode Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/editTaxOpcode", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> editTaxOpcode(@RequestBody TaxOpcodeObj taxOpcodeObj) {
		String result = "fail";
		logger.info("name is ....."+taxOpcodeObj.getName());
		try {
			result =taxOpcodeService.updateTaxOpcode(taxOpcodeObj);
		} catch (Exception e) {
			result = "Update TaxOpcode Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listTaxOpcode", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxOpcodeObjC> listTaxOpcode() {
		TaxOpcodeObjC taxOpcodeObjC = null;
		logger.info("list TaxOpcode Start .....");
		try {
			taxOpcodeObjC = taxOpcodeService.searchDataAll();
			
		} catch (Exception e) {
			logger.error("list TaxOpcode Error ...",e);
		}
		return new ResponseEntity<>(taxOpcodeObjC, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findById", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxOpcodeObj> findById(@RequestParam("opcode") String opcode) {
		logger.info("findById find by id TaxOpcode Start .....");
		TaxOpcodeObj taxOpcodeObj = new TaxOpcodeObj();
		try {
			taxOpcodeObj.setOpcode(opcode);
			taxOpcodeObj = taxOpcodeService.findById(taxOpcodeObj);
			
		} catch (Exception e) {
			logger.error("findById find by id TaxOpcode Error ...",e);
		}
		return new ResponseEntity<>(taxOpcodeObj, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchTaxOpcode", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxOpcodeObjC> searchTaxOpcode(@RequestBody TaxOpcodeObjC taxOpcodeObjC) {
		logger.info("search TaxOpcode find by id Start .....");
		try {
			taxOpcodeObjC = taxOpcodeService.searchTaxOpcode(taxOpcodeObjC);
			
		} catch (Exception e) {
			logger.error("searchRole find by id Error ...",e);
		}
		return new ResponseEntity<>(taxOpcodeObjC, HttpStatus.OK);
	}
	
	@PostMapping(value = "/deleteTaxOpcode", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteTaxOpcode(@RequestBody TaxOpcodeObjC taxOpcodeObjC) {
		logger.info("delete TaxOpcode find by id Start .....");
		String result = "Success";
		try {
			result = taxOpcodeService.deleteTaxOpcode(taxOpcodeObjC);
			
		} catch (Exception e) {
			result = "fail";
			logger.error("delete TaxOpcode find by id Error ...",e);
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
