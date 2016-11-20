package com.xiaoqiao.prmgserver.servlet.client.park;

import com.xiaoqiao.prmgserver.bean.ParkDetail;
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
@WebServlet(name = "FindParkByWifiNameServlet",urlPatterns = {"/findParkByWifiName"})
public class FindParkByWifiNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
data(request,response);
    }

    private void data(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String wifiname=request.getParameter("wifiname");
        wifiname=new String(wifiname.getBytes("ISO-8859-1"),"UTF-8");

        SqlSessionFactory sqlSessionFactory= SqlSessionUtil.getSqlSessionFactory();
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建ParkMapper对象，mybatis自动生成mapper代理对象
        ParkMapper parkMapper=sqlSession.getMapper(ParkMapper.class);

        try {
            ParkDetail parkDetail=parkMapper.findParkByWifiname(wifiname);

            Map<String,Object> data=new HashMap<String,Object>();
            data.put("response","findnamebywifiname");
            data.put("wifiname",wifiname);
            data.put("parkdetail",parkDetail);

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
