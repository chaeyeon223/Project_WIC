package kr.or.wic.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.service.LikeDeleteAjaxAction;
import kr.or.wic.service.LikeSendAjaxAction;
import kr.or.wic.service.MemberDetailInfoAction;
import kr.or.wic.service.ProductFileDeleteAjaxAction;
import kr.or.wic.service.ProductUploadAjaxAction;

/*
 * 비동기 관련 컨트롤러 
 * 컨트롤러는 미관상 주석을 if(){ //여기에 적겠음 
 */

@WebServlet("*.Ajax")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AjaxController() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
    	String viewpage="";
    	
    	Action action = null;
    	ActionForward forward = null;
    	
    	if(url_Command.equals("idCheck.Ajax")) { //아이디 중복체크
    		
    		
    	} else if (url_Command.equals("myCartPage.Ajax")) { //찜 내역(상품)
    		
    		
    	} else if (url_Command.equals("mySale.Ajax")) { //판매내역
    		
    		
    	} else if (url_Command.equals("csNoticeList.Ajax")) { //고객센터 분류(공지사항)
    		
    		
    	} else if (url_Command.equals("csQNAList.Ajax")) { //고객센터 분류(QnA)
    		
    		
    	} else if (url_Command.equals("/fileUpload.Ajax")) { //파일업로드
    		action = new ProductUploadAjaxAction();
    		forward = action.execute(request, response);
    	} else if (url_Command.equals("/fileDelete.Ajax")) { //파일삭제
    		action = new ProductFileDeleteAjaxAction();
    		forward = action.execute(request, response);
    	} else if (url_Command.equals("/sendLike.Ajax")) { //좋아요 누르기
    		action = new LikeSendAjaxAction();
    		forward = action.execute(request, response);
    	} else if (url_Command.equals("/deleteLike.Ajax")) { //좋아요 취소하기
    		action = new LikeDeleteAjaxAction();
    		forward = action.execute(request, response);
    	} else if (url_Command.equals("/memberInfo.Ajax")) { //좋아요 취소하기
    		action = new MemberDetailInfoAction();
    		forward = action.execute(request, response);
    	}
    	
    	if(forward != null) {
	    	RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
	    	dis.forward(request, response);
    	}
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
