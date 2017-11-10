package com.min.control.net;

import java.io.IOException;
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
import com.min.model.net.V2DbMxNet;
import com.min.service.call.V2CallService;
import com.min.service.net.V2NetService;
import com.min.utils.HbaseUtils;

@Controller
@RequestMapping("/api")
public class V2DBNet {
	@Autowired
	private V2NetService netService;
	@Autowired
	private V2CallService service;

	@RequestMapping(value = "/v2/MxOldNets", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void getMxOldNets(HttpServletRequest request, HttpServletResponse response) {
		// System.out.println("开始查询");
		String addTime = request.getParameter("addtime");
		JSON<V2DbMxNet> json = new JSON<V2DbMxNet>();
		List<V2DbMxNet> vList = null;
		// 获取通讯录
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"));

		if (customr != null && customr.getId() != null) {
			vList = netService.getMxOldNets(customr.getId(), addTime);
			System.out.println("获取cid：" + customr.getId());
			if (customr != null && customr.getId() != null) {
				vList = netService.getMxOldNets(customr.getId(), addTime);
				// System.out.println("获取cid：" + customr.getId());
				json.setCode("200");
				json.setMsg("返回成功");
			} else {
				json.setCode("404");
				json.setMsg("没有找到");
			}
			// System.out.println("查询结果：" + vList.size());
			json.setData(vList);
			ObjectMapper mapper = new ObjectMapper();
			HbaseUtils.setResponse(response);
			try {
				String result = mapper.writeValueAsString(json);
				response.getWriter().write(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
