package asus.com.bwie.chenxiaoyang1221.model;

import java.util.Map;

import asus.com.bwie.chenxiaoyang1221.callback.MyCallBack;
import asus.com.bwie.chenxiaoyang1221.okhttp.OkhttpUtils;
//M层实现类
public class ImodelImpl implements Imodel {

    @Override
    public void startRequestData(String urlData, Map<String, String> map, Class clazz, final MyCallBack myCallBack) {
        OkhttpUtils.getOkhttpUtils().getEneuque(urlData, map, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object obj) {
                myCallBack.onSuccess(obj);
            }

            @Override
            public void onFail(Exception e) {
                myCallBack.onFail(e);
            }
        });

    }
}
