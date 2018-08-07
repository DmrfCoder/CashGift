package cn.xiaojii.cashgift.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.bean.ProjectTableBean;

/**
 * @author dmrfcoder
 * @date 2018/8/7
 */

public class ProjectTableAdapter extends BaseAdapter {

    private String TAG="ProjectTableAdapter";

    private List<ProjectTableBean> projectTableBeanList;
    private Context context;

    public ProjectTableAdapter(Context context) {
        this.context = context;
        projectTableBeanList=new ArrayList<>();
    }

    public void setProjectTableBeanList(List<ProjectTableBean> projectTableBeanList) {
        this.projectTableBeanList = projectTableBeanList;
    }

    @Override
    public int getCount() {
        return projectTableBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return projectTableBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (view == null) {

            view = View.inflate(context, R.layout.item_projecttable, null);

            viewHolder = new ViewHolder(view);

            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ProjectTableBean projectTableBean = projectTableBeanList.get(i);



        viewHolder.txProjectName.setText(projectTableBean.getName());
        viewHolder.txSumMoneyIn.setText(projectTableBean.getStrSumMoneyIn());
        viewHolder.txSumMoneyOut.setText(projectTableBean.getStrSumMoneyOut());

        return view;
    }


    static class ViewHolder {
        public TextView txProjectName;
        private TextView txSumMoneyIn;
        private TextView txSumMoneyOut;

        public ViewHolder(View view) {
            this.txProjectName = view.findViewById(R.id.id_item_projecttable_projectname);
            this.txSumMoneyIn = view.findViewById(R.id.id_item_projecttable_summoney_in);
            this.txSumMoneyOut = view.findViewById(R.id.id_item_projecttable_summoney_out);
        }
    }
}
