package com.chanchuan.mvptest;

import androidx.recyclerview.widget.RecyclerView;

import com.chanchuan.data.InfoBean;
import com.chanchuan.frame.ApiConfig;
import com.chanchuan.frame.LoadTypeConfig;
import com.chanchuan.frame.ParamsHashMap;
import com.chanchuan.mvptest.adapter.TestAdapter;
import com.chanchuan.mvptest.base.BaseMVPActivity;
import com.chanchuan.mvptest.interfaces.DataListener;
import com.chanchuan.mvptest.model.TestModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseMVPActivity<TestModel> {
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    private ParamsHashMap paramsHashMap;
    int pageId = 0;
    private TestAdapter testAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected TestModel setModel() {
        return new TestModel();
    }

    @Override
    protected void setUpView() {
        paramsHashMap = new ParamsHashMap().add("c", "api").add("a", "getList");
        initRecyclerView(mRecyclerView, mRefresh, new DataListener() {
            @Override
            public void dataType(int mode) {
                if (mode == LoadTypeConfig.REFRESH) {
                    pageId = 0;
                    mPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.REFRESH, paramsHashMap, pageId);
                } else {
                    pageId++;
                    mPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.MORE, paramsHashMap, pageId);
                }
            }
        });
        testAdapter = new TestAdapter(this);
        mRecyclerView.setAdapter(testAdapter);
    }

    @Override
    protected void setUpData() {
        mPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.NORMAL, paramsHashMap, pageId);
    }

    @Override
    protected void netSuccess(int whichApi, int loadType, Object[] pD) {
        List<InfoBean.DataBean> datas = ((InfoBean) pD[0]).datas;
        switch (whichApi) {
            case ApiConfig.TEST_GET:
                if (loadType == LoadTypeConfig.REFRESH) {
                    mRefresh.finishRefresh();
                    testAdapter.refreshDatas(datas);
                } else if (loadType == LoadTypeConfig.MORE) {
                    mRefresh.finishLoadMore();
                    testAdapter.setDatas(datas);
                } else {
                    testAdapter.setDatas(datas);
                }
                break;
        }
    }
}
