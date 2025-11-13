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
	public EngagementManagement() {}

	/* Add Engagement to DB 
	 * 
	 * @param engagement The built engagement to add
	 * */
	public void addEngagement(Engagement engagement) {
		//add if condition depending on the type
		String sql = "INSERT INTO engagement (engagement_category, engagement_type, enthusiast_id, ";
		switch(engagement.getTargetCategory()) {
			case "player":
				sql += "player_id";
				break;
			case "coach":
				sql += "coach_id";
				break;
			case "tournament":
				sql += "tournament_id";
				break;
		}

		sql += ") VALUES(?, ?, ?, ?)";

		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, engagement.getTargetCategory());
			statement.setString(2, engagement.getType());
			statement.setInt(3, engagement.getEnthusiastID());
			statement.setInt(4, engagement.getTargetID());

			int affectedRows = statement.executeUpdate();

			if(affectedRows > 0)
				System.out.println("You have " + engagement.getType() + " this " + engagement.getTargetCategory());
		} catch(SQLException e) {
			e.printStackTrace();
		}
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
		//maybe merge the tables and then return
		//a feed where its ordered by most recent
		return null;
	}
}

