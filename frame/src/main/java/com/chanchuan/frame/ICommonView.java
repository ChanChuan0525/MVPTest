package com.chanchuan.frame;

public interface ICommonView<D> {
    /**
     * 成功回调
     *
     * @param whichApi 成功接口的标识 那个接口成功了
     * @param loadType 类型的回调，（正常加载，刷新，加载更多）
     * @param pD       一般实体类的回调，为了框架的灵活性，确保其他的一些数据的偶发性回调，所以没有将长度写死
     */
    void onSuccess(int whichApi, int loadType, D... pD);

    /**
     * 失败的回调
     *
     * @param whichApi   是哪个接口的回调
     * @param pThrowable 失败的具体描述
     */
    void onFailed(int whichApi, Throwable pThrowable);

}
