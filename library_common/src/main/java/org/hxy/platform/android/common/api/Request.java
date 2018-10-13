package org.hxy.platform.android.common.api;


import org.hxy.platform.android.common.bean.UserInfoBean;
import org.hxy.platform.android.common.entity.CategoryBean;
import org.hxy.platform.android.common.entity.ProductCatagoryBean;
import org.hxy.platform.android.common.network.response.Response;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Zaifeng on 2018/2/28.
 * 封装请求的接口
 */

public interface Request {


    /**
     * 账密登录
     * eg:http://localhost:8080/web/Login
     * @param mobile 手机号码
     * @param password   密码
     * @return
     */
    @FormUrlEncoded
    @POST("auth/login")
    Observable<Response<UserInfoBean>> login(@Field("mobile") String mobile, @Field("password") String password);

    /**
     * 获取产品分类
     * eg:http://localhost:8080/web/Login
     * @return
     */
    @POST("getCatagroy")
    Observable<Response<List<CategoryBean>>> getCatagroy();

    /**
     *
     * @param cid
     * @return
     */
    @POST("getProductCatagory")
    Observable<Response<List<ProductCatagoryBean>>> getProductCatagory(@Field("cid") String cid);





}
