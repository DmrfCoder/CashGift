package cn.xiaojii.cashgift.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author dmrfcoder
 * @date 2018/8/4
 */

public class ParcelableListBean implements Parcelable {
    private List<ProjectBean> projectBeanList;

    public ParcelableListBean(List<ProjectBean> projectBeanList) {
        this.projectBeanList = projectBeanList;
    }

    public void setProjectBeanList(List<ProjectBean> projectBeanList) {
        this.projectBeanList = projectBeanList;
    }

    public List<ProjectBean> getProjectBeanList() {

        return projectBeanList;
    }

    protected ParcelableListBean(Parcel in) {
    }

    public static final Creator<ParcelableListBean> CREATOR = new Creator<ParcelableListBean>() {
        @Override
        public ParcelableListBean createFromParcel(Parcel in) {
            return new ParcelableListBean(in);
        }

        @Override
        public ParcelableListBean[] newArray(int size) {
            return new ParcelableListBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
