package cn.xiaojii.cashgift.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.bean.fragment.ProjectBean;

/**
 * @author dmrfcoder
 * @date 2018/8/4
 */

public class RunningAccountListViewAdapter extends BaseAdapter {
    //ProjectBean其实就是RunningAccountBean


    private List<ProjectBean> projectBeanList;
    private Context context;


    public RunningAccountListViewAdapter(Context context) {
        this.context = context;
        projectBeanList = new ArrayList<>();
    }

    public List<ProjectBean> getProjectBeanList() {
        return projectBeanList;
    }

    public void setProjectBeanList(List<ProjectBean> projectBeanList) {
        if (projectBeanList==null){
            return;
        }
        this.projectBeanList = projectBeanList;
    }


    @Override
    public int getCount() {
        return projectBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return projectBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        RunningAccountListViewAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_runningaccount, null);
            holder = new RunningAccountListViewAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RunningAccountListViewAdapter.ViewHolder) convertView.getTag();
        }
        ProjectBean projectBean = projectBeanList.get(i);


        holder.txName.setText(projectBean.getName());
        holder.txSumMoney.setText(projectBean.getMoney());
        holder.txYear.setText(projectBean.getYear());
        holder.txMonthAndDay.setText(projectBean.getMonthAndDay());
        holder.txProject.setText(projectBean.getProject());

        return convertView;
    }


    static class ViewHolder {


        public TextView txYear;
        public TextView txMonthAndDay;
        public TextView txName;
        public TextView txProject;
        public TextView txSumMoney;


        ViewHolder(View view) {
            this.txYear = view.findViewById(R.id.id_item_running_time_year);
            this.txMonthAndDay = view.findViewById(R.id.id_item_running_time_month_day);
            this.txName = view.findViewById(R.id.id_item_running_name);
            this.txProject = view.findViewById(R.id.id_item_running_project);
            this.txSumMoney=view.findViewById(R.id.id_item_runing_sum_money);
        }
    }

}
