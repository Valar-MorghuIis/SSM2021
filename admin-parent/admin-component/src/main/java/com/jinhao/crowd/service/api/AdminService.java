package com.jinhao.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.jinhao.crowd.entity.Admin;

import java.util.List;

/**
 * Created on 2020/10/12.
 *
 * @author Valar Morghulis
 */
public interface AdminService {

    void saveAdmin(Admin admin);

    List<Admin> getAllAdmins();

    Admin getAdminByLoginAcct(String loginAcct,String userPswd);

    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    void removeAdmin(Integer adminId);

    Admin getAdminById(Integer adminId);

    void updateAdmin(Admin admin);
}
