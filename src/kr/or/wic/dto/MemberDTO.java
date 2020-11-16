package kr.or.wic.dto;

public class MemberDTO {
	private String id;
	private String pwd;
	private String name;
	private String addr;
	private String profile_pic;
	private int closet_num;
	private int loginCount;
	
	public int getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getProfile_pic() {
		return profile_pic;
	}
	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}
	public int getCloset_num() {
		return closet_num;
	}
	public void setCloset_num(int closet_num) {
		this.closet_num = closet_num;
	}
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", addr=" + addr + ", profile_pic="
				+ profile_pic + ", closet_num=" + closet_num + "]";
	}
}