package model;

import util.*;
import view.LoginBuilder; 
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class EngagementManagement {

	public void addEngagement(Engagement engagement) {
		//add to table via sql	
	}

	public void deleteEngagement(String type) {
		//sql again
	}

	public void getFollows(int enthusiastID) {
		//sql to get all follows of enthusiast id
	}

	public void getLikes(int enthusiastID) {
		//sql to get all likes of enthusiast id
	}

	public ArrayList<Object> getFeed() {	
		//sql to select * from all tables;
		return null;
	}
}

