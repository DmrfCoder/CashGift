package cn.xiaojii.cashgift.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.ProjectBean;

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
            convertView = View.inflate(context, R.layout.item_friendandrelatives, null);
            holder = new RunningAccountListViewAdapter().ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RunningAccountListViewAdapter.ViewHolder) convertView.getTag();
        }
        ProjectBean projectBean = projectBeanList.get(i);


        holder.name.setText(friendsAndRelativesBean.getName());
        holder.inOutCount.setText(friendsAndRelativesBean.getIn() + "/" + friendsAndRelativesBean.getOut());
        holder.sumMoney.setText(friendsAndRelativesBean.getSumMoney()+"");

        return convertView;
    }


    static class ViewHolder {


        public TextView name;
        public TextView inOutCount;
        public TextView sumMoney;


        ViewHolder(View view) {
            this.name = view.findViewById(R.id.id_item_friend_name);
            this.inOutCount = view.findViewById(R.id.id_item_friend_inout_count);
            this.sumMoney = view.findViewById(R.id.id_item_friend_sum_money);
        }
    }

}
