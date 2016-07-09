package com.mycloud.store.controller.rest.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycloud.entity.Customer;
import com.mycloud.entity.DailySettlement;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.form.SettlementListForm;
import com.mycloud.store.controller.rest.model.CustomerView;
import com.mycloud.store.controller.rest.model.DailySettlementView;
import com.mycloud.store.controller.rest.model.JQueryDatatablesPage;
import com.mycloud.store.controller.rest.model.MonthlyStatementView;
import com.mycloud.store.service.SettlementService;

@RestController
public class SettlementRestController extends BaseController {
	
	@Autowired
	private SettlementService settlementService;
	
	
	@RequestMapping(value = "/showDailySettlementList", method = { RequestMethod.POST })
	public JQueryDatatablesPage<DailySettlementView> showInventoryList(@ModelAttribute SettlementListForm settlementListForm,
	        @PageableDefault(value = 10, sort = { "id" }, direction = Direction.DESC) Pageable pageable, @RequestParam Integer echo, Model model) {

		Customer customer = getCustomer();
		Page<DailySettlement> page = settlementService.searchDailySettlement(settlementListForm, customer, pageable);

		JQueryDatatablesPage<DailySettlementView> pageview = transform2View(page, echo);
		return pageview;
	}

	private JQueryDatatablesPage<DailySettlementView> transform2View(Page<DailySettlement> page, Integer echo) {
		List<DailySettlement> content = page.getContent();

		List<DailySettlementView> dailySettlementList = new ArrayList<DailySettlementView>();
		for (DailySettlement dailySettlement : content) {
			DailySettlementView dailySettlementView = new DailySettlementView();
			dailySettlementView.setTotalNumber(dailySettlement.getTotalNumber());
			dailySettlementView.setTotalVolume(dailySettlement.getTotalVolume());
			dailySettlementView.setTotalWeight(dailySettlement.getTotalWeight());
			dailySettlementView.setPrice(dailySettlement.getPrice());
			if (dailySettlement.getSettlementDate() != null) {
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				String formatdate = dateformat.format(dailySettlement.getSettlementDate());
				dailySettlementView.setSettlementDate(formatdate);
			}
			if(dailySettlement.getMonthlyStatement()!=null){
				MonthlyStatementView monthlyStatementView = buildMonthlyStatementView(dailySettlement.getMonthlyStatement());
				dailySettlementView.setMonthlyStatement(monthlyStatementView);
			}
			dailySettlementView.setStatus(dailySettlement.getStatus());
			if(dailySettlement.getCustomer()!=null){
				CustomerView customerView = buildCustomerView(dailySettlement.getCustomer());
				dailySettlementView.setCustomer(customerView);
			}
			dailySettlementList.add(dailySettlementView);
		}
		

		JQueryDatatablesPage<DailySettlementView> page_ = new JQueryDatatablesPage<DailySettlementView>();
		page_.setAaData(dailySettlementList);
		page_.setiTotalDisplayRecords(page.getTotalElements());
		page_.setiTotalRecords(page.getTotalElements());
		page_.setsEcho(echo);
		Map<String, Object> extension = new HashMap<String, Object>();
		if(CollectionUtils.isNotEmpty(dailySettlementList)){
			DailySettlementView dailySettlementView = dailySettlementList.get(0);
			String settlementNo = dailySettlementView.getMonthlyStatement().getSettlementNo();
			extension.put("settlementNo", settlementNo);
		}else{
			extension.put("settlementNo", null);
		}
		page_.setExtension(extension);
		return page_;
	}




	
	

}
