import view.*;
import model.*;
import controller.*;

public class Main {
	public static void main(String[] args) {
		GUIView view = new GUIView();
		EnthusiastView viewEnthusiast = new CLIEnthusiastView(); 
		PlayerView viewPlayer = new CLIPlayerView();
		EngagementView viewEngagement = new CLIEngagementView();
	
		MainController app = new MainController(view, viewEnthusiast, viewPlayer, viewEngagement); 	
		
		view.setListeners(app);	

		app.start();
	}
}

