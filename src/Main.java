import view.*;
import model.*;
import controller.*;

public class Main {
	public static void main(String[] args) {
		GUIView view = new GUIView();
		PlayerView viewPlayer = new CLIPlayerView();
	
		MainController app = new MainController(view, viewPlayer); 	
		
		view.setListeners(app);	
		view.start();
	}
}

