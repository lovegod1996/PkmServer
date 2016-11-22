package com.xiaoqiao.prmgserver.servlet.client.account;

import com.xiaoqiao.prmgserver.bean.UserPay;
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
 * Created by 123 on 2016/11/22.
 */
@WebServlet(name = "UserPayServlet",urlPatterns = {"/uerPay"})
public class UserPayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        data(request,response);
    }

    private void data(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String username=request.getParameter("username");
        username=new String(username.getBytes("ISO-8859-1"),"UTF-8");
        String pp=request.getParameter("pay");
        pp=new String(pp.getBytes("ISO-8859-1"),"UTF-8");
        Double pay=Double.parseDouble(pp);

        SqlSessionFactory sqlSessionFactory= SqlSessionUtil.getSqlSessionFactory();
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建ParkMapper对象，mybatis自动生成mapper代理对象
        ParkMapper parkMapper=sqlSession.getMapper(ParkMapper.class);

        UserPay uPay=new UserPay();
        uPay.setPnum(username);
        uPay.setPay(pay);

        try {
            parkMapper.userPay(uPay);
            Map<String,Object> data=new HashMap<String,Object>();
            data.put("response","Pay");
            data.put("pnum",username);
            data.put("money",pay);
            data.put("payState","true");
            CommonUtil.renderJson(response,data);

        } catch (Exception e) {
            Map<String,Object> data=new HashMap<String,Object>();
            data.put("response","Pay");
            data.put("pnum",username);
            data.put("money",pay);
            data.put("payState","false");
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
