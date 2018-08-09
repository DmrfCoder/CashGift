package cn.xiaojii.cashgift.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.bean.ProjectTableItemBean;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public class ProjectTableItemAdapter extends BaseAdapter {

    private List<ProjectTableItemBean> projectTableItemBeanList;
    private Context context;

    public void setProjectTableItemBeanList(List<ProjectTableItemBean> projectTableItemBeanList) {
        this.projectTableItemBeanList = projectTableItemBeanList;
    }

    public ProjectTableItemAdapter(Context context) {
        this.context = context;

        projectTableItemBeanList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return projectTableItemBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return projectTableItemBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_projecttableitem, null);

            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ProjectTableItemBean projectTableItemBean = projectTableItemBeanList.get(i);

        viewHolder.txPersonName.setText(projectTableItemBean.getPersonName());
        viewHolder.txTotalMoney.setText(projectTableItemBean.getStrTotalMoney());
        viewHolder.txInMoney.setText(projectTableItemBean.getStrInMoney());
        viewHolder.txOutMoney.setText(projectTableItemBean.getStrOutMoney());
        return view;
    }


    public class ViewHolder {
        public TextView txTotalMoney;
        public TextView txInMoney;
        public TextView txOutMoney;
        public TextView txPersonName;

        public ViewHolder(View view) {
            txInMoney = view.findViewById(R.id.id_item_projecttable_item_in_money);
            txOutMoney = view.findViewById(R.id.id_item_projecttable_item_out_money);
            txTotalMoney = view.findViewById(R.id.id_item_projecttable_item_total_money);
            txPersonName = view.findViewById(R.id.id_item_projecttable_item_name);

        }
    }
}
