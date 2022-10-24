package com.example.server;

import com.example.server.pojo.Menu;
import com.example.server.pojo.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @PROJECT_NAME: yeb-java
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/9/12 0012 9:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {

    @Test
    public void test01(){
        String s="12345";
        System.out.println(s.substring(1));

    }

    @Test
    public void MD5() {
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }

    @Test
    public void test02(){
        List<Menu> menus = new ArrayList<Menu>();
        String[] str = null;
        Menu menu1 = new Menu();
        menu1.setId(1);
        Menu menu2 = new Menu();
        menu1.setId(4);
        Menu menu3 = new Menu();
        menu1.setId(3);
        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        for (Menu menu : menus) {
            //判断请求的url与菜单角色是否匹配
            str = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
        }
        System.out.println(str);
    }
}
