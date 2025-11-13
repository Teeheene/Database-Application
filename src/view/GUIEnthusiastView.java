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

public class GUIEnthusiastView {
	private JFrame frame;
	private Container cp;

	private EnthusiastController controller;

	public GUIEnthusiastView() {}	
		
	public void start() {
		frame = GUIUtil.setupGUI(frame, cp);
	}

	public void setListeners(EnthusiastController controller) {
		this.controller = controller;
	}

	public void profilePanel(Enthusiast enthusiast) {
		cp = BackgroundPanel.create("assets/login/enthusiast/profile.png");
		cp.setLayout(null);
		frame.setContentPane(cp);
		
		JLabel username = GUIUtil.createText(enthusiast.getUsername(),105,205,286,27);
		JLabel id = GUIUtil.createText(String.valueOf(enthusiast.getID()),416,205,92,27);
		JLabel sex = GUIUtil.createText(enthusiast.getSex(),532,205,87,27);
		JLabel fullname = GUIUtil.createText(enthusiast.getFullName(),105,261,514,27);
		JLabel birthday = GUIUtil.createText(enthusiast.getDateOfBirth()
				.getDisplayDate(),106,318,243,27);
		JLabel joinedBy = GUIUtil.createText(enthusiast.getJoinDate()
				.getDisplayDate(),375,318,245,27); 

		JButton engageBtn = GUIUtil.createIButton(423,0,97,53);
		JButton profileBtn = GUIUtil.createIButton(521,0,93,53);
		JButton logoutBtn = GUIUtil.createIButton(614,0,93,53);

		JButton updateBtn = GUIUtil.createIButton(455,116,80,38);
		JButton deleteBtn = GUIUtil.createIButton(544,116,80,36);
		JButton followingBtn = GUIUtil.createIButton(101,378,257,27);
		JButton likesBtn = GUIUtil.createIButton(368,378,257,27);

		cp.add(username);
		cp.add(id);
		cp.add(sex);
		cp.add(fullname);
		cp.add(birthday);
		cp.add(joinedBy);
		cp.add(engageBtn);
		cp.add(profileBtn);
		cp.add(logoutBtn);
		cp.add(updateBtn);
		cp.add(deleteBtn);
		cp.add(followingBtn);
		cp.add(likesBtn);

		
		//updateBtn.addActionListener(e -> enthusiastDashboardPanel());
		profileBtn.addActionListener(e -> profilePanel(enthusiast));
		logoutBtn.addActionListener(e -> controller.handleLogout());

		cp.revalidate();
		cp.repaint();
	}

	public void show() { frame.setVisible(true); }
	public void hide() { frame.setVisible(false); }
	public void dispose() { if(frame != null) frame.dispose(); }
}
