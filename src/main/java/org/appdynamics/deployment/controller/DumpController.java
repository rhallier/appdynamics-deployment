package org.appdynamics.deployment.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DumpController {
	
    @RequestMapping("/dump")
    String dump(@RequestParam Map<String,String> allRequestParams, ModelMap model) {
    	model.addAttribute("params", allRequestParams);
        return "dump";
    }
}
