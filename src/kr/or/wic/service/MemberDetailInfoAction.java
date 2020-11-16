package kr.or.wic.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.MemberDAO;
import net.sf.json.JSONArray;

public class MemberDetailInfoAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("memberId");
		System.out.println(id);
		MemberDAO dao = new MemberDAO();
		int[] arr = dao.memberInfo(id);
		
		JSONArray arrJson = JSONArray.fromObject(arr);
		response.setContentType("application/x-json; charset=UTF-8");
		
		try {
			response.getWriter().print(arrJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return null;
	}

}
