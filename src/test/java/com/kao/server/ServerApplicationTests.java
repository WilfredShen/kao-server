package com.kao.server;

import com.kao.server.dto.QueryableStudentMessage;
import com.kao.server.mapper.LoginMapper;
import com.kao.server.mapper.TutorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ServerApplicationTests {

    @Autowired
    LoginMapper mapper;
    @Autowired
    TutorMapper tutorService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testLogin() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date beginDate = format.parse("2019-02-23");
            Date endDate = format.parse("2023-10-01");
            List<QueryableStudentMessage> list = tutorService.getQueryableStudentByConditions(null, endDate, "985", null, "计算机科学");
            if (list != null) {
                System.err.println(list.size());
            }
            for (QueryableStudentMessage message : list
            ) {

                System.err.println(message.getName());
                System.err.println(message.getCollege());
                System.err.println(message.getMajor());
                System.err.println(message.getLevel());
                System.err.println(message.getPhone());
                System.err.println(message.getEmail());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
