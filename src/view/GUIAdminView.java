package view;

import model.*;
import controller.*;

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
	public void show() { frame.setVisible(true); }
	public void hide() { frame.setVisible(false); }
	public void dispose() { if(frame != null) frame.dispose(); }
}


