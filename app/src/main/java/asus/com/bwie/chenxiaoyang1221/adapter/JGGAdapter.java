package asus.com.bwie.chenxiaoyang1221.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import asus.com.bwie.chenxiaoyang1221.R;
import asus.com.bwie.chenxiaoyang1221.bean.JGGBean;

public class JGGAdapter extends RecyclerView.Adapter<JGGAdapter.ViewHolder> {

    private Context context;
    private List<JGGBean.DataBean> list=new ArrayList<>();

    public JGGAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void setList(List<JGGBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.jgg_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JGGBean.DataBean dataBean = list.get(position);
        Glide.with(context).load(list.get(position).getIcon()).into(holder.jgg_image);
        holder.jgg_text.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView jgg_image;
        private final TextView jgg_text;

        public ViewHolder(View itemView) {
            super(itemView);
            jgg_image = itemView.findViewById(R.id.jgg_image);
            jgg_text = itemView.findViewById(R.id.jgg_text);

        }
    }
}
