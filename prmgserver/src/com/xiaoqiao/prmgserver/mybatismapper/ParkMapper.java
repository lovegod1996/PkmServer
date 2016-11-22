package com.xiaoqiao.prmgserver.mybatismapper;

import com.xiaoqiao.prmgserver.bean.*;

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
    //根据wifi名查找停车场
    public ParkDetail findParkByWifiname(String wifiName) throws  Exception;
    //根据用户名查询进场时间
    public String findInTimeByUsername(String username)throws  Exception;
    //进场操作
    public void getIntoP(Record record) throws  Exception;
    public void updateInParkingLot(String parkName) throws  Exception;
    //出场操作
    public  void updateFee(Record record) throws  Exception;
    public  void updateOutParkingLot(String parkName) throws  Exception;
   public void userPay(UserPay userPay) throws Exception;



}
