package asus.com.bwie.chenxiaoyang1221.model;

import java.util.Map;

import asus.com.bwie.chenxiaoyang1221.callback.MyCallBack;
//M层
public interface Imodel {

    void startRequestData(String urlData, Map<String,String> map, Class clazz, MyCallBack myCallBack);


}
