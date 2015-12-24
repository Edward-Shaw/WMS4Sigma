package com.sigma.filters;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AddSessionAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private RedirectStrategy rs = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {
		setSession(authentication, request);
		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect ");
			return;
		}
		String redirectUrl = "/welcome/cp";
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication
				.getAuthorities();
		GrantedAuthority adminAuthority = new GrantedAuthorityImpl("ROLE_ADMIN");
		if (authorities.contains(adminAuthority)) {
			redirectUrl = "/welcome";
		}
		rs.sendRedirect(request, response, redirectUrl);

	}

	/**
	 * Builds the target URL according to the logic defined in the main class
	 * 
	 */
	protected String setSession(Authentication authentication,
			HttpServletRequest request) {
		String username = authentication.getName();
		if (username.equalsIgnoreCase("HC")) {
			request.getSession().setAttribute("cpName", username);
		} else if (username.equalsIgnoreCase("RPX")) {
			request.getSession().setAttribute("cpName", username);
		} else if (username.equalsIgnoreCase("ADMIN")) {
			request.getSession().setAttribute("cpName", username);
		}

		return username;
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(RedirectStrategy rs) {
		this.rs = rs;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return rs;
	}

}
