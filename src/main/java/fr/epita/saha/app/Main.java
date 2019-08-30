package fr.epita.saha.app;

import java.util.ArrayList;
import java.util.List;

import fr.epita.saha.dao.SkillsDao;
import fr.epita.saha.model.Skills;
import fr.epita.saha.model.SkillsUsers;
import fr.epita.saha.model.Users;

public class Main {

	public static SkillsDao dao;

	public static void main(String[] args) {

		dao = new SkillsDao();
		// Create SkillUsers and Skills
//		 dao.createSkillUsers();
//		 dao.createSkills();
//		 dao.createUsers();
//
//		 Main main = new Main();
//		 main.insertSkillsUsers();
//		 main.insertSkills();
//		 main.insertUsers();
		 dao.displayPoints();

	}

	private void insertSkillsUsers() {
		// Insert Skill Users
		List<SkillsUsers> skillUsers = new ArrayList<>();
		int skill_id[] = { 1, 1, 3, 2, 5 };
		int user_id[] = { 1, 2, 3, 4, 5 };

		for (int i = 0; i < skill_id.length; i++) {
			SkillsUsers skillUser = new SkillsUsers(skill_id[i], user_id[i]);
			skillUsers.add(skillUser);
		}
		dao.insertSkillUsers(skillUsers);
	}

	private void insertSkills() {
		// Insert Skills
		List<Skills> skills = new ArrayList<>();
		String names[] = { "Football", "Basketball", "Foot", "Basket", "Soccer" };
		int parent_ids[] = { 0, 0, 1, 2, 1 };
		for (int i = 0; i < names.length; i++) {
			Skills skill = new Skills(names[i], parent_ids[i]);
			skills.add(skill);
		}
		dao.insertSkill(skills);

	}

	private void insertUsers() {

		// Insert Users
		List<Users> users = new ArrayList<>();
		int points[] = { 100, 200, 100, 50, 10 };
		for (int i = 0; i < points.length; i++) {
			Users user = new Users(points[i]);
			users.add(user);
		}
		dao.insertUsers(users);
	}

}
