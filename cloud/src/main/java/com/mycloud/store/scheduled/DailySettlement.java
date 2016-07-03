/**
 * 
 */
package com.mycloud.store.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.mycloud.store.service.SettlementService;

/**
 * @author Shawn
 *
 */

@EnableAsync
@Configuration
@EnableScheduling
@Lazy(false)
public class DailySettlement extends AbstractScheduled {
	
	@Autowired
	private SettlementService settlementService;
	
	//@Scheduled(fixedDelay = 5000, initialDelay = 5000)
	// 每天凌晨 00:15 执行一次 
	@Scheduled(cron = "0 15 0 * * *")
    @Async
    public void runDailySettlement() {
		settlementService.dailySettlement();;
    }

}
