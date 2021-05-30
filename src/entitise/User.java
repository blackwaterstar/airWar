package entitise;

import dao.Check;

public class User implements Check {
	private String username;
	private String password;
	private String islogin = "0";
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}

	private long score = 0L;
	
	public String getIslogin() {
		return islogin;
	}
	public void setIslogin(String islogin) {
		this.islogin = islogin;
	}
	public String getUsername() {
		return username;
	}
	public User() {
		super();
		// TODO 自动生成的构造函数存根
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public int getIslogin() {
//		return islogin;
//	}
//	public void setIslogin(int islogin) {
//		this.islogin = islogin;
//	}
	
	public boolean checkusername() {
		boolean b = false;
		String regex="^[A-Za-z]{1,10}$";//用户名必须是纯字母的1-10位，区分大小写
		if (this.username.matches(regex)) {
			b = true;
		}
		return b;
	}
	
	public boolean checkpassword() {
		
		boolean b = false;
		String regex="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$";//密码必须是数字和字母混合的6-15位，区分大小写
		if (this.password.matches(regex)) {
			b = true;
		}
		return b;
	}
	
//	public boolean checkunumber(String string) {
//		
//		boolean b = false;
//		String regex="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$";
//		if (string.matches(regex)) {
//			b = true;
//		}
//		return b;
//	}

}
