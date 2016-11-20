package com.xiaoqiao.prmgserver.servlet.client.account;

import com.xiaoqiao.prmgserver.bean.User;
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
 * Created by 123 on 2016/11/16.
 */
@WebServlet(name = "registerServlet",urlPatterns = {"/register"})
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
data(request,response);
    }

    private void data(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String username=request.getParameter("username");
        username=new String(username.getBytes("ISO-8859-1"),"UTF-8");

        String password=request.getParameter("password");
        password=new String(password.getBytes("ISO-8859-1"),"UTF-8");

        String name=request.getParameter("pname");
        name=new String(name.getBytes("ISO-8859-1"),"UTF-8");

        String carnum=request.getParameter("carnum");
        carnum=new String(carnum.getBytes("ISO-8859-1"),"UTF-8");

        User uu=new User();
        uu.setPnum(username);
        uu.setPasw(password);
        uu.setPname(name);
        uu.setCnum(carnum);

        SqlSessionFactory sqlSessionFactory= SqlSessionUtil.getSqlSessionFactory();
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建ParkMapper对象，mybatis自动生成mapper代理对象
        ParkMapper parkMapper=sqlSession.getMapper(ParkMapper.class);

        try {

            parkMapper.addUser(uu);

            Map<String,Object> data=new HashMap<String,Object>();
            data.put("response","register");
            data.put("registerState","true");
            data.put("user",uu);
            CommonUtil.renderJson(response,data);
        } catch (Exception e) {

            Map<String,Object> data=new HashMap<String,Object>();
            data.put("response","register");
            data.put("registerState","false");
            data.put("user",uu);
            CommonUtil.renderJson(response,data);

            e.printStackTrace();
        }finally {
            sqlSession.commit();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
