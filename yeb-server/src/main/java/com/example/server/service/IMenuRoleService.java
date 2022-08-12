package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Menu;
import com.example.server.pojo.MenuRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hanbing
 * @since 2022-07-27
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 根据用户id查询菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId();
}
