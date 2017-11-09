/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.control.bill;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.model.JSON;
import com.min.model.V2DbXdTransactions;
import com.min.model.V2ZScustomerInfo;
import com.min.service.call.V2ContactService;
import com.min.service.call.V2DbXdTransactionsService;

/**
 * 账单记录Controller
 * 
 * @author dddd
 * @version 2017-11-03
 */
@Controller
@RequestMapping(value = "/api")
public class V2DbXdTransactionsController {

	@Autowired
	private V2DbXdTransactionsService v2DbXdTranService;

	@Autowired
	private V2ContactService service;

	@RequestMapping(value = "/v2/XdTransaction", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	// 账单记录查询
	public void getCusInfoId(HttpServletRequest request, HttpServletResponse response) {

		// 获取addtime
		String addTime = request.getParameter("addtime");
		JSON<V2DbXdTransactions> json = new JSON<V2DbXdTransactions>();
		List<V2DbXdTransactions> xdtrans = null;
		// 获取账单信息
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"));
		if (customr.getId() != null) {
			xdtrans = v2DbXdTranService.getContacts(customr.getId(), addTime);
			json.setCode("200");
			json.setMsg("返回成功");
		} else {
			json.setCode("404");
			json.setMsg("无相关数据");
		}
		json.setData(xdtrans);
		ObjectMapper mapper = new ObjectMapper();
		try {
			response.setContentType("text/plain;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			String result = mapper.writeValueAsString(json);
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}