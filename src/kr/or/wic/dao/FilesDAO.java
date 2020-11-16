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

import kr.or.wic.dto.FilesDTO;

public class FilesDAO {
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
	
	//1.파일정보 조회(return the file list)
	public List<FilesDTO> getAllFileList(){
		List<FilesDTO> filesList = new ArrayList<FilesDTO>();
		
		try {
			conn = ds.getConnection();
			
			String sql = "select files_num, files_name, files_path, prd_num from files";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FilesDTO file = new FilesDTO();
				
				file.setFiles_num(rs.getInt("files_num"));
				file.setFiles_name(rs.getString("files_name"));
				file.setFiles_path(rs.getString("files_path"));
				file.setPrd_num(rs.getInt("prd_num"));
				
				filesList.add(file);
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
		return filesList;
	}
	
	//1-1.파일정보 조회(ProductListPage 정보만 조회(files_name, prd_num)
	public List<FilesDTO> getFilesNamePrdNumList(){
		List<FilesDTO> filesList = new ArrayList<FilesDTO>();
		
		try {
			conn = ds.getConnection();
			
			String sql = "select files_name, prd_num from files";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FilesDTO file = new FilesDTO();
				
				file.setFiles_name(rs.getString("files_name"));
				file.setPrd_num(rs.getInt("prd_num"));
				filesList.add(file);
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
		return filesList;
	}
	
	//2.회원별 파일 조회(return the filesList by ID)
	public List<FilesDTO> getFilesListById(String id) {
		List<FilesDTO> filesList = new ArrayList<FilesDTO>();
		
		try {
			conn = ds.getConnection();
			String sql = "select files_num, files_name, files_path, prd_num, id from files where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FilesDTO file = new FilesDTO();
				
				file.setFiles_num(rs.getInt("files_num"));
				file.setFiles_name(rs.getString("files_name"));
				file.setFiles_path(rs.getString("files_path"));
				file.setPrd_num(rs.getInt("prd_num"));
				file.setId(rs.getString("id"));
				
				filesList.add(file);
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
		return filesList;
	}
	
	//2-1.상품별 파일 조회(return the files by prd_num)
	public List<FilesDTO> getFilesListByPrdNum(int prd_num) {
		List<FilesDTO> filesList = new ArrayList<FilesDTO>();
		try {
			conn = ds.getConnection();
			String sql = "select files_num, files_name, files_path, prd_num, id from files where prd_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prd_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FilesDTO file = new FilesDTO();
				
				file.setFiles_num(rs.getInt("files_num"));
				file.setFiles_name(rs.getString("files_name"));
				file.setFiles_path(rs.getString("files_path"));
				file.setPrd_num(rs.getInt("prd_num"));
				file.setId(rs.getString("id"));
				
				filesList.add(file);
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
		return filesList;
	}
	
	//3.파일 정보 등록(insert the new file)
	public int insertFile(FilesDTO file) {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into files(files_num, files_name, files_path, prd_num, id)"
						+ "values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, file.getFiles_num());
			pstmt.setString(2, file.getFiles_name());
			pstmt.setString(3, file.getFiles_path());
			pstmt.setInt(4, file.getPrd_num());
			pstmt.setString(5, file.getId());
			
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
	
	//3-1.파일정보 등록(ajax 파일 임시 등록 시 prd_num은 null)
	public int insertFilePrdNull(FilesDTO file) {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into files(files_num, files_name, files_path, id)"
						+ "values(files_seq.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, file.getFiles_name());
			pstmt.setString(2, file.getFiles_path());
			pstmt.setString(3, file.getId());
			
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
	
	//4.파일 정보 수정(update the information of a file)
	public int updateFile(int files_num, String files_name, String files_path, int prd_num) {
		int row =0;
		
		try {
			conn = ds.getConnection(); 
			String sql = "update files set files_name=?, files_path=?, prd_num=? where files_num=?";
			pstmt = conn.prepareStatement(sql);
			/* 샘플
			pstmt.setString(1, files_name);
			pstmt.setString(2, files_path);
			pstmt.setInt(3, prd_num);
			pstmt.setInt(4, files_num);
			*/
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
		return row;
	}
	
	//4-1.파일 정보 수정(prd_num만 업데이트)
	public int updateFilePrd_num(int prd_num, String id) {
		int row =0;
		
		try {
			conn = ds.getConnection(); 
			String sql = "update files set prd_num=? where prd_num is null and id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prd_num);
			pstmt.setString(2, id);
			
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
		return row;
	}
	
	//5.파일 정보 삭제(delete the file)
	public int deleteFile(int files_num) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "delete from files where files_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, files_num);
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
		return row;
	}
}
