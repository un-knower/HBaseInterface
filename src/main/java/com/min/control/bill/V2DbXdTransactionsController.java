/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.control.bill;

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
import com.min.model.V2DbXdBase;
import com.min.model.V2DbXdTransactions;
import com.min.model.V2ZScustomerInfo;
import com.min.service.bill.V2DbXdTransactionsService;
import com.min.service.call.V2CallService;
import com.min.service.net.V2DbXdNetsService;
import com.min.utils.HbaseUtils;

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
	private V2DbXdNetsService v2DbXdNetsService;
	
	@Autowired
	private V2CallService v2CallService;

	@RequestMapping(value = "/v2/XdTransaction", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	// 账单记录查询
	public void getCusInfoId(HttpServletRequest request, HttpServletResponse response) {
		
		JSON<V2DbXdTransactions> json = new JSON<V2DbXdTransactions>();
		List<V2DbXdTransactions> XdTransactions = new ArrayList<V2DbXdTransactions>();
		V2ZScustomerInfo customr = v2CallService.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),request.getParameter("mobile"));
		if (customr != null && ("2").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				List<V2DbXdBase> xdBase = v2DbXdNetsService.getV2DbXdBase(customr.getId());
				System.out.println("xdBase66666:" + xdBase.size());
				for (V2DbXdBase v2DbXdBase : xdBase) {
					List<V2DbXdTransactions> list = v2DbXdTranService.getContacts(v2DbXdBase.getId());
					System.out.println("list.size" + list.size());
					if(XdTransactions != null) {
					for (V2DbXdTransactions v2DbXdTransac : list) {
						XdTransactions.add(v2DbXdTransac);
					}
					}else {
							json.setCode("404");
							json.setMsg("未找到数据");
						}
				}
				json.setData(XdTransactions);
				json.setCode("200");
				json.setMsg("返回成功");
			}
		} else {
			json.setCode("404");
			json.setMsg("未找到数据");
		}
		ObjectMapper mapper = new ObjectMapper();
		HbaseUtils.setResponse(response);
		try {
			String result = mapper.writeValueAsString(json);
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}