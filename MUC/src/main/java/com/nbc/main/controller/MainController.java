package com.nbc.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	private static Logger log=Logger.getLogger(MainController.class);
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,Model model){
		log.info("=============index");
//		model.addAttribute("ls", ls);
//		return "list";
		return "index";
	}
	@RequestMapping("nav")
	public String nav(HttpServletRequest request,Model model){
		log.info("=============nav");
//		model.addAttribute("ls", ls);
//		return "list";
		return "main/nav";
	}
	@RequestMapping("left")
	public String left(HttpServletRequest request,Model model){
		log.info("=============nav");
//		model.addAttribute("ls", ls);
//		return "list";
		return "main/left";
	}
	@RequestMapping("head")
	public String head(HttpServletRequest request,Model model){
		return "main/head";
	}
	@RequestMapping("footer")
	public String footer(HttpServletRequest request,Model model){
		return "main/footer";
	}
	@RequestMapping("common")
	public String common(HttpServletRequest request,Model model){
		return "main/common";
	}
}
