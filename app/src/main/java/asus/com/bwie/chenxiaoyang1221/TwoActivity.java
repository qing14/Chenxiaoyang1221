package asus.com.bwie.chenxiaoyang1221;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asus.com.bwie.chenxiaoyang1221.adapter.JGGAdapter;
import asus.com.bwie.chenxiaoyang1221.adapter.ShopAdapter;
import asus.com.bwie.chenxiaoyang1221.bean.GWCBean;
import asus.com.bwie.chenxiaoyang1221.bean.JGGBean;
import asus.com.bwie.chenxiaoyang1221.bean.ShopBean;
import asus.com.bwie.chenxiaoyang1221.presenter.Ipresenter;
import asus.com.bwie.chenxiaoyang1221.presenter.IpresenterImpl;
import asus.com.bwie.chenxiaoyang1221.view.IView;

public class TwoActivity extends AppCompatActivity implements IView {

    private ImageView erweima;
    private ImageView qhLayout;
    private RecyclerView jggRecycle;
    private IpresenterImpl ipresenter;
    private boolean isLinear=true;
    private int wgCount=2;

    private int jggCount=7;
    private RecyclerView shopRecycle;
    private ShopAdapter shopAdapter;
    private JGGAdapter jggAdapter;
    private GWCBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        erweima = findViewById(R.id.ErWeiMa);
        qhLayout = findViewById(R.id.qhLayout);
        jggRecycle = findViewById(R.id.JGGRecycle);
        shopRecycle = findViewById(R.id.shopRecycle);
        ipresenter = new IpresenterImpl(this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,jggCount);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        jggRecycle.setLayoutManager(gridLayoutManager);
        Map<String,String> map=new HashMap<>();
        ipresenter.startRequest(Apis.jggPath,map,JGGBean.class);
        jggAdapter = new JGGAdapter(this);
        jggRecycle.setAdapter(jggAdapter);
        getLoader();
        isLayout();


        findViewById(R.id.qhLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLayout();
            }
        });

        shopAdapter = new ShopAdapter(this,isLinear);
        shopRecycle.setAdapter(shopAdapter);

        erweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (ContextCompat.checkSelfPermission(TwoActivity.this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){

                        ActivityCompat.requestPermissions(TwoActivity.this,new String[]{Manifest.permission.CAMERA},100);

                    }else {
                        Intent intent=new Intent(TwoActivity.this,ZXingActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
        shopAdapter.setLongItemClick(new ShopAdapter.onLongItemClick() {
            @Override
            public void onLongItemListen(int position) {
                shopAdapter.delmData(bean.getData());
            }
        });


    }

    private void getLoader() {
        Map<String,String> map=new HashMap<>();
        map.put("keywords","笔记本");
        map.put("UID","71");
        ipresenter.startRequest(Apis.GWCPath,map,GWCBean.class);
    }

    private void isLayout() {
        if (isLinear){
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
            shopRecycle.setLayoutManager(linearLayoutManager);
            qhLayout.setImageResource(R.drawable.wg);
        }else {
            GridLayoutManager gridLayoutManager=new GridLayoutManager(this,wgCount);
            shopRecycle.setLayoutManager(gridLayoutManager);
            qhLayout.setImageResource(R.drawable.xx);
            }
        shopAdapter = new ShopAdapter(this,isLinear);
        shopRecycle.setAdapter(shopAdapter);
        isLinear=!isLinear;
        getLoader();
    }

    @Override
    public void onSuccessData(Object obj) {
        if (obj instanceof GWCBean){
            bean = (GWCBean) obj;
            shopAdapter.setmData(bean.getData());
        }
        if (obj instanceof JGGBean){
            JGGBean bean= (JGGBean) obj;
            jggAdapter.setList(bean.getData());
        }
    }

    @Override
    public void onFail(Exception e) {

    }
}
