package com.xiaoqiao.prmgserver.servlet.client.record;

import com.xiaoqiao.prmgserver.bean.Record;
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
@WebServlet(name = "UpdateFeeServlet",urlPatterns = {"/updatefee"})
public class UpdateFeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
data(request,response);
    }

    private void data(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String username=request.getParameter("username");
        username=new String(username.getBytes("ISO-8859-1"),"UTF-8");
        String pay=request.getParameter("pay");
        pay=new String(pay.getBytes("ISO-8859-1"),"UTF-8");
        String outtime=request.getParameter("outtime");
        outtime=new String(outtime.getBytes("ISO-8859-1"),"UTF-8");
        System.out.println(username+"   "+pay+"   "+outtime);
        Record record=new Record();
        record.setUnum(username);
        record.setOuttime(outtime);
        record.setPay(pay);

        SqlSessionFactory sqlSessionFactory= SqlSessionUtil.getSqlSessionFactory();
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建ParkMapper对象，mybatis自动生成mapper代理对象
        ParkMapper parkMapper=sqlSession.getMapper(ParkMapper.class);


        try {
            parkMapper.updateFee(record);
            Map<String,Object> data1=new HashMap<String,Object>();
            data1.put("response","getout");
            data1.put("username",username);
            data1.put("outtime",outtime);
            data1.put("updateState","true");
            CommonUtil.renderJson(response,data1);

        } catch (Exception e) {
            Map<String,Object> data=new HashMap<String,Object>();
            data.put("response","getout");
            data.put("username",username);
            data.put("outtime",outtime);
            data.put("updateState","false");
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
