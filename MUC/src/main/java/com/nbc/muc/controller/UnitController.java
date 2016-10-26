package com.nbc.muc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbc.muc.pojo.Unit;
import com.nbc.muc.pojo.User;
import com.nbc.muc.service.IUnitService;
import com.nbc.muc.service.IUserService;


@Controller
@RequestMapping("/unit")
public class UnitController {
	@Resource
	private IUnitService unitService;
	
	private static Logger log=Logger.getLogger(UnitController.class);
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,Model model){
		log.info("到list里面了==========");
		List<Unit> ls= this.unitService.getUnitAll(1,10);
		model.addAttribute("ls", ls);
		return "unitlist";
	}
}
