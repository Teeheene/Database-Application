package model;

public class Engagement {
	private int ID;
	private String targetCategory;
	private String type;
	private int enthusiastID;
	private int targetID;
	private Timestamp createdAt;
	//temporary until IDs are implemented for other classes

	public Engagement() {
		//empty constructor
	};

	public Engagement(String targetCategory, String type, int enthusiastID, int targetID) {
		this.targetCategory = targetCategory;
		this.type = type;
		this.enthusiastID = enthusiastID;
		this.targetID = targetID;
	}

	public int getID() { return ID; }
	public String getTargetCategory() { return targetCategory; }
	public String getType() { return type; }
	public int getEnthusiastID() { return enthusiastID; }
	public int getTargetID() { return targetID; }
	public Timestamp getCreatedAt() { return createdAt; }

	public void setID(int ID) { this.ID = ID; }
	public void setTargetCategory(String targetCategory) { this.targetCategory = targetCategory; }
	public void setType(String type) { this.type = type; }
	public void setEnthusiastID(int enthusiastID) { this.enthusiastID = enthusiastID; }
	public void setTargetID(int targetID) { this.targetID = targetID; }
}
