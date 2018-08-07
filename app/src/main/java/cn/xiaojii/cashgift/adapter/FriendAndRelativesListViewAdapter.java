package cn.xiaojii.cashgift.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class FriendAndRelativesListViewAdapter extends BaseAdapter {

    private List<FriendsAndRelativesBean> friendsAndRelativesBeanList;
    private Context context;

    public void setFriendsAndRelativesBeanList(List<FriendsAndRelativesBean> friendsAndRelativesBeanList) {
        this.friendsAndRelativesBeanList = friendsAndRelativesBeanList;
    }

    public FriendAndRelativesListViewAdapter(Context context) {
        this.context = context;
        friendsAndRelativesBeanList=new ArrayList<>();
    }

    public FriendAndRelativesListViewAdapter(Context context, List<FriendsAndRelativesBean> friendsAndRelativesBeanList) {
        this.context = context;
        this.friendsAndRelativesBeanList = friendsAndRelativesBeanList;
    }

    @Override
    public int getCount() {
        return friendsAndRelativesBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return friendsAndRelativesBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_friendandrelatives, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FriendsAndRelativesBean friendsAndRelativesBean = friendsAndRelativesBeanList.get(position);
        holder.name.setText(friendsAndRelativesBean.getName());
        holder.inOutCount.setText(friendsAndRelativesBean.getIn() + "/" + friendsAndRelativesBean.getOut());
        holder.sumMoney.setText(friendsAndRelativesBean.getStrSumMoney());

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
