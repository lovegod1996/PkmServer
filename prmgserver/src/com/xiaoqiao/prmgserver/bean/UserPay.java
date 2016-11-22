package com.xiaoqiao.prmgserver.bean;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/22.
 */
public class UserPay {
    private double Pay;
    private String Pnum;

    public double getPay() {
        return Pay;
    }

    public void setPay(double pay) {
        Pay = pay;
    }

    public String getPnum() {
        return Pnum;
    }

    public void setPnum(String pnum) {
        Pnum = pnum;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
