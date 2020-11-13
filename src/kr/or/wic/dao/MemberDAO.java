package kr.or.wic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.wic.dto.ClosetDTO;
import kr.or.wic.dto.MemberDTO;
import kr.or.wic.dto.ProductDTO;

public class MemberDAO {

	static DataSource ds;
	public Connection conn = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;

	static {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			Context envctx = (Context) ctx.lookup("java:comp/env");
			ds = (DataSource) envctx.lookup("/jdbc/oracle");
		} catch (Exception e) {
			System.out.println("look up Fail: " + e.getMessage());
		}
	}

	// insertMember
	public int insertMember(MemberDTO memberdto) {
		System.out.println("enter insertMember");
		int result = 0;
		ClosetDTO closetDto = new ClosetDTO(); 
		try {
			conn = ds.getConnection();
			
			String sql = "insert into member(id,pwd,name,addr,profile_pic,closet_num) values(?,?,?,?,?,CLOSET_CLOSET_NUM.currval)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberdto.getId());
			pstmt.setString(2, memberdto.getPwd());
			pstmt.setString(3, memberdto.getName());
			pstmt.setString(4, memberdto.getAddr());
			pstmt.setString(5, memberdto.getProfile_pic());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertMember error");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// email check for register
	public String isEmail(String id) {
		String result = "false";

		try {
			conn = ds.getConnection();
			String sql = "select id from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (!rs.next())
				result = "true";
		} catch (Exception e) {
			System.out.println("isEmail Exception : " + e.getMessage());
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}
		System.out.println(result);
		return result;
	}
	
	
	//sign In
	public MemberDTO signedIn(String id, String pwd) {
		MemberDTO memberDto = new MemberDTO();
		
		try {
			conn = ds.getConnection();
			String sql = "select id, pwd from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (pwd.equals(rs.getString("pwd"))) {
					memberDto.setId(rs.getString("id"));
					memberDto.setPwd(rs.getString("pwd"));
				} else {
					memberDto.setId(rs.getString("id"));
					memberDto.setPwd(null);
				}
			} else {
				memberDto.setPwd(null);
				memberDto.setId(null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberDto;
	}
	
	
	//get all memberList 
	public List<MemberDTO> getMemberList(){
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		System.out.println("enter getMemberList dao");
		try {
			conn = ds.getConnection();
			String sql = "select id, name, addr, profile_pic, closet_num from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO memberDto = new MemberDTO();
				
				memberDto.setId(rs.getString(1));
				memberDto.setName(rs.getString(2));
				memberDto.setAddr(rs.getString(3));
				memberDto.setProfile_pic(rs.getString(4));
				memberDto.setCloset_num(rs.getInt(5));
				
				memberList.add(memberDto);
			}
		} catch (SQLException e) {
			System.out.println("getMemberList error:"+e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberList;
	}
	
	//getMemberInfo byId for edit Member from Admin Manage Site 
	public MemberDTO getMemberById(String id) {
		MemberDTO memberDto = new MemberDTO();
		
		try {
			conn=ds.getConnection();
			String sql = "select id, pwd, name, addr, profile_pic, closet_num from member where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				memberDto.setId(rs.getString(1));
				memberDto.setPwd(rs.getString(2));
				memberDto.setName(rs.getString(3));
				memberDto.setAddr(rs.getString(4));
				memberDto.setProfile_pic(rs.getString(5));
				memberDto.setCloset_num(rs.getInt(6));
			}
		} catch (SQLException e) {
			System.out.println("error get memberById");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberDto;
	}
	
	//update member's info
	public int updateMember(String id, String pwd, String name, String addr, String profile_pic) {
		int result=0;
		
		try {
			conn=ds.getConnection();
			String sql="update member set id=?, pwd=?, name=?,addr=?,profile_pic=? where id=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, addr);
			pstmt.setString(5, profile_pic);
			
			result=pstmt.executeUpdate();
		}catch (SQLException e) {
			System.out.println("update member error");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//delete member
	public int deleteMember(String id) {
		int result=0;
		
		try {
			conn=ds.getConnection();
			String sql="delete from member where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result=pstmt.executeUpdate();
		}catch (SQLException e) {
			System.out.println("delete member error");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public MemberDTO getMemberInfoForCs(String id) {

		MemberDTO dto = new MemberDTO();
		try {
			conn = ds.getConnection();
			String sql = "select id, name \r\n" + "from Member\r\n" + "where ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			System.out.println("getMemberInfoForCs Error");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
}