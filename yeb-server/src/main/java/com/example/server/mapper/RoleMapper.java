package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hanbing
 * @since 2022-07-27
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getRolesById(Integer adminId);
}
