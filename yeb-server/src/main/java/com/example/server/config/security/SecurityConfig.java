package com.example.server.config.security;

import com.example.server.mapper.AdminMapper;
import com.example.server.pojo.Admin;
//import com.example.server.service.IAdminService; //这个会导致循环注入
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @PROJECT_NAME: yeb-java
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/7/29 0029 23:40
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/login",
                "/logout",
                "/css/**",
                "/js/**",
                "/index.html",
                "favicon.ioc",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                "/captcha"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //使用JWT,不需要csrf
        http.csrf()
                .disable()
                //基于token,不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                //所有请求都需要验证
                .anyRequest()
                .authenticated()
                .and()
                //禁用缓存
                .headers()
                .cacheControl();

        //添加jwt 登录授权过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录返回结果
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);


    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            //这里按照教学视频会报循环注入的错,所以不能用IAdminService,只能用AdminMapper来查询
            Admin admin = adminMapper.getAdminByUserName(username);
            if (admin != null) {
                return admin;
            }
            return null;
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }
}
