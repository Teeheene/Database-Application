package view;

import javax.swing.*;
import java.awt.*;

public class GUIUtil {
	//creates an invsible button for overlayed options
	public static JButton createIButton(int x, int y, int w, int h) {
		JButton button = new JButton();
		button.setBounds(x, y, w, h);

		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		//debugging
		button.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		return button;
	}
}
