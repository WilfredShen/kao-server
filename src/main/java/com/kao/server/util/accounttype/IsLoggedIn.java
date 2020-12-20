package com.kao.server.util.accounttype;

import java.lang.annotation.*;

/**
 * @author 全鸿润
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface IsLoggedIn {

}
