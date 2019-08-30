package fr.epita.saha.model;

public class SkillsUsers {
	private int id;
	private int skill_id;
	private int user_id;

	public SkillsUsers(int skill_id, int user_id) {
		this.skill_id = skill_id;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSkill_id() {
		return skill_id;
	}

	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
