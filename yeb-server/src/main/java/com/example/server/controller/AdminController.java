package com.example.server.controller;


import com.example.server.pojo.Admin;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.Role;
import com.example.server.service.IAdminRoleService;
import com.example.server.service.IAdminService;
import com.example.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hanbing
 * @since 2022-07-27
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRoleService roleService;
    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/getAllAdmin")
    public List<Admin> getAllAdmin(String keywords){
        return adminService.getAllAdmin(keywords);
    }

    @ApiOperation(value = "更新操作员")
    @PutMapping("/updateAdmin")
    public RespBean updateAdmin(@RequestBody Admin admin){
        admin.setPassword(null);
        if (adminService.updateById(admin)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("deleteAdmin/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id){
        if (adminService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    /**
     * 获取所有角色
     * @return
     */
    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.list();
    }

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    @ApiOperation(value = "更新操作员角色")
    @PutMapping("/role")
    public RespBean updateAdminRole(Integer adminId,Integer[] rids){
        return adminService.updateAdminRole(adminId,rids);
    }


}
