/**
 * 
 */
package com.mycloud.store.controller.rest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.rest.model.SimplifiedReq;
import com.mycloud.store.controller.rest.model.SimplifiedResp;
import com.mycloud.store.sso.UserInfo;
import com.mycloud.store.utils.Pinyin;

/**
 * @author Shawn
 *
 */

@RestController
public class CommonController extends BaseController {

	@RequestMapping(value = "/showFirstLetter", method = { RequestMethod.POST }, produces = { "application/json" })
	public SimplifiedResp showFirstLetter(@RequestBody SimplifiedReq simplified) {
		String string2Alpha = Pinyin.String2Alpha(simplified.getSimplified());
		SimplifiedResp resp = new SimplifiedResp();
		resp.setAcronym(string2Alpha);
		return resp;
	}

	@RequestMapping(value = "/showBarcodeAcronym", method = { RequestMethod.POST }, produces = { "application/json" })
	public SimplifiedResp showBarcodeAcronym(@RequestBody SimplifiedReq simplified) {
		UserInfo userInfo = getUserInfo();
		String customerAcronym = userInfo.getCustomerAcronym();
		if (StringUtils.isEmpty(customerAcronym)) {
			customerAcronym = Pinyin.String2Alpha(userInfo.getCustomerName());
		}

		String category = simplified.getSimplified();
		String categoryAcronym = Pinyin.String2Alpha(category);

		String value = customerAcronym + "-" + categoryAcronym + "-" + RandomStringUtils.random(5, false, true);

		SimplifiedResp resp = new SimplifiedResp();
		resp.setAcronym(value);
		return resp;
	}

}
