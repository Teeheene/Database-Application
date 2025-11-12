package view;

import model.*;
import controller.*;

import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIView {
	private JFrame frame;
	private Container cp;

	private MainController controller;

	//sets up the initial gui setup before starting the menu
	public GUIView() {}

	public void setListeners(MainController controller) {
		this.controller = controller;
	}
	
	public void setupGUI() {
		GUIUtil.setGlobalFont();
		frame = new JFrame();

		//null layout for custom placing (i hate layouts!!)
		cp = BackgroundPanel.create("assets/main_menu/bg.png");
		frame.setContentPane(cp);
		cp.setLayout(null);

		frame.pack();
		frame.setVisible(true);

		Insets insets = frame.getInsets();

		int targetWidth = 720 + insets.left + insets.right;
		int targetHeight = 480 + insets.top + insets.bottom;

		frame.setSize(targetWidth, targetHeight);
		frame.setResizable(false);
		frame.setTitle("BasketGram");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuPanel();
	}

	//menu panel
	public void menuPanel() {
		cp = BackgroundPanel.create("assets/main_menu/bg.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JButton adminBtn = GUIUtil.createIButton(441,0,86,53);
		JButton loginBtn = GUIUtil.createIButton(527,0,76,53);
		JButton registerBtn = GUIUtil.createIButton(603,0,104,53);
		cp.add(adminBtn);
		cp.add(loginBtn);
		cp.add(registerBtn);

		adminBtn.addActionListener(e -> controller.handleMenu("admin"));
		loginBtn.addActionListener(e -> controller.handleMenu("login"));
		registerBtn.addActionListener(e -> controller.handleMenu("register"));

		cp.revalidate();
		cp.repaint();
	};

	//menu
	// > admin
	// > login
	// > register

	//admin login panel
	public void adminLoginPanel() {
		cp = BackgroundPanel.create("assets/admin/login.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JButton adminBtn = GUIUtil.createIButton(441,0,86,53);
		JButton loginBtn = GUIUtil.createIButton(527,0,76,53);
		JButton registerBtn = GUIUtil.createIButton(603,0,104,53);
		JButton submitBtn = GUIUtil.createIButton(568,260,98,27);
		JTextField field = GUIUtil.createTextField(184,261,364,27);

		cp.add(adminBtn);
		cp.add(loginBtn);
		cp.add(registerBtn);
		cp.add(submitBtn);
		cp.add(field);

		adminBtn.addActionListener(e -> controller.handleMenu("admin"));
		loginBtn.addActionListener(e -> controller.handleMenu("login"));
		registerBtn.addActionListener(e -> controller.handleMenu("register"));
		submitBtn.addActionListener(e -> {
			String input = field.getText();
			field.setText("");
			controller.handleAdminLogin(input);
		});
		
		//field when 'enter' pressed, accepts input to password
		field.requestFocusInWindow();
		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String input = field.getText();
					field.setText("");
					controller.handleAdminLogin(input);	
				}
			}
		});
	
		cp.revalidate();
		cp.repaint();
	};

	//login panel
	public LoginBuilder showLoginScreen() {
		//temp
		return null;
	};

	//register panel
	

	//remove ts!!! >>
	public String showEnthusiastProfile(Enthusiast enthusiast) {
		//temp
		return null;
	};

	public String showAdminProfile() {
		//temp
		return null;
	};

	public String showAdminCrud(String type) {
		//temp
		return null;
	};

}
