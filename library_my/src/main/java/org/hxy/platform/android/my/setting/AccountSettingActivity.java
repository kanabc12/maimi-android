package org.hxy.platform.android.my.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;

import org.hxy.platform.android.common.base.mvp.BaseActivity;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.common.util.IconUtil;
import org.hxy.platform.android.common.util.PreferenceUtil;
import org.hxy.platform.android.my.R;
import org.hxy.platform.android.my.R2;
import org.hxy.platform.android.my.address.RecepitAddressActivity;
import org.hxy.platform.android.my.help.HelpCenterActivity;
import org.hxy.platform.android.my.login.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

/*
 *  创建者:   tiao
 *  创建时间:  2017/7/30 0030 20:49
 *  描述：    TODO
 */
@Route(path = "/my/account")
public class AccountSettingActivity extends BaseActivity {


    @BindView(R2.id.iv_signOrReg)
    ImageView mIvSignOrReg;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_user)
    TextView mTvUser;
    @BindView(R2.id.iv_plus)
    ImageView mIvPlus;
    @BindView(R2.id.iv_xb)
    ImageView mIvXb;
    @BindView(R2.id.iv_address)
    ImageView mIvAddress;
    @BindView(R2.id.rl_address)
    RelativeLayout mRlAddress;
    @BindView(R2.id.iv_zp)
    ImageView mIvZp;
    @BindView(R2.id.iv_sm)
    ImageView mIvSm;
    @BindView(R2.id.iv_accountSecu)
    ImageView mIvAccountSecu;
    @BindView(R2.id.imageView)
    ImageView mImageView;
    @BindView(R2.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R2.id.iv_myself)
    ImageView mIvMyself;
    @BindView(R2.id.bt_unregist)
    Button mBtUnregist;
    @BindView(R2.id.rl_login)
    RelativeLayout mRlLogin;
    @BindView(R2.id.rl_help)
    RelativeLayout mRlHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_setting;
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
    protected void onStart() {
        super.onStart();
        init();
    }

    public void init() {
        if (!"".equals(PreferenceUtil.getUserId(this))) {
            mTvUser.setText(PreferenceUtil.getUserName(this));
            mBtUnregist.setVisibility(View.VISIBLE);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.huaji);
            Bitmap roundedCornerBitmap = IconUtil.getRoundedCornerBitmap(bitmap);
            mIvSignOrReg.setImageBitmap(roundedCornerBitmap);
        }
    }

    @OnClick({R2.id.iv_back, R2.id.rl_address, R2.id.bt_unregist, R2.id.rl_login, R2.id.rl_help})
    public void onClick(View view) {
        int result = view.getId();
        if(result ==R.id.iv_back){
            finish();
        }else if(result == R.id.rl_address){
            if(!"".equals(PreferenceUtil.getUserId(this))){
                Intent intent = new Intent(this, RecepitAddressActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        }else if(result == R.id.bt_unregist){
            isUnregist();
        }else if(result ==R.id.rl_login ){
            if("".equals(PreferenceUtil.getUserId(this))){
                Intent intent2 = new Intent(this, LoginActivity.class);
                startActivity(intent2);
            }
        }else if(result ==R.id.rl_help){
            if("".equals(PreferenceUtil.getUserId(this))){
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);

            }else {
                Intent intent = new Intent(this, HelpCenterActivity.class);
                startActivity(intent);
            }
        }
    }

    public void isUnregist() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认退出吗？");
//        builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Observable<LoginInfoBean> newsObservable = RetrofitFactory.getInstance().unRegist(PreferenceUtil.getUserId(AccountSettingActivity.this));
//                newsObservable.compose(compose(AccountSettingActivity.this.<LoginInfoBean>bindToLifecycle())).subscribe(new BaseObserver<LoginInfoBean>(AccountSettingActivity.this) {
//                    @Override
//                    protected void onHandleSuccess(LoginInfoBean loginInfoBean) {
//                        if("logout".equals(loginInfoBean.getResponse())){
////                            PreferenceUtil.setUserName(AccountSettingActivity.this, "");
//                            PreferenceUtil.setUserId(AccountSettingActivity.this, "");
//                            PreferenceUtil.setRegistSuccess(AccountSettingActivity.this, false);
//                            finish();
//                        }
//
//
//                    }
//
//                });
//
//            }
//        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

}
