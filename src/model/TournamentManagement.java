package model;

import java.util.ArrayList;

public class TournamentManagement {

    private ArrayList<Tournament> tournamentList;

    // Constructor
    public TournamentManagement () {
        tournamentList = new ArrayList<Tournament>();
    }

    public void addTournament (Tournament tournament) {
        tournamentList.add(tournament);
    }

    public void deleteTournament (Tournament tournament) {
        tournamentList.remove(tournament);
    }

    public Tournament updateTournament (Tournament oldTournament, Tournament updatedTournament) {
        Tournament tournamentReference = searchTournament("id", String.valueOf(oldTournament.getTournamentID()));
        if (tournamentReference != null) {
            tournamentReference.update(updatedTournament);
        }
        return tournamentReference;
    }

    public Tournament searchTournament (String category, String key) {
        for (Tournament tournament : tournamentList) {
            if (tournament == null) continue;

            switch (category.toLowerCase()) {
                case "id":
                    if (String.valueOf(tournament.getTournamentID()).equalsIgnoreCase(key))
                        return tournament;
                    break;
                case "name":
                    if (tournament.getTournamentName().equalsIgnoreCase(key))
                        return tournament;
                    break;
                case "type":
                    if (tournament.getTournamentType().equalsIgnoreCase(key))
                        return tournament;
                    break;
            }
        }
        return null;
    }

}