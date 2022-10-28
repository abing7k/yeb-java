package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.AdminRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hanbing
 * @since 2022-07-27
 */
@Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 更新操作员
     * @param adminId
     * @param rids
     * @return
     */
    Integer addRole(Integer adminId, Integer[] rids);
}
