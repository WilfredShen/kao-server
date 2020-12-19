package com.kao.server;

import com.kao.server.service.AdminService;
import com.kao.server.service.StudentService;
import com.kao.server.service.TutorService;
import com.kao.server.service.impl.AdminServiceImpl;
import com.kao.server.service.impl.StudentServiceImpl;
import com.kao.server.service.impl.TutorServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;


/**
 * @author kao-server
 */
@SpringBootApplication
@MapperScan(basePackages = "com.kao.server.mapper")
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    @Primary
    public AdminService getAdminService() {
        return new AdminServiceImpl();
    }

    @Bean
    @Primary
    public StudentService getStudentService() {
        return new StudentServiceImpl();
    }

    @Bean
    @Primary
    public TutorService getTutorService() {
        return new TutorServiceImpl();
    }
}
