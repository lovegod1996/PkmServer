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
@WebServlet(name = "LoginServlet" ,urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
data(request,response);
    }

    private void data(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String username=request.getParameter("username");
        username=new String(username.getBytes("ISO-8859-1"),"UTF-8");

        String password=request.getParameter("password");
        password=new String(password.getBytes("ISO-8859-1"),"UTF-8");
        User loginUser=new User();
        loginUser.setPnum(username);
        loginUser.setPasw(password);

        SqlSessionFactory sqlSessionFactory= SqlSessionUtil.getSqlSessionFactory();
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建ParkMapper对象，mybatis自动生成mapper代理对象
        ParkMapper parkMapper=sqlSession.getMapper(ParkMapper.class);

        try {
            User user=parkMapper.findUserByUnameAndPas(loginUser);
            if(user!=null){
                Map<String,Object> data=new HashMap<String,Object>();
                data.put("response","login");
                data.put("loginState","true");

                CommonUtil.renderJson(response,data);
            }else{
                Map<String,Object> data=new HashMap<String,Object>();
                data.put("response","login");
                data.put("loginState","false");

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
