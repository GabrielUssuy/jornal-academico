package br.edu.utfpr.gerenciadorweb.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomFailureLoginHandler extends SimpleUrlAuthenticationFailureHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String URL_REDIRECT = "/login?error=LoginInvalid&user=";

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		try {
			String username = request.getParameter("login");
			response.sendRedirect(request.getContextPath() + URL_REDIRECT + username);
			logger.info("Problemas na autenticacao do usuario [" + username + "]");
			super.onAuthenticationFailure(request, response, exception);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
