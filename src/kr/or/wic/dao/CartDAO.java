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

import kr.or.wic.dto.CartDTO;
import kr.or.wic.dto.FilesDTO;

public class CartDAO {
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
	//1. 카트에 담기
	public void addProduct(int prd_num, String id) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) from cart where prd_num = ? and id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prd_num);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				row = rs.getInt(1);
				if(row>0) {
					//지우기
					deleteProduct(prd_num,id);
				}else {
					sql = "insert into cart (prd_num,id) values(?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, prd_num);
					pstmt.setString(2, id);
					row = pstmt.executeUpdate();
					
				}
			}
			
				
		
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
		
	}
	
	//2 카트 제거하기
	public void deleteProduct(int prd_num, String id ) {
		
		int row=0;
		try {
			conn = ds.getConnection();
			String sql = "delete from cart where id=? and prd_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, prd_num);
			row = pstmt.executeUpdate();
				
		
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
		
	}
	//3. 카트 조회하기
	public List<CartDTO> getCartList(String id){
		List<CartDTO> cartList = new ArrayList<CartDTO>();
		try {
			conn = ds.getConnection();
			String sql = "select prd_num, id from cart where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartDTO cdto = new CartDTO();
				
				cdto.setId(rs.getString("id"));
				cdto.setPrd_num(rs.getInt("prd_num"));
				
				cartList.add(cdto);
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
		return cartList;
	
	}
}
