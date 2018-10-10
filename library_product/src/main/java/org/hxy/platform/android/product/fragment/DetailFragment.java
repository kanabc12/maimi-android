package org.hxy.platform.android.product.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.hxy.platform.android.common.util.ScreenLengthUtils;
import org.hxy.platform.android.product.R;
import org.hxy.platform.android.product.ui.MainActivity;
import org.hxy.platform.android.product.views.MyImageLoader;
import org.hxy.platform.android.product.views.SlideDetailsLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Time：2017-12-15 13:18.
 * @Author：zhaoXH.
 * @Description:
 */

public class DetailFragment extends BaseLazyFragment implements SlideDetailsLayout.OnSlideDetailsListener {
    private Banner banner;

    public static DetailFragment newInstance(){
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    private TextView goodsDetail,goodsDetailDivide;
    private TextView goodsConfig,goodsConfigDivide;
    FrameLayout frameLayoutContent;
    SlideDetailsLayout slideDetailsLayout;
    FloatingActionButton fabUp;
    ScrollView scrollView;

    private GoodsConfigFragment goodsConfigFragment;
    private GoodsInfoWebFragment goodsInfoWebFragment;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<TextView> tabTextList=new ArrayList<>();
    private Fragment currFragment;
    private int currIndex=0;
    public MainActivity activity;
    private float fromX;
    private ArrayList<String> listBannerImages;
    private ArrayList<Integer> listBannerImages2;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }
    @Override
    protected void getBundles() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {
        listBannerImages2.clear();
        listBannerImages2.add(R.mipmap.goods_img01);
        listBannerImages2.add(R.mipmap.goods_img02);
        listBannerImages2.add(R.mipmap.goods_img03);
        listBannerImages2.add(R.mipmap.goods_img04);
        banner.setImages(listBannerImages2);
        banner.start();
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_detail,container,false);
        initBanner(view);
        goodsDetail = ((TextView) view.findViewById(R.id.goods_detail));
        goodsConfig = ((TextView) view.findViewById(R.id.goods_config));
        goodsDetailDivide = ((TextView) view.findViewById(R.id.goods_detailDivide));
        goodsConfigDivide = ((TextView) view.findViewById(R.id.goods_configDivide));

        frameLayoutContent =  ((FrameLayout) view.findViewById(R.id.frameLayout_content));
        fabUp = ((FloatingActionButton) view.findViewById(R.id.fab_up));
        scrollView = ((ScrollView) view.findViewById(R.id.scrollView));
        slideDetailsLayout = ((SlideDetailsLayout) view.findViewById(R.id.slideDetailsLayout));
        init();
        initClickListener(view);
        return view;
    }

    private void initBanner(View view) {
        banner = ((Banner) view.findViewById(R.id.detail_banner));
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);//显示数字指示器
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setFocusableInTouchMode(true);
        banner.requestFocus();
        banner.setImageLoader(new MyImageLoader());
        banner.isAutoPlay(false);//是否自动轮播（商品详情不轮播）
        banner.setDelayTime(2000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        listBannerImages = new ArrayList<>();
        listBannerImages2 = new ArrayList<>();

        if(listBannerImages!=null){
            listBannerImages.clear();
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) banner.getLayoutParams();
        layoutParams.width = ScreenLengthUtils.getScreenWidth(getContext());
        layoutParams.height = ScreenLengthUtils.getScreenWidth(getContext());
        banner.setLayoutParams(layoutParams);
    }

    private void initClickListener(View view) {
        //全部评价
        view.findViewById(R.id.detail_allCommentRelative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setSelectPosition(1);
            }
        });
        //回到顶部
        fabUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.smoothScrollTo(0, 0);
                slideDetailsLayout.smoothClose(true);
            }
        });
        view.findViewById(R.id.pull_up_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideDetailsLayout.smoothOpen(true);
            }
        });
        //商品详情
        view.findViewById(R.id.goods_detailRelative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currIndex = 0;
                scrollCursor();
                switchFragment(currFragment, goodsInfoWebFragment);
                currFragment = goodsInfoWebFragment;
            }
        });
        //规格参数
        view.findViewById(R.id.goods_configRelative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currIndex = 1;
                scrollCursor();
                switchFragment(currFragment, goodsConfigFragment);
                currFragment = goodsConfigFragment;
            }
        });
    }
    private void scrollCursor() {

        for (int i = 0; i < tabTextList.size(); i++) {
            tabTextList.get(i).setTextColor(i == currIndex ? getResources().getColor(R.color.mainColor) : getResources().getColor(R.color.text03));
        }
        if(currIndex==0){
            goodsDetailDivide.setVisibility(View.VISIBLE);
            goodsConfigDivide.setVisibility(View.INVISIBLE);
        }else{
            goodsDetailDivide.setVisibility(View.INVISIBLE);
            goodsConfigDivide.setVisibility(View.VISIBLE);
        }
    }

    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
        if (currFragment != toFragment) {
            if (!toFragment.isAdded()) {
                getFragmentManager().beginTransaction().hide(fromFragment).add(R.id.frameLayout_content, toFragment).commitAllowingStateLoss();
            } else {
                getFragmentManager().beginTransaction().hide(fromFragment).show(toFragment).commitAllowingStateLoss();
            }
        }
    }
    //本数据的代码可以再优化，写到另一个Controller处理
    private void init() {
        initViews();
        initTabView();
        initSlide();
    }
    private void initViews() {
        fabUp.hide();
        goodsConfigFragment = new GoodsConfigFragment();
        goodsInfoWebFragment = new GoodsInfoWebFragment();
        fragmentList.add(goodsConfigFragment);
        fragmentList.add(goodsInfoWebFragment);

        currFragment = goodsInfoWebFragment;
        //默认显示商品详情tab
        getChildFragmentManager().beginTransaction().replace(R.id.frameLayout_content, currFragment).commitAllowingStateLoss();
    }
    private void initTabView() {
        tabTextList.add(goodsDetail);
        tabTextList.add(goodsConfig);
    }

    private void initSlide() {
        slideDetailsLayout.setOnSlideDetailsListener(this);
    }

    //可以继续优化
    @Override
    public void onStatusChanged(SlideDetailsLayout.Status status) {
        //当前为图文详情页
        if (status == SlideDetailsLayout.Status.OPEN) {
            fabUp.show();
            activity.operaTitleBar(true, true, false);
        } else {
            //当前为商品详情页
            fabUp.hide();
            activity.operaTitleBar(false, false, true);
        }
    }
}
