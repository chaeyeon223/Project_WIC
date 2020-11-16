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
import kr.or.wic.dto.FilesDTO;
import kr.or.wic.dto.ProductDTO;

public class ProductDAO {
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
	
	//1.상품정보 조회(return the product list)
	public List<ProductDTO> getAllProductList(){
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		
		try {
			conn = ds.getConnection();
			
			String sql = "select prd_num, prd_title, prd_price, prd_date, prd_content, prd_state, prd_count, closet_num from product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO product = new ProductDTO();
				
				product.setPrd_num(rs.getInt("prd_num"));
				product.setPrd_title(rs.getString("prd_title"));
				product.setPrd_price(rs.getInt("prd_price"));
				product.setPrd_date(rs.getDate("prd_date"));
				product.setPrd_content(rs.getString("prd_content"));
				product.setPrd_state(rs.getInt("prd_state"));
				product.setPrd_count(rs.getInt("prd_count"));
				product.setCloset_num(rs.getInt("closet_num"));
				
				productList.add(product);
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
		return productList;
	}
	//1-0-1. 상품별 대표 사진 뽑기
	private List<Integer> getProductPic(int currentPage, int pageSize){ 
		List<Integer> pic = new ArrayList<Integer>();
		int startRow = (currentPage-1) * pageSize +1;
		int endRow = startRow + pageSize-1;
		System.out.println(startRow);
		System.out.println(endRow);
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT * from\n" + 
					"(SELECT rownum as rnum , num \n" + 
					"FROM(select min(f.files_num) as num\n" + 
					"from product p join files f ON p.prd_num = f.prd_num\n" + 
					"GROUP BY p.prd_num order by p.prd_num DESC) a) WHERE rnum >=? AND rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				pic.add(rs.getInt("num"));
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return pic;
	}
	//1-0-2. 상품별 대표 사진 뽑기(byId)
	private List<Integer> getProductPic(String id){
		List<Integer> pic = new ArrayList<Integer>();
		
		try {
			conn = ds.getConnection();
			String sql = "select min(f.files_num) as num from product p join files f ON p.prd_num = f.prd_num where f.id=? GROUP BY p.prd_num order by p.prd_num desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				pic.add(rs.getInt("num"));
				
			}
		}catch (Exception e) {
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
			
		return pic;
	}
	
	//1-1-1.상품정보 조회(ProductListPage 정보만 조회(prd_num, prd_title, prd_content)
	public List<ProductDTO> getProductNumTitleContentList(int currentPage, int pageSize){
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		List<Integer> picNumList = getProductPic(currentPage, pageSize);
		
		try {
				conn = ds.getConnection();
			for(int i : picNumList) {
				String sql = "select p.prd_num, p.prd_title, p.prd_content, f.files_name, f.files_path from product p join files f on p.prd_num = f.prd_num\n" + 
						"WHERE f.files_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					FilesDTO file = new FilesDTO();
					file.setFiles_name(rs.getString("files_name"));
					file.setFiles_path(rs.getString("files_path"));
					
					ProductDTO product = new ProductDTO();
					product.setPrd_num(rs.getInt("prd_num"));
					product.setPrd_title(rs.getString("prd_title"));
					product.setPrd_content(rs.getString("prd_content"));
					product.setFiles(file);
					
					productList.add(product);
				}
			}
				
			
		} catch (SQLException e) {
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
		}
		return productList;
	}
	
