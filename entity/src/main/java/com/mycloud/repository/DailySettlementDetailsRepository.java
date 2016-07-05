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

import com.mycloud.entity.Customer;
import com.mycloud.entity.DailySettlementDetails;

@Repository
public interface DailySettlementDetailsRepository extends BaseRepository<DailySettlementDetails, Serializable> {

	List<DailySettlementDetails> findBySettlementDateAndCustomer(Date yesterday, Customer customer);

	
}
