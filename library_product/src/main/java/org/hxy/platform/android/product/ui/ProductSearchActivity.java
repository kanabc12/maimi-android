package org.hxy.platform.android.product.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import org.hxy.platform.android.common.activity.ActivityManager;
import org.hxy.platform.android.common.base.mvp.BaseActivity;
import org.hxy.platform.android.common.view.FlowLayout;
import org.hxy.platform.android.common.view.OneKeyClearEditText;
import org.hxy.platform.android.product.R;
import org.hxy.platform.android.product.R2;
import org.hxy.platform.android.product.contact.ProductSearchContact;
import org.hxy.platform.android.product.contact.ProductSearchPresenter;
import org.hxy.platform.android.product.dialog.ConfirmDialog;
import org.hxy.platform.android.product.event.SearchEvent;
import org.hxy.platform.android.product.event.SearchHistoryEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/12 14:13
 * desc   :
 * version: 1.0
 */
@Route(path = "/search/productSearch")
public class ProductSearchActivity extends BaseActivity<ProductSearchContact.View,ProductSearchContact.Presenter> implements ProductSearchContact.View  {
    @BindView(R2.id.et_search)
    OneKeyClearEditText etSearch;
    @BindView(R2.id.tv_search_back)
    TextView tvSearchBack;
    @BindView(R2.id.search_toolbar)
    Toolbar searchToolbar;
    @BindView(R2.id.iv_search_history)
    ImageView ivSearchHistory;
    @BindView(R2.id.iv_del_history)
    ImageView ivDelHistory;
    @BindView(R2.id.fl_history)
    FlowLayout flHistory;
    @BindView(R2.id.iv_search_hot)
    ImageView ivSearchHot;
    @BindView(R2.id.fl_hot)
    FlowLayout flHot;
    @BindView(R2.id.ll_search_text_ware)
    LinearLayout llSearchTextWare;
    @BindView(R2.id.activity_text_search)
    RelativeLayout activityTextSearch;
    @BindView(R2.id.rl_search_history)
    RelativeLayout rlSearchHistory;
    @BindView(R2.id.ll_search_history)
    LinearLayout llSearchHistory;
    //edit 输入的搜索词
    private String query;

    private List<String> hotList;
    private List<String> historyList;
    private ConfirmDialog dialog;
    @Autowired
    private String searchKey;

    private boolean hisFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initToolBar();
        initFlowLayout();
    }
    private void initToolBar() {
        searchToolbar.setTitle("");
        setSupportActionBar(searchToolbar);
        if(searchKey!=null){
            etSearch.setText(searchKey);
            etSearch.setSelection(searchKey.length());
        }
        etSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    query = etSearch.getText().toString().trim();
                    if (!query.isEmpty()) {
                        EventBus.getDefault().post(new SearchEvent(query));
                    } else {
                        EventBus.getDefault().post(new SearchEvent(etSearch.getHint().toString()));
                    }
                }
                return false;
            }
        });
    }
    private void initFlowLayout() {
        getPresenter().getHotSearchList();
        addChildText(flHot, hotList);
        getPresenter().getHistorySearchList();
        if (historyList != null && historyList.size() > 0) {
            rlSearchHistory.setVisibility(View.VISIBLE);
            llSearchHistory.setVisibility(View.VISIBLE);
            hisFlag = true;
            addChildText(flHistory, historyList);
        }
    }

    /**
     * @param flowLayout
     * @param list
     */
    private void addChildText(FlowLayout flowLayout, List<String> list) {
        if (list != null) {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String text = iterator.next();
                addText(flowLayout, text, false);
            }
        }
    }

    /**
     * 向FlowLayout中添加数据
     * xx 是否添加在最前面
     */
    private void addText(FlowLayout flowLayout, String text, boolean xx) {
        final TextView tvChild = (TextView) LayoutInflater.from(this)
                .inflate(R.layout.view_textview_flowflag, flowLayout, false);
        tvChild.setText(text);
        tvChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flowKey = tvChild.getText().toString();
                EventBus.getDefault().post(new SearchHistoryEvent(flowKey));
            }
        });
        if (xx) {
            flowLayout.addView(tvChild, 0);
        } else {
            flowLayout.addView(tvChild);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_search;
    }

    @Override
    public ProductSearchContact.Presenter createPresenter() {
        return new ProductSearchPresenter();
    }

    @Override
    public ProductSearchContact.View createView() {
        return this;
    }


    @Override
    public void init() {

    }

    @Override
    public void setHotSearch(List<String> hotSearchList) {
        this.hotList =  hotSearchList;
    }

    @Override
    public void setHistorySearch(List<String> historySearch) {
        this.historyList = historySearch;
    }

    @OnClick({R2.id.tv_search_back, R2.id.iv_del_history})
    public void onClick(View view) {
        int result =  view.getId();
        if(result ==  R.id.tv_search_back){
            ActivityManager.getInstance().finishActivity(ProductSearchActivity.class);
        }else if(result == R.id.iv_del_history){
            dialog = new ConfirmDialog(ProductSearchActivity.this);
            dialog.setOnDeepConfirmListener(new ConfirmDialog.DeepConfirmListener() {
                @Override
                public void onSelectCancel() {
                    dialog.dismiss();
                }

                @Override
                public void onSelectEnsure() {
                    clearSearchHistory();
                    flHistory.removeAllViews();
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    /**
     * 删除搜索历史
     */
    private void clearSearchHistory() {
        getPresenter().saveSearchHistory(null);
        initSearchHistory(null);
    }
    /**
     * 获取搜索历史
     */
    private void initSearchHistory(List<String> list) {
        if (list != null && list.size() > 0) {
            rlSearchHistory.setVisibility(View.VISIBLE);
            llSearchHistory.setVisibility(View.VISIBLE);
            hisFlag = true;
        } else {
            rlSearchHistory.setVisibility(View.GONE);
            llSearchHistory.setVisibility(View.GONE);
            hisFlag = false;
        }
    }

    /**
     * 键盘搜索按钮
     */
    public void onEvent(SearchEvent event) {

        saveSearchHistory(event.getMsg());
    }

    /**
     * 先添加flowlayout
     * 后保存搜索历史
     */
    private void saveSearchHistory(String query) {
        List<String> list = historyList;

        boolean flag = false;

        if (list == null) {

            list = new ArrayList<>();
            list.add(0, query);
        } else {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String item = iterator.next();
                if (TextUtils.equals(query, item)) {
                    iterator.remove();
                    flag = true;
                }
            }
            list.add(0, query);
        }

        if (flag == false) {
            addText(flHistory, query, true);
        }

        int size = list.size();
        if (size > 10) { // 最多保存20条
            for (int i = size - 1; i >= 10; i--) {
                list.remove(i);
            }
        }
        getPresenter().saveSearchHistory(list);

        initSearchHistory(list);

        goTextSearchResultActivity(query);
    }

    private void goTextSearchResultActivity(String query) {
//        //1.0版本
//        //Intent intent = 的搜索结果页面(先注销掉)new Intent(DuTextSearchActivity.this, TextSearchResultActivity.class);
//        Intent intent = new Intent(DuTextSearchActivity.this, ProductDetailActivity.class);
//        intent.putExtra("searchKey", query);
//        startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
