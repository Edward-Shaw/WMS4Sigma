package com.sigma.common.http;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.Cookies;

/**
 * 需要使用CAP-SSO的站继承并配置CapSSOFilter,该过滤器会自动进行SSO token检测
 * @author XuGang
 *
 */
public abstract class CapSSOFilter implements Filter{

	protected abstract String getSSOLoginURL();
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest hsr = (HttpServletRequest) request;
		Cookie[] cookies = hsr.getCookies();
		
		boolean found = false;
		for(Cookie c : cookies){
			if(c.getName().equals("com.cloume.sso.token")){
				found = true;
				break;
			}
		}
		
		if(!found){
			HttpServletResponse hsr2 = (HttpServletResponse) response;
			hsr2.sendRedirect(getSSOLoginURL());
		}
		
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterChain) throws ServletException {
	}

}
