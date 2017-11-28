package com.min.control.call;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.min.model.V2DbOperatorTask;
import com.min.model.V2DbXdBase;
import com.min.model.V2ZScustomerInfo;
import com.min.model.call.V2DbMoBase;
import com.min.model.call.V2DbMoRecordsCall;
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

		@RequestMapping(value = "/v2/OperatorCall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		public void getOperatorCall(HttpServletRequest request, HttpServletResponse response) {
			JSON<V2DbOperatorCall> json = new JSON<V2DbOperatorCall>();
			List<V2DbOperatorCall> opCall = new ArrayList<V2DbOperatorCall>();
			V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),request.getParameter("mobile"));
			if (customr != null && ("1").equals(customr.getOperatorType())) {
				if (customr.getId() != null) {
					List<V2DbOperatorTask> OTask = service.getOperatorTask(customr.getId());
					for (V2DbOperatorTask v2DbOpTask : OTask) {
						List<V2DbOperatorCall> list = service.getV2DbOperatorCall(v2DbOpTask.getPhoneid());
						for (V2DbOperatorCall v2DbOperatorCall : list) {
							opCall.add(v2DbOperatorCall);
						}
					}
					json.setData(opCall);
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

	@RequestMapping(value = "/v2/OperatorCall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void getOperatorCall(HttpServletRequest request1, HttpServletResponse response1) {
		JSON<V2DbOperatorCall> json = new JSON<V2DbOperatorCall>();
		List<V2DbOperatorCall> OperatorCall = null;
		V2ZScustomerInfo customr = service.getCustomr(request1.getParameter("idcard"), request1.getParameter("siteid"),
				request1.getParameter("mobile"));
		if (customr != null && ("1").equals(customr.getOperatorType())) {
			if (customr != null && customr.getId() != null) {
				OperatorCall = service.getV2DbOperatorCall(customr.getId());
				json.setCode("200");
				json.setMsg("返回成功");
			}
		}

	// 运营商C的通话记录接口
	@RequestMapping(value = "/v2/XdCalls", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void getXdCalls(HttpServletRequest request, HttpServletResponse response) {
		JSON<V2DbXdCalls> json = new JSON<V2DbXdCalls>();
		List<V2DbXdCalls> XdCalls = new ArrayList<V2DbXdCalls>();
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),
				request.getParameter("mobile"));
		if (customr != null && ("2").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				List<V2DbXdBase> xdBase = service.getV2DbXdBase(customr.getId());
				for (V2DbXdBase v2DbXdBase : xdBase) {
					List<V2DbXdCalls> list = service.getV2DbXdCalls(v2DbXdBase.getId());
					for (V2DbXdCalls v2DbXdCalls : list) {
						XdCalls.add(v2DbXdCalls);
					}
				}
				json.setData(XdCalls);
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

	// 运营商A的通话记录接口
	@RequestMapping(value = "/v2/MoRecordsCall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void getV2DbMoRecordsCall(HttpServletRequest request1, HttpServletResponse response1) {
		JSON<V2DbMoRecordsCall> json = new JSON<V2DbMoRecordsCall>();
		List<V2DbMoRecordsCall> list = null;

		V2ZScustomerInfo customr = service.getCustomr(request1.getParameter("idcard"), request1.getParameter("siteid"),
				request1.getParameter("mobile"));
		if (customr != null && ("0").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				// 获取运营商B的通话记录
				V2DbMoBase moBase = service.getV2DbMoBase(customr.getId());
				list = service.getV2DbMoRecordsCall(moBase.getId());
				json.setCode("200");
				json.setMsg("返回成功");
			}
		} else {
			json.setCode("404");
			json.setMsg("未找到数据");
		}
		json.setData(list);
		ObjectMapper mapper = new ObjectMapper();
		HbaseUtils.setResponse(response1);
		try {
			String result = mapper.writeValueAsString(json);
			response1.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

}
