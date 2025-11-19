//PLAYER CONTROLLER CALLS
	public ArrayList<Player> getPlayerInformation() {

		ArrayList<Player> players = new ArrayList<>();
        for(Player p : playerModel.getAllPlayers()){
			if(p == null) {continue;}
			players.add(p);
		}
		players.sort(Comparator.comparingInt(Player::getPlayerID));
		return players;
	}

	public void addPlayer(Player player) {
		playerModel.addPlayer(player);
	}

	public void togglePlayer(Player player) {
		playerModel.togglePlayer(player.getPlayerID());
	}

	public void showPlayer(int ID) {
		Player player = playerModel.searchPlayer(ID);
		view.viewPlayerPanel(player);
	}

	public boolean updatePlayerView(int ID) {
		Player player = playerModel.searchPlayer(ID);
		if(player == null) return false; 
		view.updatePlayerPanel(player);
		return true;
	}
	
	public void updatePlayer(Player updatedPlayer) {
		Player player = playerModel.updatePlayer(updatedPlayer);
		view.viewPlayerPanel(player);
	}
		
	public boolean deletePlayerView(int ID) {
		Player player = playerModel.searchPlayer(ID);
		if(player == null) return false; 
		view.deletePlayerPanel(player);
		return true;
	}
	
	public void deletePlayer(Player player) {
		playerModel.deletePlayer(player.getPlayerID());
	}

	public int generatePlayerID() {
		return playerModel.generatePlayerID();
	}
    public void handlePlayer(String option) {
		switch(option) {
			case "home":
				handleMenu("player");
				break;
			case "create":
				view.createPlayerPanel();
				break;
			case "viewAll":
				view.viewAllPlayerPanel(getPlayerInformation());
				break;
			case "update":
				view.searchUpdatePlayerPanel();
				break;
			case "delete":
				view.searchDeletePlayerPanel();
				break;
			case "report":
				view.viewReportPlayerPanel();
				break;
			case "exit":
				view.dashboardPanel();
				break;
		}
	}
