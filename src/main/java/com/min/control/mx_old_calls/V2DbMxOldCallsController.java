/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.control.mx_old_calls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.model.JSON;
import com.min.model.V2ZScustomerInfo;
import com.min.model.call.V2DbMoBase;
import com.min.model.mx_old_calls.V2DbMxBase;
import com.min.model.mx_old_calls.V2DbMxOldCalls;
import com.min.service.call.V2CallService;
import com.min.service.mx_old_calls.V2DbMxOldCallsService;
import com.min.utils.HbaseUtils;

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
	@RequestMapping(value = "/v2/MxOldCalls", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void getXdCalls(HttpServletRequest request, HttpServletResponse response) {
		JSON<V2DbMxOldCalls> json = new JSON<V2DbMxOldCalls>();
		List<V2DbMxOldCalls> mxOldCalls = new ArrayList<V2DbMxOldCalls>();

		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"));
		if (customr != null && "3".equals(customr.getOperatorType()) && customr.getId() != null) {
			// 获取运营商的语音详情
			V2DbMxBase mxBase = v2DbMxOldCallsService.getV2DbMxBase(customr.getId());
			mxOldCalls = v2DbMxOldCallsService.getV2DbMxOldCalls(mxBase.getId());
			json.setCode("200");
			json.setMsg("返回成功");
		} else {
			json.setCode("404");
			json.setMsg("没有找到");
		}
		json.setData(mxOldCalls);
		ObjectMapper mapper = new ObjectMapper();
		try {
			HbaseUtils.setResponse(response);
			String result = mapper.writeValueAsString(json);
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}