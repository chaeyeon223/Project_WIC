package kr.or.wic.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.MemberDAO;
import kr.or.wic.dto.MemberDTO;

public class CsWritePageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		String sessionId="";
		int currentPage = 1;
		int pageSize = 10;
		ActionForward forward = new ActionForward();
		
		if(request.getSession().getAttribute("id") != null) {
			sessionId = (String)request.getSession().getAttribute("id");		
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));			
			}
			if(request.getParameter("pageSize") != null) {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));			
			}
			
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getMemberInfoForCs(sessionId);
			
			request.setAttribute("dto", dto);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("sessionId", sessionId);
			
			System.out.println("CsWritePageAction");
			
			forward.setPath("CsWritePage.jsp");
			System.out.println("csWritePageAction 실행 완료");
		}else {
			
			String url = "/csPage.cs?currentPage="+currentPage+"&pageSize="+pageSize;
			request.setAttribute("msg", "권한이 없습니다. 회원가입 후 이용해주세요.");
			request.setAttribute("url", url);
			forward.setPath("Redirect.jsp");
		}
		return forward;
	}

}
