package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Menu;
import com.example.server.pojo.MenuRole;
import com.example.server.pojo.RespBean;
import org.apache.ibatis.annotations.Param;

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
     * 更新角色列表
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
