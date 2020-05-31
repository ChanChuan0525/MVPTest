package com.chanchuan.frame;

public interface ICommonModel<T> {
    /**
     * 用于model层执行耗时任务，不处理刷新和加载逻辑
     *
     * @param pPresenter 区别接受的标识
     * @param whichApi   泛型可变参数
     * @param loadType
     */
    void getData(ICommonPresenter pPresenter, int whichApi, T... params);
}
