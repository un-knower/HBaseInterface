package com.min.control.call;

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
import com.min.model.call.V2DbOperatorCall;
import com.min.model.call.V2DbXdCalls;
import com.min.service.call.V2CallService;
import com.min.utils.HbaseUtils;

@Controller
@RequestMapping("/api")
public class V2DBCallControl {

	@Autowired
	private V2CallService service;

	// 通讯录接口
	@RequestMapping(value = "/v1/contact", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getContact(HttpServletRequest request, HttpServletResponse response) {
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		Map<String, Object> cMap = HbaseUtils.returnNull();
		if (customr != null && customr.getId() != null) {
			cMap = service.getContacts(customr.getId(), 0, null);
		}
		return cMap;
	}

	// 运营商B的通话记录接口
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/v1/OperatorCall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getOperatorCall(HttpServletRequest request, HttpServletResponse response) {
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		Map<String, Object> cMap = HbaseUtils.returnNull();
		List<V2DbOperatorCall> list = new ArrayList<V2DbOperatorCall>();
		if (customr != null && ("1").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				Map<String, Object> operatorTask = service.getOperatorTask(customr.getId(), 0, null);
				for (V2DbOperatorTask v2DbOpTask : (List<V2DbOperatorTask>) operatorTask.get("data")) {
					cMap = service.getV2DbOperatorCall(v2DbOpTask.getPhoneid(), 0, null);
					for (V2DbOperatorCall vask : (List<V2DbOperatorCall>) cMap.get("data")) {
						list.add(vask);
					}
				}
			}
		}
		cMap.put("data", list);
		return cMap;
	}

	// 运营商C的通话记录接口
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/v1/XdCalls", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getXdCalls(HttpServletRequest request, HttpServletResponse response) {
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		Map<String, Object> cMap = HbaseUtils.returnNull();
		List<V2DbXdCalls> list = new ArrayList<V2DbXdCalls>();
		if (customr != null && ("2").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				Map<String, Object> xdBase = service.getV2DbXdBase(customr.getId(), 0, null);
				for (V2DbXdBase v2DbXdBase : (List<V2DbXdBase>) xdBase.get("data")) {
					cMap = service.getV2DbXdCalls(v2DbXdBase.getId(), 0, null);
					for (V2DbXdCalls v2DbXdCalls : (List<V2DbXdCalls>) cMap.get("data")) {
						list.add(v2DbXdCalls);
					}
				}
			}
		}
		cMap.put("data", list);
		return cMap;
	}

	// 运营商A的通话记录接口
	@RequestMapping(value = "/v1/MoRecordsCall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getV2DbMoRecordsCall(HttpServletRequest request1, HttpServletResponse response1) {
		V2ZScustomerInfo customr = service.getCustomr(request1.getParameter("idcard"), request1.getParameter("siteid"),
				request1.getParameter("mobile"));
		Map<String, Object> cMap = HbaseUtils.returnNull();
		if (customr != null && ("0").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				// 获取运营商B的通话记录
				V2DbMoBase moBase = service.getV2DbMoBase(customr.getId());
				if (moBase != null) {
					cMap = service.getV2DbMoRecordsCall(moBase.getId(), 0, null);
				}
			}
		}
		return cMap;
	}

	// 运营商的语音详情
	@RequestMapping(value = "/v1/MxOldCalls", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getMxOldCalls(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> mxOldCalls = null;

		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		if (customr != null && "3".equals(customr.getOperatorType()) && customr.getId() != null) {
			// 获取运营商的语音详情
			V2DbMxBase mxBase = service.getV2DbMxBase(customr.getId());
			mxOldCalls = service.getV2DbMxOldCalls(mxBase.getId(), 0, null);
		}
		return mxOldCalls;
	}
}
