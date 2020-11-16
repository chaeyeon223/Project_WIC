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
import kr.or.wic.service.MemberCheckIdAction;
import kr.or.wic.service.MypageMemberEditAction;
import kr.or.wic.service.MypageMemberEditPageAction;
import kr.or.wic.service.MemberLogInAction;
import kr.or.wic.service.MemberRegisterAction;
import kr.or.wic.service.MyCartAddAction;
import kr.or.wic.service.MyClosetEditAction;
import kr.or.wic.service.MyClosetPageAction;

/*
 * 회원 관련 컨트롤러
 */

@WebServlet(urlPatterns="*.my")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
    	String viewpage="";
    	
    	Action action = null;
    	ActionForward forward = null;
    	
    	if(url_Command.equals("/mainPage.my")) { //메인페이지 
    		forward = new ActionForward();
    		forward.setPath("Main.jsp");
    	} else if (url_Command.equals("/signUpPage.my")) { //회원가입&로그인 겸용 페이지  
    		forward = new ActionForward();
    		forward.setPath("loginRegister.jsp");
    	} else if (url_Command.equals("/signUp.my")) { //회원 가입 
    		System.out.println("sign up.my");
    		action = new MemberRegisterAction();
    		forward = action.execute(request, response);
    	} else if (url_Command.equals("/checkId.my")) { //아이디체크
    		System.out.println("checkId.my");
    		action = new MemberCheckIdAction();
    		forward = action.execute(request, response);
    	} else if (url_Command.equals("/signIn.my")) { //로그인 
    		System.out.println("sign in.my");
    		action=new MemberLogInAction();
    		forward=action.execute(request, response);
    	} else if (url_Command.equals("/signOut.my")) { //로그아웃
    		forward = new ActionForward();
    		forward.setPath("/WEB-INF/views/MemberSignOut.jsp");
    		System.out.println("signOut");
    	} else if (url_Command.equals("/myPage.my")) { //마이페이지 
    		action = new MyClosetPageAction();	
    		forward = action.execute(request, response);
    	} else if (url_Command.equals("/myInfoEditPage.my")) { //내 정보 수정 페이지 & 정보 수정 취소 페이지
    		action = new MypageMemberEditPageAction();
    		forward = action.execute(request, response);
    	} else if (url_Command.equals("/myInfoEdit.my")) { //정보 수정하기 
    		action = new MypageMemberEditAction();	
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("myClosetEdit.my")) { //옷장소개 수정하기 ajax
    		action = new MyClosetEditAction();
    		forward = action.execute(request, response);
		} else if (url_Command.equals("/myCart.my")) { //찜하기
    		action = new MyCartAddAction();
    		forward = action.execute(request, response);
    	} else if (url_Command.equals("/Like.my")) { //좋아요 (사람)
    		
    	}
    	
    	if(forward!=null) {
    	RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    	dis.forward(request, response);
    	}
    	
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}