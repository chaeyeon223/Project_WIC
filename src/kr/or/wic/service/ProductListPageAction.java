package kr.or.wic.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.CartDAO;
import kr.or.wic.dao.ProductDAO;
import kr.or.wic.dto.CartDTO;
import kr.or.wic.dto.ProductDTO;
import net.sf.json.JSONArray;

public class ProductListPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8"); // 클라언트에게 전달한 페이지의 정보 구성
		String id = (String) request.getSession().getAttribute("id");

		String viewpage = "";
		ActionForward forward = new ActionForward();
		
		//DAO, DTO 처리
		ProductDAO pdao = new ProductDAO();
		int currentPage = 1; // 현재페이지
		int pageSize = 20; // 페이지 사이즈
		int maxPageCount = 0; // 총 페이지 수
		int csListCount = pdao.countAllProductList();
		
		// 현재 페이지 재설정
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println();
		}
	
		// 페이지 사이즈 재설정
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
	
		// 총 페이지 수(with 총 게시물 수와 페이지 사이즈)
		if (csListCount % pageSize == 0) {
			maxPageCount = csListCount / pageSize;
		} else {
			maxPageCount = (csListCount / pageSize) + 1;
		}
	
		int startPage = ((currentPage - 1) / 5) * 5 + 1; // 하단 페이징 시작 번호
		int endPage = startPage + 5 - 1; // 하단 페이징 끝 번호
		if (endPage > maxPageCount) {
			endPage = maxPageCount;
		}
		
		List<ProductDTO> productList = pdao.getProductNumTitleContentList(currentPage,pageSize);
		request.setAttribute("productList", productList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("maxPageCount", maxPageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		System.out.println(productList);

		
		CartDAO cdao = new CartDAO();
		List<CartDTO> cartList = cdao.getCartList(id);
		request.setAttribute("cartList",cartList);
		System.out.println(cartList);
	
		
		//이동경로(viewpage)
		viewpage = "ProductListPage.jsp";
		forward.setPath(viewpage);
		
		return forward;
	}
}
