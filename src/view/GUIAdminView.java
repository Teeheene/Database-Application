package view;

import model.*;
import controller.*;
import util.*;

import java.util.LinkedHashMap;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIAdminView {
	private JFrame frame;
	private Container cp;

	private AdminController controller;

	public GUIAdminView() {}	
		
	public void start() {
		frame = GUIUtil.setupGUI(frame, cp, 720, 480);
		dashboardPanel();
	}

	public void setListeners(AdminController controller) {
		this.controller = controller;
	}

	public void dashboardPanel() {
		cp = BackgroundPanel.create("assets/admin/home.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JButton enthusiastBtn = GUIUtil.createIButton(259,208,200,27);
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		cp.add(enthusiastBtn);
		cp.add(backBtn);

		enthusiastBtn.addActionListener(e -> controller.handleMenu("enthusiast"));
		backBtn.addActionListener(e -> controller.handleMenu("exit"));

		cp.revalidate();
		cp.repaint();
	}

	//ENTHUSIASTS DASHBOARD!!!!!!!

	public void enthusiastDashboardPanel() {
		cp = BackgroundPanel.create("assets/admin/enthusiasts_crud.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JButton createBtn = GUIUtil.createIButton(235,198,246,27);
		JButton viewAllBtn = GUIUtil.createIButton(235,235,246,27);
		JButton updateBtn = GUIUtil.createIButton(235,269,246,27);
		JButton deleteBtn = GUIUtil.createIButton(235,305,246,27);
		JButton reportBtn = GUIUtil.createIButton(235,341,246,27);
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		cp.add(createBtn);
		cp.add(viewAllBtn);
		cp.add(updateBtn);
		cp.add(deleteBtn);
		cp.add(reportBtn);
		cp.add(backBtn);

		createBtn.addActionListener(e -> controller.handleEnthusiast("create"));
		viewAllBtn.addActionListener(e -> controller.handleEnthusiast("viewAll"));
		updateBtn.addActionListener(e -> controller.handleEnthusiast("update"));
		deleteBtn.addActionListener(e -> controller.handleEnthusiast("delete"));
		reportBtn.addActionListener(e -> controller.handleEnthusiast("report"));
		backBtn.addActionListener(e -> controller.handleEnthusiast("exit"));

		cp.revalidate();
		cp.repaint();

	}

	public void createEnthusiastPanel() {
		cp = BackgroundPanel.create("assets/admin/enthusiast/create.png");
		cp.setLayout(null);
		frame.setContentPane(cp);
		JTextField usernameField = GUIUtil.createTextField(119,198,261,27);
		JTextField lastNameField = GUIUtil.createTextField(119,254,261,27);
		JTextField firstNameField = GUIUtil.createTextField(119,308,261,27);
		JTextField middleNameField = GUIUtil.createTextField(119,364,261,27);
		JTextField yearField = GUIUtil.createTextField(445,198,139,27);
		JTextField monthField = GUIUtil.createTextField(445,253,139,27);
		JTextField dayField = GUIUtil.createTextField(445,309,139,27);
		JTextField sexField = GUIUtil.createTextField(445,364,139,27);
		JButton submitBtn = GUIUtil.createIButton(110,410,486,27);
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		cp.add(usernameField);
		cp.add(lastNameField);
		cp.add(firstNameField);
		cp.add(middleNameField);
		cp.add(yearField);
		cp.add(monthField);
		cp.add(dayField);
		cp.add(sexField);
		cp.add(submitBtn);
		cp.add(backBtn);

		submitBtn.addActionListener(e -> {
			try {
				String username = usernameField.getText().trim();
				String lastName = lastNameField.getText().trim();
				String firstName = firstNameField.getText().trim();
				String middleName = middleNameField.getText().trim();
				String sex = sexField.getText().trim();

				if(username.isEmpty() || lastName.isEmpty() || 
					firstName.isEmpty() || sex.isEmpty()) {
					JOptionPane.showMessageDialog(frame, 
						"Required Fields! Fill in all the required fields (middle name is optional).");
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
					sex, 
					new CustomTimestamp(year, month, day)
				);

				if(middleName.isEmpty()) {
					enthusiast.setMiddleName(null);
				}

				controller.addEnthusiast(enthusiast);

				usernameField.setText("");
				lastNameField.setText("");
				firstNameField.setText("");
				middleNameField.setText("");
				yearField.setText("");
				monthField.setText("");
				dayField.setText("");
				sexField.setText("");
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(frame,
					"Invalid Date. Please enter numerical values.");
			} catch (DateTimeException dte) {
				JOptionPane.showMessageDialog(frame,
					"Invalid Date. Please check the day, month and or year.");
			}
		});
		backBtn.addActionListener(e -> enthusiastDashboardPanel());

		cp.revalidate();
		cp.repaint();
	}

	public void viewAllEnthusiastPanel(LinkedHashMap<String, String> information) {
		cp = BackgroundPanel.create("assets/admin/enthusiast/view_all_bg.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JLabel overlayBg = new JLabel(new ImageIcon("assets/admin/enthusiast/view_all.png"));
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		JPanel scrollingPanel = GUIUtil.showScrollingPanel(information, -19, -3, 57, 6,
			clicked -> {
				String[] parts = clicked.split("/",2);
				String key = parts[0]; 
				controller.showEnthusiast(Integer.parseInt(key));
			});

		overlayBg.setBounds(0, 0, 720, 480);
		scrollingPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		
		cp.add(overlayBg);
		cp.add(backBtn);
		cp.add(scrollingPanel);

		backBtn.addActionListener(e -> enthusiastDashboardPanel());

		cp.revalidate();
		cp.repaint();
	}

	public void viewEnthusiastPanel(Enthusiast enthusiast) {
		cp = BackgroundPanel.create("assets/admin/enthusiast/view.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JLabel username = GUIUtil.createText(enthusiast.getUsername(),100,199,286,27);
		JLabel id = GUIUtil.createText(String.valueOf(enthusiast.getID()),410,199,92,27);
		JLabel sex = GUIUtil.createText(enthusiast.getSex(),526,199,89,27);
		JLabel fullname = GUIUtil.createText(enthusiast.getFullName(),102,273,515,27);
		JLabel birthday = GUIUtil.createText(enthusiast.getDateOfBirth()
				.getDisplayDate(),100,346,245,27);
		JLabel joinedBy = GUIUtil.createText(enthusiast.getJoinDate()
				.getDisplayDate(),370,346,245,27); 
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		cp.add(username);
		cp.add(id);
		cp.add(sex);
		cp.add(fullname);
		cp.add(birthday);
		cp.add(joinedBy);
		cp.add(backBtn);

		backBtn.addActionListener(e -> controller.handleEnthusiast("viewAll"));

		cp.revalidate();
		cp.repaint();
	}

	public void searchUpdateEnthusiastPanel() {
		cp = BackgroundPanel.create("assets/admin/enthusiast/search.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JButton submitBtn = GUIUtil.createIButton(528,260,98,27);
		JTextField field = GUIUtil.createTextField(142,261,367,27);
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		cp.add(submitBtn);
		cp.add(field);
		cp.add(backBtn);

		backBtn.addActionListener(e -> enthusiastDashboardPanel());
		submitBtn.addActionListener(e -> {
			String input = field.getText().trim();
			field.setText("");

			try {
				int id = Integer.parseInt(input);
				if(!controller.updateEnthusiastView(id)) {
					JOptionPane.showMessageDialog(frame,
						"ID does not exist.");
				}
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(frame,
					"Invalid ID number.");
			}
		});
		
		cp.revalidate();
		cp.repaint();
	}

	public void updateEnthusiastPanel(Enthusiast enthusiast) {
		cp = BackgroundPanel.create("assets/admin/enthusiast/update_view.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JLabel username = GUIUtil.createText(enthusiast.getUsername(),100,199,286,27);
		JLabel id = GUIUtil.createText(String.valueOf(enthusiast.getID()),410,199,92,27);
		JLabel sex = GUIUtil.createText(enthusiast.getSex(),526,199,89,27);
		JLabel firstname = GUIUtil.createText(enthusiast.getFirstName(),102,273,153,27);
		JLabel middlename = GUIUtil.createText(enthusiast.getMiddleName(),276,271,164,27);
		JLabel lastname = GUIUtil.createText(enthusiast.getLastName(),462,273,153,27);
		JLabel birthday = GUIUtil.createText(enthusiast.getDateOfBirth()
				.getDisplayDate(),100,346,245,27);
		JLabel joinedBy = GUIUtil.createText(enthusiast.getJoinDate()
				.getDisplayDate(),370,346,245,27); 
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		JButton saveBtn = GUIUtil.createIButton(570,440,64,27);
		JButton editUsername = GUIUtil.createIButton(354,179,36,18);
		JButton editFirstName = GUIUtil.createIButton(223,253,36,18);
		JButton editMiddleName = GUIUtil.createIButton(409,252,36,18);
		JButton editLastName = GUIUtil.createIButton(584,253,36,18);
		JButton editSex = GUIUtil.createIButton(584,179,36,18);
		JButton editBirthday = GUIUtil.createIButton(314,326,36,18);

		cp.add(username);
		cp.add(id);
		cp.add(sex);
		cp.add(firstname);
		cp.add(middlename);
		cp.add(lastname);
		cp.add(birthday);
		cp.add(joinedBy);
		cp.add(backBtn);
		cp.add(saveBtn);
		cp.add(editUsername);
		cp.add(editFirstName);
		cp.add(editMiddleName);
		cp.add(editLastName);
		cp.add(editSex);
		cp.add(editBirthday);

		backBtn.addActionListener(e -> enthusiastDashboardPanel());
		saveBtn.addActionListener(e -> controller.updateEnthusiast(enthusiast));
		editUsername.addActionListener(e -> 
				updateFieldEnthusiastPanel(enthusiast,"username"));
		editFirstName.addActionListener(e -> 
				updateFieldEnthusiastPanel(enthusiast,"first name"));
		editMiddleName.addActionListener(e -> 
				updateFieldEnthusiastPanel(enthusiast,"middle name"));
		editLastName.addActionListener(e -> 
				updateFieldEnthusiastPanel(enthusiast,"last name"));
		editSex.addActionListener(e -> 
				updateFieldEnthusiastPanel(enthusiast,"sex"));
		editBirthday.addActionListener(e -> 
				updateFieldEnthusiastPanel(enthusiast,"birthday"));

		cp.revalidate();
		cp.repaint();	

	}

	public void updateFieldEnthusiastPanel(Enthusiast enthusiast, String type) {
		cp = BackgroundPanel.create("assets/admin/enthusiast/update_field.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JButton submitBtn = GUIUtil.createIButton(519,252,98,27);
		JTextField field = GUIUtil.createTextField(100,252,400,27);
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		cp.add(submitBtn);
		cp.add(field);
		cp.add(backBtn);

		backBtn.addActionListener(e -> updateEnthusiastPanel(enthusiast));
		submitBtn.addActionListener(e -> {
			String input = field.getText().trim();
			//check for invalid inputs
			switch(type) {
				case "username": case "sex": case "first name": 
				case "last name": case "birthday":
					if(input.isEmpty()) {
						JOptionPane.showMessageDialog(frame, 
							"Required Field! Please input update for " + type + ".");
						return;
					}
			}

			switch(type) {
				case "username": 
					enthusiast.setUsername(input);
					break;
				case "sex": 
					enthusiast.setSex(input); 
					break;
				case "first name": 
					enthusiast.setFirstName(input); 
					break;
				case "middle name":
					if(input.isEmpty())
						enthusiast.setMiddleName(null);
					else
						enthusiast.setMiddleName(input);
					break;
				case "last name":
					enthusiast.setLastName(input);
					break;
				case "birthday":
					DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");	
					try {
						LocalDate date = LocalDate.parse(input, format);
						String[] dateParts = input.split("/");
						int month = Integer.parseInt(dateParts[0]);
						int day = Integer.parseInt(dateParts[1]);
						int year = Integer.parseInt(dateParts[2]);
						enthusiast.setDateOfBirth(new CustomTimestamp(year, month, day));
					} catch(DateTimeParseException dtpe) {
						JOptionPane.showMessageDialog(frame, 
							"Invalid Date. Please input it as MM/DD/YYYY");
						return;
					}
					break;
			}

			updateEnthusiastPanel(enthusiast);
		});
		
		cp.revalidate();
		cp.repaint();
		
	}

	public void searchDeleteEnthusiastPanel() {
		cp = BackgroundPanel.create("assets/admin/enthusiast/search.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JButton submitBtn = GUIUtil.createIButton(528,260,98,27);
		JTextField field = GUIUtil.createTextField(142,261,367,27);
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		cp.add(submitBtn);
		cp.add(field);
		cp.add(backBtn);

		backBtn.addActionListener(e -> enthusiastDashboardPanel());
		submitBtn.addActionListener(e -> {
			String input = field.getText().trim();
			field.setText("");

			try {
				int id = Integer.parseInt(input);
				if(!controller.deleteEnthusiastView(id)) {
					JOptionPane.showMessageDialog(frame,
						"ID does not exist.");
				}
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(frame,
					"Invalid ID number.");
				}
		});
		
		cp.revalidate();
		cp.repaint();
	}

	public void deleteEnthusiastPanel(Enthusiast enthusiast) {
		cp = BackgroundPanel.create("assets/admin/enthusiast/delete.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JLabel username = GUIUtil.createText(enthusiast.getUsername(),100,199,286,27);
		JLabel id = GUIUtil.createText(String.valueOf(enthusiast.getID()),410,199,92,27);
		JLabel sex = GUIUtil.createText(enthusiast.getSex(),526,199,89,27);
		JLabel fullname = GUIUtil.createText(enthusiast.getFullName(),102,273,515,27);
		JLabel birthday = GUIUtil.createText(enthusiast.getDateOfBirth()
				.getDisplayDate(),100,346,245,27);
		JLabel joinedBy = GUIUtil.createText(enthusiast.getJoinDate()
				.getDisplayDate(),370,346,245,27); 
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		JButton delBtn = GUIUtil.createIButton(521,440,113,27);
		cp.add(username);
		cp.add(id);
		cp.add(sex);
		cp.add(fullname);
		cp.add(birthday);
		cp.add(joinedBy);
		cp.add(backBtn);
		cp.add(delBtn);

		backBtn.addActionListener(e -> enthusiastDashboardPanel());
		delBtn.addActionListener(e -> {
			controller.toggleEnthusiast(enthusiast);
			enthusiastDashboardPanel();
		});

		cp.revalidate();
		cp.repaint();	
	}

	public void viewReportEnthusiastPanel() {
		cp = BackgroundPanel.create("assets/admin/enthusiast/report.png");
		cp.setLayout(null);
		frame.setContentPane(cp);
		
		JButton generateBtn = GUIUtil.createIButton(259,249,200,27);
		JButton generatePdfBtn = GUIUtil.createIButton(259,286,200,27);
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		
		cp.add(generateBtn);
		cp.add(generatePdfBtn);
		cp.add(backBtn);

		generateBtn.addActionListener(e -> {
			JFrame reportsFrame = new JFrame();
			Container reportsCp = new Container();
			reportsFrame = GUIUtil.setupGUI(reportsFrame, reportsCp, 1920,1080, "enthusiast reports", true);

			reportsCp = BackgroundPanel.create("assets/admin/enthusiast/report_bg.png");
			reportsCp.setLayout(null);
			reportsFrame.setContentPane(reportsCp);

			reportsCp.revalidate();
			reportsCp.repaint();
		});
		backBtn.addActionListener(e -> enthusiastDashboardPanel());

		cp.revalidate();
		cp.repaint();
	}
	
	//ENTHUSIASTS DASHBOARD!!!!!!!

	public void show() { frame.setVisible(true); }
	public void hide() { frame.setVisible(false); }
	public void dispose() { if(frame != null) frame.dispose(); }
}


