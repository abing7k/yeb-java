package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hanbing
 * @since 2022-07-27
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     *
     * @return
     */
    List<Department> getAllDepartment(Integer parentId);

    /**
     * 添加部门
     *
     * @param department
     * @return
     */
    void addDep(Department department);
}
