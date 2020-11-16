package kr.or.wic.dto;

import java.text.DecimalFormat;

public class PriceFormat {
	String priceFormat;
	
	//priceFormat 변환
	//'원' 제외, 콤마처리만
	public String makeComma(int price) {
		DecimalFormat formatter = new DecimalFormat("###,###");
		String result = formatter.format(price);
		
		return result;
	}
	
	public String makeComma(Long price) {
		DecimalFormat formatter = new DecimalFormat("###,###");
		String result = formatter.format(price);
		
		return result;
	}
	
	public String makeComma(Double price) {
		DecimalFormat formatter = new DecimalFormat("###,###");
		String result = formatter.format(price);
		
		return result;
	}
	
	public String makeComma(String price) {
		DecimalFormat formatter = new DecimalFormat("###,###");
		String result = formatter.format(price);
		
		return result;
	}
	
	//'원' 포함
	public String makeCommaWon(int price) {
		DecimalFormat formatter = new DecimalFormat("###,###");
		String result = formatter.format(price) + "원";
		
		return result;
	}
	
	public String makeCommaWon(Long price) {
		DecimalFormat formatter = new DecimalFormat("###,###");
		String result = formatter.format(price) + "원";
		
		return result;
	}
	
	public String makeCommaWon(Double price) {
		DecimalFormat formatter = new DecimalFormat("###,###");
		String result = formatter.format(price) + "원";
		
		return result;
	}
	
	public String makeCommaWon(String price) {
		DecimalFormat formatter = new DecimalFormat("###,###");
		String result = formatter.format(price) + "원";
		
		return result;
	}

	public String getPriceFormat() {
		return priceFormat;
	}

	public void setPriceFormat(String priceFormat) {
		this.priceFormat = priceFormat;
	}

	@Override
	public String toString() {
		return "PriceFormat [priceFormat=" + priceFormat + "]";
	}
}
