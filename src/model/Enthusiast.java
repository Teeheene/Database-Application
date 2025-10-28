package model;

import view.LoginBuilder;

/*
 *	Make a builder for enthusiast :D
 * */
public class Enthusiast {
	private int ID;
	private String username;
	private String lastName;
	private String firstName;
	private String middleName;
	private String sex;
	private Timestamp dateOfBirth;	
	private Timestamp joinDate;
	//temporary until IDs are implemented for other classes

	//login details
	private int password;
	private LoginBuilder loginDetails = new LoginBuilder();

	public Enthusiast() {
		//empty constructor
	};

	/*
	 * Constructor for new enthusiast
	 * When using constructor, take note of below
	 *
	 * NEED TO BE SET:
	 * ID number
	 * join date
	 * */
	public Enthusiast(String username, String lastName, String firstName, String middleName, String sex, Timestamp dateOfBirth, int password) {
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.sex = sex;
		this.dateOfBirth = dateOfBirth;
		this.loginDetails = new LoginBuilder(username, password, "enthusiast"); 
	}

	public void update(Enthusiast other) {
		if(other.getUsername() != null) { this.username = other.getUsername(); }
		if(other.getLastName() != null) { this.lastName = other.getLastName(); }
		if(other.getFirstName() != null) { this.firstName = other.getFirstName(); }
		if(other.getMiddleName() != null) { this.middleName = other.getMiddleName(); }
		if(other.getDateOfBirth() != null) { this.dateOfBirth = other.getDateOfBirth(); }
		if(other.getSex() != null) { this.sex = other.getSex(); }
		if(other.loginDetails.getPassword() != -1) { this.password = other.loginDetails.getPassword(); }
	}

	public int getID() { return ID; }
	public String getUsername() { return username; }
	public String getLastName() { return lastName; }
	public String getFirstName() { return firstName; }
	public String getMiddleName() { return middleName; }
	public String getSex() { return sex; }
	public Timestamp getDateOfBirth() { return dateOfBirth; }
	public Timestamp getJoinDate() { return joinDate; }
	public int getPassword() { return loginDetails.getPassword(); } 

	public void setUsername(String username) { this.username = username; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setMiddleName(String middleName) { this.middleName = middleName; }
	public void setSex(String sex) { this.sex = sex; }
	public void setDateOfBirth(Timestamp dateOfBirth) { this.dateOfBirth = dateOfBirth; }
	public void setPassword(int password) { this.password = password; }
}
