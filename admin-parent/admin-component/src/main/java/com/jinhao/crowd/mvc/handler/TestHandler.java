package com.jinhao.crowd.mvc.handler;

import com.jinhao.crowd.entity.Admin;
import com.jinhao.crowd.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created on 2020/10/12.
 *
 * @author Valar Morghulis
 */
@Controller
public class TestHandler {

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping("/send/array.html")
    public String testArray(@RequestBody List<Integer> array){
        Logger logger = LoggerFactory.getLogger(this.getClass());
        for (Integer number : array){
            logger.info("number:" + number);
        }
        String a = null;
        System.out.println(a.length());
        return "success";
    }

    @RequestMapping("/test/ssm.html")
    public String test(ModelMap modelMap){
        List<Admin> admins = adminService.getAllAdmins();
        modelMap.addAttribute("admins",admins);
        int i = 10/0;
        return "target";
    }
}
