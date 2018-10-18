package org.hxy.platform.android.my.address;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;

import org.hxy.platform.android.common.base.mvp.BaseActivity;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.common.util.PreferenceUtil;
import org.hxy.platform.android.my.R;
import org.hxy.platform.android.my.R2;
import org.hxy.platform.android.my.address.bean.RecepitAddressBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/9/26 11:01
 * desc   :
 * version: 1.0
 */
@Route(path = "/my/addAddress")
public class AddRecepitAddressActivity extends BaseActivity implements CityPickerListener {
    private static final String TAG = "AddRecepitAddressActivity";
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_name)
    TextView mTvName;
    @BindView(R2.id.et_name)
    EditText mEtName;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.et_phone)
    EditText mEtPhone;
    @BindView(R2.id.ib_delete_phone)
    ImageButton mIbDeletePhone;

    @BindView(R2.id.et_receipt_address)
    TextView mEtReceiptAddress;
    @BindView(R2.id.et_detail_address)
    EditText mEtDetailAddress;
    @BindView(R2.id.tv_label)
    TextView mTvLabel;
    @BindView(R2.id.ib_select_label)
    ImageView mIbSelectLabel;
    @BindView(R2.id.bt_ok)
    Button mBtOk;
    @BindView(R2.id.ib_delete)
    ImageButton mIbDelete;
    @BindView(R2.id.iv_address_area)
    ImageView mIvAddressArea;
    @BindView(R2.id.rl_address_area)
    RelativeLayout mRlAddressArea;
    private RecepitAddressBean.AddressListBean mAddressListBean;
    private Gson mGson;
    private static boolean isSelected = false;
    private CityPicker mCityPicker;
    private List<RecepitAddressBean.AddressListBean> mAddressList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.GRAY);
        }
        mCityPicker = new CityPicker(AddRecepitAddressActivity.this, this);
        mGson = new Gson();
        processIntent();
        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    mIbDeletePhone.setVisibility(View.INVISIBLE);
                } else {
                    mIbDeletePhone.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick({R2.id.iv_back, R2.id.ib_delete_phone, R2.id.ib_select_label, R2.id.bt_ok, R2.id.ib_delete, R2.id.rl_address_area})
    public void onClick(View view) {
        int result = view.getId();
        if(result == R.id.iv_back){
            finish();
        }else if(result == R.id.ib_delete){
            deleteAddress();
        }else if(result ==R.id.ib_delete_phone){
            mEtPhone.setText("");
        }else if(result==R.id.ib_select_label){
            alertLable();
        }else if(result==R.id.rl_address_area){
            selectArea();
        }else if(result==R.id.bt_ok){
            boolean isOk = checkReceiptAddressInfo();
            if (isOk) {
                if (mAddressListBean == null) {
                    insertAddress();
                } else {
                    updateAddress();
                }
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_edit_receipt_address;
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

    @Override
    public void getCity(String name) {
        mEtReceiptAddress.setText(name);
    }

    @Override
    public void onBackPressed() {
        if (mCityPicker.isShow()) {
            mCityPicker.close();
            return;
        }
        super.onBackPressed();
    }

    private void processIntent() {
        if (getIntent() != null) {
            mAddressListBean = (RecepitAddressBean.AddressListBean) getIntent().getSerializableExtra("address");
            if (mAddressListBean != null) {
                mTvTitle.setText("编辑收货地址");
                mIbDelete.setVisibility(View.VISIBLE);
                mIbDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteAddress();
                    }
                });
                mEtName.setText(mAddressListBean.getName());

                mEtPhone.setText(mAddressListBean.getPhoneNumber());
                mEtReceiptAddress.setText(mAddressListBean.getAddressArea());
                mEtDetailAddress.setText(mAddressListBean.getAddressDetail());
            }
        }
    }
    private void deleteAddress() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("确认删除吗？");
//        builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                int id = mAddressListBean.getId();
//                RequestBody requestBody = new FormBody.Builder()
//                        .add("id", id + "")
//                        .build();
//                String userId = PreferenceUtil.getUserId(AddRecepitAddressActivity.this);
//                Call<RecepitAddressBean> call = (Call<RecepitAddressBean>) RetrofitFactory.getInstance().listAddressDelete(userId, requestBody);
//                call.enqueue(new Callback<RecepitAddressBean>() {
//                    @Override
//                    public void onResponse(Call<RecepitAddressBean> call, Response<RecepitAddressBean> response) {
//                        if ("addressDelete".equals(response.body().getResponse())) {
//                            Log.d(TAG, "onResponse: " + response.body().getResponse());
////                            Toast.makeText(AddRecepitAddressActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
//                            finish();
//
//                        } else {
//                            Toast.makeText(AddRecepitAddressActivity.this, response.toString() + "", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<RecepitAddressBean> call, Throwable t) {
//                        Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//                    }
//                });
//            }
//        });
//        builder.show();
    }

    private String[] mTitles = new String[]{"无", "家", "学校", "公司"};
    private String[] mColors = new String[]{"#aabbcc", "#678923", "#dd9966", "#2233ff"};

    private void alertLable() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择地址标签");
        builder.setItems(mTitles, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTvLabel.setText(mTitles[which]);
                mTvLabel.setBackgroundColor(Color.parseColor(mColors[which]));
            }
        });
        builder.show();
    }
    private void selectArea() {
        if (!isSelected) {
            mCityPicker.show();
            isSelected = true;
        } else {
            mCityPicker.close();
            isSelected = false;
        }
    }
    public boolean isMobileNO(String phone) {
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return phone.matches(telRegex);
    }
    public boolean checkReceiptAddressInfo() {
        String name = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请填写联系人", Toast.LENGTH_SHORT).show();
            return false;
        }
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isMobileNO(phone)) {
            Toast.makeText(this, "请填写合法的手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        String receiptAddress = mEtReceiptAddress.getText().toString().trim();
        if (TextUtils.isEmpty(receiptAddress)) {
            Toast.makeText(this, "请填写收获地址", Toast.LENGTH_SHORT).show();
            return false;
        }
        String address = mEtDetailAddress.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "请填写详细地址", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void insertAddress() {
//        if (!"".equals(PreferenceUtil.getUserId(this))) {
//            String name = mEtName.getText().toString().trim();
//            String phone = mEtPhone.getText().toString().trim();
//            String receiptAddress = mEtReceiptAddress.getText().toString().trim();
//            Log.d(TAG, "insertAddress: 地址" + receiptAddress);
//            String detailAddress = mEtDetailAddress.getText().toString().trim();
//            String label = mTvLabel.getText().toString().trim();
//            HashMap<String, String> paramsMap = new HashMap<>();
//            paramsMap.put("name", name);
//            paramsMap.put("phoneNumber", phone);
//            paramsMap.put("addressArea", receiptAddress);
//            paramsMap.put("addressDetail", detailAddress);
//            paramsMap.put("province", "1");
//            paramsMap.put("city", "1");
//            paramsMap.put("zipCode", "1");
//            paramsMap.put("isDefault", "1");
//            paramsMap.put("id", "1");
//            String strEntity = mGson.toJson(paramsMap);
//            RequestBody requestBody = new FormBody.Builder()
//                    .add("name", name)
//                    .add("phoneNumber", phone)
//                    .add("addressArea", receiptAddress)
//                    .add("addressDetail", detailAddress)
//                    .add("province", "1")
//                    .add("city", "1")
//                    .add("zipCode", "1")
//                    .add("isDefault", "1")
//                    .build();
//
//            Call<RecepitAddressBean> call = (Call<RecepitAddressBean>) RetrofitFactory.getInstance().listAddressSave(PreferenceUtil.getUserId(this), requestBody);
//            call.enqueue(new Callback<RecepitAddressBean>() {
//                @Override
//                public void onResponse(Call<RecepitAddressBean> call, Response<RecepitAddressBean> response) {
//                    Log.d(TAG, "onResponse: " + response.body().getResponse());
//                    if (response.body().getAddressList() != null) {
////                        Toast.makeText(AddRecepitAddressActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
//                        //获取最后一个list
//                        getLastAddress();
//                    } else {
//                        Toast.makeText(AddRecepitAddressActivity.this, response.body().getError(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<RecepitAddressBean> call, Throwable t) {
//                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//                }
//            });
//        }
    }
    public void getLastAddress() {
//        Observable<RecepitAddressBean> newsObservable = RetrofitFactory.getInstance().listAddressList(PreferenceUtils.getUserId(this));
//        newsObservable.compose(compose(this.<RecepitAddressBean>bindToLifecycle())).subscribe(new BaseObserver<RecepitAddressBean>(this) {
//            @Override
//            protected void onHandleSuccess(RecepitAddressBean addressListBean) {
//                if(addressListBean.getAddressList() != null){
////                    Toast.makeText(AddRecepitAddressActivity.this, "访问成功"+ addressListBean.getResponse(), Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "onHandleSuccess: "+ addressListBean.getAddressList().size());
//                    mAddressList = addressListBean.getAddressList();
//                    Log.d(TAG, "onHandleSuccess: 地址单" + addressListBean.getAddressList().size());
//                    //设置保存默认
//                    setAddressDefault(mAddressList);
//                }
//
//            }
//        });
    }
    private void setAddressDefault(List<RecepitAddressBean.AddressListBean> addressList) {
//        int id = addressList.get(mAddressList.size() - 1).getId();
//        //设置默认
//        Call<RecepitAddressBean> call = (Call<RecepitAddressBean>) RetrofitFactory.getInstance().listAddressDefault(PreferenceUtil.getUserId(this), id+"");
//        call.enqueue(new Callback<RecepitAddressBean>() {
//            @Override
//            public void onResponse(Call<RecepitAddressBean> call, Response<RecepitAddressBean> response) {
//                if("addressDefault".equals(response.body().getResponse())){
//                    Log.d(TAG, "onResponse: " + response.body().getResponse());
////                        Toast.makeText(AddRecepitAddressActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//                    Toast.makeText(AddRecepitAddressActivity.this, response.toString()+"", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RecepitAddressBean> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//            }
//        });
    }

    public void setUpdateAddressDefault(int id) {
//        Call<RecepitAddressBean> call = (Call<RecepitAddressBean>) RetrofitFactory.getInstance().listAddressDefault(PreferenceUtils.getUserId(this), id+"");
//        call.enqueue(new Callback<RecepitAddressBean>() {
//            @Override
//            public void onResponse(Call<RecepitAddressBean> call, Response<RecepitAddressBean> response) {
//                if("addressDefault".equals(response.body().getResponse())){
//                    Log.d(TAG, "onResponse: " + response.body().getResponse());
////                    Toast.makeText(AddRecepitAddressActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//                    Toast.makeText(AddRecepitAddressActivity.this, response.toString()+"", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RecepitAddressBean> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//            }
//        });
    }
    private void updateAddress() {
//        final int id = mAddressListBean.getId();
//        String name = mEtName.getText().toString().trim();
//        String phone = mEtPhone.getText().toString().trim();
//        String receiptAddress = mEtReceiptAddress.getText().toString().trim();
//        String detailAddress = mEtDetailAddress.getText().toString().trim();
//        RequestBody requestBody = new FormBody.Builder()
//                .add("name", name)
//                .add("phoneNumber", phone)
//                .add("addressArea", receiptAddress)
//                .add("addressDetail", detailAddress)
//                .add("province", "1")
//                .add("city", "1")
//                .add("zipCode", "1")
//                .add("isDefault", "1")
//                .add("id", id + "")
//                .build();
//
//        Call<RecepitAddressBean> call = (Call<RecepitAddressBean>) RetrofitFactory.getInstance().listAddressSave(PreferenceUtils.getUserId(this), requestBody);
//        call.enqueue(new Callback<RecepitAddressBean>() {
//            @Override
//            public void onResponse(Call<RecepitAddressBean> call, Response<RecepitAddressBean> response) {
//                Log.d(TAG, "onResponse: " + response.body().getResponse());
//                if (response.body().getAddressList() != null) {
////                    Toast.makeText(AddRecepitAddressActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                    setUpdateAddressDefault(id);
//                } else {
//                    Toast.makeText(AddRecepitAddressActivity.this, response.body().getError(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RecepitAddressBean> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//            }
//        });
    }
}
