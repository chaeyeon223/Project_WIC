package kr.or.wic.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.MemberDAO;
import kr.or.wic.dto.MemberDTO;
/* 
@Project : WIC
@File name : MemberManagementAction.java
@Date : 2020.11.12
@Author : 문지연
*/
public class MemberManagementAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		
		String viewpage = "";
		ActionForward forward = new ActionForward();
		String id = (String)request.getSession().getAttribute("id");
		System.out.println("get session id");
		if(id == null || !id.equals("admin@admin.com")) {
			viewpage = "Main.jsp";
			forward.setPath(viewpage);
			return forward;
		}
		
		//DAO, DTO 처리
	
		MemberDAO memberDao = new MemberDAO();
		try {
			List<MemberDTO> memberList = memberDao.getMemberList();
			request.setAttribute("memberList", memberList);
			
		} catch (Exception e) {
			System.out.println("get MemberList dao error");
			e.getMessage();
		}
				
		//이동경로(viewpage)
		viewpage = "MemberManagementMain.jsp";
		forward.setPath(viewpage);
		
		return forward;
	}
	
}
