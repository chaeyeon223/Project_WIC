package kr.or.wic.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.MemberDAO;
import kr.or.wic.dto.MemberDTO;

/* 
@Project : WIC
@File name : MemberEditPageAction.java
@Date : 2020.11.13
@Author : 문지연
*/

public class MemberEditPageAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		
		String viewpage = "";
		ActionForward forward = new ActionForward();
		String adminId = (String)request.getSession().getAttribute("id");
		//System.out.println("adminId:"+adminId);
		if(adminId == null || !adminId.equals("admin@admin.com")) {
			viewpage = "Main.jsp";
			forward.setPath(viewpage);
			return forward;
		}
		
		//DAO, DTO 처리
		String id = request.getParameter("id");
		
		MemberDAO memberDao = new MemberDAO();
		MemberDTO memberDto = memberDao.getMemberById(id);
		
		request.setAttribute("memberDto", memberDto);
		
		//이동경로(viewpage)
		viewpage = "/MemberInfoEdit.jsp";
		forward.setPath(viewpage);
		
		return forward;
	}
	
}
