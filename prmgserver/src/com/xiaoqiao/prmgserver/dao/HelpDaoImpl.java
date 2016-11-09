package com.xiaoqiao.prmgserver.dao;


import com.xiaoqiao.prmgserver.util.DataSourceManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 获取帮助信息
 * Author: lovegod
 * Created by 123 on 2016/11/7.
 */
public class HelpDaoImpl {
    private QueryRunner runner=new QueryRunner(DataSourceManager.getDataSource());
    /**
     * 获取帮助列表
     * @param version
     */
    public List<Map<String ,Object>> getHelpList(int version){
        //dbutil

        //配置信息

        //查询Queryruuner
        String sql="select  name,money from account where  id  >?";
        try {
        return     runner.query(sql,new MapListHandler(),version);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
