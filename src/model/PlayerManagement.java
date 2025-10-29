package model;
import java.util.ArrayList;
public class PlayerManagement {
    private ArrayList<Player> playerList;

    public PlayerManagement(){
        playerList = new ArrayList<Player>();
    }

    	public void addPlayer(Player player) {
		playerList.add(player);
	}

	public void deletePlayer(Player player) {
		playerList.remove(player);
	}

	public Player updatePlayer(Player oldPlayer, 
			Player updatedPlayer) {
		Player playerReference = searchPlayer("id", String.valueOf(oldPlayer.getPlayerID()));
		playerReference.update(updatedPlayer);
		return playerReference;
	}
	
	public Player searchPlayer(String category, String key) {
		for(Player player : playerList) {
			if(player == null) { continue; }

			switch(category) {
				case "id":
					if(String.valueOf(player.getPlayerID()).equalsIgnoreCase(key))
						return player;
					break;
				case "name":
					if(player.getFirstName().equalsIgnoreCase(key)|| player.getMiddleName().equalsIgnoreCase(key) || player.getLastName().equalsIgnoreCase(key))
						return player;
					break;
			}
		}
		return null;
	}
}
