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

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycloud.constant.SettlementStatus;
import com.mycloud.entity.Customer;
import com.mycloud.entity.DailySettlement;
import com.mycloud.entity.MonthlyStatement;

@Repository
public interface DailySettlementRepository extends BaseRepository<DailySettlement, Serializable> {

	public List<DailySettlement> findBySettlementDateAndCustomer(Date date,Customer customer);

	public List<DailySettlement> findBySettlementDateAfterAndSettlementDateBeforeAndStatusAndCustomer(Date thisMonthsFirstDay, Date thisMonthsLastDay, SettlementStatus pending,Customer customer);

	
	@Modifying
	@Query(value="update DailySettlement d set d.status=:status where d.customer =:customer and d.monthlyStatement =:monthlyStatement ")
	public void updateSettlementByMonth(@Param("customer") Customer customer, @Param("monthlyStatement") MonthlyStatement monthlyStatement , @Param("status") SettlementStatus status);

	
}
