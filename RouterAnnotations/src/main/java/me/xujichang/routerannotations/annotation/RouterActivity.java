package me.xujichang.routerannotations.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/24 15:43.
 */
@Target(ElementType.TYPE)//修饰类
@Retention(RetentionPolicy.RUNTIME)//运行时有效
public @interface RouterActivity {
    /**
     * 该Activity对应的key
     * 在路由的时候会根据可以去查找Activity类
     */
    String flag();

    /**
     * 当Activity找不到的时候，会有远程的URl提供下载等，
     * 适用于模块
     *
     * @return
     */
    String remoteUrl();

}
