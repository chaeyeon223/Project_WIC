package kr.or.wic.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.CustomerServiceDAO;
import kr.or.wic.dto.CustomerServiceDTO;

public class CsDetailPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String sessionId="";
		if(request.getSession().getAttribute("id") != null) {
			sessionId = (String)request.getSession().getAttribute("id");	
		}
		
		int cs_secret = Integer.parseInt(request.getParameter("cs_secret"));		//비밀여부
		int cs_num = Integer.parseInt(request.getParameter("cs_num"));				//글 번호
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));	
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		ActionForward forward = new ActionForward();
		
		
		CustomerServiceDAO dao = new CustomerServiceDAO();
		CustomerServiceDTO dto = dao.csDetailPage(cs_num);
		
		if(dto.getCs_secret() == 1) {
			System.out.println("여기?");
			if( !(sessionId.equals("admin@admin.com") || sessionId.equals(dto.getId())) ){
				request.setAttribute("msg", "비밀글입니다.");
				request.setAttribute("url", "/csPage.cs?currentPage="+currentPage+"&pageSize="+pageSize);
				forward.setPath("Redirect.jsp");
//				return forward;
			}
		}else {
			dao.csDetailCounting(cs_num);
			request.setAttribute("dto", dto);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("sessionId", sessionId);
			forward.setPath("CsDetailPage.jsp");			
		}		
		return forward;
	}	
}
//		System.out.println("dao 실행 완료");
//		boolean isGet = false;
//		Cookie[] cookies = request.getCookies();
//		if(cookies != null) {
//			
//		}
