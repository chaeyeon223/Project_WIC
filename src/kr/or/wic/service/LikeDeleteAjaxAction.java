package kr.or.wic.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.Like_RecordDAO;

public class LikeDeleteAjaxAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		String send_id = request.getParameter("send_id");
		String get_id = request.getParameter("get_id");
		
		Like_RecordDAO dao = new Like_RecordDAO();
		dao.deleteLike(send_id, get_id);
		int getTestLike = dao.getGetLikeById(get_id);
		
		request.setAttribute("getTestLike", getTestLike);
		forward.setPath("LikeTestPage.jsp");
		
		return forward;
	}

}
