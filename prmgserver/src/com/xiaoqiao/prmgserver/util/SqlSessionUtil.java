package com.xiaoqiao.prmgserver.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/9.
 */
public class SqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            // 创建sqlSessionFactory

            // mybatis配置文件
            String resource = "SqlMapConfig.xml";
            // 得到配置文件流
            InputStream inputStream = Resources.getResourceAsStream(resource);

            // 创建会话工厂，传入mybatis的配置文件信息
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
   public  static SqlSessionFactory getSqlSessionFactory(){
       return  sqlSessionFactory;
   }
}
