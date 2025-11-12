package view;

import java.io.File;
import javax.swing.*;
import java.awt.*;

public class GUIUtil {

	public static void setGlobalFont()
	{
		//for aa or antialiasing (cuz its pixel-y without it)
		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");

		try
		{
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/canva-sans-medium.otf")).deriveFont(14f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);

			for(Object key : UIManager.getLookAndFeelDefaults().keySet())
			{
				if(key.toString().toLowerCase().contains("font"))
				{
					UIManager.put(key, customFont);
				}
			}
		} catch (Exception e)
		{
			System.out.println("uh oh ur font broke");
			e.printStackTrace();
		}
	}


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

	/**
	 * Creates a text field with transparent background and red border styling.dkjh
	 * 
	 * The text field is configured to be non-opaque with black text on a transparent background.
	 * 
	 * @param x the x-coordinate position of the text field.
	 * @param y the y-coordinate position of the text field.
	 * @param w the width of the text field.
	 * @param h the height of the text field.
	 * @return a configured {@code JTextField} with transparent background and red border.
	 */
	public static JTextField createTextField(int x, int y, int w, int h)
	{
		JTextField textField = new JTextField();

		textField.setBounds(x,y,w,h);
		textField.setBorder(BorderFactory.createLineBorder(Color.RED));
		textField.setBorder(null);
		textField.setOpaque(false);
		textField.setBackground(new Color(0,0,0,0));
		textField.setForeground(Color.BLACK);

		return textField;
	}
}
