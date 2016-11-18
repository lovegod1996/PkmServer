package com.xiaoqiao.prmgserver.servlet.client.record;

import com.xiaoqiao.prmgserver.mybatismapper.ParkMapper;
import com.xiaoqiao.prmgserver.util.CommonUtil;
import com.xiaoqiao.prmgserver.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/18.
 */
@WebServlet(name = "getIntimeServlet",urlPatterns = {"/getIntime"})
public class getIntimeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
data(request,response);
    }

    private void data(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String username=request.getParameter("username");
        username=new String(username.getBytes("ISO-8859-1"),"UTF-8");

        SqlSessionFactory sqlSessionFactory= SqlSessionUtil.getSqlSessionFactory();
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建ParkMapper对象，mybatis自动生成mapper代理对象
        ParkMapper parkMapper=sqlSession.getMapper(ParkMapper.class);

        try {
            String inTime=parkMapper.findInTimeByUsername(username);
            if(inTime==null){
                Map<String,Object> data1=new HashMap<String,Object>();
                data1.put("response","findInTime");
                data1.put("username",username);
                data1.put("intime","null");

                CommonUtil.renderJson(response,data1);
            }else{
                Map<String,Object> data=new HashMap<String,Object>();
                data.put("response","findInTime");
                data.put("username",username);
                data.put("intime",inTime);

                CommonUtil.renderJson(response,data);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
