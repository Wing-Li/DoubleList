package com.lyl.doublelist;

/**
 * Created by lyl on 2017/6/6.
 */

public class CommentsBean {
    private String user_name;
    private String create_time;
    private String digg_count;
    private String text;
    private String second_level_comments_count;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getDigg_count() {
        return digg_count;
    }

    public void setDigg_count(String digg_count) {
        this.digg_count = digg_count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSecond_level_comments_count() {
        return second_level_comments_count;
    }

    public void setSecond_level_comments_count(String second_level_comments_count) {
        this.second_level_comments_count = second_level_comments_count;
    }
}
