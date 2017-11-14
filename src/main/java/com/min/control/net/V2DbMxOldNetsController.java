package com.min.control.net;

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
import com.min.model.net.V2DbMxOldNets;
import com.min.service.call.V2CallService;
import com.min.service.net.V2DbMxOldNetsService;
import com.min.utils.HbaseUtils;

@Controller
@RequestMapping("/api")
public class V2DbMxOldNetsController {
	
	@Autowired
	private V2DbMxOldNetsService v2DbMxOldNetsService;
	
	@Autowired
	private V2CallService service;

	// 运营商D的上网记录
		@RequestMapping(value = "/v2/MxOldNets", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		public void getXdCalls(HttpServletRequest request, HttpServletResponse response) {
			JSON<V2DbMxOldNets> json = new JSON<V2DbMxOldNets>();
			List<V2DbMxOldNets> mxOldNets = new ArrayList<V2DbMxOldNets>();

			V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),request.getParameter("mobile"));
			if (customr != null && "3".equals(customr.getOperatorType()) && customr.getId() != null) {
				// 获取运营商D的上网记录
				V2DbMxBase mxBase = v2DbMxOldNetsService.getV2DbMxBase(customr.getId());
				mxOldNets = v2DbMxOldNetsService.getV2DbMxOldNets(mxBase.getId());
				json.setCode("200");
				json.setMsg("返回成功");
			} else {
				json.setCode("404");
				json.setMsg("未找到数据");
			}
			json.setData(mxOldNets);
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
