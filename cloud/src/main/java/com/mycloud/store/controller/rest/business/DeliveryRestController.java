package com.mycloud.store.controller.rest.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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

import com.mycloud.entity.Category;
import com.mycloud.entity.Customer;
import com.mycloud.entity.Delivery;
import com.mycloud.entity.DeliveryDetails;
import com.mycloud.entity.Logistics;
import com.mycloud.entity.Quantity;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.form.DeliveryListForm;
import com.mycloud.store.controller.rest.model.CategoryView;
import com.mycloud.store.controller.rest.model.CustomerView;
import com.mycloud.store.controller.rest.model.DeliveryDetailView;
import com.mycloud.store.controller.rest.model.DeliveryView;
import com.mycloud.store.controller.rest.model.JQueryDatatablesPage;
import com.mycloud.store.controller.rest.model.LogisticsView;
import com.mycloud.store.controller.rest.model.QuantityView;
import com.mycloud.store.controller.rest.model.SimplifiedReq;
import com.mycloud.store.controller.rest.model.SimplifiedResp;
import com.mycloud.store.service.DeliveryService;
import com.mycloud.store.sso.UserInfo;
import com.mycloud.store.utils.Pinyin;

@RestController
public class DeliveryRestController extends BaseController {

	@Autowired
	private DeliveryService deliveryService;

	@RequestMapping(value = "/showBarcodeAcronym", method = { RequestMethod.POST }, produces = { "application/json" })
	public SimplifiedResp showBarcodeAcronym(@RequestBody SimplifiedReq simplified) {
		UserInfo userInfo = getUserInfo();
		String customerAcronym = userInfo.getCustomerAcronym();
		if (StringUtils.isEmpty(customerAcronym)) {
			customerAcronym = Pinyin.String2Alpha(userInfo.getCustomerName());
		}
		
		Customer customer = getCustomer();
		
		String category = simplified.getSimplified();
		String barCode = getBarCode(customer.getCustomerNo(), customerAcronym, category);

		SimplifiedResp resp = new SimplifiedResp();
		resp.setAcronym(barCode);
		return resp;
	}

	@RequestMapping(value = "/showDeliveryList", method = { RequestMethod.POST })
	public JQueryDatatablesPage<DeliveryDetailView> showDeliveryList(@ModelAttribute DeliveryListForm deliveryListForm,
	        @PageableDefault(value = 10, sort = { "id" }, direction = Direction.DESC) Pageable pageable, @RequestParam Integer echo, Model model) {

		Customer customer = getCustomer();
		Page<DeliveryDetails> page = deliveryService.searchDetails(deliveryListForm, customer, pageable);

		JQueryDatatablesPage<DeliveryDetailView> pageview = transform2View(page, echo);
		return pageview;
	}

	private JQueryDatatablesPage<DeliveryDetailView> transform2View(Page<DeliveryDetails> page, Integer echo) {
		List<DeliveryDetails> content = page.getContent();
		
		
		List<DeliveryDetailView> deliveryDetailViewList = new ArrayList<DeliveryDetailView>();
		for (DeliveryDetails detail : content) {
			DeliveryDetailView detailView = new DeliveryDetailView();
			detailView.setBarCode(detail.getBarCode());
			detailView.setColor(detail.getColor());
			detailView.setId(detail.getId());
			detailView.setNote(detail.getNote());
			detailView.setNumber(detail.getNumber());
			detailView.setProductName(detail.getProductName());
			detailView.setVolume(detail.getVolume());
			detailView.setWeight(detail.getWeight());
			
			buildCategoryView(detail, detailView);
			buildQuantityView(detail, detailView);
			buildDeliveryView(detail,detailView);
			deliveryDetailViewList.add(detailView);
        }

		JQueryDatatablesPage<DeliveryDetailView> page_ = new JQueryDatatablesPage<DeliveryDetailView>();
		page_.setAaData(deliveryDetailViewList);
		page_.setiTotalDisplayRecords(page.getTotalElements());
		page_.setiTotalRecords(page.getTotalElements());
		page_.setsEcho(echo);
		return page_;
	}

	private void buildDeliveryView(DeliveryDetails detail, DeliveryDetailView detailView) {
	    DeliveryView deliveryView =  new DeliveryView();
	    if(detail.getDelivery()!=null){
	    	Delivery delivery = detail.getDelivery();
	    	deliveryView.setDeliveryNo(delivery.getDeliveryNo());
	    	deliveryView.setStatus(delivery.getStatus());
	    }
	    buildLogistics(detail, deliveryView);
		buildCustomer(detail, deliveryView);
		detailView.setDelivery(deliveryView);
    }

	private void buildCustomer(DeliveryDetails detail, DeliveryView deliveryView) {
	    CustomerView customerView = new CustomerView();
	    if(detail.getCustomer()!=null){
	    	Customer customer = detail.getCustomer();
	    	customerView.setAcronym(customer.getAcronym());
	    	customerView.setCustomerName(customer.getCustomerName());
	    	customerView.setCustomerNo(customer.getCustomerNo());
	    	customerView.setId(customer.getId());
	    }
		deliveryView.setCustomer(customerView);
    }

	private void buildLogistics(DeliveryDetails detail, DeliveryView deliveryView) {
	    LogisticsView logisticsView = new LogisticsView();
	    if(detail.getDelivery()!=null && detail.getDelivery().getLogistics()!=null){
	    	Logistics logistics = detail.getDelivery().getLogistics();
	    	logisticsView.setId(logistics.getId());
	    	logisticsView.setCompanyName(logistics.getCompanyName());
	    	logisticsView.setLogisticsNo(logistics.getLogisticsNo());
	    	logisticsView.setName(logistics.getName());
			logisticsView.setMobile(logistics.getMobile());
			logisticsView.setAddress(logistics.getAddress());
			logisticsView.setProvince(logistics.getProvince());
			logisticsView.setCity(logistics.getCity());
			logisticsView.setDistrict(logistics.getDistrict());
			logisticsView.setMobile(logistics.getMobile());
			logisticsView.setPrice(logistics.getPrice());
	    	if (logistics.getArrivalDate() != null) {
	    		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	    		String formatdate = dateformat.format(logistics.getArrivalDate());
	    		logisticsView.setArrivalDate(formatdate);
	    	}
	    }
		deliveryView.setLogistics(logisticsView);
    }

	private void buildQuantityView(DeliveryDetails detail, DeliveryDetailView detailView) {
	    QuantityView  quantityView = new QuantityView();
	    if(detail.getQuantity()!=null){
	    	Quantity quantity = detail.getQuantity();
	    	quantityView.setName(quantity.getName());
	    	quantityView.setId(quantity.getId());
	    }
	    detailView.setQuantity(quantityView);
    }

	private void buildCategoryView(DeliveryDetails deliveryDetail, DeliveryDetailView deliveryDetailView) {
	    CategoryView categoryView = new CategoryView();
	    if(deliveryDetail.getCategory()!=null){
	    	Category category =deliveryDetail.getCategory();
	    	categoryView.setId(category.getId());
	    	categoryView.setName(category.getName());
	    }
	    deliveryDetailView.setCategory(categoryView);
    }

}
