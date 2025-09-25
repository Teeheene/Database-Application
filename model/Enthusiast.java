package model;

public class Enthusiast {
	private static int nextID = 1;
	private int ID;
	private String username;
	private String lastName;
	private String firstName;
	private String middleName;
	private String dateOfBirth;	
	private String sex;
	private String joinDate;
	//temporary until IDs are implemented for other classes
	private String favoritePlayer;

	public Enthusiast(String username, String lastName, String firstName, String middleName, String dateOfBirth, String sex, String joinDate) {
		this.ID = nextID++;
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.joinDate = joinDate;
	}

	public int getID() { return ID; }
	public String getUsername() { return username; }
	public String getLastName() { return lastName; }
	public String getFirstName() { return firstName; }
	public String getMiddleName() { return middleName; }
	public String getDateOfBirth() { return dateOfBirth; }
	public String getSex() { return sex; }
	public String getJoinDate() { return joinDate; }
	public String favoritePlayer() { return favoritePlayer; }

	public void setUsername(String username) { this.username = username; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setMiddleName(String middleName) { this.middleName = middleName; }
	public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
	public void setSex(String sex) { this.sex = sex; }
	public void setFavoritePlayer(String favoritePlayer) { this.favoritePlayer = favoritePlayer; }
}
