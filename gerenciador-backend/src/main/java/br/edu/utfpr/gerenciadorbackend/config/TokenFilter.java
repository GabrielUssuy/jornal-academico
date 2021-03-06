package br.edu.utfpr.gerenciadorbackend.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.edu.utfpr.gerenciadorbackend.service.TokenService;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TokenFilter implements Filter {

	@Autowired private TokenService tokenService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		String token = req.getHeader("token");

		if(!tokenService.validarToken(token)) {
			resp.reset();
			resp.sendError(HttpStatus.UNAUTHORIZED.value());
		} else {
			chain.doFilter(request, response);
		}
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
