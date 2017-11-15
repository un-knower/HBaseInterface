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
import com.min.model.call.V2DbMoBase;
import com.min.model.sms.V2DbMoRecordsSms;
import com.min.service.call.V2CallService;
import com.min.service.sms.V2DbMoRecordsSmsService;
import com.min.utils.HbaseUtils;

@Controller
@RequestMapping(value = "/api")
public class V2DbMoRecordsSmsController {

	@Autowired
	private V2CallService service;
	
	@Autowired
	private V2DbMoRecordsSmsService v2DbMoRecordsSmsService;
	
		// 运营商B的短信详情
		@RequestMapping(value = "/v2/MoRecodsSms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		public void getXdCalls(HttpServletRequest request, HttpServletResponse response) {
			JSON<V2DbMoRecordsSms> json = new JSON<V2DbMoRecordsSms>();
			List<V2DbMoRecordsSms> moRecordsSms = new ArrayList<V2DbMoRecordsSms>();

			V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),request.getParameter("mobile"));
			if (customr != null && "0".equals(customr.getOperatorType()) && customr.getId() != null) {
				// 获取运营商B的短信详情
				V2DbMoBase moBase = v2DbMoRecordsSmsService.getV2DbMoBase(customr.getId());
				System.out.println("moBase" + moBase.getId());
				moRecordsSms = v2DbMoRecordsSmsService.getV2DbMoRecordsSms(moBase.getId());
				System.out.println("moRecordsSms" + moRecordsSms.size());
				json.setCode("200");
				json.setMsg("返回成功");
			} else {
				json.setCode("404");
				json.setMsg("没有找到数据");
			}
			json.setData(moRecordsSms);
			ObjectMapper mapper = new ObjectMapper();
			try {
				HbaseUtils.setResponse(response);
				String result = mapper.writeValueAsString(json);
				response.getWriter().write(result);
			} catch (IOException e) {

			}
		}
}
