package com.chanchuan.mvptest.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chanchuan.data.InfoBean;
import com.chanchuan.mvptest.R;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private List<InfoBean.DataBean> datas = new ArrayList<>();
    private Context mContext;

    public TestAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setDatas(List<InfoBean.DataBean> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void refreshDatas(List<InfoBean.DataBean> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.test_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InfoBean.DataBean dataInfo = datas.get(position);
        holder.title.setText(dataInfo.title);
        Glide.with(mContext).load(dataInfo.thumbnail).into(holder.leftImage);
        holder.desc.setText(!TextUtils.isEmpty(dataInfo.description) ? dataInfo.description : !TextUtils.isEmpty(dataInfo.author) ? dataInfo.author : dataInfo.title);
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView leftImage;
        TextView title;
        TextView desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            leftImage = itemView.findViewById(R.id.left_image);
            title = itemView.findViewById(R.id.title_content);
            desc = itemView.findViewById(R.id.desc_content);
        }
    }
}
