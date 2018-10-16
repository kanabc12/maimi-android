package org.hxy.platform.android.product.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import org.hxy.platform.android.common.bean.CommentInfoBean;
import org.hxy.platform.android.product.R;
import org.hxy.platform.android.product.R2;
import org.hxy.platform.android.product.adapter.CommentAdapter;
import org.hxy.platform.android.product.ui.ProductDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * item页ViewPager里的评价Fragment
 */
public class GoodsCommentFragment extends Fragment {
    public TextView tv_comment_count, tv_good_comment;
    public ProductDetailActivity mActivity;
    @BindView(R2.id.tv_comment_count)
    TextView mTvCommentCount;
    @BindView(R2.id.tv_good_comment)
    TextView mTvGoodComment;
    @BindView(R2.id.iv_comment_right)
    ImageView mIvCommentRight;
    @BindView(R2.id.ll_comment)
    LinearLayout mLlComment;
    @BindView(R2.id.ll_empty_comment)
    LinearLayout mLlEmptyComment;
    @BindView(R2.id.lv_comment)
    ListView mLvComment;
    private CommentInfoBean mCommentInfoBean;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (ProductDetailActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_goods_comment, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void setData(CommentInfoBean commentInfoBean) {
        mCommentInfoBean = commentInfoBean;
        if (mCommentInfoBean.getComment().size()>0){
            mLlEmptyComment.setVisibility(View.GONE);
            mLvComment.setAdapter(new CommentAdapter(getActivity(), mCommentInfoBean.getComment()));
        }else{
            mLlEmptyComment.setVisibility(View.VISIBLE);
        }

    }

}
