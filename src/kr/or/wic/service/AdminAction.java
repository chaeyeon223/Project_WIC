package kr.or.wic.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.minchanDAO;
import kr.or.wic.dto.MemberDTO;
import net.sf.json.JSONArray;

public class AdminAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("memberId");
		System.out.println(id);
//		MemberDAO dao = new MemberDao();
		minchanDAO dao = new minchanDAO();
		int[] arr = dao.memberInfo(id);
		
		JSONArray arrJson = JSONArray.fromObject(arr);
		response.setContentType("application/x-json; charset=UTF-8");
		
		try {
			response.getWriter().print(arrJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		ActionForward forward = new ActionForward();
//		request.setAttribute("dtoJson", dtoJson);
//		forward.setPath("Admin.jsp");
//		
		
		return null;
	}

}
