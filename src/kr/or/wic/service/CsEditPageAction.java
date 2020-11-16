package kr.or.wic.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.CustomerServiceDAO;
import kr.or.wic.dto.CustomerServiceDTO;

public class CsEditPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int cs_num = Integer.parseInt(request.getParameter("cs_num"));
		String sessionId = (String) request.getSession().getAttribute("id");		
		System.out.println(sessionId);
		
		CustomerServiceDAO dao = new CustomerServiceDAO();
		CustomerServiceDTO dto = dao.csDetailPage(cs_num);
		System.out.println("dao 실행 완료");
		
		request.setAttribute("dto", dto);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("sessionId", sessionId);
		
		ActionForward forward = new ActionForward();
		forward.setPath("CsEditPage.jsp");
		System.out.println("CsEditPageAction 실행 완료");
		return forward;
	}
}
