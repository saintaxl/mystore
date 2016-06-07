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

import com.mycloud.entity.Customer;
import com.mycloud.entity.User;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Serializable> {

	User findByCustomerNo(String customerNo);

	User findByCustomerName(String customerName);

	
}
