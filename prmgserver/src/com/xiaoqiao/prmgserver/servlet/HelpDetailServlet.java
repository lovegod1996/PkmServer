package com.xiaoqiao.prmgserver.servlet;



import com.xiaoqiao.prmgserver.bean.HelpDetail;
import com.xiaoqiao.prmgserver.util.CommonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/7.
 */
@WebServlet(name = "HelpDetailServlet" ,urlPatterns = {"/helpdetail"})
public class HelpDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          data(request,response);
    }

    private void data(HttpServletRequest request, HttpServletResponse response) {
        //拼装数据
        List<HelpDetail> details=new ArrayList<HelpDetail>();

        HelpDetail detail=new HelpDetail();
       detail.setTitle("怎么购买？");
        detail.setContent("不知道");
        details.add(detail);

        HelpDetail detail2=new HelpDetail();
        detail2.setTitle("怎么购买2？");
        detail2.setContent("不知道2");
        details.add(detail2);

        Map<String ,Object> data=new java.util.HashMap<String,Object>();
   data.put("response","help_detail");
        data.put("help_detaillist",details);

        CommonUtil.renderJson(response,data);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                       doPost(request,response);
    }
}
