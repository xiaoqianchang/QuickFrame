package com.changxiao.quickframe.bean;

import java.util.Date;

/**
 * 妹子model
 * 数据格式见：http://gank.io/api/data/all/20/2
 * <p>
 * Created by Chang.Xiao on 2016/7/27.
 *
 * @version 1.0
 */
public class Meizi {

    public boolean used;
    public String type;//干货类型，如Android，iOS，福利等
    public String url;//链接地址
    public String who;//作者
    public String desc;//干货内容的描述
    public Date createdAt;
    public Date updatedAt;
    public Date publishedAt;

    @Override
    public String toString() {
        return "Meizi{" +
                "used=" + used +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", who='" + who + '\'' +
                ", desc='" + desc + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", publishedAt=" + publishedAt +
                '}';
    }
}
