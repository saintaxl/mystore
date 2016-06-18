/**
 * 
 */
package com.mycloud.store.controller.rest.business;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.rest.model.SimplifiedReq;
import com.mycloud.store.controller.rest.model.SimplifiedResp;
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

	

}
