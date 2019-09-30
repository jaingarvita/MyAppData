package com.zensar.dao;

import java.sql.SQLException;
import java.util.List;

import com.zensar.entities.User;

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

public interface UserDao {

		void insert(User user) throws SQLException;
		void update(User user) throws SQLException;
		void delete(User user) throws SQLException;
		User getByUsername(String username) throws SQLException;
		List<User> getAll() throws SQLException;
		
}
