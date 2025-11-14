package model;

import util.*;
import java.sql.*;
import java.util.ArrayList;

public class TournamentManagement {

    // Constructor
    public TournamentManagement () { }

    // add tournament
    public int addTournament(Tournament tournament) {

        String sql = "INSERT INTO tournament " +
                "(tournament_name, season_year, tournament_type, start_date, end_date) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connect = DatabaseConnection.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, tournament.getTournamentName());
            statement.setInt(2, tournament.getSeasonYear());
            statement.setString(3, tournament.getTournamentType());
            statement.setDate(4, Date.valueOf(tournament.getStartDate().toStringDate()));
            statement.setDate(5, Date.valueOf(tournament.getEndDate().toStringDate()));

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Tournament added successfully.");
            }

            // Get generated ID
            try (ResultSet generatedKey = statement.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    return generatedKey.getInt(1);
                } else {
                    throw new SQLException("Failed creating tournament: No ID returned.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // delete tournament
    public void deleteTournament(int tournamentID) {

        String sql = "DELETE FROM tournament WHERE tournament_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, tournamentID);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0)
                System.out.println("Tournament deleted");
            else
                System.out.println("Failed to delete");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update tournament
    public Tournament updateTournament(Tournament oldTournament, Tournament updatedTournament) {

        Tournament tournamentReference = searchTournamentByID(oldTournament.getTournamentID());
        if (tournamentReference == null) {
            System.out.println("Tournament not found.");
            return null;
        }

        tournamentReference.update(updatedTournament);

        String sql = "UPDATE tournament SET " +
                        "tournament_name = ?, " +
                        "season_year = ?, " +
                        "tournament_type = ?, " +
                        "start_date = ?, " +
                        "end_date = ? " +
                        "WHERE tournament_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, tournamentReference.getTournamentName());
            statement.setInt(2, tournamentReference.getSeasonYear());
            statement.setString(3, tournamentReference.getTournamentType());
            statement.setDate(4, Date.valueOf(tournamentReference.getStartDate().toStringDate()));
            statement.setDate(5, Date.valueOf(tournamentReference.getEndDate().toStringDate()));
            statement.setInt(6, tournamentReference.getTournamentID());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0)
                System.out.println("Tournament updated");
            else
                System.out.println("Failed to update");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tournamentReference;
    }

    // get all tournament
    public ArrayList<Tournament> getTournament() {

        ArrayList<Tournament> tournamentList = new ArrayList<>();

        String sql = "SELECT * FROM tournament";

			  /*
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

				fix please
            while (rs.next()) {

                Tournament tournament = new Tournament(
                        rs.getInt("tournament_id"),
                        rs.getString("tournament_name"),
                        rs.getInt("season_year"),
                        rs.getString("tournament_type"),
                        new CustomTimestamp(rs.getDate("start_date")),
                        new CustomTimestamp(rs.getDate("end_date"))
                );

                tournamentList.add(tournament);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tournamentList;
				*/

			return null;
    }


    // search by id
    public Tournament searchTournamentByID(int tournamentID) {

        String sql = "SELECT * FROM tournament WHERE tournament_id = ?";
        Tournament tournament = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, tournamentID);

            ResultSet rs = statement.executeQuery();
				/* fix

            if (rs.next()) {
                tournament = new Tournament(
                        rs.getInt("tournament_id"),
                        rs.getString("tournament_name"),
                        rs.getInt("season_year"),
                        rs.getString("tournament_type"),
                        new CustomTimestamp(rs.getDate("start_date")),
                        new CustomTimestamp(rs.getDate("end_date"))
                );
            }
				*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tournament;
    }
}
