package view; 

public class LoginBuilder {
	private String username;
	private String password;
	private String type;

	public LoginBuilder() {
		//empty constructor
	}

	public LoginBuilder(String username, String password, String type) {
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public String getType() { return type; }
}
