package view;

import model.*;
import controller.*;
import util.*;

import java.util.LinkedHashMap;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIView {
	private JFrame frame;
	private Container cp;

	private MainController controller;
	private AdminController adminController;

	//sets up the initial gui setup before starting the menu
	public GUIView() {}

	public void setListeners(MainController controller) {
		this.controller = controller;
	}
	
	public void start() {
		frame = GUIUtil.setupGUI(frame, cp, 720, 480);
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
				String rs = controller.handleEnthusiastLogin(id);
				if(rs == null)
					return;
				else if(rs.equals("ERR|DNE")) 
					JOptionPane.showMessageDialog(frame, "ID does not exist.");
				else if(rs.equals("ERR|DA"))
					JOptionPane.showMessageDialog(frame, "ID is deactivated.");
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
						String rs = controller.handleEnthusiastLogin(id);
						if(rs == null) 
							return;
						else if(rs.equals("ERR|DNE")) 
							JOptionPane.showMessageDialog(frame, "ID does not exist.");
						else if(rs.equals("ERR|DA"))
							JOptionPane.showMessageDialog(frame, "ID is deactivated.");					
					} catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(frame, "Invalid ID number.");
					}
				}
			}
		});
	
		cp.revalidate();
		cp.repaint();
	}

	public void registerPanel() {
		cp = BackgroundPanel.create("assets/register/register.png");
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
		enthusiastBtn.addActionListener(e -> enthusiastRegisterPanel());
			
		cp.revalidate();
		cp.repaint();
	}

	public void enthusiastRegisterPanel() {
		cp = BackgroundPanel.create("assets/admin/enthusiast/create.png");
		cp.setLayout(null);
		frame.setContentPane(cp);
		JTextField usernameField = GUIUtil.createTextField(121,194,268,27);
		JTextField lastNameField = GUIUtil.createTextField(121,249,268,27);
		JTextField firstNameField = GUIUtil.createTextField(121,304,268,27);
		JTextField middleNameField = GUIUtil.createTextField(121,359,268,27);
		JTextField yearField = GUIUtil.createTextField(447,194,151,27);
		JTextField monthField = GUIUtil.createTextField(447,248,151,27);
		JTextField dayField = GUIUtil.createTextField(447,305,151,27);
		JButton sexF = GUIUtil.createIButton(439,359,40,27);
		JButton sexM = GUIUtil.createIButton(485,359,40,27);
		JButton sexOther = GUIUtil.createIButton(530,359,71,27);
		JButton submitBtn = GUIUtil.createIButton(575,440,60,27);
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		cp.add(usernameField);
		cp.add(lastNameField);
		cp.add(firstNameField);
		cp.add(middleNameField);
		cp.add(yearField);
		cp.add(monthField);
		cp.add(dayField);
		cp.add(sexF);
		cp.add(sexM);
		cp.add(sexOther);
		cp.add(submitBtn);
		cp.add(backBtn);

		final String[] sex = { "none" };
		Border blackBorder = BorderFactory.createLineBorder(new Color(141,53,18), 2);
		Border noBorder = BorderFactory.createLineBorder(Color.BLACK, 0);

		sexF.addActionListener(e -> {
			sex[0] = "Female";
			sexF.setBorder(blackBorder);
			sexM.setBorder(noBorder);
			sexOther.setBorder(noBorder);
		});
		sexM.addActionListener(e -> {
			sex[0] = "Male";
			sexM.setBorder(blackBorder);
			sexF.setBorder(noBorder);
			sexOther.setBorder(noBorder);
		});
		sexOther.addActionListener(e -> {
			sex[0] = "Other";
			sexOther.setBorder(blackBorder);
			sexF.setBorder(noBorder);
			sexM.setBorder(noBorder);
		});
		submitBtn.addActionListener(e -> {
			try {
				String username = usernameField.getText().trim();
				String lastName = lastNameField.getText().trim();
				String firstName = firstNameField.getText().trim();
				String middleName = middleNameField.getText().trim();

				//err handling
				if(username.isEmpty() || lastName.isEmpty() || 
					firstName.isEmpty()) {
					JOptionPane.showMessageDialog(frame, 
						"Required Fields! Fill in all the required fields (middle name is optional).");
   				return; // stop further processing
				}
				if(sex[0].equals("none")) {
					JOptionPane.showMessageDialog(frame, 
						"Required Fields! No sex chosen.");
   				return; // stop further processing
				}
				 int year = Integer.parseInt(yearField.getText().trim());
      		     int month = Integer.parseInt(monthField.getText().trim());
   			     int day = Integer.parseInt(dayField.getText().trim());

				//for validating date of birth
				LocalDate dateOfBirth = LocalDate.of(year, month, day);

				Enthusiast enthusiast = new Enthusiast(
					username, 
					lastName, firstName, middleName, 
					sex[0], 
					new CustomTimestamp(year, month, day)
				);

				if(middleName.isEmpty()) {
					enthusiast.setMiddleName(null);
				}

				controller.handleEnthusiastRegister(enthusiast);

				usernameField.setText("");
				lastNameField.setText("");
				firstNameField.setText("");
				middleNameField.setText("");
				yearField.setText("");
				monthField.setText("");
				dayField.setText("");
				sexOther.setBorder(noBorder);
				sexF.setBorder(noBorder);
				sexM.setBorder(noBorder);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(frame,
					"Invalid Date. Please enter numerical values.");
			} catch (DateTimeException dte) {
				JOptionPane.showMessageDialog(frame,
					"Invalid Date. Please check the day, month and or year.");
			}
		});
		backBtn.addActionListener(e -> registerPanel());

		cp.revalidate();
		cp.repaint();
	}

    public void playerLoginPanel(){
		cp = BackgroundPanel.create("assets/login/login_player.png");
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
				if(!controller.handlePlayerLogin(id)) {
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
						if(!controller.handlePlayerLogin(id)) {
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