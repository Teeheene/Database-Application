package view;

import model.*;

public interface View {
	void showMenuScreen();
	LoginBuilder showLoginScreen();
	String showEnthusiastProfile(Enthusiast enthusiast);
	String showAdminProfile();
	String showAdminCrud(String type);
}
