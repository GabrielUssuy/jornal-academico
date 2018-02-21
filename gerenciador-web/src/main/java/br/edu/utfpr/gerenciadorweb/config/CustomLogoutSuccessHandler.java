package br.edu.utfpr.gerenciadorweb.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String DEFAULT_TARGET_URL = "/login";
	private static final String DEFAULT_TARGET_PARAMETER = "?logout";
	private RedirectStrategy redirectStrategy;
	
	public CustomLogoutSuccessHandler() {
		super.setDefaultTargetUrl(DEFAULT_TARGET_URL);
		super.setTargetUrlParameter(DEFAULT_TARGET_PARAMETER);
		redirectStrategy = new DefaultRedirectStrategy();
		super.setRedirectStrategy(redirectStrategy);
	}
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String url = getDefaultTargetUrl() + getTargetUrlParameter();
		logger.info("Logout realizado com sucesso - Redicionando para " + request.getContextPath() + url);
		this.getRedirectStrategy().sendRedirect(request, response, url);
		super.onLogoutSuccess(request, response, authentication);
	}
}
