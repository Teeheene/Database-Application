package view;

import model.*;
import controller.*;
import util.*;

//time
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//utils
import java.util.Locale;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Arrays;
//gui
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.border.Border;
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

				controller.addEnthusiast(enthusiast);

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
		backBtn.addActionListener(e -> enthusiastDashboardPanel());

		cp.revalidate();
		cp.repaint();
	}

	public void viewAllEnthusiastPanel(LinkedHashMap<String, String> information) {
		cp = BackgroundPanel.create("assets/admin/enthusiast/view_all_bg.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JButton inactiveBtn = GUIUtil.createIButton(647,107,64,20);
		JButton activeBtn = GUIUtil.createIButton(647,133,64,20);
		JButton allBtn = GUIUtil.createIButton(647,159,64,20);
		JLabel overlayBg = new JLabel(new ImageIcon("assets/admin/enthusiast/view_all.png"));
		overlayBg.setBounds(0,0,720,480);	
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		
		JPanel scrollingPanel = new JPanel(null);
		int index = 0;
		int gap = 60;
		int y = 121;
		for(Map.Entry<String, String> entry : information.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();	

			String[] keys = key.split("-");
			int enthusiastID = Integer.parseInt(keys[0]);
			String status = keys[1];

			JPanel user = new JPanel(null);
			user.setOpaque(false);

			String imagePath = "assets/admin/enthusiast/";
			if(status.equals("active")) {
				imagePath += "banner_active";
			} else {
				imagePath += "banner_archived";
			}
			imagePath += ".png";

			JLabel banner = new JLabel(new ImageIcon(imagePath));
			banner.setBounds(0,0,588,54);
			banner.setOpaque(false);

			JButton bannerBtn = GUIUtil.createIButton(0,0,588,54);
			JLabel userInfo = GUIUtil.createText(value,62,17,443,20);

			bannerBtn.addActionListener(e -> {
				controller.showEnthusiast(enthusiastID, status);
				System.out.println("enthusiast is " + status);
			});

			user.add(userInfo);
			user.add(banner);
			user.add(bannerBtn);

			user.setBounds(41,y+index*gap,589,55);
			scrollingPanel.add(user);
			index++;
		}

		cp.add(overlayBg);
		int totalHeight = y + information.size() * gap;
		scrollingPanel.setBounds(0,0,720,totalHeight);
		scrollingPanel.setOpaque(false);
		cp.add(scrollingPanel);

		cp.addMouseWheelListener(new MouseWheelListener() {
			int offset = 0;

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) 
			{
				int rotation = e.getWheelRotation(); //1 down -1 up
				offset -= rotation * 20; //so 20 is the offset like when scrolled ykykyk

				int maxOffset = 0;
				int minOffset = Math.min(0,cp.getHeight()-20 - totalHeight);
				offset = Math.max(minOffset, Math.min(maxOffset, offset));

				scrollingPanel.setLocation(0,offset);
				cp.repaint();
			}
		});

		inactiveBtn.addActionListener(e -> controller.handleEnthusiast("viewInactive"));
		activeBtn.addActionListener(e -> controller.handleEnthusiast("viewActive"));
		allBtn.addActionListener(e -> controller.handleEnthusiast("viewAll"));
		backBtn.addActionListener(e -> enthusiastDashboardPanel());

		cp.add(backBtn);
		cp.add(inactiveBtn);
		cp.add(activeBtn);
		cp.add(allBtn);
		cp.revalidate();
		cp.repaint();
	}

	public void viewEnthusiastPanel(Enthusiast enthusiast, String status, String backPath) {
		String imagePath = "assets/admin/enthusiast/" + status + ".png";
		cp = BackgroundPanel.create(imagePath);
		cp.setLayout(null);
		frame.setContentPane(cp);

		JButton reactivateBtn = new JButton();
		if(status.equals("inactive")) {
			reactivateBtn = GUIUtil.createIButton(490,440,145,27);
			cp.add(reactivateBtn);
		}
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

		reactivateBtn.addActionListener(e -> {
			controller.toggleEnthusiast(enthusiast);
			controller.handleEnthusiast(backPath);
		});
		backBtn.addActionListener(e -> controller.handleEnthusiast(backPath));

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

			if(type.equals("sex") && 
				!(input.equals("Male") || 
				input.equals("Female") || 
				input.equals("Other"))) {
					JOptionPane.showMessageDialog(frame, 
					"Sex must be Male, Female or Other.");
				return;
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

		generateBtn.addActionListener(e -> generateReport());
		generatePdfBtn.addActionListener(e -> {
			if(controller.generatePdfEnthusiastReport())
				JOptionPane.showMessageDialog(frame, "Succesfully Generated PDF!");
			else 
				JOptionPane.showMessageDialog(frame, "Something went wrong.");
		});
		backBtn.addActionListener(e -> enthusiastDashboardPanel());

		cp.revalidate();
		cp.repaint();
	}

	public void generateReport() {
		JFrame reportsFrame = new JFrame();
		Container reportsCp = new Container();
		reportsFrame = GUIUtil.setupGUI(reportsFrame, reportsCp, 1920,1080, "enthusiast reports", true);

		reportsCp = BackgroundPanel.create("assets/admin/enthusiast/report_bg.png");
		reportsCp.setLayout(null);
		reportsFrame.setContentPane(reportsCp);

		LocalDate today = LocalDate.now();
		String month = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		String year = String.valueOf(today.getYear());
		String date = month + " " + year;

		JLabel creationDate = GUIUtil.createText(date,1350,219,443,21);

		creationDate.setHorizontalAlignment(SwingConstants.RIGHT);
		creationDate.setFont(creationDate.getFont().deriveFont(20f));
		creationDate.setForeground(new Color(213,98,51));

		String[][] data = controller.generateEnthusiastReport();
		String[] columns = new String[data[0].length];
		Arrays.fill(columns, "");

		JTable table = new JTable(data, columns);
		table.setTableHeader(null);
		table.setOpaque(false);
		table.setShowGrid(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(97);
		table.getColumnModel().getColumn(1).setPreferredWidth(367);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(170);
		table.getColumnModel().getColumn(4).setPreferredWidth(170);
		table.getColumnModel().getColumn(5).setPreferredWidth(170);
		table.getColumnModel().getColumn(6).setPreferredWidth(190);
		table.getColumnModel().getColumn(7).setPreferredWidth(170);
		table.getColumnModel().getColumn(8).setPreferredWidth(199);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setOpaque(false);
		renderer.setHorizontalAlignment(SwingConstants.LEFT);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(129,309,1663,690);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.getVerticalScrollBar().setOpaque(false);
		scroll.getHorizontalScrollBar().setOpaque(false);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));

		table.setRowHeight(40);
		
		reportsCp.add(scroll);
		reportsCp.add(creationDate);
		reportsCp.revalidate();
		reportsCp.repaint();
	}

	//ENTHUSIASTS DASHBOARD!!!!!!!

	public void show() { frame.setVisible(true); }
	public void hide() { frame.setVisible(false); }
	public void dispose() { if(frame != null) frame.dispose(); }
}


