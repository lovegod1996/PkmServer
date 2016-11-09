package com.xiaoqiao.prmgserver.servlet;


import com.xiaoqiao.prmgserver.dao.HelpDaoImpl;
import com.xiaoqiao.prmgserver.util.CommonUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.SystemUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/7.
 */
@WebServlet(name = "HelpServlet",urlPatterns = {"/help"})
public class HelpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       data(request,response);
    }

    private void data(HttpServletRequest request, HttpServletResponse response) {
        //数据库查询
        int version=0;
        try {
            version = Integer.parseInt(request.getParameter("version"));
        }catch (Exception e){
            e.printStackTrace();
        }
        //数据库操作
        HelpDaoImpl daoimpl=new HelpDaoImpl();
        List<Map<String,Object>> helpList=daoimpl.getHelpList(version);
        Map<String,Object> data=new HashMap<String,Object>();
         data.put("response","help");
        data.put("version","12");
        data.put("helplist",helpList);


     /*   JSONObject object=JSONObject.fromObject(data);
        System.out.println(object.toString());
        response.getWriter().write(object.toString());
        response.getWriter().flush();*/
        CommonUtil.renderJson(response,data);

    }




}
