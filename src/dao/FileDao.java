package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import entitise.User;


public class FileDao {
	User user;
	Vector<User> users = new Vector<User>();
	public static File file = new File("user.txt");
	static {
		try {
			file.createNewFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	private BufferedReader bReader = null;
	private BufferedWriter bWriter = null;

	// 获得用户
	public User getUser(User user) {
		boolean b = false;
		// Vector<User> users1 = new Vector<User>();
		User newuser = new User();
		// BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(file));
			String nowline = null;
			while ((nowline = bReader.readLine()) != null) {
				String[] string = nowline.split("->");
				// user1.setUsername(string[0]);
				// user1.setPassword(string[1]);
				// user1.setIslogin(new Integer(string[2]));
				// users1.add(user1);
				if (user.getUsername().equals(string[0])) {
					newuser.setUsername(string[0]);
					newuser.setPassword(string[1]);
					newuser.setIslogin(string[2]);
					break;
				}

			}
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			System.out.println("user.text文件找不到");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return newuser;
	}

	/**
	 * 修改用户"是否"登陆状态 user
	 * 
	 */
	public boolean update_IsLogin(User user) {
		boolean b = true;
		try {
			bReader = new BufferedReader(new FileReader(file));
			String nowline = null;
			while ((nowline = bReader.readLine()) != null) {
				String[] string = nowline.split("->");
				// user1.setUsername(string[0]);
				// user1.setPassword(string[1]);
				// user1.setIslogin(new Integer(string[2]));
				// users1.add(user1);
				if (user.getUsername().equals(string[0])) {

					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return b;
	}

//	//删除
//	public boolean deleteUser(User user) {
//		boolean b = false;
//		String old = user.getUsername() + user.getPassword() + user.getIslogin();
//		// BufferedReader bReader = null;
//		try {
//			StringBuffer sBuffer = new StringBuffer();;
//			bReader = new BufferedReader(new FileReader(file));
//			String nowline = null;
//			while ((nowline = bReader.readLine()) != null) {
//				if (nowline.startsWith(user.getUsername())) {
//					sBuffer.append(nowline);
//					sBuffer.replace(user.getUsername().length() + user.getPassword().length() + 5,
//							user.getUsername().length() + user.getPassword().length() + 6, user.getIslogin());
//					sBuffer.append(System.getProperty("line.separator"));
//
//				}
//
//			}
//			
//			BufferedWriter bWriter = null;
//			try {
//				bWriter = new BufferedWriter(new FileWriter(file));
//				bWriter.write(sBuffer.toString());
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				if (bWriter != null) {
//					try {
//						bWriter.close();
//					} catch (IOException e) {
//						bWriter = null;
//					}
//				}
//			}
//		} catch (FileNotFoundException e) {
//
//			System.out.println("user.text文件找不到");
//		} catch (IOException e) {
//
//			System.out.println("登录失败，请输入正确的用户名和密码");
//		}
//
//		return b;
//
//	}

	// 读取文件
	public boolean read(User user) {
		boolean b = false;
		// BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(file));
			String nowline = null;
			while ((nowline = bReader.readLine()) != null) {
				String[] string = nowline.split("->");
				if (string[0].equals(user.getUsername()) && string[1].equals(user.getPassword())) {
					b = true;
				}
			}
		} catch (FileNotFoundException e) {

			System.out.println("user.text文件找不到");
		} catch (IOException e) {

			System.out.println("登录失败，请输入正确的用户名和密码");
		}

		return b;

	}

	// 写入文件
	public boolean write(User user) {
		boolean b = true;
		// BufferedWriter bWriter = null;
		try {
			bWriter = new BufferedWriter(new FileWriter(file, true));
			bWriter.write(user.getUsername() + "->" + user.getPassword() + "->0");
			bWriter.newLine();
			bWriter.flush();
		} catch (IOException e) {
			b = false;
			System.out.println("user.text文件找不到");
		}
		return b;
	}

	// 重置登录状况
	// public boolean reset_Login(User uesr) {
	// boolean b = false;
	//
	//
	// }

	public static String randomCode() {
		StringBuilder str = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			str.append(random.nextInt(10));
		}
		return str.toString();
	}

	// 关闭数据库资源
	public void close() {
		try {
			if (bReader != null)
				bReader.close();
			if (bWriter != null)
				bWriter.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

}
