package controller;

import model.*;
import view.*;

public class EnthusiastController {
	private GUIView prevView;
	private GUIEnthusiastView view;
	private EnthusiastManagement enthusiastModel;

	public EnthusiastController(GUIView prevView, GUIEnthusiastView view) {
		this.prevView = prevView;
		this.view = view;

		enthusiastModel = new EnthusiastManagement();
	} 

	public void handleLogout() {
		view.dispose();
		prevView.menuPanel();
		prevView.show();
	}

}
