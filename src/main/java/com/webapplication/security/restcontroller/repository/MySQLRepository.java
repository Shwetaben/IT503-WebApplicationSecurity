package com.webapplication.security.restcontroller.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.webapplication.security.domain.User;

@Repository
public class MySQLRepository {

	@Value("${database.host}")
	private String dbHost;

	@Value("${database.user}")
	private String dbUser;

	@Value("${database.password}")
	private String dbPwd;

	public List<User> getUnsecureUserDeatils(String userId) {
		List<User> userList = new ArrayList<User>();
		Connection conn = null;
		Statement stmt = null;
		String query = "select * from user where user_id ='" + userId + "'";

		try {
			
			conn = DriverManager.getConnection(dbHost, dbUser, dbPwd);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				User user = new User();
				user.setUser_id((rs.getString(1)));
				user.setName(rs.getString(2));
				user.setSalary(rs.getInt(3));

				userList.add(user);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}

		return userList;
	}
	
	public List<User> getSecureUserDeatils(String userId) {
		List<User> userList = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String query = "select * from user where user_id =?";

		try {
			
			conn = DriverManager.getConnection(dbHost, dbUser, dbPwd);
			stmt = conn.prepareStatement(query);
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUser_id((rs.getString(1)));
				user.setName(rs.getString(2));
				user.setSalary(rs.getInt(3));

				userList.add(user);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}

		return userList;
	}
}
