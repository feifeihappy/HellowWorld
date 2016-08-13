package cn.itcast.huayu.menu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.activity.MenuActivity;
import cn.itcast.huayu.menu.model.menu.StepsBean;

/**
 * Created by hao on 2016/8/13.
 */
public class MenuRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final MenuActivity mMenuActivity;
    private final LayoutInflater mLayoutInflater;
    private final List<StepsBean> StepsData;

    private View mItemView;

    public MenuRecycleViewAdapter(MenuActivity menuActivity, List<StepsBean> stepsData) {
        this.StepsData = stepsData;
        this.mMenuActivity = menuActivity;
        mLayoutInflater = mMenuActivity.getLayoutInflater();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mItemView = mLayoutInflater.inflate(R.layout.item_menurecycleview, parent, false);
        MyViewHolder mMyVeiwHolder = new MyViewHolder(mItemView);
        return mMyVeiwHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StepsBean mStepsData = StepsData.get(position);

        Glide.with(mMenuActivity)
                .load(mStepsData.getImg())
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(((MyViewHolder) holder).img);
        ((MyViewHolder) holder).tvStep.setText(mStepsData.getStep());
    }

    @Override
    public int getItemCount() {
        return StepsData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_step)
        TextView tvStep;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
