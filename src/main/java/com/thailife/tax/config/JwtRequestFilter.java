package com.thailife.tax.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import com.thailife.tax.object.SecuityUserObj;
import com.thailife.tax.object.UserObj;
import com.thailife.tax.service.MyUserDetailService;
import com.thailife.tax.utils.JwtUtil;
import com.thailife.tax.utils.SecurityUtils;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private MyUserDetailService myUserDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
		final String authorizationHeader = request.getHeader("Authorization");
		String userName = null;
		String jwt = null;
		 response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");		
			response.setHeader("Content-Security-Policy", "default-src 'self'; frame-ancestors 'none'; reflected-xss block");
			response.setHeader("X-XSS-Protection", "1; mode=block"); // on
			response.setHeader("X-Frame-Options", "DENY"); // DENY, SAMEOR
			response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // HTTP 1.1.
			response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); // ****url by env server***** 
//			response.setHeader("Access-Control-Allow-Origin", "https://dev-smwa.thailife.com"); 
	        response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "*");
			response.setHeader("Access-Control-Max-Age", "3600");
	       response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
			jwt = authorizationHeader.substring(7);
			userName = jwtUtil.extractUserName(jwt);
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
			UserDetails userDetails = this.myUserDetailService.loadUserByUsername(userName);
			if(jwtUtil.validateToken(jwt, userName)){
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
						usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					SecurityUtils.setUserName(userName);
			}
		}
		 if ("OPTIONS".equals(request.getMethod())) {
	            response.setStatus(HttpServletResponse.SC_OK);
	        } else { 
	            filterChain.doFilter(request, response);
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
