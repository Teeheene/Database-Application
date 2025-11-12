package view;

import model.*;
import controller.*;

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
					new Timestamp(year, month, day)
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

	public void show() { frame.setVisible(true); }
	public void hide() { frame.setVisible(false); }
	public void dispose() { if(frame != null) frame.dispose(); }
}


