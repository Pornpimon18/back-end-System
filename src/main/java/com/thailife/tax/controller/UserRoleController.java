package com.thailife.tax.controller;

import com.thailife.tax.object.UserRoleObj;
import com.thailife.tax.object.criteria.RoleObjC;
import com.thailife.tax.object.criteria.UserRoleObjC;
import com.thailife.tax.service.RoleService;
import com.thailife.tax.service.UserRoleService;

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
@RequestMapping("/userRole")
public class UserRoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserRoleService userRoleService;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PostMapping(value = "/addUserRole", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addUserRole(@RequestBody UserRoleObj userRoleObj) {
		String result = "fail";
		logger.info("role id is ....."+userRoleObj.getRoleId());
		try {
			userRoleService.addUserRole(userRoleObj);
			result ="Success";
		} catch (Exception e) {
			result = "Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/editUserRole", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> editRole(@RequestBody UserRoleObj userRoleObj) {
		String result = "fail";
		logger.info("role name is ....."+userRoleObj.getGroupId());
		try {
			userRoleService.updateUserRole(userRoleObj);
			result ="Success";
		} catch (Exception e) {
			result = "Update UserRole Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listUserRole", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRoleObjC> listUserRole() {
		UserRoleObjC userRoleObjC = null;
		logger.info("listRole Start .....");
		try {
			userRoleObjC = userRoleService.searchDataAll();
			
		} catch (Exception e) {
			logger.error("listRole Error ...",e);
		}
		return new ResponseEntity<>(userRoleObjC, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findById", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRoleObjC> findById(@RequestParam("groupId") String groupId) {
		UserRoleObjC roleObjC = null;
		logger.info("listRole find by id Start .....");
		UserRoleObj userRoleObj = new UserRoleObj();
		try {
			userRoleObj.setGroupId(groupId);
			roleObjC = userRoleService.findByGroupIdUserRole(userRoleObj);
			
		} catch (Exception e) {
			logger.error("listRole find by id Error ...",e);
		}
		return new ResponseEntity<>(roleObjC, HttpStatus.OK);
	}
	
	@PostMapping(value = "/deleteUserRole", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteUserRole(@RequestBody UserRoleObjC userRoleObjC) {
		logger.error("deleteUserRole find by id Start .....");
		String result = "Success";
		try {
			result = userRoleService.deleteUserRole(userRoleObjC);
			
		} catch (Exception e) {
			result = "fail";
			logger.error("deleteUserRole find by id Error ...",e);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchRoleByUser", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRoleObjC> searchRoleByUser(@RequestBody UserRoleObjC userRoleObjC) {
		logger.error("searchRoleByUser find by id Start .....");
		String result = "Success";
		try {
			userRoleObjC = userRoleService.searchByUserName(userRoleObjC);
			
		} catch (Exception e) {
			result = "fail";
			logger.error("searchRoleByUser find by id Error ...",e);
		}
		return new ResponseEntity<>(userRoleObjC, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchUserRole", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRoleObjC> searchRole(@RequestBody UserRoleObjC userRoleObjC) {
		logger.error("listRole find by id Start .....");
		try {
			userRoleObjC = userRoleService.searchUserRole(userRoleObjC);
			
		} catch (Exception e) {
			logger.error("listRole find by id Error ...",e);
		}
		return new ResponseEntity<>(userRoleObjC, HttpStatus.OK);
	}
	
//	@PostMapping(value = "/testLock", produces = { MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<ResponseReconcile> testLock(@RequestBody SimpleValidationReportObj jsonObj) {
//		String result = "Success";
//		long start;
//		long end;
//		NumberFormat formatter = new DecimalFormat("#0.00000");
//		OtherDataResponse responseS = null;
//		ResponseReconcile responseR = null;
//		try {
//			start = startTime();
//			logger.info("Simple Validate App Other start ........");
//			logger.info("Simple Validate monitorId is ....." + jsonObj.getMonitorId());
//			logger.info("Simple Validate Application Name is ....." + jsonObj.getApplicationName());
//			responseS = otherSystemService.insertDataProcessing(jsonObj);
//			logger.info("Simple Validate status ....." + responseS.getStatusCode());
//			ReconcileTransection jsonObjR = new ReconcileTransection();
//			jsonObjR.setApplicationName("billPayment");
//			jsonObjR.setReqDatetime("2020072100003");
//			jsonObjR.setMonitorID("12005181734010003500");
//			jsonObjR.setInputFilename("TEST_FORMAT_256_ROUND3.txt");
//			jsonObjR.setFiCode("000");
//			jsonObjR.setPaymentDate("200501");
//			jsonObjR.setSeq(8);
//			jsonObjR.setErrorCode("");
//			jsonObjR.setStatusCode("ST003");
//			jsonObjR.setTransactionDetailID("12007151215038071100");
//			logger.info("Reconcile App Other start ........");
//			logger.info("Reconcile Application Name is :" + jsonObjR.getApplicationName());
//			logger.info("Reconcile monitorId is :" + jsonObjR.getMonitorID());
//			logger.info("Reconcile FiCode is :" + jsonObjR.getFiCode());
//			logger.info("Reconcile InputFileName is :" + jsonObjR.getInputFilename());
//			logger.info("Reconcile PaymentDate is :" + jsonObjR.getPaymentDate());
//			logger.info("Reconcile Seq is :" + jsonObjR.getSeq());
//			logger.info("Reconcile StatusCode is :" + jsonObjR.getStatusCode());
//			logger.info("Reconcile TransactionDetailId is :" + jsonObjR.getTransactionDetailID());
//			
//			responseR = otherSystemService.updateStateReconcileFormat256(jsonObjR);
//			
//			end = endTime();
//			logger.info("++++++++++++++++++++ reconcileOtherSystem : Execution time is : "
//					+ formatter.format((end - start) / 1000d) + " seconds");
//			formatter = new DecimalFormat("#0.00000");
//			logger.info("result simpleValidateAppOther .........." + responseR.getStatusCode());
//		} catch (Exception e) {
//			logger.error("UpdateProcessNid error .....", e);
//			result = "Fail";
//		}
//		return new ResponseEntity<>(responseR, HttpStatus.OK);
//	}

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
