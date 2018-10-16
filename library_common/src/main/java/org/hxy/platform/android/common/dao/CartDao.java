package org.hxy.platform.android.common.dao;

import org.hxy.platform.android.common.application.BaseApplication;
import org.hxy.platform.android.common.entity.GoodsOrderInfoBean;

import java.util.List;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/16 9:40
 * desc   :
 * version: 1.0
 */
public class CartDao {
    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param orderInfoBean
     */
    public static void insertCart(GoodsOrderInfoBean orderInfoBean) {
        BaseApplication.getDaoInstant().getGoodsOrderInfoBeanDao().insertOrReplace(orderInfoBean);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteCart(long id) {
        BaseApplication.getDaoInstant().getGoodsOrderInfoBeanDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param orderInfoBean
     */
    public static void updateCart(GoodsOrderInfoBean orderInfoBean) {
        BaseApplication.getDaoInstant().getGoodsOrderInfoBeanDao().update(orderInfoBean);
    }

    /* *//**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     *//*
    public static List<GoodsOrderInfoBean> queryLove() {
        return JDMallApplication.getDaoInstant().getGoodsOrderInfoBeanDao().queryBuilder().where(ShopDao.Properties.Type.eq(GoodsOrderInfoBean.TYPE_LOVE)).list();
    }*/

    /**
     * 查询全部数据
     */
    public static List<GoodsOrderInfoBean> queryAll() {
        return BaseApplication.getDaoInstant().getGoodsOrderInfoBeanDao().loadAll();
    }
    /**
     * 通过id查询商品
     */
    public static GoodsOrderInfoBean queryGood(long id){
        return BaseApplication.getDaoInstant().getGoodsOrderInfoBeanDao().loadByRowId(id);
    }
}
