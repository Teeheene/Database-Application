package controller;

import model.*;
import view.*;
import java.util.*;

public class EnthusiastController {
	private GUIView prevView;
	private GUIEnthusiastView view;
	private EnthusiastManagement model;
	private EngagementManagement engagementModel;

	public EnthusiastController(GUIView prevView, GUIEnthusiastView view) {
		this.prevView = prevView;
		this.view = view;

		model = new EnthusiastManagement();
		engagementModel = new EngagementManagement();
	} 

	public LinkedHashMap<String, String> handleFollowing(Enthusiast enthusiast) {
		LinkedHashMap<String, String> information = new LinkedHashMap<>();

		for(Engagement e : engagementModel.getEngagement(enthusiast.getID(), "follow")) {
			if(e == null) { continue; }
			String key = String.valueOf(e.getID());
			String value = String.valueOf(e.getSimpleInfo());
			information.put(key, value);
			System.out.println(e.getSimpleInfo());
		}

		return information;
	}

	public LinkedHashMap<String, String> handleLikes(Enthusiast enthusiast) {
		LinkedHashMap<String, String> information = new LinkedHashMap<>();

		for(Engagement e : engagementModel.getEngagement(enthusiast.getID(), "like")) {
			if(e == null) { continue; }
			String key = String.valueOf(e.getID());
			String value = String.valueOf(e.getSimpleInfo());
			information.put(key, value);
			System.out.println(e.getSimpleInfo());
		}

		return information;
	}

	public void handleUpdate(Enthusiast updatedEnthusiast) {
		Enthusiast enthusiast = model.updateEnthusiast(updatedEnthusiast);
		view.passUpdatedEnthusiast(enthusiast);
	}

	public void handleToggle(int engagementId) {
		engagementModel.toggleEngagementStatus(engagementId);
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
