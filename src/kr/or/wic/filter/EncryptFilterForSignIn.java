package kr.or.wic.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import kr.or.wic.service.EncryptWrapper;


/**
 * Servlet Filter implementation class EncryptFilter
 */
@WebFilter(urlPatterns = { 
		"/signIn.my",
		
})
public class EncryptFilterForSignIn implements Filter {

    /**
     * Default constructor. 
     */
    public EncryptFilterForSignIn() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		EncryptWrapper encW = new EncryptWrapper(req);
		System.out.println(encW.getParameter("pwd"));
		encW.getParameter("pwd");
		
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
