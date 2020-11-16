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

import kr.or.wic.dto.MemberDTO;

public class minchanDAO {

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
	
		//로그인 횟수
		public void loginCount(String id) {
			System.out.println("loginCount 진입");
			try {
				conn = ds.getConnection();
				String sql = "update member set loginCount=loginCount+1 where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
			} catch (SQLException e) {
				System.out.println("loginCount 예외 발생");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
		}
		/*
		 select * 
from(
select rownum as rnum, name, loginCount
from member
order by loginCount desc)
where 1<=rnum and rnum<=10
		 */
		
		
		
		public List<MemberDTO> goodMemberInfo(){
			System.out.println("goodMemberInfo 진입");
			
			List<MemberDTO> dtos = new ArrayList<>();
			try {
				conn = ds.getConnection();
				String sql = "select * \r\n" + 
						"from(\r\n" + 
						"select rownum as rnum, name, loginCount, id\r\n" + 
						"from member\r\n" + 
						"order by loginCount desc)\r\n" + 
						"where 1<=rnum and rnum<=5";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					MemberDTO dto = new MemberDTO();
					dto.setId(rs.getString("id"));
					dto.setName(rs.getString("name"));
					dto.setLoginCount(rs.getInt("loginCount"));
					dtos.add(dto);
					System.out.println(dto);
				}	
			} catch (SQLException e) {
				System.out.println("goodMemberInfo() 예외 발생");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
			return dtos;
		}
		
		public int[] memberInfo(String id) {
			System.out.println("memberInfo 진입");
			int[] arr = new int[5];
			try {
				conn = ds.getConnection();
				String sql = "select logincount,closet_num from member where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					arr[0] = rs.getInt("logincount");
					sql = "select * from\r\n" + 
							"(select count(*) from product where closet_num=?),\r\n" + 
							"(select count(*)  from product where closet_num=? and prd_state=0),\r\n" + 
							"(select count(*)  from product where closet_num=? and prd_state=1)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, rs.getInt("closet_num"));
					pstmt.setInt(2, rs.getInt("closet_num"));
					pstmt.setInt(3, rs.getInt("closet_num"));
					rs = pstmt.executeQuery();
					if(rs.next()) {
						arr[1] = rs.getInt(1);
						arr[2] = rs.getInt(2);
						arr[3] = rs.getInt(3);
					}
					sql = "select count(*) from chatroom where id=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						arr[4] = rs.getInt(1);
					}
					System.out.println(arr[0]);
					System.out.println(arr[1]);
					System.out.println(arr[2]);
					System.out.println(arr[3]);
					System.out.println(arr[4]);
				}
			} catch (SQLException e) {
				System.out.println("memberInfo 예외 발생");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
			return arr;
		}

	
}

