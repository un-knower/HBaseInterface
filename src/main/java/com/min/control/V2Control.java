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
import com.min.hbasedao.V2DbContactDaoImpl;
import com.min.model.JSON;
import com.min.model.V2DbContact;
import com.min.model.V2DbMxNet;
import com.min.model.V2ZScustomerInfo;
import com.min.service.V2Service;

@Controller
@RequestMapping("/api")
public class V2Control {
	@Autowired
	private V2Service service;

	@RequestMapping(value = "/v2/contact", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	// 客户信息查询
	public void getCusInfoId(HttpServletRequest request, HttpServletResponse response) {
		// System.out.println(request.getParameter("idcard"));
		System.out.println("开始查询");
		String addTime = request.getParameter("addtime");
		JSON<V2DbContact> json = new JSON<V2DbContact>();
		List<V2DbContact> contacts = null;
		// 获取通讯录
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"));
		System.out.println("获取cid：" + customr.getId());
		if (customr.getId() != null) {
			contacts = service.getContacts(customr.getId(), addTime);
			json.setCode("200");
			json.setMsg("返回成功");
		} else {
			json.setCode("404");
			json.setMsg("没有找到");
		}
		System.out.println("查询结果：" + contacts.size());
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

	@RequestMapping(value = "/v2/mxoldnets", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void getMxOldNets(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("开始查询");
		String addTime = request.getParameter("addtime");
		JSON<V2DbMxNet> json = new JSON<V2DbMxNet>();
		List<V2DbMxNet> vList = null;
		// 获取通讯录
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"));
		if (customr.getId() != null) {
			// vList = service.getMxOldNets(customr.getId(), addTime);
			System.out.println("获取cid：" + customr.getId());
			vList = new V2DbContactDaoImpl().getMxOldNets(customr.getId(), addTime);
			json.setCode("200");
			json.setMsg("返回成功");
		} else {
			json.setCode("404");
			json.setMsg("没有找到");
		}
		System.out.println("查询结果：" + vList.size());
		json.setData(vList);
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			String result = mapper.writeValueAsString(json);
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
