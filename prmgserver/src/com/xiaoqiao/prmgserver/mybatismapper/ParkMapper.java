package com.xiaoqiao.prmgserver.mybatismapper;

import com.xiaoqiao.prmgserver.bean.ParkDetail;
import com.xiaoqiao.prmgserver.bean.ParkName;

import java.util.List;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/9.
 */
public interface ParkMapper {
     //查询所有停车场的名称
    public List<ParkName> findParkList() throws Exception;
    //根据停车场名获取停车场详细信息
    public ParkDetail findParkDetailByPname(String Pname) throws  Exception;

}
