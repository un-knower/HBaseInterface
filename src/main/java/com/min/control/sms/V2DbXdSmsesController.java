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
import com.min.model.V2DbXdBase;
import com.min.model.V2ZScustomerInfo;
import com.min.model.sms.V2DbXdSmses;
import com.min.service.call.V2CallService;
import com.min.service.net.V2DbXdNetsService;
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
	private V2CallService v2CallService;
	
	@Autowired
	private V2DbXdNetsService v2DbXdNetsService;

	@Autowired
	private V2DbXdSmsesService v2DbXdSmsesService;
	
	// 运营商C的上网记录接口
			@RequestMapping(value = "/v2/XdSmses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
			public void getXdCalls(HttpServletRequest request, HttpServletResponse response){
				JSON<V2DbXdSmses> json = new JSON<V2DbXdSmses>();
				List<V2DbXdSmses> XdSmses = new ArrayList<V2DbXdSmses>();
				V2ZScustomerInfo customr = v2CallService.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"),request.getParameter("mobile"));
				if (customr != null && ("2").equals(customr.getOperatorType())) {
					if (customr.getId() != null) {
						// System.out.println(customr.getId());
						List<V2DbXdBase> xdBase = v2DbXdNetsService.getV2DbXdBase(customr.getId());
						System.out.println("xdBase66666:" + xdBase.size());
						for (V2DbXdBase v2DbXdBase : xdBase) {
							List<V2DbXdSmses> list = v2DbXdSmsesService.getV2DbXdSmses(v2DbXdBase.getId());
							System.out.println("list.size" + list.size());
							if(XdSmses != null) {
							for (V2DbXdSmses v2DbXdSmses : list) {
								XdSmses.add(v2DbXdSmses);
							}
							}else {
									json.setCode("404");
									json.setMsg("未找到数据");
								}
						}
						json.setData(XdSmses);
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
