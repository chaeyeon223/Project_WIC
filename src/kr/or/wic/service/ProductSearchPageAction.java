package kr.or.wic.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dto.FilesDTO;
import kr.or.wic.dto.ProductDTO;
import kr.or.wic.dao.FilesDAO;
import kr.or.wic.dao.ProductDAO;

public class ProductSearchPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8"); // 클라언트에게 전달한 페이지의 정보 구성
		String s =request.getParameter("search");
		System.out.println(s);
		ProductDAO pdao = new ProductDAO();
		ArrayList<ProductDTO> alist =pdao.search(s);
		System.out.println("------------------------------");
		System.out.println(alist.toString());
		
		String viewpage = "";
		ActionForward forward = new ActionForward();
		request.setAttribute("productList", alist);
		
		/*
		 * 본 service에서 해당 회원과 관리자만 접근이 가능하게 하는 기능을 여기서 구현할지 결정해야
		String id = (String)request.getSession().getAttribute("id");
		if(id == null || !id.equals("admin")) {
			viewpage = "/WEB-INF/LoginRegister/Login.jsp";
			forward.setPath(viewpage);
			return forward;
		}
		*/
		
		//DAO, DTO 처리
		

		
		//이동경로(viewpage)
		viewpage = "ProductListPage.jsp";
		forward.setPath(viewpage);
		
		return forward;
	}

}
