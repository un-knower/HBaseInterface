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
import com.min.model.call.V2DbMxBase;
import com.min.model.sms.V2DbMxOldSmses;
import com.min.service.call.V2CallService;
import com.min.service.sms.V2DbMxOldSmsesService;
import com.min.utils.HbaseUtils;

/**
 * 运营商D短信详情Controller
 * 
 * @author dddd
 * @version 2017-11-11
 */
@Controller
@RequestMapping(value = "/api")
public class V2DbMxOldSmsesController {

	@Autowired
	private V2CallService service;

	@Autowired
	private V2DbMxOldSmsesService v2DbMxOldSmsesService;
	
	// 运营商D的短信详情
		@RequestMapping(value = "/v2/MxOldSmses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		public void getXdCalls(HttpServletRequest request, HttpServletResponse response) {
			JSON<V2DbMxOldSmses> json = new JSON<V2DbMxOldSmses>();
			List<V2DbMxOldSmses> mxOldSmses = new ArrayList<V2DbMxOldSmses>();

			V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"));
			if (customr != null && "3".equals(customr.getOperatorType()) && customr.getId() != null) {
				// 获取运营商的语音详情
				V2DbMxBase mxBase = v2DbMxOldSmsesService.getV2DbMxBase(customr.getId());
				mxOldSmses = v2DbMxOldSmsesService.getV2DbMxOldSmses(mxBase.getId());
				json.setCode("200");
				json.setMsg("返回成功");
			} else {
				json.setCode("404");
				json.setMsg("没有找到数据");
			}
			json.setData(mxOldSmses);
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
