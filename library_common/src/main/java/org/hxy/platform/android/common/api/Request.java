package org.hxy.platform.android.common.api;


import org.hxy.platform.android.common.bean.CartInfoBean;
import org.hxy.platform.android.common.bean.CollectInfoBean;
import org.hxy.platform.android.common.bean.CommentInfoBean;
import org.hxy.platform.android.common.bean.ProductDesInfoBean;
import org.hxy.platform.android.common.bean.ProductInfoBean;
import org.hxy.platform.android.common.bean.UserInfoBean;
import org.hxy.platform.android.common.entity.CategoryBean;
import org.hxy.platform.android.common.entity.ProductCatagoryBean;
import org.hxy.platform.android.common.network.response.Response;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    //商品详情
    @GET("product")
    Observable<ProductInfoBean> listProductInfo(@Query("pId") int id);

    //商品评论
    @GET("product/comment")
    Observable<CommentInfoBean> listComment(@Query("pId") int id, @Query("page") int page, @Query("pageNum") int pageNum);

    @GET("product/description")
    Observable<ProductDesInfoBean> listProductDes(@Query("pId") int id);

    @GET("product/favorites")
    Observable<CollectInfoBean> listCollect(@Header("userid") String userid, @Query("pId") int id);


    //购物车
    @FormUrlEncoded
    @POST("cart")
    Observable<CartInfoBean> listCart(@Field("sku") String test);

}
