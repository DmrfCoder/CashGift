package cn.xiaojii.cashgift.view.impl;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.adapter.FragmentAdapter;
import cn.xiaojii.cashgift.interactor.MainInterator;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.presenter.impl.MainPresenter;
import cn.xiaojii.cashgift.util.PermissionUtil;
import cn.xiaojii.cashgift.view.IBaseView;
import cn.xiaojii.cashgift.view.IMainView;
import cn.xiaojii.cashgift.widght.NoScrollViewPager;

/**
 * @author dmrfcoder
 * @date 2018/8/2
 */

@SuppressLint("Registered")
public class MainActivity extends FragmentActivity implements IMainView, TabHost.OnTabChangeListener {


    private FragmentTabHost fragmentTabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = {FriendsAndRelativesFragment.class, RunningAccountFragment.class, ProjectTableFragment.class, DiscoverFragment.class, MoreFragment.class};
    private int imageViewArray[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int imageSelectedViewArray[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private String textViewArray[] = {"亲友团", "流水账", "项目表", "发现", "更多"};
    private List<BaseFragment> fragmentList = new ArrayList<>();
    private NoScrollViewPager vp;


    private FriendsAndRelativesFragment friendsAndRelativesFragment = new FriendsAndRelativesFragment();
    private RunningAccountFragment runningAccountFragment = new RunningAccountFragment();
    private ProjectTableFragment projectTableFragment = new ProjectTableFragment();
    private DiscoverFragment discoverFragment = new DiscoverFragment();
    private MoreFragment moreFragment = new MoreFragment();

    public IMainPresenter mainPresenter;

    public BaseFragment CurFragment;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtil.RequestPermission(this);
        mainPresenter = new MainPresenter(this, new MainInterator());
        initView();
        initPage();
        initData();
    }

    private void initData() {
        mainPresenter.initData();
    }

    @Override
    public void startfragment(IBaseView targetFragment) {

    }

    //    控件初始化控件
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {


        vp = (NoScrollViewPager) findViewById(R.id.id_viewpaper);


        layoutInflater = LayoutInflater.from(this);

        fragmentTabHost = (FragmentTabHost) findViewById(R.id.id_tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.id_viewpaper);
        fragmentTabHost.setOnTabChangedListener(this);

        int tabCount = textViewArray.length;

        for (int tabIndex = 0; tabIndex < tabCount; tabIndex++) {
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(textViewArray[tabIndex])
                    .setIndicator(getTabItemView(tabIndex));
            fragmentTabHost.addTab(tabSpec, fragmentArray[tabIndex], null);
            fragmentTabHost.setTag(tabIndex);
            fragmentTabHost.getTabWidget().getChildAt(tabIndex).setBackgroundColor(this.getResources().getColor(R.color.colorWhite, null));
        }
    }

    private void initPage() {


        fragmentList.add(friendsAndRelativesFragment);
        fragmentList.add(runningAccountFragment);
        fragmentList.add(projectTableFragment);
        fragmentList.add(discoverFragment);
        fragmentList.add(moreFragment);
        CurFragment = fragmentList.get(0);
        vp.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
    }

    private View getTabItemView(int tabIndex) {
        View view = layoutInflater.inflate(R.layout.content_bottomtab, null);
        ImageView mImageView = (ImageView) view
                .findViewById(R.id.id_tab_icon);
        TextView mTextView = (TextView) view.findViewById(R.id.id_tab_name);
        mImageView.setBackgroundResource(imageViewArray[tabIndex]);
        mTextView.setText(textViewArray[tabIndex]);
        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onTabChanged(String tabId) {


        int position = fragmentTabHost.getCurrentTab();

        CurFragment = fragmentList.get(position);
        for (int i = 0; i < fragmentTabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) fragmentTabHost.getTabWidget().getChildAt(i).findViewById(R.id.id_tab_name);
            ImageView iv = (ImageView) fragmentTabHost.getTabWidget().getChildAt(i).findViewById(R.id.id_tab_icon);


            if (i == position) {
                iv.setBackgroundResource(imageSelectedViewArray[i]);
                tv.setTextColor(this.getResources().getColor(R.color.colorBlue, null));

            } else {//不选中
                iv.setBackgroundResource(imageViewArray[i]);
                tv.setTextColor(this.getResources().getColor(R.color.colorBlack, null));

            }
        }


        vp.setCurrentItem(position);
    }

}
