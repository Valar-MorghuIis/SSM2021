package com.jinhao.crowd.service.impl;

import com.jinhao.crowd.mapper.RoleMapper;
import com.jinhao.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2020/10/20.
 *
 * @author Valar Morghulis
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
}
