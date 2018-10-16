package org.hxy.platform.android.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;


import com.alibaba.android.arouter.facade.annotation.Route;

import org.hxy.platform.android.common.base.mvp.BaseActivity;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Created by 25505 on 2017/7/31.
 */
@Route(path = "/index/secKill")
public class SecKillActivity  extends BaseActivity {

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {

    }
}
