package com.min.control.net;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.model.V2ZScustomerInfo;
import com.min.model.base.V2DbMxBase;
import com.min.model.base.V2DbXdBase;
import com.min.model.net.V2DbMxOldNets;
import com.min.model.net.V2DbXdNets;
import com.min.service.call.V2CallService;
import com.min.service.net.V2NetsService;
import com.min.utils.HbaseUtils;

@Controller
@RequestMapping("/api")
public class V2DbNetsController {

	@Autowired
	private V2NetsService v2NetsService;

	@Autowired
	private V2CallService v2CallService;

	// 运营商D的上网记录接口
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/v1/MxOldNets", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getMxOldNets(HttpServletRequest request, HttpServletResponse response) {
		List<V2DbMxOldNets> mxOldNets = new ArrayList<V2DbMxOldNets>();
		Map<String, Object> map = HbaseUtils.returnNull();
		V2ZScustomerInfo customr = v2CallService.getCustomr(request.getParameter("idcard"),
				request.getParameter("siteid"), request.getParameter("mobile"));
		if (customr != null && ("2").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				Map<String, Object> MxBase = (Map<String, Object>) v2CallService.getV2DbMxBase(customr.getId());
				for (V2DbMxBase v2DbMxBase : (List<V2DbMxBase>) MxBase.get("data")) {
					map = v2NetsService.getV2DbMxOldNets(v2DbMxBase.getId(), 0, null);
					List<V2DbMxOldNets> list = (List<V2DbMxOldNets>) map.get("data");
					for (V2DbMxOldNets v2DbMxOldN : list) {
						mxOldNets.add(v2DbMxOldN);
					}
				}
			}
		}
		map.put("data", mxOldNets);
		return map;
	}

	// 运营商C的上网记录接口
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/v1/XdNets", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getXdNets(HttpServletRequest request, HttpServletResponse response) {
		List<V2DbXdNets> XdNets = new ArrayList<V2DbXdNets>();
		Map<String, Object> map = null;
		V2ZScustomerInfo customr = v2CallService.getCustomr(request.getParameter("idcard"),
				request.getParameter("siteid"), request.getParameter("mobile"));
		if (customr != null && ("2").equals(customr.getOperatorType())) {
			if (customr.getId() != null) {
				Map<String, Object> xdBase = v2CallService.getV2DbXdBase(customr.getId(), 0, null);
				for (V2DbXdBase v2DbXdBase : (List<V2DbXdBase>) xdBase.get("data")) {
					map = v2NetsService.getV2DbXdNets(v2DbXdBase.getId(), 0, null);
					for (V2DbXdNets v2DbXdN : (List<V2DbXdNets>) map.get("data")) {
						XdNets.add(v2DbXdN);
					}
				}
			}
		}
		map.put("data", XdNets);
		return map;
	}
}
