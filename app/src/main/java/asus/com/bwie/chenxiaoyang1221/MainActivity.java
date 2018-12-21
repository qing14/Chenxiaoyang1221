package asus.com.bwie.chenxiaoyang1221;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import asus.com.bwie.chenxiaoyang1221.fragment.YD1;
import asus.com.bwie.chenxiaoyang1221.fragment.YD2;
import asus.com.bwie.chenxiaoyang1221.fragment.YD3;
import asus.com.bwie.chenxiaoyang1221.fragment.YD4;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vp = findViewById(R.id.vp);

        LayoutInflater inflater = getLayoutInflater();

        View view1 = inflater.inflate(R.layout.yd1_layout, null);
        View view2 = inflater.inflate(R.layout.yd2_layout, null);
        View view3 = inflater.inflate(R.layout.yd3_layout, null);
        View view4 = inflater.inflate(R.layout.yd4_layout, null);

        final ArrayList<View> pageView=new ArrayList<>();

        pageView.add(view1);
        pageView.add(view2);
        pageView.add(view3);
        pageView.add(view4);
        PagerAdapter pagerAdapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return pageView.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(pageView.get(position));

            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                container.addView(pageView.get(position));
                return pageView.get(position);
            }
        };
        vp.setAdapter(pagerAdapter);
        view4.findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,TwoActivity.class);
                startActivity(intent);
            }
        });
    }
}
