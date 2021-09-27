package com.thailife.tax.controller;

import com.thailife.tax.object.RoleObj;
import com.thailife.tax.object.TaxDeductGroupDetailObj;
import com.thailife.tax.object.criteria.RoleObjC;
import com.thailife.tax.object.criteria.TaxDeductGroupDetailObjC;
import com.thailife.tax.service.RoleService;
import com.thailife.tax.service.TaxDeductGroupDetailService;

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
@RequestMapping("/taxDeductGroupDetail")
public class TaxDeductGroupDetailController {

	@Autowired
	private TaxDeductGroupDetailService taxDeductGroupDetailService;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PostMapping(value = "/addTaxDeductGroupDetail", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addTaxDeductGroupDetail(@RequestBody TaxDeductGroupDetailObjC taxDeductGroupDetailObjC) {
		String result = "fail";
		logger.info("name is ....."+taxDeductGroupDetailObjC.getListTaxDeductGroupDetailObj().size());
		try {
			result = taxDeductGroupDetailService.addTaxDeductGroupDetail(taxDeductGroupDetailObjC);
		} catch (Exception e) {
			result = "Add TaxDeductGroupDetail Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/editTaxDeductGroupDetail", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> editTaxDeductGroupDetail(@RequestBody TaxDeductGroupDetailObj taxDeductGroupDetailObj) {
		String result = "fail";
		logger.info("name is ....."+taxDeductGroupDetailObj.getYear());
		try {
			result =taxDeductGroupDetailService.updateTaxDeductGroupDetail(taxDeductGroupDetailObj);
		} catch (Exception e) {
			result = "Update TaxDeductGroupDetail Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/findById", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxDeductGroupDetailObjC> findById(@RequestBody TaxDeductGroupDetailObjC taxDeductGroupDetailObjC) {
		logger.info("findById find by id TaxDeductGroupDetail Start .....");
		try {
			taxDeductGroupDetailObjC.setYear(taxDeductGroupDetailObjC.getYear());
			taxDeductGroupDetailObjC.setDeductGroupId(taxDeductGroupDetailObjC.getDeductGroupId());
			taxDeductGroupDetailObjC = taxDeductGroupDetailService.findById(taxDeductGroupDetailObjC);
			
		} catch (Exception e) {
			logger.error("findById find by id TaxDeductGroupDetail Error ...",e);
		}
		return new ResponseEntity<>(taxDeductGroupDetailObjC, HttpStatus.OK);
	}
	
	@PostMapping(value = "/findDataMoreCurrentDate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> findDataMoreCurrentDate(@RequestBody TaxDeductGroupDetailObjC taxDeductGroupDetailObjC) {
		logger.info("findById find by id TaxDeductGroupDetail Start .....");
		String result = "fail";
		try {
			result = taxDeductGroupDetailService.findDataMoreCurrentDate(taxDeductGroupDetailObjC);
			
		} catch (Exception e) {
			logger.error("findById find by id TaxDeductGroupDetail Error ...",e);
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
