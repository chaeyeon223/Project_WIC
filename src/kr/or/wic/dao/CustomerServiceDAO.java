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

import kr.or.wic.dto.CustomerServiceDTO;

public class CustomerServiceDAO {
	
	static DataSource ds;
	public Connection conn = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	
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
	
	//1.Cspage(공지사항) 전체 목록 출력
	public List<CustomerServiceDTO> getCsList(int currentPage ,int pageSize){
		
		List<CustomerServiceDTO> csList = new ArrayList<CustomerServiceDTO>();
		int startRow = (currentPage - 1) * pageSize + 1; // 시작 row 번호
		int endRow = startRow + pageSize - 1;	// 끝 row 번호
		System.out.println(startRow);
		System.out.println(endRow);
		
		
		try {
			conn = ds.getConnection();
			String sql = "select *\r\n" + 
					"from (select rownum as rnum, A.*\r\n" + 
					"    from (select c.cs_num, c.cs_title, c.cs_refer, c.cs_depth, c.cs_step, c.cs_count, c.cs_date, c.cs_notice, c.cs_delete, c.cs_secret, m.id, m.name from customerservice c, member m where  c.id = m.id order by cs_notice desc, cs_refer desc, cs_depth) A)\r\n" + 
					"where rnum>=? and rnum<=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow); 
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CustomerServiceDTO dto = new CustomerServiceDTO();
				dto.setCs_num(rs.getInt("CS_NUM"));	//글번호
				dto.setCs_title(rs.getString("CS_TITLE"));	 //글제목
//				dto.setCs_content(rs.getString("CS_CONTENT"));	//글내용
				dto.setCs_reffer(rs.getInt("CS_REFER"));	//그룹번호
				dto.setCs_depth(rs.getInt("CS_DEPTH"));	//그룹 내 순서
				dto.setCs_step(rs.getInt("CS_STEP"));	//들여쓰기
				dto.setCs_count(rs.getInt("CS_COUNT"));	//조회수
				dto.setCs_date(rs.getDate("CS_DATE"));	//작성일자
				dto.setCs_notice(rs.getInt("CS_NOTICE"));	//공지사항여부
				dto.setId(rs.getString("id"));	//작성자 아이디
				dto.setName(rs.getString("name"));	//작성자 아이디
				dto.setCs_delete(rs.getInt("CS_DELETE"));	//삭제여부
				dto.setCs_secret(rs.getInt("CS_SECRET"));	//공개여부
				csList.add(dto);
			}
		}catch(SQLException e){
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
		return csList;
	}
	
