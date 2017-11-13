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
import com.min.model.sms.V2DbOperatorSms;
import com.min.model.sms.V2DbOperatorTask;
import com.min.service.call.V2CallService;
import com.min.service.sms.V2DbOperatorSmsService;
import com.min.utils.HbaseUtils;

/**
 * 运营商B短信详情Controller
 * 
 * @author dddd
 * @version 2017-11-11
 */
@Controller
@RequestMapping(value = "/api")
public class V2DbOperatorSmsController {

	@Autowired
	private V2CallService service;

	@Autowired
	private V2DbOperatorSmsService v2DbOperatorSmsService;
	
	// 运营商的语音详情
		@RequestMapping(value = "/v2/OperatorSms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		public void getXdCalls(HttpServletRequest request, HttpServletResponse response) {
			JSON<V2DbOperatorSms> json = new JSON<V2DbOperatorSms>();
			List<V2DbOperatorSms> operatorSms = new ArrayList<V2DbOperatorSms>();

			V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),request.getParameter("mobile"));
			if (customr != null && "1".equals(customr.getOperatorType()) && customr.getId() != null) {
				// 获取运营商的语音详情
				V2DbOperatorTask opTask = v2DbOperatorSmsService.getV2DbOperatorTask(customr.getId());
				operatorSms = v2DbOperatorSmsService.getV2DbOperatorSms(opTask.getId());
				json.setCode("200");
				json.setMsg("返回成功");
			} else {
				json.setCode("404");
				json.setMsg("没有找到数据");
			}
			json.setData(operatorSms);
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
