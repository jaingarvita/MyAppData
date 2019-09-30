package com.zensar.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * @author Garvita Jain
 * @version 1.0
 * @creation_date 21/09/2019 5:29PM
 * @modification_date 21/09/2019 5:29PM
 * @copyright Zensar Technologies. All rights reserved.
 * @description It is Data Access Object Interface.
 * 				It is used in Persistence Layer of Application.
 *
 */
import java.util.List;

import com.zensar.entities.User;

public class UserDaoImpl implements UserDao {
	
	private Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into user_login values(?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		int insertCount = pstmt.executeUpdate();
		if(insertCount >0) {
			System.out.println("new user inserted");
		}
		else {
			System.out.println("Sorry!");
		}

	}

	@Override
	public void update(User user) throws SQLException {
		// TODO Auto-generated method stub
 String sql = "UPDATE USER_LOGIN SET PASSWORD=? WHERE USERNAME=?";
		 PreparedStatement pstmt = connection.prepareStatement(sql);
		 pstmt.setString(1, user.getPassword());
		 pstmt.setString(2, user.getUsername());
		 int updateCount = pstmt.executeUpdate();
		 if(updateCount >0) {
				System.out.println("user record is updated successfully");
			}
			else {
				System.out.println("Sorry!user record could not be updated");
			}
		 
	}

	@Override
	public void delete(User user) throws SQLException {
		// TODO Auto-generated method stub
		 String sql = "DELETE FROM USER_LOGIN  WHERE USERNAME=?";
		 PreparedStatement pstmt = connection.prepareStatement(sql);
		 pstmt.setString(1, user.getPassword());
		 
		 int deleteCount = pstmt.executeUpdate();
		 
		 if(deleteCount >0) {
				System.out.println("user record is deleted successfully");
			}
			else {
				System.out.println("Sorry!user record could not be deleted");
			}
		 
		 

	}

	@Override
	public User getByUsername(String username) throws SQLException{
		// TODO Auto-generated method stub
		String sql = "select username, password from user_login where username=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			User user = new User();
			user.setUsername(rs.getString(1));
			user.setPassword(rs.getString(2));
			return user;
		}
		else
		return null;
	}

	@Override
	public List<User> getAll() throws SQLException{
		// TODO Auto-generated method stub
		String sql = "select username, password from user_login";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<User> users= new ArrayList<User>();
		while (rs.next()) {
			User user = new User();
			user.setUsername(rs.getString(1));
            user.setPassword(rs.getString(2));
            users.add(user);
            }
		return users;
	


	
	}

}
