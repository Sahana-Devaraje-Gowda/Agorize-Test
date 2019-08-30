package fr.epita.saha.model;

public class Results {
	private long id;
	private String name;
	private int points;
	private int users_count;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getUsers_count() {
		return users_count;
	}

	public void setUsers_count(int users_count) {
		this.users_count = users_count;
	}
}
