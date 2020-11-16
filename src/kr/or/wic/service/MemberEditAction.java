package kr.or.wic.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.MemberDAO;
/* 
@Project : WIC
@File name : MemberEditAction.java
@Date : 2020.11.13
@Author : 문지연
*/
public class MemberEditAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		
		String viewpage = "";
		ActionForward forward = new ActionForward();
		String adminId = (String)request.getSession().getAttribute("id");

		if(adminId == null || !adminId.equals("admin@admin.com")) {
			viewpage = "Main.jsp";
			forward.setPath(viewpage);
			return forward;
		}
		
		//DAO, DTO 처리
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String profile_pic = request.getParameter("preview");
		String id = request.getParameter("id");
		
		MemberDAO memberDao = new MemberDAO();
		memberDao.updateMember(name, addr, profile_pic, id);
		//이동경로(viewpage)
		viewpage = "/managePage.Mg";
		forward.setPath(viewpage);
		
		return forward;
	}
	
}
