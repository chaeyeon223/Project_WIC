package kr.or.wic.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.FilesDAO;
import kr.or.wic.dao.MemberDAO;
import kr.or.wic.dto.FilesDTO;
import kr.or.wic.dto.MemberDTO;

public class ProductUploadPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//sessionid 값 받아오고, files에서 id로 검색 후 prd_num이 null이면 모두 delete
		String id = (String)request.getSession().getAttribute("id");
		FilesDAO fdao = new FilesDAO();
		List<FilesDTO> fileList = fdao.getFilesListById(id);
		for(FilesDTO file : fileList) {
			if(file.getPrd_num() == 0) {
				fdao.deleteFile(file.getFiles_num());
			}

		}
		
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = mdao.getMemberById(id);
		
		String addr = mdto.getAddr();
		request.setAttribute("addr", addr);
		
		ActionForward forward = new ActionForward();
		forward.setPath("ProductUploadPage.jsp");
		
		return forward;
	}

}
