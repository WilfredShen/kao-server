package com.kao.server;

import com.kao.server.dto.QueryableStudentMessage;
import com.kao.server.dto.UpdatedStudentMessage;
import com.kao.server.entity.Admin;
import com.kao.server.mapper.AdminMapper;
import com.kao.server.mapper.LoginMapper;
import com.kao.server.mapper.TutorMapper;
import com.kao.server.service.StudentService;
import com.kao.server.util.properties.SmsProperties;
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
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    StudentService studentService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testLogin() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date beginDate = format.parse("2019-02-23");
            Date endDate = format.parse("2023-10-01");
            List<QueryableStudentMessage> list = null;
            try {
                list = tutorService.getQueryableStudentByConditions(null, endDate, "985", null, "计算机科学");
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    @Test

    public void testAdminLogin(){
        String username = "帅润";
        String password = "534261";

        Admin admin = null;
        try {
            admin = adminMapper.findAdminByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println(admin.getUsername()+admin.getPassword());
    }

    @Test
    public void testUpdateStuMsg(){
        UpdatedStudentMessage updatedTutorMessage = new UpdatedStudentMessage();
        updatedTutorMessage.setCollege("湖南大学");
        updatedTutorMessage.setEmail("wewewe@qq.com");
        updatedTutorMessage.setMajor("软件工程");
        updatedTutorMessage.setPhoneNumber("1121212");
        updatedTutorMessage.setQueryable(true);
        updatedTutorMessage.setExpectedMajor("通信工程");
        int uid = 260458588;
        Integer raws = studentService.updateStudentMsg(updatedTutorMessage,uid);
        System.err.println(raws);
    }

}
