package com.chanchuan.frame;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManager {
    private NetManager() {
    }

    private static NetManager netManager;

    public static NetManager getInstance() {
        if (netManager == null) {
            synchronized (NetManager.class) {
                if (netManager == null) {
                    netManager = new NetManager();
                }
            }
        }
        return netManager;
    }

    public <T> ApiService getService(T... t) {
        String baseUrl = ServerAddressConfig.BASE_URL;
        if (t != null && t.length != 0) baseUrl = (String) t[0];
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class);
    }

    public <T, O> void netWork(Observable<T> localTestInfo, final ICommonPresenter pPresenter, final int whichApi, final int loadType, final O... os) {
        localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        pPresenter.addObserver(d);
                    }

                    @Override
                    protected void onSuccess(Object o) {
                        pPresenter.onSuccess(whichApi, loadType, o, os);
                    }

                    @Override
                    protected void onFailed(Throwable e) {
                        pPresenter.onFailed(whichApi, e);
                    }
                });
    }
}
