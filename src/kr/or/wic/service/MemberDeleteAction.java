package kr.or.wic.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.ClosetDAO;
import kr.or.wic.dao.MemberDAO;
/* 
@Project : WIC
@File name : MemberDeleteAction.java
@Date : 2020.11.13
@Author : 문지연
*/
public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			int closet_num = 9999;
			if(request.getParameter("closet_num")!= null) {
				closet_num=Integer.parseInt(request.getParameter("closet_num")); 				
			}
			String id = request.getParameter("id");
			MemberDAO memberDao = new MemberDAO();
			
			int result = memberDao.deleteMember(id,closet_num);
			System.out.println("deleted Id:"+id);
			
			if(result!=0) {
				System.out.println("Deleted");
			}else {
				System.out.println("Failed");
			}
			
			ActionForward forward = new ActionForward();
			forward.setPath("/managePage.Mg");
			return forward;
		}
}