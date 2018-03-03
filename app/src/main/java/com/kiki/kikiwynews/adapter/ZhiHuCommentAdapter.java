package com.kiki.kikiwynews.adapter;

import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.bean.zhihu.CommentBean;
import com.kiki.kikiwynews.utils.DateUtil;
import com.kiki.kikiwynews.utils.GlideUtils;

import java.util.List;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by KIKI on 2018/1/21.
 * 346409606@qq.com
 */

public class ZhiHuCommentAdapter extends BaseQuickAdapter<CommentBean.CommentsBean,BaseViewHolder>{
    private TextView tvCommentReply;
    private TextView tvCommentExpand;

    /**
     * 未知
     */
    private static final int STATE_NULL=0;

    /**
     * 无需展开
     */
    private static final int STATE_NONE =1;

    /**
     * 已展开
     */
    private static final int STATE_EXPAND = 2;

    /**
     * 已收缩
     */
    private static final int STATE_SHRINK = 3;

    /**
     * 起始最多显示2行
     */
    private static final int MAX_LINE = 2;

    public ZhiHuCommentAdapter(List<CommentBean.CommentsBean> data) {
        super(R.layout.item_zhihu_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean.CommentsBean item) {
        GlideUtils.load(Utils.getContext(),item.getAvatar(), (ImageView) helper.getView(R.id.civ_comment_face));
        helper.setText(R.id.tv_comment_name,item.getAuthor());
        helper.setText(R.id.tv_comment_content,item.getContent());
        helper.setText(R.id.tv_comment_time, DateUtil.formatTime2String(item.getTime()));
        helper.setText(R.id.tv_comment_like, String.valueOf(item.getLikes()));

        tvCommentReply=helper.getView(R.id.tv_comment_reply);
        tvCommentExpand=helper.getView(R.id.tv_comment_expand);
        if(item.getReply_to()!=null && item.getReply_to().getId()!=0){
            tvCommentReply.setVisibility(View.VISIBLE);
            SpannableString ss=new SpannableString("@" + item.getReply_to().getAuthor() + ": " + item.getReply_to().getContent());
            ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext,R.color.comment_at)),0,item.getReply_to().getAuthor().length()+2,SPAN_INCLUSIVE_INCLUSIVE);
            tvCommentReply.setText(ss);
            if(item.getReply_to().getExpandState()==STATE_NONE){
                tvCommentExpand.setVisibility(View.GONE);
            }else if(item.getReply_to().getExpandState()==STATE_EXPAND){
                tvCommentReply.setMaxLines(Integer.MAX_VALUE);
                tvCommentExpand.setText("收起");
                tvCommentExpand.setVisibility(View.VISIBLE);
                tvCommentExpand.setOnClickListener(new OnStateClickListener(helper.getAdapterPosition(), tvCommentReply));
            }else {    //已收缩
                tvCommentReply.setMaxLines(MAX_LINE);
                tvCommentExpand.setText("展开");
                tvCommentExpand.setVisibility(View.VISIBLE);
                tvCommentExpand.setOnClickListener(new OnStateClickListener(helper.getAdapterPosition(),tvCommentReply));
            }
        } else {
            tvCommentReply.setVisibility(View.GONE);
            tvCommentExpand.setVisibility(View.GONE);
        }
    }

    private class OnStateClickListener implements View.OnClickListener{

        TextView replyView;
        int position;

        public OnStateClickListener(int position,TextView replyView){
            this.position=position;
            this.replyView=replyView;
        }

        @Override
        public void onClick(View view) {
            TextView tv= (TextView) view;
            if(mData.get(position).getReply_to().getExpandState()==STATE_SHRINK){
                tv.setText("收起");
                replyView.setMaxLines(Integer.MAX_VALUE);
                replyView.setEllipsize(null);
                mData.get(position).getReply_to().setExpandState(STATE_EXPAND);
            }else{
                tv.setText("展开");
                replyView.setMaxLines(MAX_LINE);
                replyView.setEllipsize(TextUtils.TruncateAt.END);
                mData.get(position).getReply_to().setExpandState(STATE_SHRINK);
            }
        }
    }
}
