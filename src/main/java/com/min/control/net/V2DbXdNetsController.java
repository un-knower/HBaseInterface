package com.min.control.net;

import java.io.IOException;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
import com.min.model.net.V2DbXdNets;
import com.min.service.call.V2CallService;
import com.min.service.net.V2DbXdNetsService;
import com.min.utils.HbaseUtils;

@Controller
@RequestMapping("/api")
public class V2DbXdNetsController {

	@Autowired
	private V2DbXdNetsService v2DbXdNetsService;
	
	@Autowired
	private V2CallService v2CallService;

	// 运营商C的上网记录接口
		@RequestMapping(value = "/v2/XdNets", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		public void getXdCalls(HttpServletRequest request, HttpServletResponse response){
			JSON<V2DbXdNets> json = new JSON<V2DbXdNets>();
			List<V2DbXdNets> XdCalls = new ArrayList<V2DbXdNets>();
			V2ZScustomerInfo customr = v2CallService.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),request.getParameter("mobile"));
			if (customr != null && ("2").equals(customr.getOperatorType())) {
				if (customr.getId() != null) {
					System.out.println("customr.getId" + customr.getId());
					List<V2DbXdBase> xdBase = v2DbXdNetsService.getV2DbXdBase(customr.getId());
					System.out.println("xdBase66666:" + xdBase.size());
					for (V2DbXdBase v2DbXdBase : xdBase) {
						List<V2DbXdNets> list = v2DbXdNetsService.getV2DbXdNets(v2DbXdBase.getID());
						System.out.println("list.size" + list.size());
						if(XdCalls != null) {
						for (V2DbXdNets v2DbXdNets : list) {
							XdCalls.add(v2DbXdNets);
						}
						}else {
								json.setCode("404");
								json.setMsg("未找到数据");
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
				e.printStackTrace();
			}
		}
	
	/*// 运营商C的短信详情
		@RequestMapping(value = "/v2/XdNets", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		public void getXdNets(HttpServletRequest request, HttpServletResponse response) {
			JSON<V2DbXdNets> json = new JSON<V2DbXdNets>();
			List<V2DbXdNets> xdNets = new ArrayList<V2DbXdNets>();

			V2ZScustomerInfo customr = v2CallService.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),request.getParameter("mobile"));
			if (customr != null && "2".equals(customr.getOperatorType()) && customr.getId() != null) {
			  if (customr.getId() != null) {
				// 获取运营商C的短信详情s
				List<V2DbXdBase> xdBase = v2CallService.getV2DbXdBase(customr.getId());
				System.out.println("xdBase.size():" + xdBase.size());
				for (V2DbXdBase v2DbXdBase : xdBase) {
					//List<V2DbXdNets> list = v2DbXdNetsService.getV2DbXdNets(v2DbXdBase.getID());
					xdNets = v2DbXdNetsService.getV2DbXdNets(v2DbXdBase.getID());
					for (V2DbXdNets v2DbXdNets : xdNets) {
						xdNets.add(v2DbXdNets);
					}
					
				}
				json.setData(xdNets);
				json.setCode("200");
				json.setMsg("返回成功");
			  }
			} else {
				json.setCode("404");
				json.setMsg("没有找到数据");
			}
			ObjectMapper mapper = new ObjectMapper();
			try {
				HbaseUtils.setResponse(response);
				String result = mapper.writeValueAsString(json);
				response.getWriter().write(result);
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
*/
}
