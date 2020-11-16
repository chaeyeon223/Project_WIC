package kr.or.wic.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.CustomerServiceDAO;
import kr.or.wic.dto.CustomerServiceDTO;

public class CsEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ACTION 진입");
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int cs_num = Integer.parseInt(request.getParameter("cs_num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int cs_notice = 2;
		if(request.getParameter("cs_notice") != null) {
			cs_notice =  Integer.parseInt(request.getParameter("cs_notice"));
		}
		int cs_secret = 2;
		if(request.getParameter("cs_secret") != null) {
			cs_secret =  Integer.parseInt(request.getParameter("cs_secret"));
		}
		
		System.out.println("dao 이전");
		CustomerServiceDAO dao = new CustomerServiceDAO();
		int result = dao.csEdit(cs_num, title, content, cs_notice, cs_secret);
		if(result != 0 ) {
			System.out.println("dao 실행 완료");			
		}
		String msg = "수정 성공!";
		String url = "/csDetailPage.cs?cs_num="+cs_num+"&currentPage="+currentPage+"&pageSize="+pageSize;
//		request.setAttribute("currentPage", currentPage);
//		request.setAttribute("pageSize", pageSize);
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		ActionForward forward = new ActionForward();
		forward.setPath("Redirect.jsp");
		System.out.println("CsDetailPageAction 실행 완료");
		return forward;
	}
}
