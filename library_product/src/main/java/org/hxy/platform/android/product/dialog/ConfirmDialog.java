package org.hxy.platform.android.product.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;



import org.hxy.platform.android.product.R;
import org.hxy.platform.android.product.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by EtherealPatrick on 2017/3/27.
 */

public class ConfirmDialog extends Dialog {

  @BindView(R2.id.tv_confirm_title)
  TextView tvConfirmTitle;
  @BindView(R2.id.tv_confirm_cancel)
  TextView tvConfirmCancel;
  @BindView(R2.id.tv_confirm_ensure)
  TextView tvConfirmEnsure;
  private Context context;
  private String msg;

  public ConfirmDialog(@NonNull Context context) {
    super(context, R.style.MyDialog);
    this.context = context;
  }

  public ConfirmDialog(@NonNull Context context, String msg) {
    super(context, R.style.MyDialog);
    this.context = context;
    this.msg = msg;
  }

  @Override public void show() {
    super.show();
    Window dialogWindow = getWindow();
    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
    dialogWindow.setGravity(Gravity.CENTER);
    dialogWindow.setAttributes(lp);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dialog_confirm_center);
    ButterKnife.bind(this);
    if (msg == null){
      tvConfirmTitle.setText("是否删除所有历史记录?");
    }else {
      tvConfirmTitle.setText(msg);
    }

    tvConfirmCancel.setText("取消");
    tvConfirmEnsure.setText("确定");
  }

  private DeepConfirmListener mDeepConfirmListener;
  public void setOnDeepConfirmListener(DeepConfirmListener deepConfirmListener){
    this.mDeepConfirmListener = deepConfirmListener;

  }

  @OnClick({ R2.id.tv_confirm_cancel, R2.id.tv_confirm_ensure })
  public void onClick(View view) {
    int result = view.getId();
    if(result == R.id.tv_confirm_cancel){
      mDeepConfirmListener.onSelectCancel();
    }else if(result == R.id.tv_confirm_ensure){
      mDeepConfirmListener.onSelectEnsure();
    }
  }

  public interface DeepConfirmListener{
    void onSelectCancel();
    void onSelectEnsure();
  }
}
