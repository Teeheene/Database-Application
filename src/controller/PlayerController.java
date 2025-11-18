package controller;

import model.*;
import view.*;
import javax.swing.JOptionPane;

public class PlayerController {

    private GUIView prevView;          // Main menu screen
    private GUIPlayerView playerView;  // Player screen
    private PlayerManagement model;

    public PlayerController(GUIView prevView, GUIPlayerView view) {
        this.prevView = prevView;
        this.playerView = view;

        this.model = new PlayerManagement();
    }

    /* ===========================================================
        NAVIGATION / VIEW CONTROL
       =========================================================== */
    public void showPlayerProfile(Player p) {
        playerView.profilePanel(p);
        playerView.show();
    }

    public void handleBackToMenu() {
        playerView.hide();
        prevView.menuPanel();
        prevView.show();
    }

    public void handleLogout() {
        playerView.hide();
        prevView.menuPanel();
        prevView.show();
    }

    /* ===========================================================
        CRUD OPERATIONS – rewritten for GUI use
       =========================================================== */

    // Called when GUI saves player updates
    public void handleUpdate(Player updatedPlayer) {
        try {
            Player result = model.updatePlayer(updatedPlayer);
            if (result != null) {
                JOptionPane.showMessageDialog(null, "Player updated successfully!");
                playerView.profilePanel(result);     // Refresh
            } else {
                JOptionPane.showMessageDialog(null, "Database update failed.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating: " + e.getMessage());
        }
    }

    // Called by deletePanel()
    public void handleDelete(Player p) {
    try {
        model.deletePlayer(p.getPlayerID());   // void
        playerView.displayMessage("Player deleted successfully!");
    } catch (Exception e) {
        playerView.displayError("Error deleting player: " + e.getMessage());
    }
}

    /* ===========================================================
        SEARCH → show profile
       =========================================================== */
    public void handleSearchPlayer(int playerID) {
        try {
            Player p = model.searchPlayer(playerID);
            if (p != null) {
                showPlayerProfile(p);
            } else {
                JOptionPane.showMessageDialog(null, "Player not found.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Search error: " + e.getMessage());
        }
    }

    /* ===========================================================
        CREATE PLAYER – GUI version (uses a dialog)
       =========================================================== */
    public void handleCreatePlayer(Player newPlayer) {
        try {
            int newID = model.addPlayer(newPlayer);

            if (newID > 0) {
                newPlayer.setPlayerID(newID);
                JOptionPane.showMessageDialog(null, "Player created! ID = " + newID);

                showPlayerProfile(newPlayer);
            } else {
                JOptionPane.showMessageDialog(null, "Create failed.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

}
