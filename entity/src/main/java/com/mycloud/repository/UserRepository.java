/**
 * 
 */
package com.mycloud.repository;

/**
 * @author Shawn
 *
 */
import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.mycloud.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User, Serializable> {

	User findByEmail(String email);

	User findByUsername(String username);

	User findByEmailAndPassword(String email, String password);
	
}
