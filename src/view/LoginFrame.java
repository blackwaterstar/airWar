package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;


import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import dao.FileDao;
import entitise.User;
import mygame.GameMain;




public class LoginFrame extends JFrame {
	private JLabel username_Label, password_Label, background_Label;
	private JTextField username_Text;
	private JPasswordField password_Text;
	private JButton login_Button, register_Button;
	private Font scrip_Song = new Font("宋体", Font.TYPE1_FONT, 20);
	private ImageIcon loginImage;
	private JPanel panel;
	private JFrame jFrame;
	private User user = new User();// 创建用户对象
	//private ClientInfo  clientInfo = new ClientInfo ();

	public LoginFrame() {
		jFrame = new JFrame("登录页面");
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		// 界面背景图片
		// ImageIcon icon=new ImageIcon("src/image/login.jpg");
		loginImage = new ImageIcon(this.getClass().getResource("/image/login.jpg"));
		// 将图片放入background_Label中
		background_Label = new JLabel(loginImage);
		// 设置label的大小
		background_Label.setBounds(0, 0, loginImage.getIconWidth(), loginImage.getIconHeight());
		// 把内容窗格转化为JPanel，并且用方法setOpaque()来使内容窗格透明
		JPanel bottom_panel = (JPanel) jFrame.getContentPane();
		bottom_panel.setOpaque(false);// 不绘制所有像素，可以显示面板上的多层控件
		// 把背景图片添加到分层窗格的最底层作为背景
		jFrame.getLayeredPane().add(background_Label, new Integer(Integer.MIN_VALUE));//标签添加到层面板最底层
//		getContentPane().setLayout(null);
//		getContentPane().add(background_Label);
//		background_Label.setOpaque(false);
//		jFrame.add(background_Label);

		//panel.setBackground(Color.WHITE);// 设置背景颜色。
		username_Label = new JLabel("用户名"); // 创建"用户名"标签。
		username_Label.setFont(scrip_Song);
		username_Label.setForeground(Color.GRAY);
		username_Label.setBounds(80, 80, 80, 20); // 设置"用户名"标签位置。
		panel.add(username_Label); // 添加"用户名"标签。

		username_Text = new JTextField(); // 创建"用户名"文本域。
		username_Text.setBounds(160, 80, 120, 25); // 设置"用户名"文本域位置。
		panel.add(username_Text); // 添加"用户名"文本域。

		password_Label = new JLabel("密码"); // 创建"密码"标签。
		password_Label.setFont(scrip_Song);
		password_Label.setForeground(Color.GRAY);
		password_Label.setBounds(80, 140, 50, 20);
		panel.add(password_Label); // 添加"密码"标签。

		password_Text = new JPasswordField(); // 创建"密码"文本域。
		password_Text.setBounds(160, 140, 120, 25); // 设置"密码"文本域位置。
		panel.add(password_Text); // 添加"密码"文本域。

		login_Button = new JButton("登录"); // 创建"登录"按钮。
		login_Button.setBounds(100, 210, 60, 20); // 设置"登录"按钮位置。
		login_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String username = username_Text.getText().trim();
				String password = new String(password_Text.getPassword());
//				User user = new User();// 创建用户对象
//				ClientInfo  clientInfo = new ClientInfo ();
				user.setUsername(username);
				user.setPassword(password);
				FileDao filecheck = new FileDao();
				if (username.equals("")) {
					JOptionPane.showMessageDialog(jFrame, "用户名不能为空", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (password.equals("")) {
					JOptionPane.showMessageDialog(jFrame, "密码不能为空", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (!filecheck.read(user)) {
					JOptionPane.showMessageDialog(jFrame, "账号或密码错误，请重新登录", "", JOptionPane.WARNING_MESSAGE);
					Reset();
					username_Text.requestFocus();
					return;
				}

				if (filecheck.read(user)) {
					JOptionPane.showMessageDialog(jFrame, "登陆成功！");
					jFrame.dispose();// 关闭当前窗口。
					GameMain gameMain = new GameMain(user);
					//gameMain.setVisible(true);
					return ;
				}
			}
		});
		panel.add(login_Button); // 添加"登录"按钮 。

		register_Button = new JButton("注册"); // 创建"注册"按钮。
		register_Button.setBounds(250, 210, 60, 20); // 设置"注册"按钮位置。
		register_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
				RegisterFrame register = new RegisterFrame();
			}
		});
		panel.add(register_Button);

		username_Text.addFocusListener(new FocusListener() {
			Border oldBorder;

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO 自动生成的方法存根
				// 还原旧边框
				username_Text.setBorder(oldBorder);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO 自动生成的方法存根
				// 保存旧边框
				oldBorder = username_Text.getBorder();
				// 设置一个新边框
				/*
				 * 创建具有指定红色、绿色和蓝色值的不透明的 sRGB 颜色， 这些值都在 (0 - 255) 的范围内。绘制时实际使用的颜色取决于
				 * 从给出的可用于给定输出设备的颜色空间中找到的最匹配颜色。alpha 值的默认值为 255。
				 */
				username_Text.setBorder(BorderFactory.createLineBorder(new Color(0, 128, 255)));
			}
		});

		password_Text.addFocusListener(new FocusListener() {
			Border oldBorder;

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO 自动生成的方法存根
				// 还原旧边框
				password_Text.setBorder(oldBorder);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO 自动生成的方法存根
				// 保存旧边框
				oldBorder = password_Text.getBorder();
				// 设置一个新边框
				password_Text.setBorder(BorderFactory.createLineBorder(new Color(0, 128, 255)));
			}
		});

		password_Text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				// TODO 自动生成的方法存根
				if (event.getKeyText(event.getKeyCode()).compareToIgnoreCase("Enter") == 0) {
					login_Button.doClick();// enter键连接登录按钮
				}
			}
		});

		
		// panel加入创建的画布中
		jFrame.add(panel);
		jFrame.setSize(loginImage.getIconWidth(), loginImage.getIconHeight());// 窗体大下
		jFrame.setResizable(false);// 不可更改窗体大小
		jFrame.setLocationRelativeTo(null); // 设置窗口居中
		jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);// 默认关闭方式
		jFrame.setVisible(true);// 窗口可见
		try {
			// ImageIO.read(this.getClass().getResource("/image/schoolbadge.jpg"));
			Image img = ImageIO.read(this.getClass().getResource("/image/schoolbadge.jpg"));
			jFrame.setIconImage(img);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
//	// udp协议
//	private boolean userLogin(User user) {
//		byte[] receiveBuf = new byte[1024];// 接收用
//		InetAddress address;
//		boolean b = false;
//		try {
//			address = InetAddress.getByName(Client.SERVER_IP);
//			String msg = "LOGIN->" + user.getUsername() + "->" + user.getPassword();
//			byte[] sendBuf = msg.getBytes();// 发送用
//			DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, address, Client.SERVER_PORT);
//			// DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, address);
//			Client.socket.send(packet);
//			System.out.println("发送登录请求");
//			clientInfo.setUsername(user.getUsername());
//			clientInfo.setPort(Client.SERVER_PORT);
//			clientInfo.setAddress(address);
//			// 接收
//			packet = new DatagramPacket(receiveBuf, receiveBuf.length, address, Client.SERVER_PORT);
//			// packet = new DatagramPacket(receiveBuf, receiveBuf.length, address);
//			Client.socket.receive(packet);
//			byte[] data = packet.getData();
//			ByteArrayInputStream bais = new ByteArrayInputStream(data);
//			// ObjectInputStream ois = new ObjectInputStream(bais);
//			DataInputStream ois = new DataInputStream(new BufferedInputStream(bais));
//			b = ois.readBoolean();
//			//System.out.println(b);
//		} catch (IOException e) {
//			// TODO 自动生成的 catch 块
//			//e.printStackTrace();
//			System.out.println("账号密码错误，请求驳回");
//		}
//		return b;
//	}

	

	// 重置
	public void Reset() {
		username_Text.setText("");
		password_Text.setText("");
		username_Text.requestFocus();
	}

}
