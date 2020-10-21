package com.jinhao.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.jinhao.crowd.entity.Role;

/**
 * Created on 2020/10/20.
 *
 * @author Valar Morghulis
 */
public interface RoleService {

    PageInfo<Role> getPageInfo(Integer pageNum,Integer pageSize,String keyword);
}