	//1-1-2.상품정보 조회(개별 회원이 등록한 상품의 첫번째 사진 조회 >> 해당 사진의 정보 조회)
	public List<ProductDTO> getEachMemberAllProductAndFileList(String id){
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		List<Integer> picNumList = getProductPic(id);
		
		try {
			conn = ds.getConnection();
			for(int i : picNumList) {
				String sql = "select p.prd_num, p.prd_title, p.prd_price, p.prd_date, p.prd_content, p.prd_state, p.prd_count, p.closet_num, f.files_name, f.files_path from product p join files f on p.prd_num = f.prd_num WHERE f.files_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					ProductDTO product = new ProductDTO();
					FilesDTO file = new FilesDTO();
					file.setFiles_name(rs.getString("files_name"));
					file.setFiles_path(rs.getString("files_path"));
					product.setFiles(file);
					
					product.setPrd_num(rs.getInt("prd_num"));
					product.setPrd_title(rs.getString("prd_title"));
					product.setPrd_price(rs.getInt("prd_price"));
					product.setPrd_date(rs.getDate("prd_date"));
					product.setPrd_content(rs.getString("prd_content"));
					product.setPrd_state(rs.getInt("prd_state"));
					product.setPrd_count(rs.getInt("prd_count"));
					product.setCloset_num(rs.getInt("closet_num"));
					
					productList.add(product);
				}
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
		return productList;
	}
	
	//1-2.closet_num으로 prd_num 조회
	public int getCloset_numByPrd_num(int closet_num) {
		int prd_num = 0;
		try {
			conn = ds.getConnection();
			String sql = "select prd_num from product where closet_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, closet_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				prd_num = rs.getInt("prd_num");
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
		return prd_num;
	}
	
	//2.회원별 상품 조회(return the productList by ID)
	public List<ProductDTO> getProductListById(String id) {
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		
		try {
			conn = ds.getConnection();
			String sql = "select p.prd_num, p.prd_title, p.prd_price, p.prd_date, p.prd_content, p.prd_state, p.prd_count, p.closet_num from product p, member m where p.closet_num = m.closet_num and m.id= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO product = new ProductDTO();
				
				product.setPrd_num(rs.getInt("prd_num"));
				product.setPrd_title(rs.getString("prd_title"));
				product.setPrd_price(rs.getInt("prd_price"));
				product.setPrd_date(rs.getDate("prd_date"));
				product.setPrd_content(rs.getString("prd_content"));
				product.setPrd_state(rs.getInt("prd_state"));
				product.setPrd_count(rs.getInt("prd_count"));
				product.setCloset_num(rs.getInt("closet_num"));
				
				productList.add(product);
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
		return productList;
	}
	
	//2-1.상품번호로 상품 조회(return the product by prd_num)
	public ProductDTO getProduct(int prd_num) {
		ProductDTO product = new ProductDTO();
		try {
			conn = ds.getConnection();
			String sql = "select prd_title, prd_price, prd_date, prd_content, prd_state, prd_count, closet_num from product where prd_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prd_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				product.setPrd_num(prd_num);
				product.setPrd_title(rs.getString("prd_title"));
				product.setPrd_price(rs.getInt("prd_price"));
				product.setPrd_date(rs.getDate("prd_date"));
				product.setPrd_content(rs.getString("prd_content"));
				product.setPrd_state(rs.getInt("prd_state"));
				product.setPrd_count(rs.getInt("prd_count"));
				product.setCloset_num(rs.getInt("closet_num"));
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
		return product;
	}
	
	//2-2.prd_seq.currval 조회
	public int getPrd_seqCurrval() {
		int prd_num = 0;
		try {
			conn = ds.getConnection();
			String sql = "select prd_seq.currval from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				prd_num = rs.getInt("currval");
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
		return prd_num;
	}
	
	//3.상품 정보 등록(insert the new product)
	public int insertProduct(ProductDTO product) {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into product(prd_num, prd_title, prd_price, prd_date, prd_content, prd_state, prd_count, closet_num)"
						+ "values(prd_seq.nextval,?,?,sysdate,?,0,0,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, product.getPrd_title());
			pstmt.setInt(2, product.getPrd_price());
			pstmt.setString(3, product.getPrd_content());
			pstmt.setInt(4, product.getCloset_num());
			
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
	
	//4.상품 정보 수정(update the information of product)
	public int updateProduct(int prd_num, String prd_title, int prd_price, String prd_content, int prd_state) {
		int row =0;
		
		try {
			conn = ds.getConnection();
			String sql = "update product set prd_title=? , prd_price=? , prd_content=? , prd_state=? where prd_num=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, prd_title);
			pstmt.setInt(2, prd_price);
			pstmt.setString(3, prd_content);
			pstmt.setInt(4, prd_state);
			pstmt.setInt(5, prd_num);
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
	
	//5.상품 정보 삭제(delete the product)
	public int deleteProduct(int prd_num) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "delete from product where prd_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prd_num);
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
	
	//6.파일 등록 (upload the file)
	public int updateFile(String files_name, String files_path) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "select max(prd_num) from product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				sql = "insert into files (files_num,files_name,files_path,prd_num) values(prd_num.nextval,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, files_name);
				pstmt.setString(2, files_path);
				pstmt.setInt(3, rs.getInt("max(prd_num)"));
				row = pstmt.executeUpdate();
				
			}else {
				System.out.println("rs 없음 ");
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
		return row;
	}
	
	public int deleteFile(String files_name) {
		int row=0;
		try {
			conn = ds.getConnection();
			String sql = "delete from files where files_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, files_name);
			row = pstmt.executeUpdate();
			
		}catch (SQLException e) {
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
	// 0검색기능
		// 검색하면 데이터를 디비에서 찾아서 가져옴
		// 디비에서 LIKE구문으로 특정검색
		public ArrayList<ProductDTO> search(String productName) {
			//DTO를 담는 list 생성 search(String productName) 매개변수를 가지고있는 search 함수 
			//검색한 단어를 포함하면 나오게 함 LIKE
			ArrayList<ProductDTO> productList = new ArrayList<ProductDTO>();
				
			//리스트 초기화 
			try {
				conn = ds.getConnection();
				String sql = "select * from product where prd_title like '%'||?||'%'";
				pstmt = conn.prepareStatement(sql);//연결된 데이터베이스를 넣어줌 
				pstmt.setString(1,productName); //? 안에 파라미터값 넣어줌 
				System.out.println("1"+productName);
				rs = pstmt.executeQuery(); //결과가 나오면 실행시켜서  rs  담는다 
				
				while(rs.next()) {
					ProductDTO product = new ProductDTO();
					//뽑아오는 내용 
					product.setPrd_num(rs.getInt("Prd_num"));
					product.setPrd_title(rs.getString("Prd_title"));
					product.setPrd_price(rs.getInt("Prd_price"));
					product.setPrd_date(rs.getDate("Prd_date"));
					product.setPrd_content(rs.getString("Prd_content"));
					product.setPrd_state(rs.getInt("Prd_state"));
					product.setPrd_count(rs.getInt("Prd_count"));
					product.setCloset_num(rs.getInt("Closet_num"));
					productList.add(product);
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
			return productList;
		}
		
		//7 총 게시물 수 
		
		public int countAllProductList() {
			int result = 0;
			try {
				conn  = ds.getConnection();
				String sql = "select count(*) from product";
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
		
	//(최신)상품 정보 수정(update the information of product)
	public int updateProduct(ProductDTO product) {
		int row = 0;
		
		try {
			conn = ds.getConnection(); //prd_title, prd_price, prd_date, prd_content, closet_num //prd_state, prd_count처리 필요
			String sql = "update product set prd_title=? , prd_price=? , prd_date=sysdate, prd_content=?, prd_state=0 where prd_num=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, product.getPrd_title());
			pstmt.setInt(2, product.getPrd_price());
			pstmt.setString(3, product.getPrd_content());
			pstmt.setInt(4, product.getPrd_num());
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
	
	//1-0-3. cart에서 찜한 상품 번호로 files의 대표사진 뽑기
	private List<Integer> getPrdNumsInCartById(String id){
		List<Integer> cartPicList = new ArrayList<Integer>();
		
		try {
			conn = ds.getConnection();
			String sql = "select min(f.files_num) as num from cart c join files f ON c.prd_num = f.prd_num where c.id=? GROUP BY c.prd_num order by c.prd_num desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cartPicList.add(rs.getInt("num"));
				
			}
		}catch (Exception e) {
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
			
		return cartPicList;
	}
	
	//1-1-3.상품정보 조회(개별 회원이 찜한 상품의 첫번째 사진 조회 >> 해당 사진의 정보 조회)
	public List<ProductDTO> getEachMemberAllCartProductAndFileList(String id){
		List<ProductDTO> cartProductList = new ArrayList<ProductDTO>();
		List<Integer> cartPicList = getPrdNumsInCartById(id);
		
		try {
			conn = ds.getConnection();
			for(int i : cartPicList) {
				String sql = "select p.prd_num, p.prd_title, p.prd_price, p.prd_date, p.prd_content, p.prd_state, p.prd_count, p.closet_num, f.files_name, f.files_path from product p join files f on p.prd_num = f.prd_num WHERE f.files_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					ProductDTO product = new ProductDTO();
					FilesDTO file = new FilesDTO();
					file.setFiles_name(rs.getString("files_name"));
					file.setFiles_path(rs.getString("files_path"));
					product.setFiles(file);
					
					product.setPrd_num(rs.getInt("prd_num"));
					product.setPrd_title(rs.getString("prd_title"));
					product.setPrd_price(rs.getInt("prd_price"));
					product.setPrd_date(rs.getDate("prd_date"));
					product.setPrd_content(rs.getString("prd_content"));
					product.setPrd_state(rs.getInt("prd_state"));
					product.setPrd_count(rs.getInt("prd_count"));
					product.setCloset_num(rs.getInt("closet_num"));
					
					cartProductList.add(product);
				}
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
		return cartProductList;
	}
}