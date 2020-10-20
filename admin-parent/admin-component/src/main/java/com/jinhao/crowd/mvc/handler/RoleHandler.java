package com.jinhao.crowd.mvc.handler;

import com.jinhao.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created on 2020/10/20.
 *
 * @author Valar Morghulis
 */
@Controller
public class RoleHandler {

    @Autowired
    private RoleService roleService;
}
