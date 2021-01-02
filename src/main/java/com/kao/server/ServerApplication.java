package com.kao.server;

import com.kao.server.service.AdminService;
import com.kao.server.service.StudentService;
import com.kao.server.service.TutorService;
import com.kao.server.service.impl.AdminServiceImpl;
import com.kao.server.service.impl.StudentServiceImpl;
import com.kao.server.service.impl.TutorServiceImpl;
import com.kao.server.util.verification.IdentityUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


/**
 * @author kao-server
 */
@SpringBootApplication
@MapperScan(basePackages = "com.kao.server.mapper")
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        try {
            IdentityUtil.verify("330411200005262215", "沈伟峰");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成并注入AdminServiceImpl
     *
     * @return AdminServiceImpl
     */
    @Bean
    @Primary
    public AdminService getAdminService() {
        return new AdminServiceImpl();
    }

    /**
     * 生成并注入StudentServiceImpl
     *
     * @return StudentServiceImpl
     */
    @Bean
    @Primary
    public StudentService getStudentService() {
        return new StudentServiceImpl();
    }

    /**
     * 生成并注入TutorServiceImpl
     *
     * @return TutorServiceImpl
     */
    @Bean
    @Primary
    public TutorService getTutorService() {
        return new TutorServiceImpl();
    }
}
