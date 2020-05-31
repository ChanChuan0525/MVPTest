package com.chanchuan.mvptest.model;

import com.chanchuan.frame.ApiConfig;
import com.chanchuan.frame.ICommonModel;
import com.chanchuan.frame.ICommonPresenter;
import com.chanchuan.frame.NetManager;

import java.util.Map;

public class TestModel implements ICommonModel {

    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        NetManager netManager = NetManager.getInstance();
        switch (whichApi) {
            case ApiConfig
                    .TEST_GET:
                int dateType = (int) params[0];
                Map param = (Map) params[1];
                int pageId = (int) params[2];
                netManager.netWork(netManager.getService().getInfoData(param, pageId), pPresenter, whichApi, dateType);
                break;
            case ApiConfig.ADVERT:
                break;
        }
    }
}
