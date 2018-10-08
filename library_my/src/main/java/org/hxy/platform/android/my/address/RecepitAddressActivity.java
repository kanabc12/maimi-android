package org.hxy.platform.android.my.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.hxy.platform.android.common.base.mvp.BaseActivity;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.my.R;
import org.hxy.platform.android.my.R2;
import org.hxy.platform.android.my.address.adapter.AddressRvAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date：2018/9/15 on 16:37
 * Author: kanabc12@126.com
 * Description:
 */
@Route(path = "/my/address")
public class RecepitAddressActivity extends BaseActivity {

    private static final String TAG = "RecepitAddressActivity";
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.rv_receipt_address)
    RecyclerView mRvReceiptAddress;
    @BindView(R2.id.bt_add_address)
    Button mBtAddAddress;
    private AddressRvAdapter mAddressRvAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepit_address);
        ButterKnife.bind(this);
        mRvReceiptAddress.setLayoutManager(new LinearLayoutManager(this));
        mAddressRvAdapter = new AddressRvAdapter(this);
        mRvReceiptAddress.setAdapter(mAddressRvAdapter);
    }

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

    @OnClick({R2.id.iv_back, R2.id.bt_add_address})
    public void onClick(View view) {
        int result = view.getId();
        if(result == R.id.iv_back){
            finish();
        }else {
            Intent intent = new Intent(this, AddRecepitAddressActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}
