package com.thailife.tax.controller;

import com.thailife.tax.object.RoleObj;
import com.thailife.tax.object.UserRoleObj;
import com.thailife.tax.object.criteria.GroupMenuObjC;
import com.thailife.tax.object.criteria.UserObjC;
import com.thailife.tax.service.LoginService;
import com.thailife.tax.service.UserRoleService;
import com.thailife.tax.service.UserService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "/**", allowedHeaders = "/**")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@GetMapping(value = "/menuPermission", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<GroupMenuObjC> menuPermission(UserObjC user) {
		String result = "";
		GroupMenuObjC groupMenuObjC = null;
		logger.info("user id is ....."+user.getId());
		try {
			groupMenuObjC = loginService.listMenu(user.getId());
		} catch (Exception e) {
			result = "Error";
		}
		return new ResponseEntity<>(groupMenuObjC, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/listUser", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserObjC> listUser() {
		UserObjC userObjC = null;
		logger.error("listUser Start .....");
		try {
			userObjC = userService.searchDataAll();
			System.out.println("listUser"+userObjC.getListUserObj().size());
		} catch (Exception e) {
			logger.error("listUser Error ...",e);
		}
		return new ResponseEntity<>(userObjC, HttpStatus.OK);
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
