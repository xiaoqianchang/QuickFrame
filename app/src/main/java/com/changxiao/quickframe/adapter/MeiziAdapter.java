package com.changxiao.quickframe.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changxiao.quickframe.R;
import com.changxiao.quickframe.bean.Meizi;
import com.changxiao.quickframe.utils.ZRDateUtils;
import com.changxiao.quickframe.widget.RatioImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/7/27.
 *
 * @version 1.0
 */
public class MeiziAdapter extends RecyclerView.Adapter<MeiziAdapter.MeiziHolder> {

    private Context context;
    private List<Meizi> meizis;
    private int lastPosition = 0;

    public MeiziAdapter(Context context, List<Meizi> meizis) {
        this.context = context;
        this.meizis = meizis;
    }

    @Override
    public MeiziHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meizi, parent, false);
        return new MeiziHolder(view);
    }

    @Override
    public void onBindViewHolder(MeiziHolder holder, int position) {
        Meizi meizi = meizis.get(position);
        holder.card.setTag(meizi);
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        holder.ivMeizi.setBackgroundColor(Color.argb(204, red, green, blue));
        Glide.with(context)
                .load(meizi.url)
                .crossFade()
                .into(holder.ivMeizi);

        holder.tvWho.setText(meizi.who);
        holder.tvAvatar.setText(meizi.who.substring(0, 1).toUpperCase());
        holder.tvDesc.setText(meizi.desc);
        holder.tvTime.setText(ZRDateUtils.formatTime(meizi.publishedAt.getTime(), ZRDateUtils.TIME_FORMAT3));
        holder.tvAvatar.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return meizis == null ? 0 : meizis.size();
    }

    private void showItemAnimation(MeiziHolder holder, int position) {
        if (position > lastPosition) {
            lastPosition = position;
            ObjectAnimator.ofFloat(holder.card, "translationY", 1f * holder.card.getHeight(), 0f)
                    .setDuration(500)
                    .start();
        }
    }

    class MeiziHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_meizi)
        RatioImageView ivMeizi;
        @Bind(R.id.tv_who)
        TextView tvWho;
        @Bind(R.id.tv_avatar)
        TextView tvAvatar;
        @Bind(R.id.tv_desc)
        TextView tvDesc;
        @Bind(R.id.tv_time)
        TextView tvTime;

        @OnClick(R.id.iv_meizi)
        void meiziClick() {
        }

        @OnClick(R.id.rl_gank)
        void gankClick() {
        }

        View card;

        public MeiziHolder(View itemView) {
            super(itemView);
            card = itemView;
            ButterKnife.bind(this, itemView);
            ivMeizi.setOriginalSize(300,150);
        }
    }
}
