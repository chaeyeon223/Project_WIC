package kr.or.wic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Like_RecordDAO {
	static DataSource ds;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	static {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			Context envctx = (Context)ctx.lookup("java:comp/env");
			ds = (DataSource)envctx.lookup("/jdbc/oracle");
		} catch (Exception e) {
			System.out.println("look up Fail: " + e.getMessage());
		}
	}
	
	//1.좋아요 받은 수 조회(return the getLike)
	public int getGetLikeById(String id){
		int getLike = 0;
		try {
			conn = ds.getConnection();
			
			String sql = "select count(get_id) from like_record where get_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				getLike = rs.getInt("count(get_id)");
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
		return getLike;
	}
	
	//2.좋아요 누른 수 조회(return the sendLike)
	public int getSendLikeById(String id){
		int sendLike = 0;
		try {
			conn = ds.getConnection();
			
			String sql = "select count(send_id) from like_record where send_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sendLike = rs.getInt("count(send_id)");
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
		return sendLike;
	}
	
	//3.좋아요 누르기
	public int sendLike(String send_id, String get_id) {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into like_record(send_id, get_id) values(?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, send_id);
			pstmt.setString(2, get_id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
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
	
	//5.좋아요 취소하기
	public int deleteLike(String send_id, String get_id) {
		int result = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "delete from like_record where send_id=? and get_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, send_id);
			pstmt.setString(2, get_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
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
	
	//6.좋아요 클릭 여부
	public int checkLike(String send_id, String get_id) {
		int result = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "select send_id, get_id from like_record where send_id=? and get_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, send_id);
			pstmt.setString(2, get_id);
			rs = pstmt.executeQuery();
			
			result = (rs.next()) ? 1 : 0;
		} catch (SQLException e) {
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
}
