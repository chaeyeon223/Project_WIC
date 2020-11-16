package kr.or.wic.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.FilesDAO;
import kr.or.wic.dto.FilesDTO;

public class ProductEditCancelAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {		
		//sessionid 값 받아오고, files에서 id로 검색 후 prd_num이 null이면 모두 delete
		//파일 수정 중 뒤로가기 시 파일 업로드를 submit 하지 않은 것은 삭제
		String id = (String)request.getSession().getAttribute("id");
		FilesDAO fdao = new FilesDAO();
		List<FilesDTO> fileListNullCheck = fdao.getFilesListById(id);
		for(FilesDTO file : fileListNullCheck) {
			if(file.getPrd_num() == 0) {
				fdao.deleteFile(file.getFiles_num());
			}
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/ProductDetailPage.Pd");
		
		return forward;
	}
}
