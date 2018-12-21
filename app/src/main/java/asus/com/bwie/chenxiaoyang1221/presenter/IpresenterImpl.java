package asus.com.bwie.chenxiaoyang1221.presenter;

import java.util.Map;

import asus.com.bwie.chenxiaoyang1221.callback.MyCallBack;
import asus.com.bwie.chenxiaoyang1221.model.ImodelImpl;
import asus.com.bwie.chenxiaoyang1221.view.IView;
//P层实现类
public class IpresenterImpl implements Ipresenter {

    private IView mIvew;
    private ImodelImpl mImodel;

    public IpresenterImpl(IView mIvew) {
        this.mIvew = mIvew;
        mImodel=new ImodelImpl();
    }

    @Override
    public void startRequest(String urlData, Map<String, String> map, Class clazz) {
        mImodel.startRequestData(urlData, map, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object obj) {
                mIvew.onSuccessData(obj);
            }

            @Override
            public void onFail(Exception e) {
                mIvew.onFail(e);
            }
        });
    }
    public void detach(){
        mImodel=null;
        mIvew=null;
    }
}
