/**
 * 
 */
package com.mycloud.repository;

/**
 * @author Shawn
 *
 */
import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mycloud.constant.SettlementStatus;
import com.mycloud.entity.Customer;
import com.mycloud.entity.MonthlyStatement;

@Repository
public interface MonthlyStatementRepository extends BaseRepository<MonthlyStatement, Serializable> {

	public List<MonthlyStatement> findByMonthAndCustomerAndStatus(String thisMonthsStr, Customer cust, SettlementStatus pending);


	
}
