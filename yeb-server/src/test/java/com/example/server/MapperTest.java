package com.example.server;

import com.example.server.mapper.AdminMapper;
import com.example.server.pojo.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @PROJECT_NAME: yeb-java
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/7/31 0031 22:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {
    @Autowired
    AdminMapper adminMapper;

    @Test
    public void adminMapperTest(){
        Admin adminByUserName = adminMapper.getAdminByUserName("admin");
        System.out.println(adminByUserName);
    }

}
