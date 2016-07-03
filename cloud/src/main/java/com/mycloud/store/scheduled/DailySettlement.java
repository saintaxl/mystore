/**
 * 
 */
package com.mycloud.store.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
public class DailySettlement {
	
	@Autowired
	private SettlementService settlementService;
	
	@Scheduled(fixedDelay = 5000, initialDelay = 5000)
    @Async
    public void runDailySettlement() {
		settlementService.dailySettlement();;
    }

}
