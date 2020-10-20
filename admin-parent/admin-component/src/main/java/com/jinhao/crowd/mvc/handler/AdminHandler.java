package com.jinhao.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.jinhao.crowd.entity.Admin;
import com.jinhao.crowd.exception.LoginFailedException;
import com.jinhao.crowd.service.api.AdminService;
import com.jinhao.crowd.util.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created on 2020/10/14.
 *
 * @author Valar Morghulis
 */
@Controller
public class AdminHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/save.html")
    public String saveAdmin(Admin admin){
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
    }

    /**
     *  管理员用户删除
     */
    @RequestMapping("admin/do/remove/{adminId}/{pageNum}/{keyword}.html")
    public String removeAdmin(@PathVariable("adminId") Integer adminId,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("keyword") String keyword){

        // 通过ID删除用户
        adminService.removeAdmin(adminId);

        // 通过重定向返回分页，防止请求多次提交，带上pageNum和keyword可以回到删除时的页面
        return "redirect:/admin/get/page.html?pageNum=" + pageNum +"&keyword=" + keyword;
    }

    /**
     *  管理员用户分页
     */
    @RequestMapping("admin/get/page.html")
    public String getPage(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                          @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                          ModelMap modelMap){
        // 获取pageinfo对象
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);

        //将pageinfo对象封装到modelmap中
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGEINFO,pageInfo);
        return "admin-page";
    }

    /**
     *  管理员登出
     */
    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session){

        // 清除session退出登录
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }

    /**
     *  管理员登录验证
     */
    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("userPswd") String userPswd,
                          HttpSession session){
        Admin admin = adminService.getAdminByLoginAcct(loginAcct,userPswd);

        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);

        return "redirect:/admin/to/main/page.html";
    }
}
