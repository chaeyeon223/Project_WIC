package kr.or.wic.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dto.ProductDTO;
import kr.or.wic.dao.MemberDAO;
import kr.or.wic.dao.ProductDAO;

public class MypageMemberEditAction implements Action{
	//Member Edit 
		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			response.setContentType("text/html;charset=UTF-8"); 
			
			String viewpage = "";
			ActionForward forward = new ActionForward();
			//String id = (String)request.getSession().getAttribute("id");
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String addr = request.getParameter("addr");
			String profile_pic = request.getParameter("profile_pic");
			
			
			MemberDAO memberdao = new MemberDAO();
			memberdao.updateMember(id, pwd, name, addr, profile_pic);
			
			// edit 안됨 !!!
			viewpage = "/myPage.my";
			forward.setPath(viewpage);
			
			return forward;
			
			
		}

	}
