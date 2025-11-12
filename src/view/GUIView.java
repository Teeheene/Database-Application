package view;

import model.*;
import controller.*;

import javax.swing.*;
import java.awt.*;

public class GUIView {
	private JFrame frame;
	private Container cp;
	private JLabel promptLabel;

	private MainController controller;

	//sets up the initial gui setup before starting the menu
	public GUIView() {}

	public void setListeners(MainController controller) {
		this.controller = controller;
	}
	
	public void setupGUI() {
		frame = new JFrame();
		promptLabel = new JLabel();

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

		showMenuScreen();
	}

	//menu panel
	public void showMenuScreen() {
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
