package com.chanchuan.frame;

import com.chanchuan.data.InfoBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    @GET(".")
    Observable<InfoBean> getInfoData(@QueryMap Map<String, Object> params, @Query("page_id") int pageId);
}
