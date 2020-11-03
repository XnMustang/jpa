package com.wangjun;

import com.wangjun.entity.Customer;
import com.wangjun.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @Description:
 * @Author : 王俊
 * @date :  2020/11/2
 */

public class JpaTest {

    /**
     * 测试jpa的保存
     *  1.加载配置文件创建工厂对象（实体管理器工厂）
     *  2.通过实体管理器工厂获取实体管理器
     *  3.获取事务对象，开启事务
     *  4.完成增删改查操作
     *  5.提交事务（回滚事务）
     *  6.释放资源
     */
    @Test
    public void testSave(){
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
//        EntityManager em = factory.createEntityManager();
//        EntityTransaction ts = em.getTransaction(); //获取事务对象

        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction ts = em.getTransaction();//获取事务对象
        ts.begin();    //开启事务

        Customer customer = new Customer();
        customer.setCustName("王先生");
        customer.setCustAddress("河南");
        em.persist(customer);    //保存操作

        ts.commit();    //提交事务

        em.close();     //释放资源
//        factory.close();
    }

    /**
     * 根据id查询客户 find
     *      1.查询的对象就是当前客户对象本身
     *      2.在调用find方法的时候，就会发送sql语句查询数据库
     */
    @Test
    public void testFind(){
        //根据工具类获取实体管理器entityManager
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        //完成业务操作
        Customer customer = em.find(Customer.class, 1L);
        System.out.println(customer);
        //提交事务
        transaction.commit();
        //释放资源
        em.close();
    }

    /**
     * 根据id查询客户 Reference  懒加载
     *      1.获取的对象是一个动态代理对象
     *      2.调用getReference方法不会立即发送sql语句查询数据库
     *      *当调用查询结果对象的时候，才会发送查询的sql语句:     什么时调用，什么时候发送sql语句查询数据库
     */
    @Test
    public void testReference(){
        //根据工具类获取实体管理器entityManager
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        //完成业务操作
        Customer customer = em.getReference(Customer.class, 1L);
        System.out.println(customer);
        //提交事务
        transaction.commit();
        //释放资源
        em.close();
    }

}
