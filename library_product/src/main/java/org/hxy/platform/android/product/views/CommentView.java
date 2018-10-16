package org.hxy.platform.android.product.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.hxy.platform.android.product.R;
import org.hxy.platform.android.product.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 25505 on 2017/8/3.
 */

public class CommentView extends RelativeLayout {
    @BindView(R2.id.tv_cv_name)
    TextView mTvCvName;
    @BindView(R2.id.tv_cv_time)
    TextView mTvCvTime;
    @BindView(R2.id.tv_cv_comment)
    TextView mTvCvComment;

    public CommentView(Context context) {
        this(context, null);
    }

    public CommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.comment_view, this);
        ButterKnife.bind(this,this);
    }
    public void setName(String name){
        mTvCvName.setText(name);
    }
    public void setTime(String time){
        mTvCvTime.setText(time);
    }
    public void setComment(String comment){
        mTvCvComment.setText(comment);
    }
}
