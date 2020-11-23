package com.kao.server;

import com.kao.server.mapper.LoginMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {

    @Autowired
    LoginMapper mapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testLogin(){

    }

}
