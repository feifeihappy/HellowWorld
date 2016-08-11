package cn.itcast.huayu.menu.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.model.menu.MenuDataVo;
import cn.itcast.huayu.menu.util.LogUtil;
import cn.itcast.huayu.menu.util.ToastUtil;

/**
 * @author ln：zpf on 2016/7/30
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static FragmentActivity mcontext;
    private final LayoutInflater mLayoutInflater;
    static Callback mCallback = null;
    private List<MenuDataVo> mData;
    public static final int ITEM_TYPE_HEADER = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    public static final int ITEM_TYPE_CONTENT = 3;
    private int mHeaderCount = 1;
    private int mBottomCount = 1;
    private View itemView;

    public RecyclerViewAdapter(FragmentActivity activity, List<MenuDataVo> mData, Callback mCallback) {
        mLayoutInflater = LayoutInflater.from(activity);
        mcontext = activity;
        this.mData = mData;
        this.mCallback = mCallback;
    }

    public interface Callback {
        public void viewClick(View v);
    }


    @Override
    public int getItemViewType(int position) {
        if (mHeaderCount != 0 && position < mHeaderCount) {
            return ITEM_TYPE_HEADER;//头view
        } else if (mBottomCount != 0 && position >= (getDataSize() + mHeaderCount)) {
            return ITEM_TYPE_BOTTOM;//尾view
        } else {
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.item_recyclerview_header, parent, false));
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            return new BottomViewHolder(mLayoutInflater.inflate(R.layout.item_recyclerview_bottom, parent, false));
        } else if (viewType == ITEM_TYPE_CONTENT) {
            itemView = mLayoutInflater.inflate(R.layout.item_recyclerview, parent, false);
            LogUtil.getInstance().error(String.valueOf(itemView));
            return new VViewHolder(itemView, mData);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VViewHolder) {
            MenuDataVo ListData = mData.get(position - 1);
            ((VViewHolder) holder).getTextView().setText(ListData.getTitle());
            ((VViewHolder) holder).mTextViewId.setText(String.valueOf(position));
            ((VViewHolder) holder).mTvTags.setText(ListData.getTags());

            Glide.with(mcontext)
                    .load(ListData.getAlbums().get(0))
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade()
                    .into(((VViewHolder) holder).mImg);
            itemView.setTag(position);
        } else if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof BottomViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return getDataSize() + mHeaderCount + mBottomCount;
    }


    public int getDataSize() {
        return mData.size();
    }

    public void setData(List<MenuDataVo> mData) {
        this.mData = mData;
    }


    public static class VViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextView;
        private final TextView mTextViewId;
        private final TextView mTvTags;
        private final ImageView mImg;

        public VViewHolder(View itemView, final List<MenuDataVo> mData) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.tv_title);
            mTextViewId = (TextView) itemView.findViewById(R.id.tv_id);
            mTvTags = (TextView) itemView.findViewById(R.id.tv_tags);
            mImg = (ImageView) itemView.findViewById(R.id.iv);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ToastUtil.showToast(mcontext, "第" + getPosition() + "item");
                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer m = (Integer) v.getTag();
                    mCallback.viewClick(v);

                }
            });


        }
        public TextView getTextView() {
            return mTextView;
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View headerView) {
            super(headerView);
        }
    }

    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
