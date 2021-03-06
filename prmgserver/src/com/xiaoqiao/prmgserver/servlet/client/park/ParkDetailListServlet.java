package com.xiaoqiao.prmgserver.servlet.client.park;

import com.xiaoqiao.prmgserver.bean.ParkDetail;
import com.xiaoqiao.prmgserver.bean.ParkName;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: lovegod
 * Created by 123 on 2016/12/22.
 */
@WebServlet(name = "ParkDetailListServlet",urlPatterns = {"/parkdetailist"})
public class ParkDetailListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        data(request,response);
    }

    private void data(HttpServletRequest request, HttpServletResponse response) {
        SqlSessionFactory sqlSessionFactory= SqlSessionUtil.getSqlSessionFactory();
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建ParkMapper对象，mybatis自动生成mapper代理对象
        ParkMapper parkMapper=sqlSession.getMapper(ParkMapper.class);
        List<ParkDetail> parkDetailList=new ArrayList<ParkDetail>();
        try {
            parkDetailList=parkMapper.findParkDetailList();
            //封装json
            Map<String,Object> data=new HashMap<String,Object>();
            data.put("response","parkDetaillist");
            data.put("parkDetailList",parkDetailList);

            CommonUtil.renderJson(response,data);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.commit();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
