package view;

import model.*;

public interface View {
	String showMenuScreen();
	LoginBuilder showLoginScreen();
	String showEnthusiastProfile(Enthusiast enthusiast);
}
