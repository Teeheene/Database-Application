package view; 

public class LoginBuilder {
	private String username;
	private int password; 
	private String type;

	public LoginBuilder() {
		this.password = -1;
	}

	public LoginBuilder(String username, int password, String type) {
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public String getUsername() { return username; }
	public int getPassword() { return password; }
	public String getType() { return type; }
}
