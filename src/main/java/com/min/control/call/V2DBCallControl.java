package com.min.control.call;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.model.V2DbOperatorTask;
import com.min.model.V2DbXdBase;
import com.min.model.V2ZScustomerInfo;
import com.min.model.call.V2DbMoBase;
import com.min.service.call.V2CallService;

@Controller
@RequestMapping("/api")
public class V2DBCallControl {

	@Autowired
	private V2CallService service;

	// 通讯录接口
	@RequestMapping(value = "/v2/contact", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getContact(HttpServletRequest request, HttpServletResponse response) {
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		Map<String, Object> cMap = null;
		if (customr != null && customr.getId() != null) {
			cMap = service.getContacts(customr.getId(), 0, null);
		}
		return cMap;
	}

	// 运营商B的通话记录接口

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/v2/OperatorCall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getOperatorCall(HttpServletRequest request, HttpServletResponse response) {
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		Map<String, Object> cMap = null;
		if (customr != null && ("1").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				Map<String, Object> operatorTask = service.getOperatorTask(customr.getId(), 0, null);
				for (V2DbOperatorTask v2DbOpTask : (List<V2DbOperatorTask>) operatorTask.get("data")) {
					cMap = service.getV2DbOperatorCall(v2DbOpTask.getPhoneid(), 0, null);
				}
			}
		}
		return cMap;
	}

	// 运营商C的通话记录接口
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/v2/XdCalls", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getXdCalls(HttpServletRequest request, HttpServletResponse response) {
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		Map<String, Object> cMap = null;
		if (customr != null && ("2").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				Map<String, Object> xdBase = service.getV2DbXdBase(customr.getId(), 0, null);
				for (V2DbXdBase v2DbXdBase : (List<V2DbXdBase>) xdBase.get("data")) {
					cMap = service.getV2DbXdCalls(v2DbXdBase.getId(), 0, null);
				}
			}
		}
		return cMap;
	}

	// 运营商A的通话记录接口
	@RequestMapping(value = "/v2/MoRecordsCall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getV2DbMoRecordsCall(HttpServletRequest request1, HttpServletResponse response1) {
		V2ZScustomerInfo customr = service.getCustomr(request1.getParameter("idcard"), request1.getParameter("siteid"),
				request1.getParameter("mobile"));
		Map<String, Object> cMap = null;
		if (customr != null && ("0").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				// 获取运营商B的通话记录
				V2DbMoBase moBase = service.getV2DbMoBase(customr.getId());
				if (moBase != null) {
					cMap = service.getV2DbXdCalls(moBase.getId(), 0, null);
				}
			}
		}
		return cMap;
	}

	/*
	 * list = service.getV2DbMoRecordsCall(moBase.getId()); json.setCode("200");
	 * json.setMsg("返回成功"); } } else { json.setCode("404"); json.setMsg("未找到数据"); }
	 * json.setData(list); ObjectMapper mapper = new ObjectMapper();
	 * HbaseUtils.setResponse(response1); try { String result =
	 * mapper.writeValueAsString(json); response1.getWriter().write(result); } catch
	 * (IOException e) { // TODO Auto-generated catch block } }
	 */

}
