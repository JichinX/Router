package me.xujichang.routerannotations.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/24 16:06.
 */
@Target(ElementType.TYPE)//修饰类
@Retention(RetentionPolicy.RUNTIME)//运行时有效
public @interface RouterService {
    String flag();
}
