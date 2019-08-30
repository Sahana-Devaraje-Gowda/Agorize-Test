package fr.epita.saha.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.saha.model.Results;
import fr.epita.saha.model.Skills;
import fr.epita.saha.model.SkillsUsers;
import fr.epita.saha.model.Users;

public class SkillsDao {
	private String sql_url = "jdbc:postgresql://127.0.0.1:5432/test";
	private String sql_user = "postgres";
	private String sql_password = "root";

	public void createSkillUsers() {
		String SQL_CREATE_SKILLUSERS = "CREATE TABLE SkillsUsers (id SERIAL PRIMARY KEY, skill_id integer, user_id integer);";

		try { 
			Connection conn = DriverManager.getConnection(sql_url, sql_user, sql_password);
			PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE_SKILLUSERS);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertSkillUsers(List<SkillsUsers> skillUsers) {
		for (SkillsUsers skillsUser : skillUsers) {
			String SQL_INSERT_SKILLUSERS = "INSERT INTO SkillsUsers (skill_id, user_id) VALUES (" + skillsUser.getSkill_id() + "," + skillsUser.getUser_id() + ");";

			try { 
				Connection conn = DriverManager.getConnection(sql_url, sql_user, sql_password);
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_SKILLUSERS);
				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void createSkills() {
		String SQL_CREATE_SKILL = "CREATE TABLE Skills (id SERIAL PRIMARY KEY, name varchar not null, parent_id integer);";

		try { 
			Connection conn = DriverManager.getConnection(sql_url, sql_user, sql_password);
			PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE_SKILL);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertSkill(List<Skills> skills) {
		for (Skills skill : skills) {
			String SQL_INSERT_SKILLUSERS = null;
			
			if(skill.getParent_id() == 0) {
				SQL_INSERT_SKILLUSERS = "INSERT INTO Skills (name) VALUES ('" + skill.getName() + "');";
			} else {
				SQL_INSERT_SKILLUSERS = "INSERT INTO Skills (name, parent_id) VALUES ('" + skill.getName() + "'," + skill.getParent_id()+ ");";
			}
			
			try { 
				Connection conn = DriverManager.getConnection(sql_url, sql_user, sql_password);
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_SKILLUSERS);
				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void createUsers() {
		String SQL_CREATE_USER = "CREATE TABLE Users (id SERIAL PRIMARY KEY, points integer);";

		try { 
			Connection conn = DriverManager.getConnection(sql_url, sql_user, sql_password);
			PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE_USER);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertUsers(List<Users> users) {
		for (Users user : users) {
			String SQL_INSERT_USERS = "INSERT INTO Users (points) VALUES (" + user.getPoints() + ");";

			try { 
				Connection conn = DriverManager.getConnection(sql_url, sql_user, sql_password);
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_USERS);
				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void displayPoints() {
		List<Results> result = new ArrayList<>();
		
		String SQL_SELECT = "SELECT sk1.id AS ID,\n" + 
				"       sk1.NAME                                    AS NAME,\n" + 
				"       Sum(u1.points) + COALESCE(parent.point, 0) AS POINTS,\n" + 
				"       Count(u1.id) + COALESCE(parent.count, 0)   AS USER_COUNT\n" + 
				"FROM   skills sk1 \n" + 
				"       LEFT JOIN (SELECT Count(u2.id)   AS count,\n" + 
				"                         Sum(u2.points) AS point,\n" + 
				"                         sk2.parent_id   AS parent_id\n" + 
				"                  FROM   skills sk2\n" + 
				"                         INNER JOIN skillsusers su2\n" + 
				"                                 ON su2.skill_id = sk2.id\n" + 
				"                         INNER JOIN users u2\n" + 
				"                                 ON u2.id = su2.user_id\n" + 
				"                  GROUP  BY sk2.parent_id )AS parent\n" + 
				"              ON sk1.id = parent.parent_id\n" + 
				"       INNER JOIN skillsusers su1\n" + 
				"               ON su1.skill_id = sk1.id\n" + 
				"       INNER JOIN users u1\n" + 
				"               ON u1.id = su1.user_id\n" + 
				"GROUP BY sk1.name , parent.point, parent.count, sk1.parent_id, sk1.id\n" + 
				"HAVING sk1.parent_id IS NULL;";
		
		try {
			Connection conn = DriverManager.getConnection(sql_url, sql_user, sql_password);
		
			 PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			System.out.println("ID\t\t" + "Name\t\t" + "Points\t\t"  + "User_Count");
			
			while(resultSet.next()) {
				int id = resultSet.getInt("ID");
				String name = resultSet.getString("NAME");
				int points = resultSet.getInt("POINTS");
				int user_count = resultSet.getInt("USER_COUNT");
				
				Results results = new Results();
				results.setId(id);
				results.setName(name);
				results.setPoints(points);
				results.setUsers_count(user_count);
				
				result.add(results);
				
				System.out.println(results.getId() + "\t\t" + results.getName() + "\t" + results.getPoints() + "\t\t"  + results.getUsers_count());
 			}
			//result.forEach(x -> System.out.println(x.getPoints() + "\n" + x.getName() + "\n" + x.getUsers_count()));
			
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
