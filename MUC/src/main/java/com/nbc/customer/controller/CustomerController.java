package com.nbc.customer.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbc.customer.pojo.Customer;
import com.nbc.customer.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Resource
	private ICustomerService customerService;
	
	private static Logger log=Logger.getLogger(CustomerController.class);
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,Model model){
		log.info("到list里面了==========");
		List<Customer> ls= this.customerService.getAll(1,10);
		model.addAttribute("ls", ls);
//		return "list";
		return "customer/list";
	}
}
