package kr.or.wic.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.wic.action.Action;
import kr.or.wic.action.ActionForward;
import kr.or.wic.dao.ClosetDAO;
import kr.or.wic.dao.MemberDAO;
import kr.or.wic.dto.ClosetDTO;
import kr.or.wic.dto.MemberDTO;

public class MemberRegisterAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		System.out.println("member register action");
		ClosetDAO closetDao = new ClosetDAO();
		MemberDAO memberDao = new MemberDAO();
		MemberDTO memberDto = new MemberDTO();
		int result_closet=0;
		int result = 0;
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		int filesize = 10 * 1024 * 1024; // 10M
		String filename = "";
		
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(
					request,	// 요청 객체 (Multipart 와 연결)
					uploadpath, // 저장경로 (실질적 저장 경로)
					filesize,   // 파일 크기 (10M)(한번에 업로드할 최대 파일 크기)
					"UTF-8",    // 한글 인코딩
					new DefaultFileRenamePolicy() // 파일 중복 처리 객체
			);
		} catch (IOException e) {
			System.out.println("multiRequest error");
			e.printStackTrace();
		}
		
		Enumeration filenames = multi.getFileNames();
		filename = multi.getFilesystemName((String)filenames.nextElement());
		
		memberDto.setId(multi.getParameter("id"));
		memberDto.setPwd(multi.getParameter("pwd"));
		memberDto.setName(multi.getParameter("name"));
		memberDto.setAddr(multi.getParameter("addr"));
		memberDto.setProfile_pic(filename);

		result_closet = closetDao.createCloset();
		String viewpage ="";
		if(result_closet!=0) {
			result = memberDao.insertMember(memberDto);
			
			
			if(result != 0) {
				viewpage = "Main.jsp";
			}else {
				viewpage = "loginRegister.jsp";
			}
			
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath(viewpage);
		
		return forward;
	}
}