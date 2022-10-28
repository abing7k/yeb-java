package com.example.server.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Admin;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.Role;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hanbing
 * @since 2022-07-27
 */
public interface IAdminService extends IService<Admin> {
    /**
     * 登录之后,返回token
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRolesById(Integer adminId);

    /**
     * 获取所有操作员
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmin(String keywords);

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    RespBean updateAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
