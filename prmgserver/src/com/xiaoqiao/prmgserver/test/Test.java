package com.xiaoqiao.prmgserver.test;

import com.xiaoqiao.prmgserver.bean.ParkDetail;
import com.xiaoqiao.prmgserver.bean.ParkName;
import com.xiaoqiao.prmgserver.mybatismapper.ParkMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.InputStream;
import java.util.List;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/9.
 */
public class Test {

    private SqlSessionFactory sqlSessionFactory;

    // 此方法是在执行testFindUserById之前执行
    @Before
    public void setUp() throws Exception {
        // 创建sqlSessionFactory

        // mybatis配置文件
        String resource = "SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 创建会话工厂，传入mybatis的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
    }
    @org.junit.Test
    public void testFindParkList() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建ParkMapper对象，mybatis自动生成mapper代理对象
        ParkMapper parkMapper=sqlSession.getMapper(ParkMapper.class);
        List<ParkName> parkList=parkMapper.findParkList();
        for (int i = 0; i <parkList.size() ; i++) {
          System.out.println(parkList.get(i));
        }
    }
    @org.junit.Test
    public void testFindParkDetailByPname() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建ParkMapper对象，mybatis自动生成mapper代理对象
        ParkMapper parkMapper=sqlSession.getMapper(ParkMapper.class);
        ParkDetail parkDetail=parkMapper.findParkDetailByPname("郑州万达停车场");
        System.out.println(parkDetail);
    }

}
