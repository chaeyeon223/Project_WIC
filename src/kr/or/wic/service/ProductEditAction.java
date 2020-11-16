package kr.or.wic.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dto.ProductDTO;
import kr.or.wic.dao.FilesDAO;
import kr.or.wic.dao.MemberDAO;
import kr.or.wic.dao.ProductDAO;

public class ProductEditAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String productName = request.getParameter("productName");
		int productPrice = Integer.parseInt(request.getParameter("productPrice"));
		String context = request.getParameter("context");
		String id = (String)request.getSession().getAttribute("id");
		
		//closet_num
		MemberDAO mdao = new MemberDAO();
		int closet_num = mdao.getCloset_numById(id);
		
		//productDTO 객체에 정보 담기
		ProductDTO pdto = new ProductDTO();
		pdto.setPrd_num(prd_num);
		pdto.setPrd_title(productName);
		pdto.setPrd_price(productPrice);
		pdto.setPrd_content(context);
		pdto.setCloset_num(closet_num);
		
		//product 정보 insert(dao)
		ProductDAO pdao = new ProductDAO();
		pdao.updateProduct(pdto);
		
		//file 정보에 prd_num 입력
		FilesDAO fdao = new FilesDAO();
		fdao.updateFilePrd_num(prd_num, id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/myPage.my");
		
		
		return forward;
	}
}
