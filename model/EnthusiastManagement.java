package model;

import java.util.ArrayList;

public class EnthusiastManagement {
	private ArrayList<Enthusiast> enthusiastList;

	public EnthusiastManagement() {
		enthusiastList = new ArrayList<Enthusiast>();
	}

	public void addEnthusiast(Enthusiast enthusiast) {
		enthusiastList.add(enthusiast);
	}
}
