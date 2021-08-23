package com.thailife.tax.object;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


public class SecuityUserObj  implements HttpSessionBindingListener{

	private UserObj user	= null;
	
	private String name 		= "";
	private String sessionId 	= "";
	private String token       	= "";
	private String language     = "";
	private String captCha      = "";
	private String typeUser;
	private byte[] captchImage = null;
	private Boolean isLogin = false;
	// All logins.
    private static Map<SecuityUserObj, HttpSession> logins = new HashMap<SecuityUserObj, HttpSession>();

   

    @Override
    public boolean equals(Object other) {
    	
        return (other instanceof SecuityUserObj) && (user != null && user.getUserName()!= null) ? user.getUserName().equals(((SecuityUserObj) other).user.getUserName()) : (other == this);
    }

    @Override
    public int hashCode() {
    	
        return (user != null && user.getUserName() != null) ? (this.getClass().hashCode() +user.getUserName().hashCode()) : super.hashCode();
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
    	
    	
    	
    	
        HttpSession session = logins.remove(event.getValue());
        
        if (session != null) {
        	
            session.invalidate();
        }
        
        
        
        
        logins.put(this, event.getSession());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
    	
        logins.remove(this);
        
    }

	
	
	
	public String getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}

	public UserObj getUser() {
		return user;
	}
	public void setUser(UserObj user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getCaptCha() {
		return captCha;
	}
	public void setCaptCha(String captCha) {
		this.captCha = captCha;
	}

	public byte[] getCaptchImage() {
		return captchImage;
	}

	public void setCaptchImage(byte[] captchImage) {
		this.captchImage = captchImage;
	}

	public Boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}
	
}
