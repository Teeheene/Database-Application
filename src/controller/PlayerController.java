package controller;

import model.Player;
import model.PlayerManagement;
import view.PlayerView;
import view.CLIPlayerView;

public class PlayerController {
    private PlayerManagement model;
    private PlayerView view;

    public PlayerController(PlayerManagement model, PlayerView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        boolean running = true;

        while (running) {
            try {
                view.displayMenu();
                int choice = view.getMenuChoice();

                switch (choice) {
                    case 1:
                        createPlayer();
                        break;
                    case 2:
                        searchPlayer();
                        break;
                    case 3:
                        updatePlayer();
                        break;
                    case 4:
                        deletePlayer();
                        break;
                    case 5:
                        displayAllPlayers();
                        break;
                    case 6:
                        running = false;
                        view.displayMessage("Thank you for using Player Management System!");
                        break;
                    default:
                        view.displayError("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                view.displayError("An error occurred: " + e.getMessage());
            }
        }
        view.close();
    }

    private void createPlayer() {
        try {
            Player newPlayer = view.getPlayerInput();
            
            Player existingPlayer = model.searchPlayer("id", String.valueOf(newPlayer.getPlayerID()));
            if (existingPlayer != null) {
                view.displayError("Player with ID " + newPlayer.getPlayerID() + " already exists.");
                return;
            }

            model.addPlayer(newPlayer);
            view.displayMessage("Player added successfully!");
            view.displayPlayer(newPlayer);
        } catch (Exception e) {
            view.displayError("Failed to add player: " + e.getMessage());
        }
    }

    private void searchPlayer() {
        try {
            String category = view.getSearchCategory();
            String key = view.getSearchKey();
            
            Player player = model.searchPlayer(category, key);
            view.displayPlayer(player);
        } catch (Exception e) {
            view.displayError("Failed to search player: " + e.getMessage());
        }
    }

    private void updatePlayer() {
        try {
            view.displayMessage("Search for the player to update:");
            String category = view.getSearchCategory();
            String key = view.getSearchKey();
            
            Player oldPlayer = model.searchPlayer(category, key);
            
            if (oldPlayer == null) {
                view.displayError("Player not found.");
                return;
            }

            view.displayMessage("Current player details:");
            view.displayPlayer(oldPlayer);

            if (view.confirmAction("update this player")) {
                Player updatedInfo = view.getPlayerUpdateInput();
                Player updatedPlayer = model.updatePlayer(oldPlayer, updatedInfo);
                
                view.displayMessage("Player updated successfully!");
                view.displayPlayer(updatedPlayer);
            } else {
                view.displayMessage("Update cancelled.");
            }
        } catch (Exception e) {
            view.displayError("Failed to update player: " + e.getMessage());
        }
    }

    private void deletePlayer() {
        try {
            view.displayMessage("Search for the player to delete:");
            String category = view.getSearchCategory();
            String key = view.getSearchKey();
            
            Player player = model.searchPlayer(category, key);
            
            if (player == null) {
                view.displayError("Player not found.");
                return;
            }

            view.displayMessage("Player found:");
            view.displayPlayer(player);

            if (view.confirmAction("delete this player")) {
                model.deletePlayer(player);
                view.displayMessage("Player deleted successfully!");
            } else {
                view.displayMessage("Deletion cancelled.");
            }
        } catch (Exception e) {
            view.displayError("Failed to delete player: " + e.getMessage());
        }
    }

    private void displayAllPlayers() {
        try {
            view.displayPlayers(model.getAllPlayers());
        } catch (Exception e) {
            view.displayError("Failed to display players: " + e.getMessage());
        }
    }
}
