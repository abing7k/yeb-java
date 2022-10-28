package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.Utils.AdminUtils;
import com.example.server.Utils.JwtTokenUtils;
import com.example.server.mapper.AdminMapper;
import com.example.server.mapper.AdminRoleMapper;
import com.example.server.mapper.RoleMapper;
import com.example.server.pojo.Admin;
import com.example.server.pojo.AdminRole;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.Role;
import com.example.server.service.IAdminRoleService;
import com.example.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hanbing
 * @since 2022-07-27
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private IAdminRoleService adminRoleService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    AdminRoleMapper adminRoleMapper;


    /**
     * 登陆之后返回token
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码出入错误,请重新驶入");
        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用,请联系管理员");
        }

        //更新Security用户登录对象
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成token
        String token = jwtTokenUtils.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>()
                .eq("username", username)
                .eq("enabled", true));
    }


    /**
     * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRolesById(Integer adminId) {
        return roleMapper.getRolesById(adminId);
    }

    @Override
    public List<Admin> getAllAdmin(String keywords) {
        return adminMapper.getAllAdmin(AdminUtils.getCurrentAdmin().getId(),keywords);
    }

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
        Integer result = adminRoleMapper.addRole(adminId, rids);
        if (rids.length == result){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
