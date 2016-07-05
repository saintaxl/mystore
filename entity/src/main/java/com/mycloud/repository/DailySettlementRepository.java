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

import com.mycloud.constant.SettlementStatus;
import com.mycloud.entity.Customer;
import com.mycloud.entity.DailySettlement;

@Repository
public interface DailySettlementRepository extends BaseRepository<DailySettlement, Serializable> {

	public List<DailySettlement> findBySettlementDateAndCustomer(Date date,Customer customer);

	public List<DailySettlement> findBySettlementDateAfterAndSettlementDateBeforeAndStatusAndCustomer(Date thisMonthsFirstDay, Date thisMonthsLastDay, SettlementStatus pending,Customer customer);

	
}
