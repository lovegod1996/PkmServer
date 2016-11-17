package com.xiaoqiao.prmgserver.mybatismapper;

import com.xiaoqiao.prmgserver.bean.ParkDetail;
import com.xiaoqiao.prmgserver.bean.ParkName;
import com.xiaoqiao.prmgserver.bean.Record;
import com.xiaoqiao.prmgserver.bean.User;

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
    //根据用户名和密码获取登陆信息
    public User findUserByUnameAndPas(User user) throws  Exception;
    //根据用户名查找用户信息
    public User findUserByName(String name) throws  Exception;
    //添加用户信息
    public void addUser(User user) throws  Exception;
    //根据用户名查找停车记录
    public List<Record> findRecordByUsername(String Uname) throws  Exception;
}
