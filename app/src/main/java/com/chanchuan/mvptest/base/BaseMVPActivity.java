package com.chanchuan.mvptest.base;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.chanchuan.frame.CommonPresenter;
import com.chanchuan.frame.ICommonModel;
import com.chanchuan.frame.ICommonView;

import butterknife.ButterKnife;

public abstract class BaseMVPActivity<M extends ICommonModel> extends BaseActivity implements ICommonView {
    public M mModel;
    public CommonPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        mModel = setModel();
        mPresenter = new CommonPresenter(this, mModel);
        setUpView();
        setUpData();
    }


    protected abstract M setModel();
    protected abstract void setUpView();
    protected abstract void setUpData();


    @Override
    public void onSuccess(int whichApi, int loadType, Object[] pD) {
        netSuccess(whichApi, loadType, pD);
    }

    protected abstract void netSuccess(int whichApi, int loadType, Object[] pD);

    @Override
    public void onFailed(int whichApi, Throwable pThrowable) {
        showLog("net work error" + whichApi + "error content" + pThrowable != null && !TextUtils.isEmpty(pThrowable.getMessage()) ? pThrowable.getMessage() : "不明错误类型");
        netFailed(whichApi, pThrowable);
    }

    private void netFailed(int whichApi, Throwable pThrowable) {
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.clear();
    }
}
