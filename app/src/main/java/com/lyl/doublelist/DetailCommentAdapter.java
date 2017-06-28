package com.lyl.doublelist;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lyl on 2017/5/23.
 */

public class DetailCommentAdapter extends RecyclerView.Adapter<DetailCommentAdapter.CommentViewHolder> {

    public static final int COMMENT_TYPE_HOT = 1;
    public static final int COMMENT_TYPE_ALL = 2;

    private int mCommentType;
    private Context mContext;
    private List<CommentsBean> mData;

    public DetailCommentAdapter(Context context, List<CommentsBean> data, int commentType) {
        mContext = context;
        mData = data;
        mCommentType = commentType;
    }

    public void setData(List<CommentsBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_eassay_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        final CommentsBean data = mData.get(position);

        holder.name.setText(data.getUser_name());

        holder.time.setText(data.getCreate_time());

        holder.upImg.setColorFilter(Color.GRAY);

        holder.upNum.setText(String.valueOf(data.getDigg_count()));

        holder.content.setText(data.getText());

        // 如果是热门就 显示出更多
        if (COMMENT_TYPE_HOT == mCommentType) {
            holder.replyNum.setVisibility(View.VISIBLE);
            holder.replyNum.setText(mContext.getString(R.string.text_show_num_comment, data.getSecond_level_comments_count()));

            holder.replyNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(mContext, DetailCommentReplyActivity.class);
//                    mContext.startActivity(intent);
                }
            });
        } else {
            holder.replyNum.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mData != null && mData.size() > 0 ? mData.size() : 0;
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;
        TextView time;
        ImageView upImg;
        TextView upNum;
        TextView content;
        TextView replyNum;

        public CommentViewHolder(View view) {
            super(view);
            icon = (ImageView) view.findViewById(R.id.item_comment_icon);
            name = (TextView) view.findViewById(R.id.item_comment_name);
            time = (TextView) view.findViewById(R.id.item_comment_time);
            upImg = (ImageView) view.findViewById(R.id.item_comment_thumb_up_img);
            upNum = (TextView) view.findViewById(R.id.item_comment_up_num);
            content = (TextView) view.findViewById(R.id.item_comment_content);
            replyNum = (TextView) view.findViewById(R.id.item_comment_hot_comment);
        }
    }
}
