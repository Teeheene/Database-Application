import view.*;
import model.*;
import controller.*;

public class Main {
	public static void main(String[] args) {
		View view = new CLIView();
		EnthusiastView viewEnthusiast = new CLIEnthusiastView(); 
		PlayerView viewPlayer = new CLIPlayerView();
		EngagementView viewEngagement = new CLIEngagementView();
	
		MainController app = new MainController(view, viewEnthusiast, viewPlayer, viewEngagement); 	

		app.start();
	}
}

