package com.wangjun.utils;

/**
 * @Description: jpa工具类
 * @Author : 王俊
 * @date :  2020/11/3
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 解决实体管理器工厂的浪费资源喝耗时问题
 * 通过静态代码块的形式，当程序第一次访问此工具类的时候，创建一个公共的实体管理器工厂对象
 */
public class JpaUtils {

    private static EntityManagerFactory factory;

    static {
        //加载配置文件，创建entityManagerFactory
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
