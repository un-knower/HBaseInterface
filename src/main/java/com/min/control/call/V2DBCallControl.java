package com.min.control.call;

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
import com.min.model.V2ZScustomerInfo;
import com.min.model.call.V2DbContact;
import com.min.model.call.V2DbMoBase;
import com.min.model.call.V2DbMoRecordsCall;
import com.min.model.call.V2DbOperatorCall;
import com.min.model.call.V2DbXdCalls;
import com.min.service.call.V2CallService;

@Controller
@RequestMapping("/api")
public class V2DBCallControl {

	@Autowired
	private V2CallService service;

	@RequestMapping(value = "/v2/contact", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void getContact(HttpServletRequest request, HttpServletResponse response) {
		// System.out.println(request.getParameter("idcard"));
		// System.out.println("开始查询");
		String addTime = request.getParameter("addtime");
		JSON<V2DbContact> json = new JSON<V2DbContact>();
		List<V2DbContact> contacts = null;
		// 客户信息获取
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"));
		// System.out.println("获取cid：" + customr.getId());
		// 获取成功
		if (customr != null && customr.getId() != null) {
			contacts = service.getContacts(customr.getId(), addTime);
			json.setCode("200");
			json.setMsg("返回成功");
		} else { // 没有查到结果
			json.setCode("404");
			json.setMsg("没有找到");
		}
		// System.out.println("查询结果：" + contacts.size());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/v2/OperatorCall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	// 客户信息查询
	public void getOperatorCall(HttpServletRequest request1, HttpServletResponse response1) {
		String addTime = request1.getParameter("addtime");
		JSON<V2DbOperatorCall> json = new JSON<V2DbOperatorCall>();
		List<V2DbOperatorCall> OperatorCall = null;
		V2ZScustomerInfo customr = service.getCustomr(request1.getParameter("idcard"), request1.getParameter("siteid"));
		if (customr != null && customr.getId() != null) {
			// 获取运营商B的通话记录
			OperatorCall = service.getV2DbOperatorCall(customr.getId(), addTime);
			json.setCode("200");
			json.setMsg("返回成功");
		} else {
			json.setCode("404");
			json.setMsg("没有找到");
		}
		json.setData(OperatorCall);
		ObjectMapper mapper = new ObjectMapper();
		try {
			response1.setContentType("text/plain;charset=UTF-8");
			response1.setCharacterEncoding("utf-8");
			response1.setHeader("Pragma", "No-cache");
			response1.setHeader("Cache-Control", "no-cache");
			response1.setDateHeader("Expires", 0);
			String result = mapper.writeValueAsString(json);
			response1.getWriter().write(result);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	// 运营商C的通话记录查询
	@RequestMapping(value = "/v2/XdCalls", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void getXdCalls(HttpServletRequest request, HttpServletResponse response) {
		String addTime = request.getParameter("addtime");
		JSON<V2DbXdCalls> json = new JSON<V2DbXdCalls>();
		List<V2DbXdCalls> XdCalls = new ArrayList<V2DbXdCalls>();
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"));
		if (customr != null && customr.getId() != null) {
			List<V2DbXdBase> xdBase = service.getV2DbXdBase(customr.getId(), addTime);
			for (V2DbXdBase v2DbXdBase : xdBase) {
				List<V2DbXdCalls> list = service.getV2DbXdCalls(v2DbXdBase.getID(), addTime);
				for (V2DbXdCalls v2DbXdCalls : list) {
					XdCalls.add(v2DbXdCalls);
				}
			}
			json.setData(XdCalls);
			json.setCode("200");
			json.setMsg("返回成功");
		} else {
			json.setCode("404");
			json.setMsg("没有找到");
		}
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
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/v2/MoRecordsCall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	// 客户信息查询
	public void getV2DbMoRecordsCall(HttpServletRequest request1, HttpServletResponse response1) {
		 System.out.println(request1.getParameter("idcard"));
		 System.out.println("开始查询");
		String addTime = request1.getParameter("addtime");
		JSON<V2DbMoRecordsCall> json = new JSON<V2DbMoRecordsCall>();
		List<V2DbMoRecordsCall> list = null;

		V2ZScustomerInfo customr = service.getCustomr(request1.getParameter("idcard"), request1.getParameter("siteid"));
		if (customr != null && customr.getId() != null) {
			// 获取运营商B的通话记录
			V2DbMoBase moBase = service.getV2DbMoBase(customr.getId(), addTime);
			list = service.getV2DbMoRecordsCall(moBase.getId(), addTime);
			json.setCode("200");
			json.setMsg("返回成功");
		} else {
			json.setCode("404");
			json.setMsg("没有找到");
		}
		json.setData(list);
		ObjectMapper mapper = new ObjectMapper();
		try {
			response1.setContentType("text/plain;charset=UTF-8");
			response1.setCharacterEncoding("utf-8");
			response1.setHeader("Pragma", "No-cache");
			response1.setHeader("Cache-Control", "no-cache");
			response1.setDateHeader("Expires", 0);
			String result = mapper.writeValueAsString(json);
			response1.getWriter().write(result);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
