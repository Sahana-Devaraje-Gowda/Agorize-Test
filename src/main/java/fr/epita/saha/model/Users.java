package fr.epita.saha.model;

public class Users {
	private int id;
	private int points;

	public Users(int points) {
		super();
		this.points = points;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
