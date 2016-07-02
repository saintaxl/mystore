package com.mycloud.store.controller.rest.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycloud.entity.Customer;
import com.mycloud.entity.Express;
import com.mycloud.entity.ExpressDetails;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.form.ExpressListForm;
import com.mycloud.store.controller.rest.model.CustomerView;
import com.mycloud.store.controller.rest.model.ExpressDetailView;
import com.mycloud.store.controller.rest.model.ExpressRequest;
import com.mycloud.store.controller.rest.model.ExpressView;
import com.mycloud.store.controller.rest.model.InventoryView;
import com.mycloud.store.controller.rest.model.JQueryDatatablesPage;
import com.mycloud.store.controller.rest.model.LogisticsView;
import com.mycloud.store.service.ExpressService;

@RestController
public class ExpressRestController extends BaseController {

	@Autowired
	private ExpressService expressService;

	@RequestMapping(value = "/showExpressDetailList", method = { RequestMethod.POST })
	public JQueryDatatablesPage<ExpressDetailView> showExpressDetailList(@ModelAttribute ExpressListForm expressListForm,
	        @PageableDefault(value = 10, sort = { "id" }, direction = Direction.DESC) Pageable pageable, @RequestParam Integer echo, Model model) {

		Customer customer = getCustomer();
		Page<ExpressDetails> page = expressService.searchDetails(expressListForm, customer, pageable);

		JQueryDatatablesPage<ExpressDetailView> pageview = transform2View(page, echo);
		return pageview;
	}

	@RequestMapping(value = "/showExpressList", method = { RequestMethod.POST })
	public JQueryDatatablesPage<ExpressView> showExpressList(@ModelAttribute ExpressListForm expressListForm,
	        @PageableDefault(value = 10, sort = { "id" }, direction = Direction.DESC) Pageable pageable, @RequestParam Integer echo, Model model) {

		Customer customer = getCustomer();
		Page<Express> page = expressService.searchExpress(expressListForm, customer, pageable);

		JQueryDatatablesPage<ExpressView> pageview = transform2ExpressView(page, echo);
		return pageview;
	}
	
	
	@RequestMapping(value = "/calculatePrice", method = { RequestMethod.POST }, produces = { "application/json" })
	public BigDecimal calculatePrice(@RequestBody ExpressRequest expressRequest) {
		BigDecimal price = expressService.calculatePrice(expressRequest);
		return price;
	}

	private JQueryDatatablesPage<ExpressView> transform2ExpressView(Page<Express> page, Integer echo) {
		List<Express> content = page.getContent();
		List<ExpressView> expressViewList = new ArrayList<ExpressView>();
		for (Express express : content) {
			ExpressView expressView = new ExpressView();
			expressView.setId(express.getId());
			expressView.setStatus(express.getStatus());
			expressView.setExpressNo(express.getExpressNo());
			if (express.getCustomer() != null) {
				CustomerView customerView = buildCustomerView(express.getCustomer());
				expressView.setCustomer(customerView);
			}

			if(express.getLogistics()!=null){
				LogisticsView logisticsView = buildLogisticsView(express.getLogistics());
				expressView.setLogistics(logisticsView);
			}
			expressViewList.add(expressView);
		}
		
		JQueryDatatablesPage<ExpressView> page_ = new JQueryDatatablesPage<ExpressView>();
		page_.setAaData(expressViewList);
		page_.setiTotalDisplayRecords(page.getTotalElements());
		page_.setiTotalRecords(page.getTotalElements());
		page_.setsEcho(echo);
		return page_;
	}

	private JQueryDatatablesPage<ExpressDetailView> transform2View(Page<ExpressDetails> page, Integer echo) {
		List<ExpressDetails> content = page.getContent();

		List<ExpressDetailView> expressDetailViewList = new ArrayList<ExpressDetailView>();
		for (ExpressDetails detail : content) {
			ExpressDetailView detailView = new ExpressDetailView();
			detailView.setId(detail.getId());
			detailView.setNumber(detail.getNumber());
			if (detail.getCustomer() != null) {
				CustomerView customerView = buildCustomerView(detail.getCustomer());
				detailView.setCustomer(customerView);
			}
			if (detail.getExpress() != null) {
				ExpressView expressView = buildExpressView(detail.getExpress());
				detailView.setExpress(expressView);
			}
			if (detail.getInventory() != null) {
				InventoryView inventoryView = buildInventoryView(detail.getInventory());
				detailView.setInventory(inventoryView);
			}

			expressDetailViewList.add(detailView);
		}

		JQueryDatatablesPage<ExpressDetailView> page_ = new JQueryDatatablesPage<ExpressDetailView>();
		page_.setAaData(expressDetailViewList);
		page_.setiTotalDisplayRecords(page.getTotalElements());
		page_.setiTotalRecords(page.getTotalElements());
		page_.setsEcho(echo);
		return page_;
	}

	

}
