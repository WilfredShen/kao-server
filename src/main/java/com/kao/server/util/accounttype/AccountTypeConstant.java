package com.kao.server.util.accounttype;

/**
 * @author 全鸿润
 */
public class AccountTypeConstant {

    private static final String STUDENT_TYPE = "student";
    private static final String TEACHER_TYPE = "tutor";

    /**
     * 获取学生类型
     *
     * @return 学生用户类型
     */
    public static String getStudentType() {
        return STUDENT_TYPE;
    }

    /**
     * 获取教师类型
     *
     * @return 教师类型
     */
    public static String getTeacherType() {
        return TEACHER_TYPE;
    }
}
