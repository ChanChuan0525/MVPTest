package com.chanchuan.mvptest.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chanchuan.frame.LoadTypeConfig;
import com.chanchuan.mvptest.interfaces.DataListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract int setLayoutId();

    public void initRecyclerView(RecyclerView pRecyclerView, SmartRefreshLayout pRefreshLayout, final DataListener pDataListener) {
        if (pRecyclerView != null) pRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (pRefreshLayout != null && pDataListener != null) {
            pRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    pDataListener.dataType(LoadTypeConfig.MORE);
                }

                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    pDataListener.dataType(LoadTypeConfig.REFRESH);
                }
            });
        }
    }

    public void showLog(Object content) {
        Log.e("陳川", content.toString());
    }

    public void showToast(Object content) {
        Toast.makeText(this, content.toString(), Toast.LENGTH_SHORT).show();
    }
}
