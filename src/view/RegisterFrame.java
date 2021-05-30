package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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
import model.CheckNumber;


public class RegisterFrame extends JFrame {
	private JLabel username_Label, password_Label, repassword_Label;
	private JLabel checkNumber_Label,change_Label, background_Label,checkIcon_Label;
	private JTextField username_Text, checknember_text;
	private JPasswordField password_Text, repassword_Text;
	private JButton register_Button, reset_Button, return_Button;
	private Font scrip = new Font("宋体", Font.TYPE1_FONT, 20);
	private ImageIcon registerImage;
	private JPanel panel;
	private CheckNumber checkNumber; 
	JFrame jFrame = new JFrame("注册页面");

	public RegisterFrame() {

		// 界面背景图片
		// ImageIcon icon=new ImageIcon("src/image/login.jpg");
		registerImage = new ImageIcon(this.getClass().getResource("/image/register2.jpg"));
		// Image im=new Image(icon);
		// 将图片放入background_Label中
		background_Label = new JLabel(registerImage);

		// 设置label的大小
		background_Label.setBounds(0, 0, registerImage.getIconWidth(), registerImage.getIconHeight());
		// 把内容窗格转化为JPanel，并且用方法setOpaque()来使内容窗格透明
		JPanel bottom_panel = (JPanel) jFrame.getContentPane();
		bottom_panel.setOpaque(false);// 不绘制所有像素，可以显示面板上的多层控件
		// 把背景图片添加到分层窗格的最底层作为背景
		jFrame.getLayeredPane().add(background_Label, new Integer(Integer.MIN_VALUE));

		String number = FileDao.randomCode();
		
		panel = new JPanel();
		panel.setOpaque(false);
		// 设置布局
		panel.setLayout(null);
		
		username_Label = new JLabel("用户名");
		username_Label.setFont(scrip);
		username_Label.setForeground(Color.GRAY);
		username_Label.setBounds(100, 45, 200, 40);
		panel.add(username_Label);

		password_Label = new JLabel("密 码");
		password_Label.setFont(scrip);
		password_Label.setForeground(Color.GRAY);
		password_Label.setBounds(100, 130, 200, 40);
		panel.add(password_Label);

		repassword_Label = new JLabel("确认密码");
		repassword_Label.setFont(scrip);
		repassword_Label.setForeground(Color.GRAY);
		repassword_Label.setBounds(100, 210, 200, 40);
		panel.add(repassword_Label);

		checkNumber_Label = new JLabel("验证码" );
		checkNumber_Label.setFont(scrip);
		checkNumber_Label.setForeground(Color.GRAY);
		checkNumber_Label.setBounds(100, 290, 70, 40);
		panel.add(checkNumber_Label);
		
		checkNumber = new CheckNumber();
		checkIcon_Label = new JLabel();
		checkIcon_Label.setIcon(new ImageIcon(checkNumber.getCheckImage()));
		checkIcon_Label.setBounds(420, 285, 130, 50);
		panel.add(checkIcon_Label);
		
		change_Label = new JLabel( "<html><body>"+"看不清"+"<br>"+"换一张"+"<body></html>");
		change_Label.setFont(new Font("仿宋", Font.TYPE1_FONT, 15));
		change_Label.setForeground(Color.BLACK);
		change_Label.setBounds(510,290, 100, 40);
		//监听，鼠标进入文字就变成小手形状
		change_Label.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) // 鼠标进入
			{
				change_Label.setForeground(Color.BLUE);
				change_Label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) // 鼠标移除
			{
				change_Label.setForeground(Color.BLACK);
				setCursor(Cursor.getDefaultCursor());
			}
		});
		change_Label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 1){	
					//重新获取验证码
					checkNumber = new CheckNumber();
					checkIcon_Label.setIcon(new ImageIcon(checkNumber.getCheckImage()));
					checkIcon_Label.setBounds(420, 285, 130, 50);
					panel.add(checkIcon_Label);
				}
			}
		});
		panel.add(change_Label);
		
		// 添加两个输入框，用于输入账号和密码
		username_Text = new JTextField();
		username_Text.setBounds(200, 50, 200, 30);
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
				username_Text.setBorder(BorderFactory.createLineBorder(new Color(0, 128, 255)));
			}
		});
		panel.add(username_Text);
		
		password_Text = new JPasswordField();
		password_Text.setBounds(200, 135, 200, 30);
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
		panel.add(password_Text);
		
		repassword_Text = new JPasswordField();
		repassword_Text.setBounds(200, 215, 200, 30);
		repassword_Text.addFocusListener(new FocusListener() {
			Border oldBorder;

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO 自动生成的方法存根
				// 还原旧边框
				repassword_Text.setBorder(oldBorder);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO 自动生成的方法存根
				// 保存旧边框
				oldBorder = repassword_Text.getBorder();
				// 设置一个新边框
				repassword_Text.setBorder(BorderFactory.createLineBorder(new Color(0, 128, 255)));
			}
		});
		panel.add(repassword_Text);
		
		checknember_text = new JTextField();
		checknember_text.setBounds(200, 295, 200, 30);
		checknember_text.addFocusListener(new FocusListener() {
			Border oldBorder;

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO 自动生成的方法存根
				// 还原旧边框
				checknember_text.setBorder(oldBorder);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO 自动生成的方法存根
				// 保存旧边框
				oldBorder = checknember_text.getBorder();
				// 设置一个新边框
				checknember_text.setBorder(BorderFactory.createLineBorder(new Color(0, 128, 255)));
			}
		});
		panel.add(checknember_text);
		
		checknember_text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				// TODO 自动生成的方法存根
				if (event.getKeyText(event.getKeyCode()).compareToIgnoreCase("Enter") == 0) {
					register_Button.doClick();// enter键连接登录按钮
				}
			}
		});
		
		// 添加按钮
		register_Button = new JButton("注册");
		register_Button.setForeground(Color.BLACK);
		register_Button.setBounds(110, 380, 80, 40);
		register_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = username_Text.getText().trim();// 获得用户名。
				String password = new String(password_Text.getPassword()); // 获得密码。
				String password1 = new String(repassword_Text.getPassword()); // 获得确认密码。
				String checknumber = checknember_text.getText().trim();// 获得验证码。
				User user = new User();// 创建用户对象
				user.setUsername(username);
				user.setPassword(password);
				FileDao filecheck = new FileDao();

				if (username.equals("")) {
					JOptionPane.showMessageDialog(jFrame, "用户名不能为空！", "", JOptionPane.WARNING_MESSAGE);
					Reset();
					recheckNumber();
					return;
				}
				if (password.equals("")) {
					JOptionPane.showMessageDialog(jFrame, "密码不能为空！", "", JOptionPane.WARNING_MESSAGE);
					password_Text.setText("");
					repassword_Text.setText("");
					password_Text.requestFocus();
					recheckNumber();
					return;
				}
				if (!user.checkusername()) {
					JOptionPane.showMessageDialog(jFrame, "用户名格式必须是纯字母的1-10位，区分大小写，请重新输入！", "",
							JOptionPane.WARNING_MESSAGE);
					Reset();
					recheckNumber();
					return;
				}
				if (!user.checkpassword()) {
					JOptionPane.showMessageDialog(jFrame, "密码格式必须是数字和字母混合的6-15位，区分大小写，请重新新输入！", "",
							JOptionPane.WARNING_MESSAGE);
					password_Text.setText("");
					repassword_Text.setText("");
					checknember_text.setText("");
					password_Text.requestFocus();
					recheckNumber();
					return;
				}
				if (password1.equals("")) {
					JOptionPane.showMessageDialog(jFrame, "确认密码不能为空！", "", JOptionPane.WARNING_MESSAGE);
					checknember_text.setText("");
					repassword_Text.requestFocus();
					recheckNumber();
					return;
				}
				if (!password.equals(password1)) {
					JOptionPane.showMessageDialog(jFrame, "两次密码不一致！", "", JOptionPane.WARNING_MESSAGE);
					password_Text.setText("");
					repassword_Text.setText("");
					checknember_text.setText("");
					password_Text.requestFocus();
					recheckNumber();
					return;
				}
				if (!checkNumber.getChecknumber().equals(checknumber)) {
					JOptionPane.showMessageDialog(jFrame, "验证码错误，请重新输入！", "", JOptionPane.WARNING_MESSAGE);
					checknember_text.setText("");
					recheckNumber();
					return;
				}
				if (filecheck.write(user)) {// 注册处理
					JOptionPane.showMessageDialog(jFrame, "注册成功！");
					jFrame.dispose();// 关闭当前窗口
					LoginFrame login = new LoginFrame();// 返回登陆页面。
					return;
				} else {
					JOptionPane.showMessageDialog(jFrame, "注册失败！用户已存在。");
					Reset(); // 重置
					recheckNumber();
					return;
				}

			}
		});
		panel.add(register_Button);
		
		reset_Button = new JButton("重置");
		reset_Button.setForeground(Color.BLACK);
		reset_Button.setBounds(250, 380, 80, 40);
		// 注册"重置"按钮事件监听。
		reset_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Reset();
			}
		});
		panel.add(reset_Button);

		return_Button = new JButton("返回");
		return_Button.setForeground(Color.BLACK);
		return_Button.setBounds(390, 380, 80, 40);
		return_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				LoginFrame login = new LoginFrame();
				jFrame.dispose();
			}
		});
		panel.add(return_Button);

		// 加入创建的画布中
		jFrame.add(panel);
		jFrame.setSize(registerImage.getIconWidth(), registerImage.getIconHeight());// 窗体大下
		jFrame.setResizable(false);// 不可更改窗体大小
		jFrame.setLocationRelativeTo(null); // 设置窗口居中
		jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);// 默认关闭方式
		jFrame.setVisible(true);// 窗口可见	
		try {
			// TODO 自动生成的方法存根
			// ImageIO.read(this.getClass().getResource("/image/schoolbadge.jpg"));
			Image img = ImageIO.read(this.getClass().getResource("/image/schoolbadge.jpg"));
			jFrame.setIconImage(img);

		} catch (IOException e) {
			// TODO 自动生成的方法存根
			e.printStackTrace();
		}

	}
	// 重置
	protected void Reset() {
		// TODO 自动生成的方法存根
		username_Text.setText("");
		password_Text.setText("");
		repassword_Text.setText("");
		checknember_text.setText("");
		username_Text.requestFocus();
	}

	//重新获取验证码
	protected void recheckNumber() {
		// TODO 自动生成的方法存根
		checkNumber = new CheckNumber();
		checkIcon_Label.setIcon(new ImageIcon(checkNumber.getCheckImage()));
		//checkIcon_Label.setBounds(460, 280, 120, 50);
		checkIcon_Label.setBounds(420, 285, 130, 50);
		panel.add(checkIcon_Label);
	}

	
}
