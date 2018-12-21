package asus.com.bwie.chenxiaoyang1221.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import asus.com.bwie.chenxiaoyang1221.R;
import asus.com.bwie.chenxiaoyang1221.bean.GWCBean;
import asus.com.bwie.chenxiaoyang1221.bean.ShopBean;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private List<GWCBean.DataBean> mData=new ArrayList<>();
    private Context mContext;
    private boolean isLinear;

    public ShopAdapter(Context mContext, boolean isLinear) {
        this.mContext = mContext;
        this.isLinear = isLinear;
    }

    public void setmData(List<GWCBean.DataBean> mDatas) {
        this.mData = mDatas;
        notifyDataSetChanged();
    }
    public void delmData(List<GWCBean.DataBean> mDatas) {
        mData.remove(mDatas);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (isLinear){
            View view = View.inflate(mContext, R.layout.recycle_line, null);
            return new ViewHolder(view);
        }else {
            View view = View.inflate(mContext, R.layout.recycle_grid, null);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        GWCBean.DataBean dataBean = mData.get(position);
        String images = mData.get(position).getImages();
        String replace = images.split("\\|")[0].replace("https", "http");
        Glide.with(mContext).load(replace).into(holder.shop_image);
        holder.shop_name.setText(mData.get(position).getTitle());
        holder.shop_price.setText("￥"+mData.get(position).getPrice());
        holder.recycler.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longItemClick!=null){
                    longItemClick.onLongItemListen(position);
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView shop_image;
        private final TextView shop_name;
        private final TextView shop_price;
        private final LinearLayout recycler;

        public ViewHolder(View itemView) {
            super(itemView);

            shop_image = itemView.findViewById(R.id.line_shop_image);
            shop_name = itemView.findViewById(R.id.line_shop_name);
            shop_price = itemView.findViewById(R.id.line_shop_price);
            recycler = itemView.findViewById(R.id.recycle);
        }
    }
    public onLongItemClick longItemClick;

    public void setLongItemClick(onLongItemClick longItemClicks) {
        longItemClick = longItemClicks;
    }

    public interface onLongItemClick{
        void onLongItemListen(int position);
    }

}
