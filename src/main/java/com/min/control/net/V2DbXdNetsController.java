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
	private V2CallService v2CallService;
	
	@Autowired
	private V2DbXdNetsService v2DbXdNetsService;

	
	// 运营商C的上网记录接口
			@RequestMapping(value = "/v2/XdNets", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
			public void getXdCalls(HttpServletRequest request, HttpServletResponse response){
				JSON<V2DbXdNets> json = new JSON<V2DbXdNets>();
				List<V2DbXdNets> XdNets = new ArrayList<V2DbXdNets>();
				V2ZScustomerInfo customr = v2CallService.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),request.getParameter("mobile"));
				if (customr != null && ("2").equals(customr.getOperatorType())) {
					if (customr.getId() != null) {
						List<V2DbXdBase> xdBase = v2DbXdNetsService.getV2DbXdBase(customr.getId());
						for (V2DbXdBase v2DbXdBase : xdBase) {
							List<V2DbXdNets> list = v2DbXdNetsService.getV2DbXdNets(v2DbXdBase.getID());
							System.out.println("list.size" + list.size());
							if(XdNets != null) {
							for (V2DbXdNets v2DbXdN : list) {
								XdNets.add(v2DbXdN);
							}
							}else {
									json.setCode("404");
									json.setMsg("未找到数据");
								}
						}
						json.setData(XdNets);
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
	
	
}
