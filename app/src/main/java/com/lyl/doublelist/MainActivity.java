package com.lyl.doublelist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    NestedScrollView Nestedscrollview;
    RecyclerView RecyclerHot;
    RecyclerView RecyclerAll;

    private DetailCommentAdapter mHotCommentAdapter;
    private DetailCommentAdapter mAllCommentAdapter;

    private List<CommentsBean> mHotCommentsList = new ArrayList<>();
    private List<CommentsBean> mAllCommentsList = new ArrayList<>();

    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        initView();
        setView();
        getData();
    }

    private void initView() {
        Nestedscrollview = (NestedScrollView) findViewById(R.id.nestedscrollview);
        RecyclerHot = (RecyclerView) findViewById(R.id.recycler_hot);
        RecyclerAll = (RecyclerView) findViewById(R.id.recycler_all);
    }

    private void setView() {
        // 设置热门评论列表
        WrappingLinearLayoutManager wrappingLinearLayoutManager = new WrappingLinearLayoutManager(mContext);
        wrappingLinearLayoutManager.setAutoMeasureEnabled(false);// 如果导入的包是  Android Support Library 23.2.0  以上的，需要加这句
        RecyclerHot.setLayoutManager(wrappingLinearLayoutManager);

        mHotCommentAdapter = new DetailCommentAdapter(mContext, mHotCommentsList, DetailCommentAdapter.COMMENT_TYPE_HOT);
        RecyclerHot.setAdapter(mHotCommentAdapter);
        RecyclerHot.setNestedScrollingEnabled(false);


        // 设置全部评论列表
        WrappingLinearLayoutManager wrappingLinearLayoutManager2 = new WrappingLinearLayoutManager(mContext);
        wrappingLinearLayoutManager2.setAutoMeasureEnabled(false);// 如果导入的包是  Android Support Library 23.2.0  以上的，需要加这句
        RecyclerAll.setLayoutManager(wrappingLinearLayoutManager2);

        mAllCommentAdapter = new DetailCommentAdapter(mContext, mAllCommentsList, DetailCommentAdapter.COMMENT_TYPE_ALL);
        RecyclerAll.setAdapter(mAllCommentAdapter);
        RecyclerAll.setNestedScrollingEnabled(true);

        // 全部评论需要下拉刷新更多
        Nestedscrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    // 向下滑动
                }

                if (scrollY < oldScrollY) {
                    // 向上滑动
                }

                if (scrollY == 0) {
                    // 顶部
                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    // 底部
                    getData();
                }
            }
        });
    }


    private void getData() {
        if (page == 0) {
            List<CommentsBean> top_comments = new ArrayList<>();
            CommentsBean commentsBean;
            for (int i = 0; i < 6; i++) {
                commentsBean = new CommentsBean();
                commentsBean.setUser_name("美女t");
                commentsBean.setDigg_count("112");
                commentsBean.setText("今天放假终于可以去网吧了，做下来点了一杯水，打开网吧的点歌系统，点了一首我喜欢的white food，准备好好享受一下，为什么整个网吧的人都跑去吧台问这首手歌是多少号点的？是不是想偷我的歌单？" + i);
                commentsBean.setCreate_time("05-09 14:59");
                commentsBean.setSecond_level_comments_count(i * 2 + "");
                top_comments.add(commentsBean);
            }

            if (top_comments != null && top_comments.size() > 0) {
                mHotCommentsList.addAll(top_comments);
                mHotCommentAdapter.setData(mHotCommentsList);
            }
        }


        List<CommentsBean> recent_comments = new ArrayList<>();
        CommentsBean commentsBean;
        for (int i = 0; i < 15; i++) {
            commentsBean = new CommentsBean();
            if (i % 2 == 0) {
                commentsBean.setUser_name("美女t");
            } else {
                commentsBean.setUser_name("精选360");
            }

            commentsBean.setDigg_count("赞 " + (page * 15 + i + 1));
            commentsBean.setText("微信搜索公众号，关注我的名字。有惊喜哦O(∩_∩)O");
            commentsBean.setCreate_time("05-09 14:59");
            commentsBean.setSecond_level_comments_count(i * 2 + "");
            recent_comments.add(commentsBean);
        }
        if (recent_comments != null && recent_comments.size() > 0) {
            mAllCommentsList.addAll(recent_comments);
            mAllCommentAdapter.setData(mAllCommentsList);
            Toast.makeText(mContext, "新增15条", Toast.LENGTH_SHORT).show();
        }

        page++;
    }
}
