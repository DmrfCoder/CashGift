package cn.xiaojii.cashgift.view.impl;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

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
public class MainActivity extends FragmentActivity implements IMainView, View.OnClickListener {


    private int[] imageViewArray = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int[] imageSelectedViewArray = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private List<Fragment> fragmentList = new ArrayList<>();


    private FriendsAndRelativesFragment friendsAndRelativesFragment;
    private RunningAccountFragment runningAccountFragment;
    private ProjectTableFragment projectTableFragment;
    private DiscoverFragment discoverFragment;
    private MoreFragment moreFragment;

    public MainPresenter mainPresenter;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private int curSelectTabIndex = 0;

    private ImageView[] imageViews = new ImageView[5];
    private TextView[] textViews = new TextView[5];
    private LinearLayout[] linearLayouts = new LinearLayout[5];


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

        friendsAndRelativesFragment = new FriendsAndRelativesFragment();
        runningAccountFragment = new RunningAccountFragment();
        projectTableFragment = new ProjectTableFragment();
        discoverFragment = new DiscoverFragment(mainPresenter);
        moreFragment = new MoreFragment(mainPresenter);


        fragmentList.add(friendsAndRelativesFragment);
        fragmentList.add(runningAccountFragment);
        fragmentList.add(projectTableFragment);
        fragmentList.add(discoverFragment);
        fragmentList.add(moreFragment);



    }



    //    控件初始化控件
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {


        findViewById(R.id.id_linear0).setOnClickListener(this);
        findViewById(R.id.id_linear1).setOnClickListener(this);
        findViewById(R.id.id_linear2).setOnClickListener(this);
        findViewById(R.id.id_linear3).setOnClickListener(this);
        findViewById(R.id.id_linear4).setOnClickListener(this);

        imageViews[0] = findViewById(R.id.id_image_zeroth);
        imageViews[1] = findViewById(R.id.id_image_first);
        imageViews[2] = findViewById(R.id.id_image_second);
        imageViews[3] = findViewById(R.id.id_image_third);
        imageViews[4] = findViewById(R.id.id_image_fourth);


        textViews[0] = findViewById(R.id.id_tx_zeroth);
        textViews[1] = findViewById(R.id.id_tx_first);
        textViews[2] = findViewById(R.id.id_tx_second);
        textViews[3] = findViewById(R.id.id_tx_third);
        textViews[4] = findViewById(R.id.id_tx_fourth);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initPage() {
        imageViews[0].setBackgroundResource(imageSelectedViewArray[curSelectTabIndex]);
        textViews[0].setTextColor(this.getResources().getColor(R.color.colorBlue, null));


        startfragment(fragmentList.get(0),false);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
        mainPresenter.onDestroy();

    }


    @Override
    public void startfragment(Fragment targetFragment,boolean addToBackStack) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.id_fragment_layout, targetFragment,targetFragment.getClass().getName());
        if (addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onTabChanged(int position) {
        if (position != curSelectTabIndex) {

            imageViews[curSelectTabIndex].setBackgroundResource(imageViewArray[curSelectTabIndex]);
            textViews[curSelectTabIndex].setTextColor(this.getResources().getColor(R.color.colorBlack, null));


            curSelectTabIndex = position;
            imageViews[curSelectTabIndex].setBackgroundResource(imageSelectedViewArray[curSelectTabIndex]);
            textViews[curSelectTabIndex].setTextColor(this.getResources().getColor(R.color.colorBlue, null));



            startfragment(fragmentList.get(curSelectTabIndex),false);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_linear0:
                onTabChanged(0);
                break;
            case R.id.id_linear1:
                onTabChanged(1);
                break;
            case R.id.id_linear2:
                onTabChanged(2);
                break;
            case R.id.id_linear3:
                onTabChanged(3);
                break;
            case R.id.id_linear4:
                onTabChanged(4);
                break;
            default:
                break;

        }

    }


}
