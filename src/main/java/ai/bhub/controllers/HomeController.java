package ai.bhub.controllers;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home() {
		return "Today is a good day";
	}
	
	@GetMapping(value="/execute")
	public String execute() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		ProcessInstantiationBuilder processInstance = engine.getRuntimeService().createProcessInstanceByKey("first_process_execute");
		
		Map<String, String> obj = new HashMap<>();
		obj.put("value", "10");
		obj.put("name", "Some Name");
				
		processInstance.setVariable("springReturn", new JSONObject(obj).toString());
		
		processInstance.executeWithVariablesInReturn();
		
		return "BPMN Executed";
		
	}
}
