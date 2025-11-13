package view;

import model.*;
import controller.*;

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
	
	public void start() {
		frame = GUIUtil.setupGUI(frame, cp);
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
	}

	//menu
	// > admin
	// > login
	// > register

	//admin login panel
	public void adminLoginPanel() {
		cp = BackgroundPanel.create("assets/admin/login.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JButton homeBtn = GUIUtil.createIButton(11,6,100,40);
		JButton adminBtn = GUIUtil.createIButton(441,0,86,53);
		JButton loginBtn = GUIUtil.createIButton(527,0,76,53);
		JButton registerBtn = GUIUtil.createIButton(603,0,104,53);
		JButton submitBtn = GUIUtil.createIButton(568,260,98,27);
		JTextField field = GUIUtil.createTextField(184,261,364,27);

		cp.add(homeBtn);
		cp.add(adminBtn);
		cp.add(loginBtn);
		cp.add(registerBtn);
		cp.add(submitBtn);
		cp.add(field);

		homeBtn.addActionListener(e -> menuPanel());
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
					if(!controller.handleAdminLogin(input))
						JOptionPane.showMessageDialog(frame, 
							"Incorrect Password.");
				}
			}
		});
	
		cp.revalidate();
		cp.repaint();
	};

	//normal login panel
	public void loginPanel() {
		cp = BackgroundPanel.create("assets/login/login.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JButton homeBtn = GUIUtil.createIButton(11,6,100,40);
		JButton adminBtn = GUIUtil.createIButton(441,0,86,53);
		JButton loginBtn = GUIUtil.createIButton(527,0,76,53);
		JButton registerBtn = GUIUtil.createIButton(603,0,104,53);
		JButton enthusiastBtn = GUIUtil.createIButton(259,208,200,27);

		cp.add(homeBtn);
		cp.add(adminBtn);
		cp.add(loginBtn);
		cp.add(registerBtn);
		cp.add(enthusiastBtn);

		homeBtn.addActionListener(e -> menuPanel());
		adminBtn.addActionListener(e -> controller.handleMenu("admin"));
		loginBtn.addActionListener(e -> controller.handleMenu("login"));
		registerBtn.addActionListener(e -> controller.handleMenu("register"));
		enthusiastBtn.addActionListener(e -> enthusiastLoginPanel());
			
		cp.revalidate();
		cp.repaint();
	};

	public void enthusiastLoginPanel() {
		cp = BackgroundPanel.create("assets/login/login_enthusiast.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JButton homeBtn = GUIUtil.createIButton(11,6,100,40);
		JButton adminBtn = GUIUtil.createIButton(441,0,86,53);
		JButton loginBtn = GUIUtil.createIButton(527,0,76,53);
		JButton registerBtn = GUIUtil.createIButton(603,0,104,53);
		JButton submitBtn = GUIUtil.createIButton(528,260,98,27);
		JTextField field = GUIUtil.createTextField(142,261,366,27);

		cp.add(homeBtn);
		cp.add(adminBtn);
		cp.add(loginBtn);
		cp.add(registerBtn);
		cp.add(submitBtn);
		cp.add(field);

		homeBtn.addActionListener(e -> menuPanel());
		adminBtn.addActionListener(e -> controller.handleMenu("admin"));
		loginBtn.addActionListener(e -> controller.handleMenu("login"));
		registerBtn.addActionListener(e -> controller.handleMenu("register"));
		submitBtn.addActionListener(e -> {
			String input = field.getText();
			field.setText("");

			try {
				int id = Integer.parseInt(input);
				if(!controller.handleEnthusiastLogin(id)) {
					JOptionPane.showMessageDialog(frame, "ID does not exist.");
				}	
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(frame, "Invalid ID number.");
			}
		});
		
		//field when 'enter' pressed, accepts input to password
		field.requestFocusInWindow();
		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String input = field.getText();
					field.setText("");

					try {
						int id = Integer.parseInt(input);
						if(!controller.handleEnthusiastLogin(id)) {
							JOptionPane.showMessageDialog(frame, "ID does not exist.");
						}	
					} catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(frame, "Invalid ID number.");
					}
				}
			}
		});
	
		cp.revalidate();
		cp.repaint();
	}

	public void show() { frame.setVisible(true); }
	public void hide() { frame.setVisible(false); }
}
