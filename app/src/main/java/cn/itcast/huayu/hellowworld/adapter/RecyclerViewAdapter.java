package cn.itcast.huayu.hellowworld.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.itcast.huayu.hellowworld.R;
import cn.itcast.huayu.hellowworld.activity.FisterActivity_;
import cn.itcast.huayu.hellowworld.util.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * @author ln：zpf on 2016/7/30
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private  String[] mData;
    private  static FragmentActivity mcontext;

    public RecyclerViewAdapter(FragmentActivity activity, String[] mData) {
        mcontext = activity;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTextView().setText(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextView;

        public ViewHolder( View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(mcontext, "第" + getPosition() + "item");
//                    FisterActivity_.intent(mcontext).start();
                    EventBus.getDefault().post("呵呵");
                }
            });
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
        public TextView getTextView() {
            return mTextView;
        }
    }
}
