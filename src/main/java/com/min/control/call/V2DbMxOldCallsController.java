/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.control.call;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.min.model.V2ZScustomerInfo;
import com.min.model.call.V2DbMxBase;
import com.min.service.call.V2CallService;
import com.min.service.call.V2DbMxOldCallsService;

/**
 * 语音详情Controller
 * 
 * @author dddd
 * @version 2017-11-10
 */
@Controller
@RequestMapping(value = "/api")
public class V2DbMxOldCallsController {

	@Autowired
	private V2CallService service;

	@Autowired
	private V2DbMxOldCallsService v2DbMxOldCallsService;

	// 运营商的语音详情
	@RequestMapping(value = "/v1/MxOldCalls", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getXdCalls(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> mxOldCalls = null;

		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		if (customr != null && "3".equals(customr.getOperatorType()) && customr.getId() != null) {
			// 获取运营商的语音详情
			V2DbMxBase mxBase = v2DbMxOldCallsService.getV2DbMxBase(customr.getId());
			mxOldCalls = v2DbMxOldCallsService.getV2DbMxOldCalls(mxBase.getId());
		}
		return mxOldCalls;
	}
}