package com.jinhao.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinhao.crowd.entity.Admin;
import com.jinhao.crowd.entity.AdminExample;
import com.jinhao.crowd.exception.LoginAcctRepeatException;
import com.jinhao.crowd.exception.LoginFailedException;
import com.jinhao.crowd.mapper.AdminMapper;
import com.jinhao.crowd.service.api.AdminService;
import com.jinhao.crowd.util.CrowdConstant;
import com.jinhao.crowd.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created on 2020/10/12.
 *
 * @author Valar Morghulis
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {

        // 生成系统时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String createDate = format.format(date);
        admin.setCreateTime(createDate);

        // 密码加密
        String userPswd = admin.getUserPswd();
        String password = CrowdUtil.md5(userPswd);
        admin.setUserPswd(password);

        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctRepeatException(CrowdConstant.MESSAGE_LOGIN_FAILED_NOT_UNIQUE);
            } else {
                throw e;
            }
        }


    }

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> admins = adminMapper.selectByExample(new AdminExample());
        return admins;
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        // 1.根据登录账号查询admin对象
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        // 2.判断admin对象是否为空
        if (admins == null || admins.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED_ERROR_USERNAME);
        }
        if (admins.size() > 1) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED_NOT_UNIQUE);
        }

        // 3.如果admin对象不为空，将其取出
        Admin admin = admins.get(0);
        if (admin == null) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 4.获得admin对象的密码
        String userPswdDB = admin.getUserPswd();

        // 5.将表单提交的密码与数据库密码进行比较
        String userPswdForm = CrowdUtil.md5(userPswd);

        if (!Objects.equals(userPswdDB, userPswdForm)) {
            // 6.如果比较结果不一致则抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED_ERROR_PASSWORD);
        }

        // 7.如果一致则返回admin对象
        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        // 调用pagehelper的静态方法开启分页功能
        PageHelper.startPage(pageNum, pageSize);

        // 执行查询，获取结果
        List<Admin> admins = adminMapper.selectAdminsByKeyword(keyword);

        // 封装到pageInfo对象中
        return new PageInfo<>(admins);
    }

    @Override
    public void removeAdmin(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }
}
