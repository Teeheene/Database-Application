package model;

import view.LoginBuilder; 
import java.util.ArrayList;

public class EnthusiastManagement {
	private ArrayList<Enthusiast> enthusiastList;

	public EnthusiastManagement() {
		enthusiastList = new ArrayList<Enthusiast>();
	}

	public void addEnthusiast(Enthusiast enthusiast) {
		enthusiastList.add(enthusiast);
	}

	public Enthusiast verifyLogin(LoginBuilder loginDetails) {
		for(Enthusiast enthusiast : enthusiastList) {
			if(enthusiast == null) { continue; }
			if(enthusiast.getUsername().equalsIgnoreCase(loginDetails.getUsername()) 
					&& enthusiast.getPassword().equalsIgnoreCase(loginDetails.getPassword())) {
				return enthusiast;
			}
		}	
		return null;
	}
}
