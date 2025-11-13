package model;

import util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class CoachManagement {
    public CoachManagement() {
    }

    public int addCoach(Coach coach) {
        String sql = "INSERT INTO coach (coach_id, lastname, firstname, middlename, sex, date_of_birth, start_year, end_year, years_in_field, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, coach.getCoachID());
            statement.setString(2, coach.getLastName());
            statement.setString(3, coach.getFirstName());
            statement.setString(4, coach.getMiddleName());
            statement.setDate(5, Date.valueOf(coach.getBirthday()));
            statement.setString(6, String.valueOf(coach.getGender()));
            statement.setInt(7, coach.getStartYear());
            statement.setInt(8, coach.getEndYear());
            statement.setInt(9, coach.getYearsInField());
            statement.setBoolean(10, coach.isInGameStatus());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0)
                System.out.println("Coach added.");

            try (ResultSet generatedKey = statement.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    int newID = generatedKey.getInt(1);
                    return newID;
                } else
                    throw new SQLException("Failed creating coach.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void deleteCoach(int coach_id) {
        String sql = "DELETE FROM coach WHERE coach_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, coach_id);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0)
                System.out.println("Coach deleted.");
            else
                System.out.println("Failed to delete coach.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Coach updateCoach(Coach oldCoach, Coach newCoach) {
        String sql = "UPDATE coach SET lastname = ?, firstname = ?, middlename = ?, date_of_birth = ?, sex = ?, status = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, newCoach.getCoachID());
            statement.setString(2, newCoach.getLastName());
            statement.setString(3, newCoach.getFirstName());
            statement.setString(4, newCoach.getMiddleName());
            statement.setDate(5, Date.valueOf(newCoach.getBirthday()));
            statement.setString(6, String.valueOf(newCoach.getGender()));
            statement.setInt(7, newCoach.getStartYear());
            statement.setInt(8, newCoach.getEndYear());
            statement.setInt(9, newCoach.getYearsInField());
            statement.setBoolean(10, newCoach.isInGameStatus());

            int updatedRows = statement.executeUpdate();

            if (updatedRows > 0)
                System.out.println("Coach successfully updated.");
            else
                System.out.println("No coach found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchCoach(oldCoach.getCoachID());
    }

    public Coach searchCoach(int coach_id) {
        String sql = "SELECT * FROM coach WHERE coach_id = ?";
        Coach coach = null;

        /*try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, coach_id);

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }*/

        return coach;
    }
}