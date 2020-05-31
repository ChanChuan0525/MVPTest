package com.chanchuan.frame;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class CommonPresenter<V extends ICommonView, M extends ICommonModel> implements ICommonPresenter {


    private SoftReference<V> mView;
    private SoftReference<M> mModel;
    private List<Disposable> mDisposableList;

    public CommonPresenter(V pView, M pModel) {
        mDisposableList = new ArrayList<>();
        mView = new SoftReference<>(pView);
        mModel = new SoftReference<>(pModel);
    }

    @Override
    public void getData(int whichApi, Object... pPS) {
        if (mModel != null && mModel.get() != null) mModel.get().getData(this, whichApi, pPS);
    }

    @Override
    public void addObserver(Disposable pDisposable) {
        if (mDisposableList == null) return;
        mDisposableList.add(pDisposable);
    }

    @Override
    public void onSuccess(int whichApi, int loadType, Object... pD) {
        if (mView != null && mView.get() != null) mView.get().onSuccess(whichApi, loadType, pD);
    }

    @Override
    public void onFailed(int whichApi, Throwable pThrowable) {
        if (mView != null && mView.get() != null) mView.get().onFailed(whichApi, pThrowable);
    }

    public void clear() {
        for (Disposable disposable : mDisposableList) {
            if (disposable != null && !disposable.isDisposed()) disposable.dispose();
        }
        if (mView != null) {
            mView.clear();
            mView = null;
        }
        if (mModel != null) {
            mModel.clear();
            mModel = null;
        }
    }
}
