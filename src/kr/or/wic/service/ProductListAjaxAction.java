package kr.or.wic.service;

import java.io.IOException;
import java.util.ArrayList;
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

public class ProductListAjaxAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = (String) request.getSession().getAttribute("id");
		ProductDAO pdao = new ProductDAO();
		CartDAO cdao = new CartDAO();
		
	
		int currentPage = 0; // 현재페이지
		int pageSize = 0; // 페이지 사이즈
		int maxPageCount = 0; // 총 페이지 수
		int csListCount = pdao.countAllProductList();
		
		currentPage += 1;
		pageSize += 10;
		
		

		// 총 페이지 수(with 총 게시물 수와 페이지 사이즈)
		if (csListCount % pageSize == 0) {
			maxPageCount = csListCount / pageSize;
		} else {
			maxPageCount = (csListCount / pageSize) + 1;
		}

		
		
		
		List<Object> object = new ArrayList<Object>();
		//DAO, DTO 처리
		List<ProductDTO> productList = pdao.getProductNumTitleContentList(currentPage,pageSize);
		request.setAttribute("productList", productList);
		System.out.println(productList);
		object.add(productList);
		
		
		
		List<CartDTO> cartList = cdao.getCartList(id);
		request.setAttribute("cartList",cartList);
		System.out.println(cartList);
		object.add(cartList);
		
		JSONArray prdJson = JSONArray.fromObject(object);
		
		response.setContentType("application/x-json; charset=UTF-8");

		try {
			response.getWriter().print(prdJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
