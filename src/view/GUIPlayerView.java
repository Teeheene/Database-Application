package view;
import model.*;
import controller.*;
import util.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import java.awt.*;

public class GUIPlayerView {
    private JFrame frame;
    private Container cp;

    private PlayerController controller;
    private Player player; 

    public GUIPlayerView() {}

    public void start() {
        frame = GUIUtil.setupGUI(frame, cp, 720, 480);
    }

    public void setListeners(PlayerController controller) {
        this.controller = controller;
    }

    // FIX 1: Overloaded method to use stored player object (used by back button)
    public void profilePanel() {
        if (this.player != null) {
            profilePanel(this.player);
        }
    }

    public void profilePanel(Player player) {
        this.player = player; // <--- FIX 1: Store the player object
        cp = BackgroundPanel.create("assets/login/player/profile.png");
        cp.setLayout(null);
        frame.setContentPane(cp);
        
        // Data labels
        // FIX: Corrected getPLayerID() -> getPlayerID()
        JLabel id = GUIUtil.createText(String.valueOf(player.getPlayerID()),416,205,92,27);
        // FIX: Corrected getSex() -> getGender()
        JLabel sex = GUIUtil.createText(String.valueOf(player.getGender()),532,205,87,27); 
        JLabel fullname = GUIUtil.createText(player.getFullName(),105,261,514,27);
        JLabel birthday = GUIUtil.createText(player.getDateOfBirth(),106,318,243,27);
        JLabel height = GUIUtil.createText(String.valueOf(player.getHeight()) + " cm",368,318,125,27);
        JLabel weight = GUIUtil.createText(String.valueOf(player.getWeight()) + " kg",521,318,98,27);
        // FIX: Corrected getRStatus() -> getStatus()
        JLabel rStatus = GUIUtil.createText(player.getStatus() ? "Active" : "Inactive",659,205,92,27);
        

        JButton updateBtn = GUIUtil.createIButton(455,116,80,38);
        JButton deleteBtn = GUIUtil.createIButton(544,116,80,36);

        // FIX 1: Add action listeners for navigation
        updateBtn.addActionListener(e -> updatePanel());
        deleteBtn.addActionListener(e -> deletePanel());

        cp.add(id);
        cp.add(sex);
        cp.add(fullname);
        cp.add(birthday);
        cp.add(height);
        cp.add(weight);
        cp.add(rStatus);
        cp.add(updateBtn);
        cp.add(deleteBtn);

        cp.revalidate();
        cp.repaint();
    }
    
    public void updatePanel() {
        if (player == null) {
             // Handle case where player is not initialized (e.g., error handling)
            return; 
        }

        cp = BackgroundPanel.create("assets/admin/player/update_view.png");
        cp.setLayout(null);
        frame.setContentPane(cp);

        // FIX 1: Corrected getPLayerID() to getPlayerID()
        JLabel id = GUIUtil.createText(String.valueOf(player.getPlayerID()),410,199,92,27);
        JLabel firstname = GUIUtil.createText(player.getFirstName(),102,273,153,27);
        JLabel middlename = GUIUtil.createText(player.getMiddleName(),276,271,164,27);
        JLabel lastname = GUIUtil.createText(player.getLastName(),462,273,153,27);
        // Displaying date as stored in Player model (String)
        JLabel birthday = GUIUtil.createText(player.getDateOfBirth(),100,346,245,27);
        
        // FIX 2: Corrected getSex() to getGender()
        JLabel sex = GUIUtil.createText(String.valueOf(player.getGender()),526,199,89,27);
        JLabel height = GUIUtil.createText(String.valueOf(player.getHeight()) + " cm",310,346,125,27);
        JLabel weight = GUIUtil.createText(String.valueOf(player.getWeight()) + " kg",521,346,98,27);
        
        // FIX 3: Corrected getRStatus() to getStatus()
        JLabel rStatus = GUIUtil.createText(player.getStatus() ? "Active" : "Inactive",659,199,92,27);
    
        JButton backBtn = GUIUtil.createIButton(648,440,64,27);
        JButton saveBtn = GUIUtil.createIButton(570,440,64,27);
        
        // FIX 4: Removed editUsername as Player model does not have a username field
        // JButton editUsername = GUIUtil.createIButton(354,179,36,18); 
        
        JButton editFirstName = GUIUtil.createIButton(223,253,36,18);
        JButton editMiddleName = GUIUtil.createIButton(409,252,36,18);
        JButton editLastName = GUIUtil.createIButton(584,253,36,18);
        JButton editSex = GUIUtil.createIButton(584,179,36,18);
        JButton editBirthday = GUIUtil.createIButton(314,326,36,18);

        cp.add(id);
        cp.add(sex);
        cp.add(firstname);
        cp.add(middlename);
        cp.add(lastname);
        cp.add(birthday);
        cp.add(height);
        cp.add(weight);
        cp.add(rStatus);

        cp.add(backBtn);
        cp.add(saveBtn);
        // cp.add(editUsername); // Removed
        cp.add(editFirstName);
        cp.add(editMiddleName);
        cp.add(editLastName);
        cp.add(editSex);
        cp.add(editBirthday);

        // FIX 1: Changed to call the parameterless profilePanel() overload
        backBtn.addActionListener(e -> profilePanel()); 
        saveBtn.addActionListener(e -> controller.handleUpdate(player));
        
        // FIX 4: Removed editUsername listener
        // editUsername.addActionListener(e -> updateFieldPanel("username")); 
        
        editFirstName.addActionListener(e -> updateFieldPanel("first name"));
        editMiddleName.addActionListener(e -> updateFieldPanel("middle name"));
        editLastName.addActionListener(e -> updateFieldPanel("last name"));
        editSex.addActionListener(e -> updateFieldPanel("sex"));
        editBirthday.addActionListener(e -> updateFieldPanel("birthday"));

        cp.revalidate();
        cp.repaint(); 
    }

