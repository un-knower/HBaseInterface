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
import com.min.model.V2DbXdBase;
import com.min.model.V2ZScustomerInfo;
import com.min.model.call.V2DbMoBase;
import com.min.model.call.V2DbMxBase;
import com.min.model.sms.V2DbOperatorTask;
import com.min.model.sms.V2DbXdSmses;
import com.min.service.call.V2CallService;
import com.min.service.net.V2NetsService;
import com.min.service.sms.V2DbMoRecordsSmsService;
import com.min.service.sms.V2DbMxOldSmsesService;
import com.min.service.sms.V2DbOperatorSmsService;
import com.min.service.sms.V2DbXdSmsesService;

@Controller
@RequestMapping(value = "/api")
public class V2DbMoRecordsSmsController {

	@Autowired
	private V2DbXdSmsesService v2DbXdSmsesService;

	@Autowired
	private V2DbOperatorSmsService v2DbOperatorSmsService;

	@Autowired
	private V2DbMxOldSmsesService v2DbMxOldSmsesService;

	@Autowired
	private V2CallService service;

	@Autowired
	private V2DbMoRecordsSmsService v2DbMoRecordsSmsService;

	@Autowired
	private V2NetsService netService;

	// 运营商B的短信详情
	@RequestMapping(value = "/v1/MoRecodsSms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getMoRecodsSms(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> sms = null;
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		if (customr != null && "0".equals(customr.getOperatorType()) && customr.getId() != null) {
			// 获取运营商B的短信详情
			V2DbMoBase moBase = v2DbMoRecordsSmsService.getV2DbMoBase(customr.getId());
			sms = v2DbMoRecordsSmsService.getV2DbMoRecordsSms(moBase.getId());
		}
		return sms;
	}

	// 运营商D的短信详情
	@RequestMapping(value = "/v1/MxOldSmses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getMxOldSmses(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = null;
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		if (customr != null && "3".equals(customr.getOperatorType()) && customr.getId() != null) {
			// 获取运营商的语音详情
			V2DbMxBase mxBase = v2DbMxOldSmsesService.getV2DbMxBase(customr.getId());
			map = v2DbMxOldSmsesService.getV2DbMxOldSmses(mxBase.getId());
		}
		return map;
	}

	// 运营商的语音详情
	@RequestMapping(value = "/v1/OperatorSms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getOperatorSms(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = null;
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		if (customr != null && "1".equals(customr.getOperatorType()) && customr.getId() != null) {
			// 获取运营商的语音详情
			V2DbOperatorTask opTask = v2DbOperatorSmsService.getV2DbOperatorTask(customr.getId());
			map = v2DbOperatorSmsService.getV2DbOperatorSms(opTask.getId());
		}
		return map;
	}

	// 运营商C的上网记录接口
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/v1/XdSmses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getXdSmses(HttpServletRequest request, HttpServletResponse response) {
		List<V2DbXdSmses> XdSmses = new ArrayList<V2DbXdSmses>();
		Map<String, Object> map =null; 
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		if (customr != null && ("2").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				Map<String, Object> xdBase = netService.getV2DbXdBase(customr.getId());
				for (V2DbXdBase v2DbXdBase : (List<V2DbXdBase>) xdBase.get("data")) {
					map = v2DbXdSmsesService.getV2DbXdSmses(v2DbXdBase.getId());
					for (V2DbXdSmses v2DbXdSmses : (List<V2DbXdSmses>)map.get("data")) {
						XdSmses.add(v2DbXdSmses);
					}
				}
			}
		}
		map.put("data", XdSmses);
		return map;
	}
}
