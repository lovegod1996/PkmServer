package com.xiaoqiao.prmgserver.test;


import com.xiaoqiao.prmgserver.dao.HelpDaoImpl;


import java.util.List;
import java.util.Map;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/7.
 */
public class HelpTest {

    public void testGetHelpList(){
        HelpDaoImpl daoimpl=new HelpDaoImpl();
        List<Map<String ,Object>> helpList=daoimpl.getHelpList(0);
       System.out.println(helpList);
    }
}
