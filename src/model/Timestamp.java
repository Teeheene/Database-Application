package model;

public class Timestamp {
	private int year;
	private int month;
	private int day;
	
	public Timestamp(int year, int month, int day) {
		this.year = year;
		this.month = month; 
		this.day = day;
	}
	
	public void displayDate() { 
		System.out.println(year + "/" + month + "/" + day);
	}

	public String getFormattedDate() {
		return year + "-" + month + "-" + day + " 00:00:00";	
	}

	public int getYear() { return year; }
	public int getMonth() { return month; }
	public int getDay() { return day; }

	public void setYear(int year) { this.year = year; }
	public void setMonth(int month) { this.month = month; }
	public void setDay(int day) { this.day = day; }
}
