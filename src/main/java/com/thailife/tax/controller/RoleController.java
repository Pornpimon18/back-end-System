package com.thailife.tax.controller;

import com.thailife.tax.object.RoleObj;
import com.thailife.tax.object.criteria.RoleObjC;
import com.thailife.tax.service.RoleService;

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
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PostMapping(value = "/addRole", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addRole(@RequestBody RoleObj roleObj) {
		String result = "fail";
		logger.info("role name is ....."+roleObj.getRoleName());
		try {
			result = roleService.addRole(roleObj);
		} catch (Exception e) {
			result = "Add Role Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/editRole", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> editRole(@RequestBody RoleObj roleObj) {
		String result = "fail";
		logger.info("role name is ....."+roleObj.getRoleName());
		try {
			result =roleService.updateRole(roleObj);
		} catch (Exception e) {
			result = "Update Role Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listRole", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<RoleObjC> listRole() {
		RoleObjC roleObjC = null;
		logger.error("listRole Start .....");
		try {
			roleObjC = roleService.searchDataAll();
			
		} catch (Exception e) {
			logger.error("listRole Error ...",e);
		}
		return new ResponseEntity<>(roleObjC, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findById", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<RoleObj> findById(@RequestParam("id") String id) {
		logger.error("findById find by id Start .....");
		RoleObj roleObj = new RoleObj();
		try {
			roleObj.setId(id);
			roleObj = roleService.findById(roleObj);
			
		} catch (Exception e) {
			logger.error("findById find by id Error ...",e);
		}
		return new ResponseEntity<>(roleObj, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchRole", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<RoleObjC> searchRole(@RequestBody RoleObjC roleObjC) {
		logger.error("searchRole find by id Start .....");
		try {
			roleObjC = roleService.searchRole(roleObjC);
			
		} catch (Exception e) {
			logger.error("searchRole find by id Error ...",e);
		}
		return new ResponseEntity<>(roleObjC, HttpStatus.OK);
	}
	
	@PostMapping(value = "/deleteRole", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteRole(@RequestBody RoleObjC roleObjC) {
		logger.error("deleteRole find by id Start .....");
		String result = "Success";
		try {
			result = roleService.deleteRole(roleObjC);
			
		} catch (Exception e) {
			result = "fail";
			logger.error("deleteRole find by id Error ...",e);
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
