package kr.or.wic.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.CustomerServiceDAO;
import kr.or.wic.dto.CustomerServiceDTO;

public class CsReWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id"); 			//작성자의 아이디
		String title = request.getParameter("title");		//답글의 제목
		String content = request.getParameter("content");	//답글 내용
		int cs_secret = Integer.parseInt(request.getParameter("cs_secret")); //비밀여부
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		int cs_num = Integer.parseInt(request.getParameter("cs_num"));		//원글의 num
		int cs_refer = Integer.parseInt(request.getParameter("cs_refer"));	//원글의 refer
		int cs_depth = Integer.parseInt(request.getParameter("cs_depth"));	//원글의 depth
		int cs_step = Integer.parseInt(request.getParameter("cs_step"));	//원글의 step
		
		System.out.println(cs_num);
		CustomerServiceDAO dao = new CustomerServiceDAO();
		boolean result = dao.csRewrite(title, content, id, cs_num, cs_refer, cs_depth, cs_step, cs_secret);
		
		String msg ="";
		String url ="";
		
		if(result) {
			System.out.println("result true");
			msg = "답글 작성 완료";
			url = "/csPage.cs?currentPage="+currentPage+"&pageSize="+pageSize;
		}else {
			msg = "답글 작성 실패";
			url = "/csDetailPage.cs?cs_num="+cs_num+"&currentPage="+currentPage+"&pageSize="+pageSize;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		ActionForward forward = new ActionForward();
		forward.setPath("Redirect.jsp");
		System.out.println("CsReWriteAction 실행 완료");
		return forward;
	}
}
