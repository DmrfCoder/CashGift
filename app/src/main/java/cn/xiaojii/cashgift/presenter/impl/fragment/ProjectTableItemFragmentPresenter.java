package cn.xiaojii.cashgift.presenter.impl.fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.xiaojii.cashgift.bean.global.ConstantsBean;
import cn.xiaojii.cashgift.bean.fragment.ProjectBean;
import cn.xiaojii.cashgift.bean.message.ProjectListMessageBean;
import cn.xiaojii.cashgift.interactor.inter.fragment.IProjectTableItemInteractor;
import cn.xiaojii.cashgift.interactor.impl.ProjectTableItemFragmentInteractor;
import cn.xiaojii.cashgift.presenter.inter.base.IBaseFragmentPresenter;
import cn.xiaojii.cashgift.presenter.inter.fragment.IProjectTableItemPresenter;
import cn.xiaojii.cashgift.view.inter.fragment.IProjectTableItemView;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public class ProjectTableItemFragmentPresenter implements IProjectTableItemPresenter, IBaseFragmentPresenter, IProjectTableItemInteractor.UpdatePtiViewListener {
    private IProjectTableItemView iProjectTableItemView;
    private ProjectTableItemFragmentInteractor projectTableItemInteractor;

    public ProjectTableItemFragmentPresenter(IProjectTableItemView iProjectTableItemView, ProjectTableItemFragmentInteractor projectTableItemInteractor) {
        this.iProjectTableItemView = iProjectTableItemView;
        this.projectTableItemInteractor = projectTableItemInteractor;
        EventBus.getDefault().register(this);
    }

    @Override
    public void addProjectFromDialog(ProjectBean projectBean) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void initDataFromMainInteractor(ProjectListMessageBean projectListMessageBean) {
        if (projectListMessageBean.getTag().equals(ConstantsBean.TAG_PROJECTTABLE)) {
            projectTableItemInteractor.initData(projectListMessageBean.getProjectBeans(), this);
        } else if (projectListMessageBean.getTag().equals(ConstantsBean.TAG_MAINPRESENTER)) {
            List<ProjectBean> projectBeans = projectListMessageBean.getProjectBeans();
            ProjectBean projectBean = projectBeans.get(projectBeans.size() - 1);
            projectTableItemInteractor.addSignalProjectBean(projectBean,this);
        }

    }

    @Override
    public void updateView() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onUpdateViewError() {

    }

    @Override
    public void onUpdateViewSuccess(List dataList, int inCount, int inMoney, int outCount, int outMoney, int sumMoney, String personName) {
        iProjectTableItemView.updateListView(dataList);
        iProjectTableItemView.updateView(inCount, inMoney, outCount, outMoney, sumMoney, personName);

    }
}
