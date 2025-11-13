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

	public void getEngagementListByType(int enthusiastID, String type) {
		ArrayList<Engagement> engagementList = new ArrayList<>();

		String sql = 
			"SELECT *" + 
			"FROM enthusiast AS e" +
			"JOIN engagement AS g" + 
				"ON e.enthusiast_id = g.enthusiast_id" +
			"WHERE g.core_id = ?" +
			"AND g.type = ?;";
		
		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, enthusiastID);
			statement.setString(2, type);

			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				engagement = new Engagement(
					rs.getInt("engagement_id"),
					rs.getString("engagement_category"),
					rs.getString("engagement_type"),
					rs.getInt("enthusiast_id"),
					switch(rs.getString(engagement_category)) {
						case "player":
							rs.getInt("player_id");
							break;
						case "coach":
							rs.getInt("coach_id");
							break;
						case "tournament":
							rs.getInt("tournament_id");
							break;
					},
					rs.getString("created_at");
				);
				engagementList.add(engagement);
			} 		
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return engagementList;
	}

	public void getEngagement(int enthusiastID, String type) {
		ArrayList<Engagement> engagementList = new ArrayList<>();

		String sql = 
			"SELECT *" + 
			"FROM enthusiast AS e" +
			"JOIN engagement AS g" + 
				"ON e.enthusiast_id = g.enthusiast_id" +
			"WHERE g.core_id = ?" +
			"AND g.type = ?;";
		
		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, enthusiastID);
			statement.setString(2, type);

			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				engagement = new Engagement(
					rs.getInt("engagement_id"),
					rs.getString("engagement_category"),
					rs.getString("engagement_type"),
					rs.getInt("enthusiast_id"),
					switch(rs.getString(engagement_category)) {
						case "player":
							rs.getInt("player_id");
							break;
						case "coach":
							rs.getInt("coach_id");
							break;
						case "tournament":
							rs.getInt("tournament_id");
							break;
					},
					rs.getString("created_at");
				);
				engagementList.add(engagement);
			} 		
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return engagementList;
	}

	//THE TARGET
	//THE TOTAL LIKES
	//THE TOTAL FOLLOWS
	public ArrayList<Object> getFeed(String orderColumn, String orderDir) {	
		ArrayList<Object> feed = new ArrayList<>;

		//verifying/whitlist for ordercolumns
		Set<String> allowedCols = Set.of("created_at");
		Set<String> allowedDir = Set.of("ASC", "DESC");

		if (!allowedCols.contains(orderColumn.toLowerCase())) orderColumn = "created_at";
		if (!allowedDir.contains(orderDir.toUpperCase())) orderDir = "DESC";

		//select everyone!! muhehehee
		//and then that table will be used
		//to order them by MOST recent!
		String sql = """
			CREATE TABLE IF NOT EXISTS all_ids AS
         SELECT player_id AS id, 'player' AS source, created_at
			FROM player
         UNION ALL
         SELECT coach_id AS id, 'coach' AS source, created_at
         FROM coach
         UNION ALL
         SELECT tournament_id AS id, 'tournament' AS source, created_at
         FROM tournaments
         ORDER BY %s %s 
			""".formatted(orderColumn, orderDir);

		//get the stuff and stuff
		//it in the ykyk the array list
		//thats the feed for enthusiast
		try(Connection conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement()) {
			statement.executeUpdate(sql);
			System.out.println("Successfully created table");

			ResultSet rs = statement.executeQuery("SELECT * FROM all_ids"); 
			while(rs.next()) {
				String source = rs.getString("source");
				int id = rs.getInt("id");
				
				/*waiting for groupmate crud*/
				switch(source) {
					case "player":
						break;
					case "coach":
						break;
					case "tournament":
						break;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return feed;
	}
}

