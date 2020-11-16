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
import kr.or.wic.dto.FilesDTO;
import kr.or.wic.dto.MemberDTO;
import kr.or.wic.dto.ProductDTO;
import net.sf.json.JSONArray;

public class MyClosetEditAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			
		String id = (String)request.getSession().getAttribute("id");
		String content = request.getParameter("contentedit");
		System.out.println(id);
		System.out.println(content);
		
		MemberDAO dao = new MemberDAO();
		dao.setClosetInfo(id, content);
		
			
		JSONArray contentJson = JSONArray.fromObject(content);
	      response.setContentType("application/x-json; charset=UTF-8");
	      
	      try {
	         response.getWriter().print(contentJson);
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
			
			return null;
		}
	
	}
