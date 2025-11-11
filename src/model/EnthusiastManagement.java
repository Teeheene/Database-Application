package model;

import util.*;
import view.LoginBuilder; 
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;

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
		String sql = "INSERT INTO enthusiast (username, lastname, firstname, middlename, sex, date_of_birth) " 
			+ "VALUES (?, ?, ?, ?, ?, ?)";

		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, enthusiast.getUsername());
			statement.setString(2, enthusiast.getLastName());
			statement.setString(3, enthusiast.getFirstName());
			statement.setString(4, enthusiast.getMiddleName());
			statement.setString(5, enthusiast.getSex());
			statement.setDate(6, Date.valueOf(enthusiast
						.getDateOfBirth()
						.getFormattedDate()));

			statement.executeUpdate();
			System.out.println("Enthusiast added.");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEnthusiast(int enthusiastID) {
		String sql = "DELETE FROM enthusiast WHERE enthusiast_id = ?";

		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, enthusiastID);

			int rowsDeleted = statement.executeUpdate();
			if(rowsDeleted > 0) 
				System.out.println("Enthusiast deleted");
			else
				System.out.println("Failed to delete");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public Enthusiast updateEnthusiast(Enthusiast oldEnthusiast, Enthusiast updatedEnthusiast) {
		Enthusiast enthusiastReference = 
			searchEnthusiastByID(oldEnthusiast.getID());
		enthusiastReference.update(updatedEnthusiast);

		String sql = "UPDATE enthusiast SET " +
			"username = ?, " +
			"lastname = ?, " + 
			"firstname = ?, " + 
			"middlename = ?, " + 
			"sex = ?, " + 
			"date_of_birth = ? " +
			"WHERE enthusiast_id = ?";

		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, enthusiastReference.getUsername());
			statement.setString(2, enthusiastReference.getLastName());
			statement.setString(3, enthusiastReference.getFirstName());
			statement.setString(4, enthusiastReference.getMiddleName());
			statement.setString(5, enthusiastReference.getSex());
			statement.setDate(6, Date.valueOf(enthusiastReference
						.getDateOfBirth()
						.getFormattedDate()));
			statement.setInt(7, enthusiastReference.getID());

			int rowsAffected = statement.executeUpdate();
			if(rowsAffected > 0)
				System.out.println("Enthusiast updated");
			else
				System.out.println("Failed to update");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return enthusiastReference;
	}
		
	public ArrayList<Enthusiast> getEnthusiasts() {
		ArrayList<Enthusiast> enthusiastsList = new ArrayList<>();

		String sql = "SELECT * FROM enthusiast";
	
		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Enthusiast enthusiast = new Enthusiast(
					rs.getInt("enthusiast_id"),
					rs.getString("username"),
					rs.getString("lastname"),
					rs.getString("firstname"),
					rs.getString("middlename"),
					rs.getString("sex"),
					rs.getString("date_of_birth"),
					rs.getString("created_at")
				);
				enthusiastList.add(enthusiast);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return enthusiastList;
	}


	public Enthusiast searchEnthusiastByID(int ID) {
		String sql = "SELECT * FROM enthusiast WHERE enthusiast_id = ?";
		Enthusiast enthusiast = null;
	
		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, ID);

			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				enthusiast = new Enthusiast(
					rs.getInt("enthusiast_id"),
					rs.getString("username"),
					rs.getString("lastname"),
					rs.getString("firstname"),
					rs.getString("middlename"),
					rs.getString("sex"),
					rs.getString("date_of_birth"),
					rs.getString("created_at")
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return enthusiast;
	}
}
