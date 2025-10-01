import view.*;
import model.*;
import controller.*;

public class Main {
	public static void main(String[] args) {
		View view = new CLIView();
		EnthusiastView viewEnthusiast = new CLIEnthusiastView(); 
	
		MainController app = new MainController(view, viewEnthusiast); 	

		app.start();
	}
}

