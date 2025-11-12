package view;

import model.*;
import controller.*;
import util.*;

import java.util.LinkedHashMap;
import java.time.LocalDate;
import java.time.DateTimeException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIAdminView {
	private JFrame frame;
	private Container cp;

	private AdminController controller;

	public GUIAdminView() {}	
		
	public void start() {
		frame = GUIUtil.setupGUI(frame, cp);
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

				if(username.isEmpty() || lastName.isEmpty() || 
					firstName.isEmpty() || sex.isEmpty()) {
					JOptionPane.showMessageDialog(frame, 
						"Please fill in all required fields (middle name is optional).");
   				return; // stop further processing
				}

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
					"Invalid date input. Please enter numerical values.");
			} catch (DateTimeException dte) {
				JOptionPane.showMessageDialog(frame,
					"Invalid date. Please check the day, month and or year.");
			}
		});
		backBtn.addActionListener(e -> enthusiastDashboardPanel());

		cp.revalidate();
		cp.repaint();
	}

	public void viewAllEnthusiastPanel(LinkedHashMap<String, String> information) {
		cp = new JPanel(null);
		cp.setLayout(null);
		frame.setContentPane(cp);

		JLabel overlayBg = new JLabel(new ImageIcon("assets/admin/enthusiast/view_all.png"));
		JButton backBtn = GUIUtil.createIButton(648,440,64,27);
		JPanel scrollingPanel = GUIUtil.showScrollingPanel(information, clicked -> {
			 String[] parts = clicked.split("/",2);
			 String key = parts[0]; 
			 controller.showEnthusiast(Integer.parseInt(key));
		});

		overlayBg.setBounds(0, 0, frame.getWidth(), frame.getHeight());
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

		backBtn.addActionListener(e -> enthusiastDashboardPanel());

		cp.revalidate();
		cp.repaint();
	}
	
	public void searchDeleteEnthusiastPanel() {
		cp = BackgroundPanel.create("assets/admin/enthusiast/search.png");
		cp.setLayout(null);
		frame.setContentPane(cp);

		JButton submitBtn = GUIUtil.createIButton(568,260,98,27);
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
					"Invalid ID number");
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
			controller.deleteEnthusiast(enthusiast);
			enthusiastDashboardPanel();
		});

		cp.revalidate();
		cp.repaint();	
	}

	public void show() { frame.setVisible(true); }
	public void hide() { frame.setVisible(false); }
	public void dispose() { if(frame != null) frame.dispose(); }
}


