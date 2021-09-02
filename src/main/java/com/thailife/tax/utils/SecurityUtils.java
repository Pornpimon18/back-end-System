package com.thailife.tax.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.thailife.tax.constant.ApplicationConstant;
import com.thailife.tax.entity.User;
import com.thailife.tax.object.SecuityUserObj;
import com.thailife.tax.object.UserObj;
import com.thailife.tax.repository.UserRepository;

@Component
public class SecurityUtils {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected static String userName;
	
	public static void setSecurityInfo(SecuityUserObj o)throws Exception{
		HttpSession session = getSession(true);
		
		o.setSessionId(session.getId());
		session.setAttribute(ApplicationConstant.SECURITY_TOKEN,o.getToken());
		session.setAttribute(ApplicationConstant.SECURITY_INFO,o);
		//
//		printAllSession(session,"setSecurityInfo");
	}
	
	public static void setUserName(String o)throws Exception{
		userName = o;
	}
	
	public static String getUserName()throws Exception{
					
			return userName;
	}
	
	
//	
	public static SecuityUserObj getSecurityInfo()throws Exception{
		HttpSession session = getSession(false);
		
//		printAllSession(session,"getSecurityInfo");
		
		if(session != null && session.getAttribute(ApplicationConstant.SECURITY_TOKEN) != null){
			String token = (String)session.getAttribute(ApplicationConstant.SECURITY_TOKEN);
					
			return (SecuityUserObj)session.getAttribute(ApplicationConstant.SECURITY_INFO+token);
		}else{
			return null;
		}
//		return (SecuityUserObj)session.getAttribute(ApplicationConstant.SECURITY_ONFO+session.getId());	
	}
	public static HttpSession getSession(boolean isCreate)throws Exception{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(isCreate);
	}
	public static void removeSession()throws Exception{
		
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		
		attr.getRequest().getSession(false).removeAttribute(ApplicationConstant.SECURITY_INFO+attr.getRequest().getSession(false).getAttribute(ApplicationConstant.SECURITY_TOKEN));
		attr.getRequest().getSession(false).removeAttribute(ApplicationConstant.SECURITY_AUTHERIZE);
		attr.getRequest().getSession(false).removeAttribute(ApplicationConstant.SECURITY_TOKEN);
//		attr.getRequest().getSession(false).invalidate();
	}
	public static void terminateSession()throws Exception{
		
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		
//		attr.getRequest().getSession(false).removeAttribute(ApplicationConstant.SECURITY_ONFO+attr.getRequest().getSession(false).getAttribute(ApplicationConstant.SECURITY_TOKEN));
		attr.getRequest().getSession(false).invalidate();
	}
	public static String getClientIpAddress() throws Exception{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			
		return attr.getRequest().getRemoteAddr();

	
	}
	public static void setAutherized(HashMap<String,String> map)throws Exception{
		HttpSession session = getSession(false);
		
		
		session.setAttribute(ApplicationConstant.SECURITY_AUTHERIZE,map);
	}
//	public static void printAllSession(HttpSession session,String method)throws Exception{
//		
//			if(session == null){
//				log.info("+++ method>>>>>>>> "+method+" ++++++++ session nulllllllllllllllllllllllll :");
//			}else{
//		
//				Enumeration e = (Enumeration) (session.getAttributeNames());
//
//		        while ( e.hasMoreElements())
//		        {
//		            Object tring;
//		            if((tring = e.nextElement())!=null)
//		            {
//		            	log.info("+++ method>>>>>>>> "+method+" ++++++++ session print key :"+tring.toString());
//		            }
//	
//		        }
//			}
//	}
	
//	public static boolean checkAutherized(HashMap<String,String> map,String url)throws Exception{
//
//		  String roleRigth1 = "findByID,list";
//		  String roleRigth2 = "add,update,delete,approve,reject";
//		  String[] urls = url.split("/");
//		  String key = urls[3];
//		  String method = urls[4];
////		  
//		  boolean isAuth = false;
//		  for (Map.Entry<String, String> entry : map.entrySet()){
//
//
////			  if(entry.getKey().contains(key)){
//			  if(entry.getKey().equals(key)){
//				  
//				  if(entry.getValue() != null  && entry.getValue().equals("1") && roleRigth2.contains(method)){
//					  return false;
//				  }else{
//					  return true;
//				  }
//			  }
//		              
//		    } 
//		  return isAuth;
//		
//	}
	
}
