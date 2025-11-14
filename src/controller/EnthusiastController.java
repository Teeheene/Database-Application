package controller;

import model.*;
import view.*;

public class EnthusiastController {
	private GUIView prevView;
	private GUIEnthusiastView view;
	private EnthusiastManagement model;

	public EnthusiastController(GUIView prevView, GUIEnthusiastView view) {
		this.prevView = prevView;
		this.view = view;

		model = new EnthusiastManagement();
	} 

	public void handleUpdate(Enthusiast updatedEnthusiast) {
		Enthusiast enthusiast = model.updateEnthusiast(updatedEnthusiast);
		view.passUpdatedEnthusiast(enthusiast);
	}

	public void handleDelete(Enthusiast enthusiast) {
		model.deleteEnthusiast(enthusiast.getID());
	}

	public void handleLogout() {
		view.dispose();
		prevView.menuPanel();
		prevView.show();
	}

}
