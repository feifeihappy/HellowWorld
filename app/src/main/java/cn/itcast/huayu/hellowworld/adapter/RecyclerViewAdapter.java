package cn.itcast.huayu.hellowworld.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

import cn.itcast.huayu.hellowworld.R;
import cn.itcast.huayu.hellowworld.activity.FisterActivity;
import cn.itcast.huayu.hellowworld.activity.FisterActivity_;
import cn.itcast.huayu.hellowworld.model.menu.MenuDataVo;
import cn.itcast.huayu.hellowworld.model.menu.MenuListData;
import cn.itcast.huayu.hellowworld.model.menu.MenuResult;
import cn.itcast.huayu.hellowworld.util.ToastUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.greenrobot.event.EventBus;

/**
 * @author ln：zpf on 2016/7/30
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static FragmentActivity mcontext;
    private List<MenuDataVo> mData;

    public RecyclerViewAdapter(FragmentActivity activity, List<MenuDataVo> mData) {
        mcontext = activity;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(itemView,mData);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MenuDataVo ListData = mData.get(position);
        holder.getTextView().setText(ListData.getTitle());
        holder.mTextViewId.setText(String.valueOf(position));
        holder.mTvTags.setText(ListData.getTags());

        Glide.with(mcontext)
                .load(ListData.getAlbums().get(0))
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<MenuDataVo> mData) {
        this.mData = mData;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextView;
        private final TextView mTextViewId;
        private final TextView mTvTags;
        private final ImageView mImg;

        public ViewHolder(View itemView, final List<MenuDataVo> mData) {
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
                    EventBus.getDefault().post(new MenuListData(mData.get(getAdapterPosition())));
                    ToastUtil.showToast(mcontext, "第" + getPosition() + "item");
                    //简单的dialog
                  /*  new SweetAlertDialog(mcontext)
                            .setTitleText("Here's a message!")
                            .show();*/

                    new SweetAlertDialog(mcontext, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("你确定?")
                            .setContentText("FisterActivity".substring(6,14).replace("A","a"))
                            .setCancelText("取消")
                            .setConfirmText("是的")
                            .showCancelButton(true)
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.cancel();
                                }
                            })
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.cancel();
                                    Intent intent = new Intent(mcontext, FisterActivity_.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("mData", mData.get(getAdapterPosition()));
                                    intent.putExtras(bundle);
                                    mcontext.startActivity(intent);

                                }
                            })
                            .show();
                }
            });


        }

        public TextView getTextView() {
            return mTextView;
        }
    }
}
