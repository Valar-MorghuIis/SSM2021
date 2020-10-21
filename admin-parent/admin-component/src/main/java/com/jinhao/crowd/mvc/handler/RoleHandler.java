package com.jinhao.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.jinhao.crowd.entity.Role;
import com.jinhao.crowd.service.api.RoleService;
import com.jinhao.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2020/10/20.
 *
 * @author Valar Morghulis
 */
@Controller
public class RoleHandler {

    @Autowired
    private RoleService roleService;


    @RequestMapping("/role/get/page/info.json")
    @ResponseBody
    public ResultEntity<PageInfo<Role>> getRolePageInfo(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                              @RequestParam(value = "keyword",defaultValue = "") String keyword){
        PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
        return ResultEntity.successWithData(pageInfo);
    }
}
