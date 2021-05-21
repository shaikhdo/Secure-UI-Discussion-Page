package ca.sheridancollege.shaikhdo.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.shaikhdo.beans.User;

@Repository

public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User findUserAccount(String email) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user where email = :email";
		parameters.addValue("email", email);
		ArrayList<User> userList = (ArrayList<User>) jdbc.query(query, parameters,
				new BeanPropertyRowMapper<User>(User.class));
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

	public List<String> getRolesById(Long userId) {
		ArrayList<String> roleList = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT user_role.userId, sec_role.roleName " + "FROM user_role, sec_role "
				+ "WHERE user_role.roleId = sec_role.roleId " + "AND userId = :userId";
		parameters.addValue("userId", userId);

		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			roleList.add((String) row.get("roleName"));
		}
		return roleList;

	}

	public void addUser(String email, String password) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO sec_user " + "(email, encryptedPassword, enabled)"
				+ " VALUES (:email, :encryptedPassword, 1)";
		parameters.addValue("email", email);
		parameters.addValue("encryptedPassword", passwordEncoder.encode(password));
		jdbc.update(query, parameters);
	}

	public void addRole(Long userId, Long roleId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO user_role (userId, roleId) " 
		+ "VALUES (:userId, :roleId)";
		parameters.addValue("userId", userId);
		parameters.addValue("roleId", roleId);
		jdbc.update(query, parameters);
	}
	
	public void insertPost(User userComment) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO userpubpost(email, userPost, sub_date, sub_time) VALUES (:email, :userPost, :sub_date, :sub_time)";
		namedParameters.addValue("email", userComment.getEmail());
		namedParameters.addValue("userPost", userComment.getUserPost());
		namedParameters.addValue("sub_date", userComment.getSub_date());
		namedParameters.addValue("sub_time", userComment.getSub_time());
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0)
			System.out.println("Inserted comment into database.");
	}

	public List<User> getUserCommentList() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * from userpubpost";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<User>(User.class));
	}
	


}
