package org.hxy.platfrom.android.app;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.hxy.platform.android.app.R;
import org.hxy.platform.android.cart.ShoppingCartFragment;
import org.hxy.platform.android.category.CategoryFragment;
import org.hxy.platform.android.common.base.mvp.BaseActivity;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.common.widget.bottomnavigation.BadgeItem;
import org.hxy.platform.android.common.widget.bottomnavigation.BottomNavigationBar;
import org.hxy.platform.android.common.widget.bottomnavigation.BottomNavigationItem;
import org.hxy.platform.android.find.FindFragment;
import org.hxy.platform.android.index.MainHomeFragment;
import org.hxy.platform.android.my.PersonFragment;

import butterknife.BindView;

@Route(path = "/app/main")
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    private MainHomeFragment mMainHomeFragment;
    private CategoryFragment mCategoryFragment;
    private FragmentManager mFragmentManager;
    private FindFragment mFindFragment;
    private PersonFragment personFragment;
    private ShoppingCartFragment shoppingCartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
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

    public void initView() {
        mMainHomeFragment = (MainHomeFragment) mFragmentManager.findFragmentByTag("home_fg");
        mCategoryFragment = (CategoryFragment) mFragmentManager.findFragmentByTag("class_fg");
        mFindFragment = (FindFragment) mFragmentManager.findFragmentByTag("find_fg");
        personFragment = (PersonFragment) mFragmentManager.findFragmentByTag("my_fg");
        shoppingCartFragment = (ShoppingCartFragment) mFragmentManager.findFragmentByTag("cart_fg");

        if (mMainHomeFragment == null) {
            mMainHomeFragment = MainHomeFragment.newInstance();
            addFragment(R.id.main_container, mMainHomeFragment, "home_fg");
        }
        if (mCategoryFragment == null) {
            mCategoryFragment = CategoryFragment.newInstance();
            addFragment(R.id.main_container, mCategoryFragment, "class_fg");
        }
        if (mFindFragment == null) {
            mFindFragment = FindFragment.newInstance();
            addFragment(R.id.main_container, mFindFragment, "find_fg");
        }
        if (personFragment == null) {
            personFragment = PersonFragment.newInstance();
            addFragment(R.id.main_container, personFragment, "my_fg");
        }
        if (shoppingCartFragment == null) {
            shoppingCartFragment = ShoppingCartFragment.newInstance();
            addFragment(R.id.main_container, shoppingCartFragment, "cart_fg");
        }
        mFragmentManager.beginTransaction().show(mMainHomeFragment).hide(mCategoryFragment).hide(mFindFragment).hide(personFragment).hide(shoppingCartFragment)
                .commitAllowingStateLoss();
        initBottomNavigation();
    }
    private void initBottomNavigation() {
        BadgeItem numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.colorAccent)
                .setText("99+")
                .setHideOnSelect(false);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);


        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.axh, "").setInactiveIconResource(R.drawable.axg).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axd, "").setInactiveIconResource(R.drawable.axc).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axf, "").setInactiveIconResource(R.drawable.axe).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axb, "").setInactiveIconResource(R.drawable.axa).setActiveColorResource(R.color.colorAccent).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.axj, "").setInactiveIconResource(R.drawable.axi).setActiveColorResource(R.color.colorAccent))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        if (position == 0) {
            mFragmentManager.beginTransaction().hide(mFindFragment).hide(mCategoryFragment).show(mMainHomeFragment).hide(personFragment).hide(shoppingCartFragment)
                    .commitAllowingStateLoss();
        } else if (position == 1) {
            mFragmentManager.beginTransaction().hide(mFindFragment).hide(mMainHomeFragment).show(mCategoryFragment).hide(personFragment).hide(shoppingCartFragment)
                    .commitAllowingStateLoss();
        } else if (position == 2) {
            mFragmentManager.beginTransaction().hide(mCategoryFragment).hide(mMainHomeFragment).show(mFindFragment).hide(personFragment).hide(shoppingCartFragment)
                    .commitAllowingStateLoss();
        } else if (position == 3) {
            mFragmentManager.beginTransaction().hide(mCategoryFragment).hide(mMainHomeFragment).show(shoppingCartFragment).hide(mFindFragment).hide(personFragment)
                    .commitAllowingStateLoss();
        } else if (position == 4) {
            mFragmentManager.beginTransaction().hide(mCategoryFragment).hide(mMainHomeFragment).show(personFragment).hide(mMainHomeFragment).hide(shoppingCartFragment)
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
