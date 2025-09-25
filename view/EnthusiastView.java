package view;

import model.*;

public interface EnthusiastView {
	Enthusiast createEnthusiast();
	void showEnthusiast(Enthusiast enthusiast);
	void showUpdate(Enthusiast enthusiast);
	void showDelete(Enthusiast enthusiast);
}
