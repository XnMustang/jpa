package com.wangjun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @Description: 客户的实体类
 * @Author : 王俊
 * @date :  2020/11/2
 */

/**
 * 配置映射关系：
 *      1.实体类和表的映射关系
 *      2.实体类中属性和表中字段的映射关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity //声明是一个实体类
@Table(name = "cst_customer")  //配置实体类和表的映射
public class Customer {
    /**
     *  @Id:声明主键的配置
     *  @GeneratedValue:配置主键生成策略
     *      strategy
     *          GenerationType.IDENTITY：自增(底层数据库必须支持自动增长)
     *          GenerationType.SEQUENCE：序列，Oracle（底层数据库必须支持序列）
     *          GenerationType.TABLE：jpa提供的一种机制，通过一张数据库表的形式帮助我们完成主键自增
     *          GenerationType.AUTO:程序自动的选择主键生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;        //主键

    @Column(name = "cust_name")
    private String custName;    //名称

    @Column(name = "cust_source")
    private String custSource;  //客户来源

    @Column(name = "cust_level")
    private String custLevel;   //客户级别

    @Column(name = "cust_industry")
    private String custIndustry;//客户所属行业

    @Column(name = "cust_phone")
    private String custPhone;   //联系方式

    @Column(name = "cust_address")
    private String custAddress; //地址
}
