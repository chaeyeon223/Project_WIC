package kr.or.wic.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dto.FilesDTO;
import kr.or.wic.dto.MemberDTO;
import kr.or.wic.dto.PriceFormat;
import kr.or.wic.dto.ProductDTO;
import kr.or.wic.dao.FilesDAO;
import kr.or.wic.dao.MemberDAO;
import kr.or.wic.dao.ProductDAO;

public class ProductEditPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8"); // 클라언트에게 전달한 페이지의 정보 구성
		
		//sessionid 값 받아오고, files에서 id로 검색 후 prd_num이 null이면 모두 delete
		String id = (String)request.getSession().getAttribute("id");
		FilesDAO fdao = new FilesDAO();
		List<FilesDTO> fileListNullCheck = fdao.getFilesListById(id);
		for(FilesDTO file : fileListNullCheck) {
			if(file.getPrd_num() == 0) {
				fdao.deleteFile(file.getFiles_num());
			}
		}
		
		String viewpage = "";
		ActionForward forward = new ActionForward();
		
		//DAO, DTO 처리
		int prd_num = Integer.parseInt(request.getParameter("prd_num"));
		
		//product 정보
		ProductDAO dao = new ProductDAO();
		ProductDTO product = dao.getProduct(prd_num);
		
		//file 정보
		List<FilesDTO> fileList = new ArrayList<FilesDTO>();
		fileList = fdao.getFilesListByPrdNum(prd_num);
		
		//member 정보
		MemberDTO member = new MemberDTO();
		MemberDAO mdao = new MemberDAO();
		String sellerId = fileList.get(0).getId();
		member = mdao.getMemberById(sellerId);
		
		request.setAttribute("product", product);
		request.setAttribute("fileList", fileList);
		request.setAttribute("member", member);
		
		//이동경로(viewpage)
		viewpage = "ProductEditPage.jsp";
		forward.setPath(viewpage);
		
		return forward;
	}

}
