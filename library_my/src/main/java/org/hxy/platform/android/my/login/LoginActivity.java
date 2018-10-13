package org.hxy.platform.android.my.login;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import org.hxy.platform.android.common.base.mvp.BaseActivity;
import org.hxy.platform.android.common.bean.UserInfoBean;
import org.hxy.platform.android.common.util.PreferenceUtil;
import org.hxy.platform.android.common.util.ToastUtil;
import org.hxy.platform.android.my.R;
import org.hxy.platform.android.my.R2;
import org.hxy.platform.android.my.login.contract.LoginContract;
import org.hxy.platform.android.my.login.contract.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Date：2018/10/7 on 8:06
 * Author: kanabc12@126.com
 * Description:
 */
@Route(path = "/my/login")
public class LoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.et_name)
    EditText mEtName;
    @BindView(R2.id.et_password)
    EditText mEtPassword;
    @BindView(R2.id.bt_login)
    Button mBtLogin;
    @BindView(R2.id.tv_regist)
    TextView mTvRegist;
    @BindView(R2.id.tv_pwd)
    TextView mTvPwd;
    @BindView(R2.id.pwd_delete)
    ImageView mPwdDelete;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public LoginContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    mPwdDelete.setVisibility(View.INVISIBLE);

                    mBtLogin.setBackgroundColor(Color.parseColor("#D6D7D7"));
                    mBtLogin.setTextColor(Color.parseColor("#BABABA"));
                } else {
                    mPwdDelete.setVisibility(View.VISIBLE);
                    mBtLogin.setTextColor(Color.WHITE);
                    mBtLogin.setBackgroundColor(Color.RED);
                }
            }
        });
    }

    @Override
    public String getMobile() {
        return mEtName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void saveLoignInfo(UserInfoBean userInfoBean) {
        PreferenceUtil.setUserName(LoginActivity.this, mEtName.getText().toString());
        PreferenceUtil.setUserId(LoginActivity.this, userInfoBean.getUserid());
    }

    @Override
    public void setErrorMessage(String message) {
        ToastUtil.showShort(LoginActivity.this, message);
    }

    @OnClick({R2.id.iv_back, R2.id.bt_login, R2.id.tv_regist, R2.id.pwd_delete})
    public void onClick(View view) {
        int result = view.getId();
        if (result == R.id.iv_back) {
            finish();
        } else if (result == R.id.bt_login) {
            login();
        } else if (result == R.id.tv_regist) {
            navigate(view,"/my/register");
        } else if (result == R.id.pwd_delete) {
            mEtPassword.setText("");
        }
    }
    private void navigate(View view,String url){
        ActivityOptionsCompat compat = ActivityOptionsCompat.
                makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        ARouter.getInstance().build(url)
                .withOptionsCompat(compat)
                .navigation();
    }

    public void login() {
        //保存账号密码
        final String name = mEtName.getText().toString().trim();
        String pwd = mEtPassword.getText().toString().trim();
        //判断是否为空
        if (TextUtils.isEmpty(name)) {//str == null || str.length() == 0
            ToastUtil.showShort(this, "用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {//str == null || str.length() == 0
            ToastUtil.showShort(this, "密码不能为空");
            return;
        }
        getPresenter().login(name,pwd);
    }
}
