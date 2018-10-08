package org.hxy.platform.android.my.register;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.hxy.platform.android.common.base.mvp.BaseActivity;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.common.util.ToastUtil;
import org.hxy.platform.android.common.widget.VerificationSeekBar;
import org.hxy.platform.android.my.R2;

import butterknife.BindView;

/**
 * Date：2018/9/15 on 16:37
 * Author: kanabc12@126.com
 * Description:
 */
@Route(path = "/my/register")
public class RegisterActivity extends BaseActivity {

    private Context mContext;

    @BindView(R2.id.editPhoneNum)
    EditText editPhoneNumber;

    @BindView(R2.id.sb_progress)
    VerificationSeekBar seekbar;
    @BindView(R2.id.btn_next_1)
    Button buttonNext1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
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

    public void initEvent() {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress() != seekBar.getMax()) {
                    seekBar.setProgress(0);
                    buttonNext1.setEnabled(false);
                } else {
                    // todo 做滑动到最右的操作.
                    if(isEditEmpty(editPhoneNumber)){
                        ToastUtil.showShort(RegisterActivity.this,"手机号不能为空");
                        seekBar.setProgress(0);
                    }
                    buttonNext1.setEnabled(true);
                }
            }
        });
    }

    /**
     * First Next click
     *
     * @param view
     */
    public void onBtnNextOneClick(View view) {

    }

    private boolean isEditEmpty(EditText text) {
        return text == null || "".equals(text.getText().toString());
    }


}
