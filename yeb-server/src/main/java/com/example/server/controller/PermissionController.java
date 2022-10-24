package com.example.server.controller;

import com.example.server.pojo.Role;
import com.example.server.pojo.RespBean;
import com.example.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

/**
 * @PROJECT_NAME: yeb-java
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/10/24 0024 21:47
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PermissionController {
    @Autowired
    public IRoleService roleService;

    @ApiOperation(value = "获取职位信息")
    @GetMapping("/getAllRole")
    public List<Role> getAllRole() {
        return roleService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/addRole")
    public RespBean addRole(@RequestBody Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_"+role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("添加成功");
        } else {
            return RespBean.error("添加失败");
        }
    }

    @ApiOperation(value = "修改职位信息")
    @PostMapping("/updateRole")
    public RespBean updateRole(@RequestBody Role role) {
        if (roleService.updateById(role)) {
            return RespBean.success("修改成功");
        } else {
            return RespBean.error("修改失败");
        }
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("deleteRoleById/{id}")
    public RespBean deleteRoleById(@PathVariable Integer id) {
        if (roleService.removeById(id)) {
            return RespBean.success("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/deleteRoleByIds")
    public RespBean deleteRoleByIds(Integer[] ids) {
        if (roleService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }
}
