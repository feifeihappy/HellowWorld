package cn.itcast.huayu.menu.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.greendao.User;

/**
 * @author ln：zpf on 2016/8/19
 */
public class FragmentTwoListAdapter extends BaseAdapter {
    private List<User> mUser;
    private final LayoutInflater activity;

    public FragmentTwoListAdapter(List<User> mUser, FragmentActivity activity) {
        this.mUser = mUser;
        this.activity = activity.getLayoutInflater();
    }

    public void setData(List<User> mUserData) {
        mUser = mUserData;
    }

    @Override
    public int getCount() {
        return mUser == null ? 0 : mUser.size();
    }

    @Override
    public Object getItem(int position) {
        return mUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = activity.inflate(R.layout.itme_fragmenttwo, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        User mData = mUser.get(position);
        holder.tv01.setText(mData.getName());
        holder.tv02.setText(mData.getTime());


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_01)
        TextView tv01;
        @BindView(R.id.tv_02)
        TextView tv02;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
