package com.kao.server.config;

import com.kao.server.service.AdminService;
import com.kao.server.service.StudentService;
import com.kao.server.service.TutorService;
import com.kao.server.service.impl.AdminServiceImpl;
import com.kao.server.service.impl.StudentServiceImpl;
import com.kao.server.service.impl.TutorServiceImpl;
import com.kao.server.util.aspect.AdminAspect;
import com.kao.server.util.aspect.LoginAspect;
import com.kao.server.util.aspect.StudentAspect;
import com.kao.server.util.aspect.TutorAspect;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

/**
 * @author 全鸿润
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.kao.server.util.aspect")
public class AspectConfig {

    @Bean
    public LoginAspect getLoginAspect() {
        return new LoginAspect();
    }

    @Bean
    public AdminAspect getAdminAspect() {
        return new AdminAspect();
    }

    @Bean(name = "AdminServiceOfAspect")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AdminService getAdminService() {
        return new AdminServiceImpl();
    }

    @Bean
    public StudentAspect getStudentAspect() {
        return new StudentAspect();
    }

    @Bean(name = "StudentServiceOfAspect")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public StudentService getStudentService() {
        return new StudentServiceImpl();
    }

    @Bean
    public TutorAspect getTutorAspect() {
        return new TutorAspect();
    }

    @Bean(name = "TutorServiceOfAspect")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public TutorService getTutorService() {
        return new TutorServiceImpl();
    }
}
