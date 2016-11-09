package com.xiaoqiao.prmgserver.util;


import org.apache.commons.dbcp.BasicDataSourceFactory;


import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/7.
 */
public class DataSourceManager {
    private static DataSource ds;
    static {
        try{
            InputStream in=DataSourceManager.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            //使用dbcp实现的数据源
            Properties props=new Properties();
            props.load(in);
            ds= BasicDataSourceFactory.createDataSource(props);
        }catch (Exception e){
            e.printStackTrace();
            throw  new ExceptionInInitializerError();

        }
    }
    public static DataSource getDataSource(){
        return  ds;
    }

}
