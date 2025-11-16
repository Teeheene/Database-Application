package model;

import util.*;

import java.sql.*;
import java.util.ArrayList;


public class CoachManagement {
    public CoachManagement() {
    }

    public int addCoach(Coach coach) {
        String sql = "INSERT INTO coach (coach_id, lastname, firstname, middlename, sex, date_of_birth, start_year, end_year, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, coach.getCoachID());
            statement.setString(2, coach.getLastName());
            statement.setString(3, coach.getFirstName());
            statement.setString(4, coach.getMiddleName());
            statement.setDate(5, Date.valueOf(coach.getBirthday().toStringDate()));
            statement.setString(6, String.valueOf(coach.getGender()));
            statement.setInt(7, coach.getStartYear());
            statement.setInt(8, coach.getEndYear());
            statement.setBoolean(9, coach.isInGameStatus());

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
            statement.setDate(5, Date.valueOf(newCoach.getBirthday().toStringDate()));
            statement.setString(6, String.valueOf(newCoach.getGender()));
            statement.setInt(7, newCoach.getStartYear());
            statement.setInt(8, newCoach.getEndYear());
            statement.setBoolean(9, newCoach.isInGameStatus());

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

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, coach_id);

            try(ResultSet rs = statement.executeQuery()) {
                if(rs.next()) {
                    coach = new Coach(
                            rs.getInt("coach_id"),
                            rs.getString("lastname"),
                            rs.getString("firstname"),
                            rs.getString("middlename"),
                            rs.getString("date_of_birth"),
                            rs.getString("sex"),
                            rs.getInt("start_year"),
                            rs.getInt("end_year"),
                            rs.getBoolean("status")
                    );
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return coach;
    }

    public ArrayList<Coach> getAllCoaches() {
        ArrayList<Coach> coaches = new ArrayList<>();
        String sql = "SELECT * FROM coach";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Coach coach = new Coach(
                        rs.getInt("coach_id"),
                        rs.getString("lastname"),
                        rs.getString("firstname"),
                        rs.getString("middlename"),
                        rs.getString("date_of_birth"),
                        rs.getString("sex"),
                        rs.getInt("start_year"),
                        rs.getInt("end_year"),
                        rs.getBoolean("status")
                );
                coaches.add(coach);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return coaches;
    }
}