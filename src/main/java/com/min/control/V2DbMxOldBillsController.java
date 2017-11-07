/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.control;

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
import com.min.model.JSON_MxOldBills;
import com.min.model.V2DbMxOldBills;
import com.min.model.V2ZScustomerInfo;
import com.min.service.V2DbMxOldBillsService;


/**
 * old_billsController
 * @author dddd
 * @version 2017-11-03
 */
@Controller
@RequestMapping(value="/api")
public class V2DbMxOldBillsController {

	@Autowired
	private V2DbMxOldBillsService v2DbMxOldBillsService;
	
	@RequestMapping(value = "/v2/MxOldBills", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	// 账单记录查询
	public void getCusInfoId(HttpServletRequest request, HttpServletResponse response) {
		V2ZScustomerInfo info = new V2ZScustomerInfo();
		// post请求传来身份证号和平台id
		info.setIdNumber(request.getParameter("idcard"));
		info.setMemberId(request.getParameter("siteid"));
		// 获取cid
		V2ZScustomerInfo customr = v2DbMxOldBillsService.getCustomr(info);
		JSON_MxOldBills json = new JSON_MxOldBills();
		List<V2DbMxOldBills> contacts = null;
		// 获取通讯录
		String cid = customr.getId();
		if (cid != null && cid.length() > 0) {
			contacts = v2DbMxOldBillsService.getContacts(cid);
			json.setCode("200");
			json.setMsg("返回成功");
		} else {
			json.setCode("");
			json.setMsg("");
		}
		json.setData(contacts);
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