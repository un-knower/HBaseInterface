package com.min.control.sms;

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
import com.min.model.V2ZScustomerInfo;
import com.min.model.sms.V2DbXdBase;
import com.min.model.sms.V2DbXdSmses;
import com.min.service.call.V2CallService;
import com.min.service.sms.V2DbXdSmsesService;
import com.min.utils.HbaseUtils;

/**
 * 运营商C短信详情Controller
 * 
 * @author dddd
 * @version 2017-11-11
 */
@Controller
@RequestMapping(value = "/api")
public class V2DbXdSmsesController {

	@Autowired
	private V2CallService service;

	@Autowired
	private V2DbXdSmsesService v2DbXdSmsesService;
	
	// 运营商的语音详情
			@RequestMapping(value = "/v2/XdSmses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
			public void getXdCalls(HttpServletRequest request, HttpServletResponse response) {
				JSON<V2DbXdSmses> json = new JSON<V2DbXdSmses>();
				List<V2DbXdSmses> xdSmses = new ArrayList<V2DbXdSmses>();

				V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),request.getParameter("mobile"));
				if (customr != null && "2".equals(customr.getOperatorType()) && customr.getId() != null) {
					System.out.println("customr.getId()" + customr.getId());
					
					// 获取运营商的语音详情
					V2DbXdBase xdBase = v2DbXdSmsesService.getV2DbXdBase(customr.getId());
					System.out.println("xdBase.getId()" + xdBase.getId());
					xdSmses = v2DbXdSmsesService.getV2DbXdSmses(xdBase.getId());
					json.setCode("200");
					json.setMsg("返回成功");
				} else {
					json.setCode("404");
					json.setMsg("没有找到数据");
				}
				json.setData(xdSmses);
				ObjectMapper mapper = new ObjectMapper();
				try {
					HbaseUtils.setResponse(response);
					String result = mapper.writeValueAsString(json);
					response.getWriter().write(result);
				} catch (IOException e) {
					// TODO: handle exception
				}
			}
}
