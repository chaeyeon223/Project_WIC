package kr.or.wic.dto;

import java.util.Date;

public class CustomerServiceDTO {
	private int cs_num;			//게시물 고유번호
	private String cs_title;	//게시물 제목
	private String cs_content;	//게시물 내용
	private int cs_reffer;		//게시물 그룹 
	private int cs_depth;		//게시물 그룹 내 순서
	private int cs_step;		//답글 들여쓰기
	private int cs_count;		//조회수
	private Date cs_date;		//작성일자 
	private int cs_notice;		//공지사항여부  ->  0:일반글, 1:공지글
	private String id;			//작성자 아이디
	private String name;		//작성자 이름
	private int cs_delete;		//삭제여부 -> 0:삭제X, 1:삭제O
	private int cs_secret;		//비밀여부 -> 0:공개, 1:비공개
	
	
	public int getCs_delete() {
		return cs_delete;
	}
	public void setCs_delete(int cs_delete) {
		this.cs_delete = cs_delete;
	}
	public int getCs_secret() {
		return cs_secret;
	}
	public void setCs_secret(int cs_secret) {
		this.cs_secret = cs_secret;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCs_num() {
		return cs_num;
	}
	public void setCs_num(int cs_num) {
		this.cs_num = cs_num;
	}
	public String getCs_title() {
		return cs_title;
	}
	public void setCs_title(String cs_title) {
		this.cs_title = cs_title;
	}
	public String getCs_content() {
		return cs_content;
	}
	public void setCs_content(String cs_content) {
		this.cs_content = cs_content;
	}

	public int getCs_reffer() {
		return cs_reffer;
	}
	public void setCs_reffer(int cs_reffer) {
		this.cs_reffer = cs_reffer;
	}
	public int getCs_depth() {
		return cs_depth;
	}
	public void setCs_depth(int cs_depth) {
		this.cs_depth = cs_depth;
	}
	public int getCs_step() {
		return cs_step;
	}
	public void setCs_step(int cs_step) {
		this.cs_step = cs_step;
	}
	public int getCs_count() {
		return cs_count;
	}
	public void setCs_count(int cs_count) {
		this.cs_count = cs_count;
	}
	public Date getCs_date() {
		return cs_date;
	}
	public void setCs_date(Date cs_date) {
		this.cs_date = cs_date;
	}
	public int getCs_notice() {
		return cs_notice;
	}
	public void setCs_notice(int cs_notice) {
		this.cs_notice = cs_notice;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CustumoerServiceDTO [cs_num=" + cs_num + ", cs_title=" + cs_title + ", cs_content=" + cs_content
				+ ", cs_reffer=" + cs_reffer + ", cs_depth=" + cs_depth + ", cs_step="
				+ cs_step + ", cs_count=" + cs_count + ", cs_date=" + cs_date + ", cs_notice=" + cs_notice + ", id="
				+ id + "]";
	}
}
