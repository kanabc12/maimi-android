package org.hxy.platform.android.my.login;

import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.hxy.platform.android.common.base.mvp.BaseActivity;
import org.hxy.platform.android.common.bean.UserInfoBean;
import org.hxy.platform.android.my.R;
import org.hxy.platform.android.my.R2;
import org.hxy.platform.android.my.login.contract.LoginContract;
import org.hxy.platform.android.my.login.contract.LoginPresenter;

import butterknife.BindView;

/**
 * Date：2018/10/7 on 8:06
 * Author: kanabc12@126.com
 * Description:
 */
public class LoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View{

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

    }
}
