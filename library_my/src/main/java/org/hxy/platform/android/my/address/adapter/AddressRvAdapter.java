package org.hxy.platform.android.my.address.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.hxy.platform.android.my.R;
import org.hxy.platform.android.my.R2;
import org.hxy.platform.android.my.address.AddRecepitAddressActivity;
import org.hxy.platform.android.my.address.RecepitAddressActivity;
import org.hxy.platform.android.my.address.bean.RecepitAddressBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/*
 *  创建者:   tiao
 *  创建时间:  2017/8/2 0002 19:00
 *  描述：    TODO
 */
public class AddressRvAdapter extends RecyclerView.Adapter {
    private static final int TYPE_DEFALUT = 1;
    public static final int TYPE_NORMAL = 0;
    private Context mContext;
    private static final String TAG = "AddressRvAdapter";

    public AddressRvAdapter(Context context) {
        mContext = context;
    }


    private List<RecepitAddressBean.AddressListBean> mAddressBeanList = new ArrayList<>();

    public void setAddressBeanList(List<RecepitAddressBean.AddressListBean> addressBeanList) {
        mAddressBeanList = addressBeanList;
        Collections.sort(mAddressBeanList, new Comparator<RecepitAddressBean.AddressListBean>() {
            @Override
            public int compare(RecepitAddressBean.AddressListBean o1, RecepitAddressBean.AddressListBean o2) {
                if (o1.getIsDefault() > o2.getIsDefault()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_DEFALUT;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        switch (viewType) {
            case TYPE_DEFALUT:
                View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_recepit_address, parent, false);
                DefaultHolder defaultHolder = new DefaultHolder(itemView);
                return defaultHolder;
            case TYPE_NORMAL:
                View normalItemView = LayoutInflater.from(mContext).inflate(R.layout.item_recepit_address, parent, false);
                NormalHolder normalHolder = new NormalHolder(normalItemView);
                return normalHolder;
            default:
                Log.e("home", "怎么出现了第三种holder");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_DEFALUT:
                ((DefaultHolder) holder).setData(mAddressBeanList.get(position));
                break;
            case TYPE_NORMAL:
                ((NormalHolder) holder).setData(mAddressBeanList.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mAddressBeanList != null) {
            return mAddressBeanList.size();
        }
        return 0;
    }

    class DefaultHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.ll_address)
        LinearLayout mLlAddress;
        @BindView(R2.id.tv_select)
        TextView mTvSelect;
        @BindView(R2.id.tv_name)
        TextView mTvName;
        @BindView(R2.id.tv_phone)
        TextView mTvPhone;
        @BindView(R2.id.tv_address)
        TextView mTvAddress;
        @BindView(R2.id.iv_select)
        ImageView mIvSelect;
        @BindView(R2.id.tv_delete)
        TextView mTvDelete;
        @BindView(R2.id.tv_edit)
        TextView mTvEdit;

        @OnClick({R2.id.tv_edit, R2.id.tv_delete, R2.id.ll_address})
        public void onAddOrMinusClick(View view) {
            int result = view.getId();
            if(result==R.id.tv_edit){
                Intent intent = new Intent(mContext, AddRecepitAddressActivity.class);
                intent.putExtra("address", mAddressListBean);
                mContext.startActivity(intent);
            }else if(result ==R.id.tv_delete){
                deleteAddress();
            }else if(result == R.id.ll_address ){
                Intent intent2 = new Intent();
                intent2.putExtra("address", mAddressListBean);
                ((RecepitAddressActivity) mContext).setResult(Activity.RESULT_OK, intent2);
                ((RecepitAddressActivity) mContext).finish();
            }
        }

        private void deleteAddress() {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("确认删除吗？");
//            builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    int id = mAddressListBean.getId();
//                    RequestBody requestBody = new FormBody.Builder()
//                            .add("id", id + "")
//                            .build();
//                    String userId = PreferenceUtil.getUserId(mContext);
//                    Call<RecepitAddressBean> call = (Call<RecepitAddressBean>) RetrofitFactory.getInstance().listAddressDelete(userId, requestBody);
//                    call.enqueue(new Callback<RecepitAddressBean>() {
//                        @Override
//                        public void onResponse(Call<RecepitAddressBean> call, Response<RecepitAddressBean> response) {
//                            if ("addressDelete".equals(response.body().getResponse())) {
//                                Log.d(TAG, "onResponse: " + response.body().getResponse());
////                                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
//                                mAddressBeanList.remove(mAddressListBean);
//                                notifyDataSetChanged();
//
//                            } else {
//                                Toast.makeText(mContext, response.toString() + "", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<RecepitAddressBean> call, Throwable t) {
//                            Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//                        }
//                    });
//                }
//            });
            builder.show();

        }

        private RecepitAddressBean.AddressListBean mAddressListBean;

        DefaultHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(RecepitAddressBean.AddressListBean addressListBean) {
            this.mAddressListBean = addressListBean;
            String name = addressListBean.getName();
            Log.d(TAG, "setData: " + name);
            mTvName.setText(addressListBean.getName());
            mTvAddress.setText(addressListBean.getAddressArea() + " " + addressListBean.getAddressDetail());
            mTvPhone.setText(addressListBean.getPhoneNumber());
            mIvSelect.setBackgroundResource(R.mipmap.xuanzhezhuangtai);
            mTvSelect.setText("默认地址");
        }


    }

    class NormalHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.ll_select)
        LinearLayout mLlselect;
        @BindView(R2.id.tv_name)
        TextView mTvName;
        @BindView(R2.id.tv_select)
        TextView mTvSelect;
        @BindView(R2.id.tv_phone)
        TextView mTvPhone;
        @BindView(R2.id.tv_address)
        TextView mTvAddress;
        @BindView(R2.id.iv_select)
        ImageView mIvSelect;
        @BindView(R2.id.tv_delete)
        TextView mTvDelete;
        @BindView(R2.id.tv_edit)
        TextView mTvEdit;
        @BindView(R2.id.ll_address)
        LinearLayout mLlAddress;
        private Object mAddressList;

        @OnClick({R2.id.ll_select, R2.id.tv_edit, R2.id.tv_delete, R2.id.ll_address})
        public void onAddOrMinusClick(View view) {
            int result = view.getId();
            if(result ==  R.id.ll_select){
                selectAddress();
            }else if(result== R.id.tv_edit){
                Intent intent = new Intent(mContext, AddRecepitAddressActivity.class);
                intent.putExtra("address", mAddressListBean);
                mContext.startActivity(intent);
            }else if(result== R.id.tv_delete){
                deleteAddress();
            }

        }

        private void selectAddress() {
//            int id = mAddressListBean.getId();
//            Log.d(TAG, "selectAddress: " + id);
//            RequestBody requestBody = new FormBody.Builder()
//                    .add("id", id + "")
//                    .build();
//            String userId = PreferenceUtil.getUserId(mContext);
//            Log.d(TAG, "selectAddress: " + userId);
//            Call<RecepitAddressBean> call = (Call<RecepitAddressBean>) RetrofitFactory.getInstance().listAddressDefault(userId, id + "");
//            call.enqueue(new Callback<RecepitAddressBean>() {
//                @Override
//                public void onResponse(Call<RecepitAddressBean> call, Response<RecepitAddressBean> response) {
//                    if ("addressDefault".equals(response.body().getResponse())) {
//                        Log.d(TAG, "onResponse: " + response.body().getResponse());
////                        Toast.makeText(mContext, "设置成功", Toast.LENGTH_SHORT).show();
//                        getAddressList();
//                    } else {
//                        Toast.makeText(mContext, response.toString() + "", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<RecepitAddressBean> call, Throwable t) {
//                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//                }
//            });
        }

        private RecepitAddressBean.AddressListBean mAddressListBean;


        NormalHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(RecepitAddressBean.AddressListBean addressListBean) {
            this.mAddressListBean = addressListBean;
            String name = addressListBean.getName();
            Log.d(TAG, "setData: " + name);
            mTvName.setText(addressListBean.getName());
            mTvAddress.setText(addressListBean.getAddressArea() + " " + addressListBean.getAddressDetail());
            mTvPhone.setText(addressListBean.getPhoneNumber());
            mIvSelect.setBackgroundResource(R.mipmap.weixuanzhezhuang);
            mTvSelect.setText("设为默认");

        }

        private void deleteAddress() {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("确认删除吗？");
//            builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    int id = mAddressListBean.getId();
//                    RequestBody requestBody = new FormBody.Builder()
//                            .add("id", id + "")
//                            .build();
//                    String userId = PreferenceUtil.getUserId(mContext);
//                    Call<RecepitAddressBean> call = (Call<RecepitAddressBean>) RetrofitFactory.getInstance().listAddressDelete(userId, requestBody);
//                    call.enqueue(new Callback<RecepitAddressBean>() {
//                        @Override
//                        public void onResponse(Call<RecepitAddressBean> call, Response<RecepitAddressBean> response) {
//                            if ("addressDelete".equals(response.body().getResponse())) {
//                                Log.d(TAG, "onResponse: " + response.body().getResponse());
////                                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
//                                mAddressBeanList.remove(mAddressListBean);
//                                notifyDataSetChanged();
//
//                            } else {
//                                Toast.makeText(mContext, response.toString() + "", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<RecepitAddressBean> call, Throwable t) {
//                            Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//                        }
//                    });
//                }
//            });
            builder.show();

        }

        public void getAddressList() {
            /*Observable<RecepitAddressBean> newsObservable = RetrofitFactory.getInstance().listAddressList(PreferenceUtil.getUserId(mContext));

            newsObservable.compose(((RecepitAddressActivity)mContext).<RecepitAddressBean>bindToLifecycle()).subscribe(new BaseObserver<RecepitAddressBean>(mContext) {
                @Override
                protected void onHandleSuccess(RecepitAddressBean recepitAddressBean) {
                    if(recepitAddressBean.getAddressList() != null){
                        Toast.makeText(mContext, "访问成功"+ recepitAddressBean.getResponse(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onHandleSuccess: "+ recepitAddressBean.getAddressList().size());
                        setAddressBeanList(recepitAddressBean.getAddressList());

                    }else {
                        Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/
//            Call<RecepitAddressBean> call = (Call<RecepitAddressBean>) RetrofitFactory.getInstance().listAddressListCall(PreferenceUtil.getUserId(mContext));
//            call.enqueue(new Callback<RecepitAddressBean>() {
//                @Override
//                public void onResponse(Call<RecepitAddressBean> call, Response<RecepitAddressBean> response) {
//
//                    if (response.body().getAddressList() != null) {
////                        Toast.makeText(mContext, "访问成功"+ response.body().getResponse(), Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, "onHandleSuccess: " + response.body().getAddressList().size());
//                        if (response.body().getAddressList() != null) {
//                            Toast.makeText(mContext, "访问成功" + response.body().getResponse(), Toast.LENGTH_SHORT).show();
//                            Log.d(TAG, "onHandleSuccess: " + response.body().getAddressList().size());
//                            setAddressBeanList(response.body().getAddressList());
//
//                        } else {
//                            Toast.makeText(mContext, response.toString() + "", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<RecepitAddressBean> call, Throwable t) {
//                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//
//                }
//            });
        }
    }
}