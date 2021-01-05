package movie;

import java.sql.Date;

public class UserVO {
	private String id;
	private String pass;
	private String email;
	private String tel;
	private Date date;
	
	public UserVO(String id, String pass, String email, String tel, Date date) {
		this.id = id;
		this.pass = pass;
		this.email = email;
		this.tel = tel;
		this.date = date;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
}
