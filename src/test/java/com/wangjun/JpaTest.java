package com.wangjun;

import com.wangjun.entity.Customer;
import com.wangjun.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

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

    /**
     * 删除用户
     */
    @Test
    public void testRemove(){
        //根据工具类获取实体管理器entityManager
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        //完成业务操作  先查询 后传入对象
        Customer customer = em.getReference(Customer.class, 1L);
        em.remove(customer);
        //提交事务
        transaction.commit();
        //释放资源
        em.close();
    }

    /**
     * 更新用户信息
     */
    @Test
    public void testUpdate(){
        //根据工具类获取实体管理器entityManager
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        //完成业务操作  先查询 后传入对象
        Customer customer = em.getReference(Customer.class, 2L);
        customer.setCustAddress("上海");
        customer.setCustIndustry("IT");
        em.persist(customer);
        //提交事务
        transaction.commit();
        //释放资源
        em.close();
    }

    /**
     * 查询全部
     * sql: select * from customer
     * jpql:from Customer
     */
    @Test
    public void testFindAll(){
        //根据工具类获取实体管理器entityManager
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        //完成业务操作
        String jpql = "from Customer";
        Query query = em.createQuery(jpql);     //创建query查询对象，Query对象才是执行jpql的对象
        //发送查询 并封装结果集
        List resultList = query.getResultList();
        for (Object customer : resultList) {
            System.out.println(customer);
        }
        transaction.commit();
        em.close();
    }

    /**
     * 根据id倒叙查询所有用户
     * sql:select * from customer order by cust_id desc;
     * jpql:from Customer order by custId desc
     */
    @Test
    public void testOrders(){
        //根据工具类获取实体管理器entityManager
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        //完成业务操作
        String jpql = "from Customer order by custId desc ";
        Query query = em.createQuery(jpql);     //创建query查询对象，Query对象才是执行jpql的对象
        //发送查询 并封装结果集
        List resultList = query.getResultList();
        for (Object customer : resultList) {
            System.out.println(customer);
        }
        transaction.commit();
        em.close();
    }

    /**
     * 统计函数,统计总数
     * sql:select count(cust_id) from customer
     * jpql:select count(cust_id) from customer
     */
    @Test
    public void testCount(){
        //根据工具类获取实体管理器entityManager
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        //完成业务操作
        String jpql = "select count(custId) from Customer";
        Query query = em.createQuery(jpql);     //创建query查询对象，Query对象才是执行jpql的对象
        //发送查询 并封装结果集，得到唯一的结果集
        Object result = query.getSingleResult();
        System.out.println(result);
        transaction.commit();
        em.close();
    }

    /**
     * 分页查询
     * sql:select * from customer limit ?,?;
     * jpql:from Customer
     */
    @Test
    public void testPage(){
        //根据工具类获取实体管理器entityManager
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        //完成业务操作
        String jpql = "from Customer";
        Query query = em.createQuery(jpql);     //创建query查询对象，Query对象才是执行jpql的对象
        //对参数赋值  分页参数
        query.setFirstResult(0);    //起始索引
        query.setMaxResults(2);     //每页条数
        //发送查询 并封装结果集
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        transaction.commit();
        em.close();
    }

    /**
     * 条件查询
     * sql:select * from customer where cust_name like ?
     * jpql:from Customer where custName like ?
     */
    @Test
    public void testCondition(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        String jpql = "from Customer where custName like ? ";
        Query query = entityManager.createQuery(jpql);
        //对参数赋值 占位符参数
        //param1：占位符索引位置 param2：取值
        query.setParameter(1,"王%");
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        tx.commit();
        entityManager.close();
    }

}
