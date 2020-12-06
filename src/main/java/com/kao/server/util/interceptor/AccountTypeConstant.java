package com.kao.server.util.interceptor;

/**
 * @author 全鸿润
 */
public class AccountTypeConstant {

    private static final String STUDENT_TYPE = "student";
    private static final String TEACHER_TYPE = "tutor";
    private static final String ADMIN_TYPE = "admin";


    public static String getStudentType() {
        return STUDENT_TYPE;
    }

    public static String getTeacherType() {
        return TEACHER_TYPE;
    }

    public static String getAdminType() {
        return ADMIN_TYPE;
    }
}
