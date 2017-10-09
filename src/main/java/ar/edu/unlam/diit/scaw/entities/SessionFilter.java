package ar.edu.unlam.diit.scaw.entities;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println("FILTER");

		// Cargamos la sesion
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);

	    String scaw = httpRequest.getContextPath() + "/";
	    String login = httpRequest.getContextPath() + "/faces/index.xhtml";
	    String uri = httpRequest.getRequestURI().toString();
	    
	    System.out.println(scaw);
	    System.out.println(uri);
		
		Object usuario = null;
		// Preguntamos por un objeto en la sesion
		if (session != null)
			usuario = session.getAttribute("logeado");

		if (session == null || usuario == null && !uri.endsWith(scaw) && !uri.endsWith(login)){
			httpResponse.sendRedirect(login);
			return;
		}else{
			chain.doFilter(request, response);
			return;
		}
		
	}

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	
}
