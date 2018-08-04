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
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.impl.MainInterator;
import cn.xiaojii.cashgift.presenter.impl.MainPresenter;
import cn.xiaojii.cashgift.util.PermissionUtil;
import cn.xiaojii.cashgift.inter.IBaseFragmentView;
import cn.xiaojii.cashgift.view.IMainView;
import cn.xiaojii.cashgift.widght.NoScrollViewPager;

/**
 * @author dmrfcoder
 * @date 2018/8/2
 */

@SuppressLint("Registered")
public class MainActivity extends FragmentActivity implements IMainView, TabHost.OnTabChangeListener, IMainView.OnAddProjectInFragmentListener {


    public FragmentTabHost fragmentTabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = {FriendsAndRelativesFragment.class, RunningAccountFragment.class, ProjectTableFragment.class, DiscoverFragment.class, MoreFragment.class};
    private int imageViewArray[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int imageSelectedViewArray[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private String textViewArray[] = {"亲友团", "流水账", "项目表", "发现", "更多"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private NoScrollViewPager vp;


    private FriendsAndRelativesFragment friendsAndRelativesFragment;
    private RunningAccountFragment runningAccountFragment;
    private ProjectTableFragment projectTableFragment;
    private DiscoverFragment discoverFragment;
    private MoreFragment moreFragment;

    public MainPresenter mainPresenter;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtil.RequestPermission(this);
        init();


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {
        initData();
        initView();
        initPage();
    }

    private void initData() {
        mainPresenter = new MainPresenter(this, new MainInterator());
        mainPresenter.initActivityData();
    }

    @Override
    public void startfragment(IBaseFragmentView targetFragment) {

    }

    //    控件初始化控件
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {


        friendsAndRelativesFragment = new FriendsAndRelativesFragment(mainPresenter);
        runningAccountFragment = new RunningAccountFragment(mainPresenter);
        projectTableFragment = new ProjectTableFragment(mainPresenter);
        discoverFragment = new DiscoverFragment(mainPresenter);
        moreFragment = new MoreFragment(mainPresenter);

        fragmentList.add(friendsAndRelativesFragment);
        fragmentList.add(runningAccountFragment);
        fragmentList.add(projectTableFragment);
        fragmentList.add(discoverFragment);
        fragmentList.add(moreFragment);


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


        vp.setAdapter(new FragmentAdapter(getSupportFragmentManager(), mainPresenter));
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

    @Override
    public void onAddProjectInFragmentSuccess(ProjectBean projectBean) {
        mainPresenter.addProject(projectBean);
    }

    @Override
    public void onAddProjectInFragmentError() {

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        mainPresenter.onDestroy();

    }
}
