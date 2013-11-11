package com.audit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FlowTest {

	@RequestMapping("/createFlow")
	public String createFlow() {

		List<String> users = new ArrayList<String>();
		users.add("sadasd1");
		users.add("dasdasd1");
		try {
			// iWorkFlowComponent.createWorkFlow("YS005", "arrangePeople","1");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