	//2. CsListCount(총 게시물 수)
	public int getCsListCount() {
		int result = 0;
		try {
			conn  = ds.getConnection();
			String sql = "select count(*) from customerservice";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
				System.out.println(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
		return result;
	}
	
	//3. writeCs(고객센터 글 작성하기)
	public void writeCs(String id, String title, String content, int notice, int cs_serect) {
		System.out.println("DAO 진입");
		try {
			conn  = ds.getConnection();
			System.out.println("연결완료");
			String sql = "insert into CUSTOMERSERVICE(CS_NUM, CS_REFER, CS_DEPTH, CS_STEP, CS_NOTICE, ID, CS_TITLE, CS_CONTENT, CS_COUNT, CS_DATE, CS_SECRET)\r\n" + 
					"values(CUSTOMERSERVICE_CS_NUM.nextval,CUSTOMERSERVICE_CS_REFFER.nextval,0,0,?,?,?,?,0,SYSDATE,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice);
			pstmt.setString(2, id);
			pstmt.setString(3, title); 
			pstmt.setString(4, content);
			pstmt.setInt(5, cs_serect);
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("writeCs() 오류 발생");
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
	}
	
	//조회 수 올리기
	public void csDetailCounting(int cs_num) {
		System.out.println("csDetailCounting 진입");
		
		try {
			conn = ds.getConnection();
			String sql = "update customerservice set cs_count=cs_count+1 where cs_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cs_num);
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("csDetailCounting 예외 발생");
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
	
	//공지사항 상세페이지 & 수정페이지
	public CustomerServiceDTO csDetailPage(int cs_num) {
		System.out.println("csDetailPage() 진입");
		CustomerServiceDTO dto = new CustomerServiceDTO();
		
		try {
			conn  = ds.getConnection();
			String sql = "select c.cs_num, c.cs_title, c.cs_content, c.cs_count, c.cs_date, c.id, m.name, c.cs_refer, c.cs_depth, c.cs_step, c.cs_notice, c.cs_secret \r\n" + 
					"from customerservice c, member m\r\n" +  
					"where c.cs_num=? and c.id = m.id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cs_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setCs_num(rs.getInt("cs_num"));	//글번호
				dto.setCs_title(rs.getString("cs_title"));	 //글제목
				dto.setCs_content(rs.getString("cs_content"));	//글내용
				dto.setCs_count(rs.getInt("cs_count"));	//조회수
				dto.setCs_date(rs.getDate("cs_date"));	//작성일자
				dto.setId(rs.getString("id"));	//작성자 아이디
				dto.setName(rs.getString("name"));	//작성자 이름
				dto.setCs_reffer(rs.getInt("cs_refer"));	//refer
				dto.setCs_depth(rs.getInt("cs_depth"));	//depth
				dto.setCs_step(rs.getInt("cs_step"));	//step
				dto.setCs_notice(rs.getInt("cs_notice"));
				dto.setCs_secret(rs.getInt("cs_secret"));
				System.out.println("csDetailPage 거의 끝");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("여기서 오류?");
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
	
	
	//글 수정하기!
	public int csEdit(int cs_num, String title, String content, int cs_notice, int cs_secret) {
		int result = 0;
		try {
			conn = ds.getConnection();
			
			if(cs_notice == 2) {
				String sql = "update CUSTOMERSERVICE\r\n" + 
						"set cs_title=? , cs_content=? ,cs_secret=?" + 
						"where cs_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				pstmt.setInt(3, cs_secret);				
//				pstmt.setInt(3, cs_notice);
				pstmt.setInt(4, cs_num);
			}else {
				String sql = "update CUSTOMERSERVICE\r\n" + 
						"set cs_title=? , cs_content=? ,cs_notice=?" + 
						"where cs_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				pstmt.setInt(3, cs_notice);
				pstmt.setInt(4, cs_num);
//				pstmt.setInt(5, cs_secret);								
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("csEdit() 성공!!");
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("csEdit() Error");
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
		return result;
	}
	
	@SuppressWarnings("finally")
	public boolean csRewrite(String title, String content, String id, int cs_num, int cs_refer, int cs_depth, int cs_step, int cs_secret) {
		boolean result = false;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			String sql = "update CUSTOMERSERVICE\r\n" + 
					"    set cs_depth= ?\r\n" + 
					"    where cs_refer=? and cs_depth>?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cs_depth+1);
			pstmt.setInt(2, cs_refer);
			pstmt.setInt(3, cs_depth);
			rs = pstmt.executeQuery();
//			if(rs.next()) {
//			}
			System.out.println("1차 성공");
			sql = "insert into customerservice(CS_NUM, CS_REFER, CS_DEPTH, CS_STEP, CS_NOTICE, ID, CS_TITLE, CS_CONTENT, CS_COUNT, CS_DATE, CS_SECRET) \r\n" + 
					"    values(CUSTOMERSERVICE_CS_NUM.nextval, ?, ?, ?, 0, ?, ?, ?, 0, sysdate)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cs_refer);
			pstmt.setInt(2, cs_depth+1);
			pstmt.setInt(3, cs_step+1);
			pstmt.setString(4, id);
			pstmt.setString(5, title);
			pstmt.setString(6,content);
			pstmt.setInt(7, cs_secret);
			System.out.println("2차 실행 중");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = true;
				System.out.println("2차 성공");
				conn.commit();
			}
		} catch (SQLException e) {
			System.out.println("csRewrite() 오류");
			conn.rollback();
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}

	}
}








