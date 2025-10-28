package model;

import view.LoginBuilder; 
import java.util.ArrayList;

enum SearchType {
	ID, USERNAME;
}

public class EnthusiastManagement {
	private ArrayList<Enthusiast> enthusiastList;

	public EnthusiastManagement() {
		enthusiastList = new ArrayList<Enthusiast>();
	}

	/* *
	 * Once MySQL is connected, this in-memory storage
	 * can be updated. Basic CRUD can be seen below :)
	 * */

	public void addEnthusiast(Enthusiast enthusiast) {
		enthusiastList.add(enthusiast);
	}

	public void deleteEnthusiast(Enthusiast enthusiast) {
		enthusiastList.remove(enthusiast);
	}

	public Enthusiast updateEnthusiast(Enthusiast oldEnthusiast, Enthusiast updatedEnthusiast) {
		Enthusiast enthusiastReference = 
			searchEnthusiast(SearchType.ID, String.valueOf(oldEnthusiast.getID()));
		enthusiastReference.update(updatedEnthusiast);
		return enthusiastReference;
	}
	
	public Enthusiast searchEnthusiast(SearchType category, String key) {
		for(Enthusiast enthusiast : enthusiastList) {
			if(enthusiast == null) { continue; }
			switch(category) {
				case ID:
					if(String.valueOf(enthusiast.getID()).equalsIgnoreCase(key))
						return enthusiast;
					break;
				case USERNAME: 
					if(enthusiast.getUsername().equalsIgnoreCase(key))
						return enthusiast;
					break;
			}
		}
		return null;
	}

	public Enthusiast verifyLogin(LoginBuilder loginDetails) {
		for(Enthusiast enthusiast : enthusiastList) {
			if(enthusiast == null) { continue; }
			if(enthusiast.getUsername().equalsIgnoreCase(loginDetails.getUsername()) && 
					enthusiast.getPassword() == loginDetails.getPassword()) {
				return enthusiast;
			} 
		}	
		return null;
	}
}
