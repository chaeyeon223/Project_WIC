package kr.or.wic.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.MemberDAO;
import net.sf.json.JSONArray;

public class MyClosetEditAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String id = (String)request.getSession().getAttribute("id");
		String content = request.getParameter("contentedit");
		System.out.println(id);
		System.out.println(content);
		
		MemberDAO dao = new MemberDAO();
		dao.setClosetInfo(id, content);
		
			
		

		return null;
		}
	}