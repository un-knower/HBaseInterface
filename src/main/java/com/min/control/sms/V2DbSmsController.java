package com.min.control.sms;

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

import com.min.model.V2DbMoBase;
import com.min.model.V2DbMxBase;
import com.min.model.V2DbOperatorTask;
import com.min.model.V2DbXdBase;
import com.min.model.V2ZScustomerInfo;
import com.min.model.sms.V2DbOperatorSms;
import com.min.model.sms.V2DbXdSmses;
import com.min.service.call.V2CallService;
import com.min.service.sms.V2DbSmsService;
import com.min.utils.HbaseUtils;

@Controller
@RequestMapping(value = "/api")
public class V2DbSmsController {

	@Autowired
	private V2DbSmsService v2DbSmsService;

	@Autowired
	private V2CallService service;

	// 运营商B的短信详情
	@RequestMapping(value = "/v1/MoRecodsSms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getMoRecodsSms(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> sms = HbaseUtils.returnNull();
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		if (customr != null && "0".equals(customr.getOperatorType()) && customr.getId() != null) {
			// 获取运营商B的短信详情
			V2DbMoBase moBase = service.getV2DbMoBase(customr.getId());
			sms = v2DbSmsService.getV2DbMoRecordsSms(moBase.getId(), 0, null);
		}
		return sms;
	}

	// 运营商D的短信详情
	@RequestMapping(value = "/v1/MxOldSmses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getMxOldSmses(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = HbaseUtils.returnNull();
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		if (customr != null && "3".equals(customr.getOperatorType()) && customr.getId() != null) {
			// 获取运营商的语音详情
			V2DbMxBase mxBase = service.getV2DbMxBase(customr.getId());
			map = v2DbSmsService.getV2DbMxOldSmses(mxBase.getId(), 0, null);
		}
		return map;
	}

	// 运营商的语音详情
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/v1/OperatorSms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getOperatorSms(HttpServletRequest request, HttpServletResponse response) {
		List<V2DbOperatorSms> OpSms = new ArrayList<V2DbOperatorSms>();
		Map<String, Object> map = HbaseUtils.returnNull();
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		if (customr != null && "1".equals(customr.getOperatorType()) && customr.getId() != null) {
			// 获取运营商的语音详情
			Map<String, Object> opTask = service.getOperatorTask(customr.getId(), 0, null);
			for (V2DbOperatorTask v2DbOperatorTask : (List<V2DbOperatorTask>) opTask.get("data")) {
			map = v2DbSmsService.getV2DbOperatorSms(v2DbOperatorTask.getId(), 0, null);
			for (V2DbOperatorSms operatorSms : (List<V2DbOperatorSms>) map.get("data")) {
				OpSms.add(operatorSms);
					}
				}
			}
		map.put("data", OpSms);
		return map;
	}

	// 运营商C的上网记录接口
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/v1/XdSmses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getXdSmses(HttpServletRequest request, HttpServletResponse response) {
		List<V2DbXdSmses> XdSmses = new ArrayList<V2DbXdSmses>();
		Map<String, Object> map =HbaseUtils.returnNull(); 
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		if (customr != null && ("2").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				Map<String, Object> xdBase = service.getV2DbXdBase(customr.getId(), 0, null);
				for (V2DbXdBase v2DbXdBase : (List<V2DbXdBase>) xdBase.get("data")) {
					map = v2DbSmsService.getV2DbXdSmses(v2DbXdBase.getId(), 0, null);
					for (V2DbXdSmses v2DbXdSmses : (List<V2DbXdSmses>) map.get("data")) {
						XdSmses.add(v2DbXdSmses);
					}
				}
			}
		}
		map.put("data", XdSmses);
		return map;
	}
}
