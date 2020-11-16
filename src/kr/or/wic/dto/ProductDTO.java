package kr.or.wic.dto;

import java.util.Date;

public class ProductDTO {
	private int prd_num;
	private String prd_title;
	private int prd_price;
	private Date prd_date;
	private String prd_content;
	private int prd_state;
	private int prd_count;
	private int closet_num;
	private FilesDTO files;

	public FilesDTO getFiles() {
		return files;
	}
	public void setFiles(FilesDTO files) {
		this.files = files;
	}
	public int getPrd_num() {
		return prd_num;
	}
	public void setPrd_num(int prd_num) {
		this.prd_num = prd_num;
	}
	public String getPrd_title() {
		return prd_title;
	}
	public void setPrd_title(String prd_title) {
		this.prd_title = prd_title;
	}
	public int getPrd_price() {
		return prd_price;
	}
	public void setPrd_price(int prd_price) {
		this.prd_price = prd_price;
	}
	public Date getPrd_date() {
		return prd_date;
	}
	public void setPrd_date(Date prd_date) {
		this.prd_date = prd_date;
	}
	public String getPrd_content() {
		return prd_content;
	}
	public void setPrd_content(String prd_content) {
		this.prd_content = prd_content;
	}
	public int getPrd_state() {
		return prd_state;
	}
	public void setPrd_state(int prd_state) {
		this.prd_state = prd_state;
	}
	public int getPrd_count() {
		return prd_count;
	}
	public void setPrd_count(int prd_count) {
		this.prd_count = prd_count;
	}
	public int getCloset_num() {
		return closet_num;
	}
	public void setCloset_num(int closet_num) {
		this.closet_num = closet_num;
	}
	@Override
	public String toString() {
		return "ProductDTO [prd_num=" + prd_num + ", prd_title=" + prd_title + ", prd_price=" + prd_price
				+ ", prd_date=" + prd_date + ", prd_content=" + prd_content + ", prd_state=" + prd_state
				+ ", prd_count=" + prd_count + ", closet_num=" + closet_num + "]";
	}
}
