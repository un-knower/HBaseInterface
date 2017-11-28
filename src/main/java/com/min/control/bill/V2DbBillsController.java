/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.control.bill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.min.model.V2DbXdBase;
import com.min.model.V2DbXdTransactions;
import com.min.model.V2ZScustomerInfo;
import com.min.service.bill.V2DbBillsService;
import com.min.service.call.V2CallService;
import com.min.service.net.V2NetsService;
import com.min.utils.HbaseUtils;

/**
 * old_billsController
 * 
 * @author dddd
 * @version 2017-11-03
 */
@Controller
@RequestMapping(value = "/api")
public class V2DbBillsController {

	@Autowired
	private V2DbBillsService billsService;

	@Autowired
	private V2NetsService netsService;

	@Autowired
	private V2CallService v2CallService;

	@RequestMapping(value = "/v1/MxOldBills", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	// 账单记录查询
	public Map<String, Object> getMxOldBills(HttpServletRequest request, HttpServletResponse response) {
		// 获取cid
		V2ZScustomerInfo customr = v2CallService.getCustomr(request.getParameter("idcard"),
				request.getParameter("siteid"), request.getParameter("mobile"));
		// 获取通讯录
		if (customr.getId() != null) {
			return billsService.getMxOldBills(customr.getId());
		}
		return HbaseUtils.returnNull();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/v1/XdTransaction", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	// 账单记录查询
	public Map<String, Object> XdTransaction(HttpServletRequest request, HttpServletResponse response) {

		List<V2DbXdTransactions> XdTransactions = new ArrayList<V2DbXdTransactions>();
		V2ZScustomerInfo customr = v2CallService.getCustomr(request.getParameter("idcard"),
				request.getParameter("siteid"), request.getParameter("mobile"));
		Map<String, Object> map = HbaseUtils.returnNull();
		if (customr != null && ("2").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				Map<String, Object> xdBase = netsService.getV2DbXdBase(customr.getId());
				for (V2DbXdBase v2DbXdBase : (List<V2DbXdBase>) xdBase.get("data")) {
					map = billsService.getDbXdTransactions(v2DbXdBase.getId());
					List<V2DbXdTransactions> list = (List<V2DbXdTransactions>) map.get("data");
					for (V2DbXdTransactions v2 : list) {
						XdTransactions.add(v2);
					}
				}
			}
		}
		map.put("data", XdTransactions);
		return map;
	}
}