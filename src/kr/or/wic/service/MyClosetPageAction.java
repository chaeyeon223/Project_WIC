package kr.or.wic.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.ClosetDAO;
import kr.or.wic.dao.Like_RecordDAO;
import kr.or.wic.dao.MemberDAO;
import kr.or.wic.dao.ProductDAO;
import kr.or.wic.dto.ClosetDTO;
import kr.or.wic.dto.MemberDTO;
import kr.or.wic.dto.ProductDTO;

public class MyClosetPageAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8"); // 클라언트에게 전달한 페이지의 정보 구성
		
		String viewpage = "";
		ActionForward forward = new ActionForward();
		String id = (String)request.getSession().getAttribute("id");
		
		//해당 회원의 옷장 정보를 가지고 들어가야
		//Left: 회원의 name, profile_pic, addr, Like_Record 테이블의 get_id 수 count, 옷장 테이블(closet_num과 일치하는)의 closet_title, closet_content
		//Right: Product테이블의 해당 회원의 closet_num과 일치하는 product 객체(prd_num(링크 시 prd_num을 파라미터로), +필요한 정보만), files에서 prd_num의 첫번째 사진 파일의 file_name
		
		//회원(name, profile_pic, addr, +@) 정보
		MemberDTO member = new MemberDTO();
		MemberDAO mdao = new MemberDAO();
		member = mdao.getMemberById(id); //해당 회원의 모든 정보

		//Like 받은 수
		Like_RecordDAO ldao = new Like_RecordDAO();
		int getLike = ldao.getGetLikeById(id);
		
		//closet(closet_num, closet_title, closet_content) 정보
		ClosetDTO closet = new ClosetDTO();
		ClosetDAO cdao = new ClosetDAO();
		closet = cdao.getClosetById(id);
		System.out.println(closet);
		
		//product 객체 정보
		ProductDAO pdao = new ProductDAO();
		List<ProductDTO> productList = pdao.getEachMemberAllProductAndFileList(id);
		
		//file(file_name) 정보(모든 파일 리스트의 name 중 각 prd_num의 첫번째 파일)
		request.setAttribute("member", member);
		request.setAttribute("getLike", getLike);
		request.setAttribute("closet", closet);
		request.setAttribute("productList", productList);
		
		viewpage = "MyCloset.jsp";
		forward.setPath(viewpage);
		return forward;
	}
}
