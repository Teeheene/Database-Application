package view;

import model.*;

public interface EnthusiastView {
	Enthusiast createEnthusiast();
	void showEnthusiast(Enthusiast enthusiast);
	Enthusiast showUpdate();
	boolean showDelete(Enthusiast enthusiast);
}
