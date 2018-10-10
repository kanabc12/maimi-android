package org.hxy.platform.android.product.views;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * explain: banner图需要
 * Created by: Zhao
 * Created time: 2016/11/22 13:01
 */

public class MyImageLoader extends ImageLoader {


    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
//        Glide.with(context)
//                .load((String)path)
//                .asBitmap()
//                .centerCrop()
//                .priority(Priority.HIGH)
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into(imageView);
        imageView.setImageResource(((Integer) path));
    }
}
