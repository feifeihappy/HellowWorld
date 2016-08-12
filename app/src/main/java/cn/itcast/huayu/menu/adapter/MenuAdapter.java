package cn.itcast.huayu.menu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.activity.MenuActivity;
import cn.itcast.huayu.menu.model.menu.StepsBean;

/**
 * @author ln：zpf on 2016/8/12
 */
public class MenuAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private final List<StepsBean> stepsData;
    private final MenuActivity menuActivity;

    public MenuAdapter(MenuActivity menuActivity, List<StepsBean> stepsData) {
        this.menuActivity = menuActivity;
        mInflater = menuActivity.getLayoutInflater();
        this.stepsData = stepsData;
    }

    /**
     * 获得 LayoutInflater 实例的三种方式
     * 在实际开发中LayoutInflater这个类还是非常有用的，它的作用类似于findViewById()。不同点是LayoutInflater是用来找res/layout/下的xml布局文件
     * LayoutInflater inflater = getLayoutInflater();  //调用Activity的getLayoutInflater()
     * <p/>
     * LayoutInflater localinflater =  (LayoutInflater)context.getSystemService（Context.LAYOUT_INFLATER_SERVICE);
     * <p/>
     * LayoutInflater inflater = LayoutInflater.from(context);
     */

    @Override
    public int getCount() {
        return stepsData.size();
    }

    @Override
    public Object getItem(int position) {
        return stepsData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("getView " + position + " " + convertView);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.menuadapter_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        StepsBean stepsDataValue = stepsData.get(position);
        holder.tvStep.setText(stepsDataValue.getStep());
        Glide
                .with(menuActivity)
                .load(stepsDataValue.getImg())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.ivImg);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_step)
        TextView tvStep;
        @BindView(R.id.iv_img)
        ImageView ivImg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
