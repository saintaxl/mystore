/**
 * 
 */
package com.mycloud.store.controller.rest.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Shawn
 *
 */
public class ExpressRequest {
	
	private List<ExpressDetailRequest> details;
	
	private Integer logisticsCompanyId;

	public List<ExpressDetailRequest> getDetails() {
		return details;
	}

	public void setDetails(List<ExpressDetailRequest> details) {
		this.details = details;
	}

	public Integer getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	public void setLogisticsCompanyId(Integer logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		ExpressRequest request  = new ExpressRequest();
		request.setLogisticsCompanyId(12);
		
		List<ExpressDetailRequest> details = new ArrayList<ExpressDetailRequest>();
		ExpressDetailRequest req1 = new ExpressDetailRequest();
		ExpressDetailRequest req2 = new ExpressDetailRequest();
		
		req1.setId(1);
		req1.setNumber(10);
		
		req2.setId(2);
		req2.setNumber(20);
		
		
		details.add(req1);
		details.add(req2);
		request.setDetails(details);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String value = mapper.writeValueAsString(request);
		System.out.println(value);
    }
	

}
