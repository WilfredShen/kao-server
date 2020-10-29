package com.kao.server;

import com.kao.server.entity.User;
import com.kao.server.mapper.QueryMapper;
import com.kao.server.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

@SpringBootTest
class ServerApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    UserMapper userMapper;
    @Autowired
    QueryMapper queryMapper;
    @Test
    void contextLoads() {
        try {
            Connection connection = dataSource.getConnection();
            System.err.println(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /*
    注册功能
     */
    @Test
    void testInsert() {

        User user = new User();
        user.setUid(2);
        user.setUsername("大爷");
        user.setName("小李");
        user.setPassword("123456");
        user.setAccount_type("游客");
        Date date = new Date(System.currentTimeMillis());
        user.setBirthday(date);
        user.setSalt("12345263");
        user.setEmail("12122@qq.com");
        user.setPhone("18856232232");
        user.setSex(false);
        user.setIdentity("460002300005034429");

        System.err.println(userMapper.insertNewUser(user));
    }

    /*
    登录功能
     */
    @Test
    public void tsetLogin() {

        String username = "芜湖";
        String password = "1234";

        User user = userMapper.selectPasswordByUserName(username);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                System.out.println("登录成功");
            } else {
                System.out.println("密码输入错误");
            }
        } else {
            System.out.println("用户名或密码输入错误");
        }
    }

    @Test
    public void updatePassword() {

        User user = userMapper.selectPasswordByUserName("芜湖");
        if (user != null) {
            userMapper.updatePassword("芜湖","1234");
        } else {
            System.err.println("没有该用户");
        }
    }

}