    public void updateFieldPanel(String type) {
        if (player == null) return;
        
        cp = BackgroundPanel.create("assets/admin/player/update_field.png");
        cp.setLayout(null);
        frame.setContentPane(cp);

        JButton submitBtn = GUIUtil.createIButton(519,252,98,27);
        JTextField field = GUIUtil.createTextField(100,252,400,27);
        JButton backBtn = GUIUtil.createIButton(648,440,64,27);
        cp.add(submitBtn);
        cp.add(field);
        cp.add(backBtn);

        backBtn.addActionListener(e -> updatePanel());
        submitBtn.addActionListener(e -> {
            String input = field.getText().trim();
            
            // Validation for required fields
            switch(type) {
                // FIX 5: Removed "username" from required field validation
                case "sex": case "first name": 
                case "last name": case "birthday":
                    if(input.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, 
                            "Required Field! Please input update for " + type + ".");
                        return;
                    }
            }

            switch(type) {
                // FIX 5: Removed case "username"
                case "sex": 
                    // FIX 6: Changed setSex() to setGender() for consistency with getGender()
                    player.setSex(input.toUpperCase()); 
                    break;
                case "first name": 
                    player.setFirstName(input); 
                    break;
                case "middle name":
                    if(input.isEmpty())
                        player.setMiddleName(null);
                    else
                        player.setMiddleName(input);
                    break;
                case "last name":
                    player.setLastName(input);
                    break;
                case "birthday":
                    // FIX 7: Confirmed date parsing logic is correct (MM/DD/YYYY input -> YYYY-MM-DD model)
                    DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy"); 
                    DateTimeFormatter outputFormat = DateTimeFormatter.ISO_DATE; // YYYY-MM-DD
                    try {
                        LocalDate date = LocalDate.parse(input, inputFormat);
                        // Store the date in the YYYY-MM-DD format as required by Player.java
                        player.setDateOfBirth(date.format(outputFormat)); 
                    } catch(DateTimeParseException dtpe) {
                        JOptionPane.showMessageDialog(frame, 
                            "Invalid Date. Please input it as MM/DD/YYYY");
                        return;
                    }
                    break;
            }

            updatePanel();
        });
        
        cp.revalidate();
        cp.repaint();
        
    }

    // FIX 8: Corrected method name to proper Java convention: passUpdatedPlayer
    public void passUpdatedPlayer(Player player) {
        // Assuming Player.java has the public void update(Player other) method
        this.player.update(player); 
        profilePanel(); // Calls the parameterless overload
    }

    public void deletePanel() {
        if (player == null) return;
        
        int choice = JOptionPane.showConfirmDialog(
                        frame, 
                        "Do you want to proceed?", 
                        "Account Deletion", 
                        JOptionPane.YES_NO_OPTION
                        );

        if(choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Successful Deletion! Logging out."); 
            controller.handleDelete(player);
            controller.handleLogout();
        } else {
            JOptionPane.showMessageDialog(null, "Deletion cancelled.");
        }
    }

    public void displayMessage(String message) {
    JOptionPane.showMessageDialog(
        frame,     // parent component (your view)
        message, 
        "Information",
        JOptionPane.INFORMATION_MESSAGE
    );
}

    public void displayError(String message) {
    JOptionPane.showMessageDialog(
        frame, 
        message, 
        "Error",  
        JOptionPane.ERROR_MESSAGE
    );
}
    public void start(Player player) {
        this.player = player;
        profilePanel(player);
    }
    public void show() { frame.setVisible(true); }
    public void hide() { frame.setVisible(false); }
    public void dispose() { if(frame != null) frame.dispose(); }
}