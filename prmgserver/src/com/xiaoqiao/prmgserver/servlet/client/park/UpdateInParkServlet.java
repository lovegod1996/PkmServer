package com.xiaoqiao.prmgserver.servlet.client.park;

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
@WebServlet(name = "UpdateInParkServlet",urlPatterns = {"/updatein"})
public class UpdateInParkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        data(request,response);
    }

    private void data(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String parkname=request.getParameter("parkname");
        parkname=new String(parkname.getBytes("ISO-8859-1"),"UTF-8");

        SqlSessionFactory sqlSessionFactory= SqlSessionUtil.getSqlSessionFactory();
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建ParkMapper对象，mybatis自动生成mapper代理对象
        ParkMapper parkMapper=sqlSession.getMapper(ParkMapper.class);

        try {
            parkMapper.updateInParkingLot(parkname);
            Map<String,Object> data1=new HashMap<String,Object>();
            data1.put("response","updateinpark");
            data1.put("username",parkname);
            data1.put("updateState","true");

            CommonUtil.renderJson(response,data1);

        } catch (Exception e) {
            Map<String,Object> data1=new HashMap<String,Object>();
            data1.put("response","updateinpark");
            data1.put("username",parkname);
            data1.put("updateState","false");

            CommonUtil.renderJson(response,data1);
            e.printStackTrace();
        }finally {
            sqlSession.commit();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
