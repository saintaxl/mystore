/**
 * 
 */
package com.mycloud.repository;

/**
 * @author Shawn
 *
 */
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mycloud.constant.ExpressStatus;
import com.mycloud.entity.Customer;
import com.mycloud.entity.Express;

@Repository
public interface ExpressRepository extends BaseRepository<Express, Serializable> {

	List<Express> findByCreateDateAfterAndCreateDateBeforeAndStatusAndCustomer(Date lastMonths25th, Date thisMonths25th, ExpressStatus status, Customer customer);


	
}
