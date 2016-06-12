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

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Serializable> {

	Customer findByCustomerNo(String customerNo);

	Customer findByCustomerName(String customerName);

	
}
