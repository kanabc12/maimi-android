package org.hxy.platform.android.common.network.request;


import org.hxy.platform.android.common.bean.UserInfoBean;
import org.hxy.platform.android.common.network.response.Response;

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
     * @param pass   密码
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Observable<Response<UserInfoBean>> login(@Field("mobile") String mobile, @Field("pass") String pass);

}
