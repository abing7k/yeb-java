package com.example.server.controller;


import com.example.server.pojo.Position;
import com.example.server.pojo.RespBean;
import com.example.server.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hanbing
 * @since 2022-07-27
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    public IPositionService positionService;

    @ApiOperation(value = "获取职位信息")
    @GetMapping("/")
    public List<Position> getAllPostion() {
        return positionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/addPosition")
    public RespBean addPosition(@RequestBody Position position) {
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)) {
            return RespBean.success("添加成功");
        } else {
            return RespBean.error("添加失败");
        }
    }

    @ApiOperation(value = "修改职位信息")
    @PostMapping("/updatePosition")
    public RespBean updatePosition(@RequestBody Position position) {
        if (positionService.updateById(position)) {
            return RespBean.success("修改成功");
        } else {
            return RespBean.error("修改失败");
        }
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("deletePositionById/{id}")
    public RespBean deletePositionById(@PathVariable Integer id) {
        if (positionService.removeById(id)) {
            return RespBean.success("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/deletePositionByIds")
    public RespBean deletePositionByIds(Integer[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }

}
