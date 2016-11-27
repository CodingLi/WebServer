package utils;

import java.util.List;


/**
 * Created by Keen on 10/18/2016.
 */
public class ExecuteSql {


    private ContactBean contactBean;

    public ExecuteSql(){
        contactBean = new ContactBean();
    }




    /**
     * 获取资讯表中的相关内容
     * @param index
     * @return
     */
    public List<Object> getZixunList(String index){

        Integer[] params = new Integer[]{Integer.parseInt(index)};
        String strSql = "select zixun_id,zixun_title,zixun_digest,zixun_image_link,zixun_source,zixun_publish_time,zixun_comment_count from ns_zixun where zixun_id<? order by zixun_id desc limit 0,30";
        //index==0 取前30条最新数据
        if(index.equals("0")){
            strSql = "select zixun_id,zixun_title,zixun_digest,zixun_image_link,zixun_source,zixun_publish_time,zixun_comment_count from ns_zixun order by zixun_id desc limit 0,30";
            params = null;
        }
        return contactBean.excuteQuery(strSql, params);
    }
    public List<Object> getZixunContent(String id){

        //news_id超过一定值parseInt会解析出错，上同
        Integer[] params = new Integer[]{Integer.parseInt(id)};
        String strSql = "select zixun_content from ns_zixun where zixun_id=?";
        return contactBean.excuteQuery(strSql, params);
    }


    /**
     * 获取推荐表中的相关内容
     * @param index
     * @return
     */
    public List<Object> getTuijianList(String index){

        Integer[] params = new Integer[]{Integer.parseInt(index)};
        String strSql = "select tuijian_id,tuijian_title,tuijian_digest,tuijian_image_link,tuijian_source,tuijian_publish_time,tuijian_comment_count from ns_tuijian where tuijian_id<? order by tuijian_id desc limit 0,30";
        //index==0 取前30条最新数据
        if(index.equals("0")){
            strSql = "select tuijian_id,tuijian_title,tuijian_digest,tuijian_image_link,tuijian_source,tuijian_publish_time,tuijian_comment_count from ns_tuijian order by tuijian_id desc limit 0,30";
            params = null;
        }
        return contactBean.excuteQuery(strSql, params);
    }
    public List<Object> getTuijianContent(String id){

        //news_id超过一定值parseInt会解析出错，上同
        Integer[] params = new Integer[]{Integer.parseInt(id)};
        String strSql = "select tuijian_content from ns_tuijian where tuijian_id=?";
        return contactBean.excuteQuery(strSql, params);
    }

    /**
     * 获取热点表中的内容
     * @param index
     * @return
     */
    public List<Object> getRedianList(String index){

        Integer[] params = new Integer[]{Integer.parseInt(index)};
        String strSql = "select redian_id,redian_title,redian_digest,redian_image_link,redian_source,redian_publish_time,redian_comment_count from ns_redian where redian_id<? order by redian_id desc limit 0,30";
        //index==0 取前30条最新数据
        if(index.equals("0")){
            strSql = "select redian_id,redian_title,redian_digest,redian_image_link,redian_source,redian_publish_time,redian_comment_count from ns_redian order by redian_id desc limit 0,30";
            params = null;
        }
        return contactBean.excuteQuery(strSql, params);
    }
    public List<Object> getRedianContent(String id){

        //news_id超过一定值parseInt会解析出错，上同
        Integer[] params = new Integer[]{Integer.parseInt(id)};
        String strSql = "select redian_content from ns_redian where redian_id=?";
        return contactBean.excuteQuery(strSql, params);
    }

    /**
     * 获取指数表中的内容
     * @param index
     * @return
     */
    public List<Object> getZhishuList(String index){

        Integer[] params = new Integer[]{Integer.parseInt(index)};
        String strSql = "select zhishu_id,zhishu_title,zhishu_digest,zhishu_image_link,zhishu_source,zhishu_publish_time,zhishu_comment_count from ns_zhishu where zhishu_id<? order by zhishu_id desc limit 0,30";
        //index==0 取前30条最新数据
        if(index.equals("0")){
            strSql = "select zhishu_id,zhishu_title,zhishu_digest,zhishu_image_link,zhishu_source,zhishu_publish_time,zhishu_comment_count from ns_zhishu order by zhishu_id desc limit 0,30";
            params = null;
        }
        return contactBean.excuteQuery(strSql, params);
    }
    public List<Object> getZhishuContent(String id){

        //news_id超过一定值parseInt会解析出错，上同
        Integer[] params = new Integer[]{Integer.parseInt(id)};
        String strSql = "select zhishu_content from ns_zhishu where zhishu_id=?";
        return contactBean.excuteQuery(strSql, params);
    }




    //下面的函数需要改动
    /**
     *
     * @param news_id  该文章下的评论
     * @param count  显示数目
     * @return
     */
    public List<Object> getComment(String news_id, String count){

        Integer[] params = new Integer[]{Integer.parseInt(news_id), Integer.parseInt(count)};
        String strSql = "select comment_id,news_id,user_id,user_name,comment_date,comment_content,like_count,reply_count from ns_comments where news_id = ? order by news_id asc limit 0,?";
        return contactBean.excuteQuery(strSql, params);
    }

    /**
     * 向数据库中添加一条评论
     *
     */
    public void addComment(String news_id){

    }

    /**
     * 删除指定的评论
     * @param comment_id
     */
    public void delComment(String comment_id){

        Integer[] params = new Integer[]{Integer.parseInt(comment_id)};
        String strSql = "delete from ns_comments where comment_id = ?";
        contactBean.executeUpdate(strSql, params);
    }

    /**
     *评论赞的数量加1
     */
    public void addLikeCount(String comment_id){

        Integer[] params = new Integer[]{Integer.parseInt(comment_id)};
        String strSql = "update ns_comments set like_count = like_count + 1 where comment_id=?";
        contactBean.executeUpdate(strSql, params);

    }

    /**
     * 评论赞的数目减1
     * @param comment_id
     */
    public void decreaseLikeCount(String comment_id){
        Integer[] params = new Integer[]{Integer.parseInt(comment_id)};
        String strSql = "update ns_comments set like_count = like_count - 1 where comment_id=?";
        contactBean.executeUpdate(strSql, params);
    }

    /**
     * 回复数加一
     */
    public void addReplyCount(String comment_id){

        Integer[] params = new Integer[]{Integer.parseInt(comment_id)};
        String strSql = "update ns_comments set reply_count = reply_count + 1 where comment_id=?";
        contactBean.executeUpdate(strSql, params);

    }

    /**
     * 回复数减1
     * @param comment_id
     */
    public void decreaseReplyCount(String comment_id){

        Integer[] params = new Integer[]{Integer.parseInt(comment_id)};
        String strSql = "update ns_comments set reply_count = reply_count - 1 where comment_id=?";
        contactBean.executeUpdate(strSql, params);
    }

    /**
     * 文章评论数目加1
     * @param news_id
     */
    public void addCommentCount(String news_id){

        Integer[] params = new Integer[]{Integer.parseInt(news_id)};
        String strSql = "update ns_news set comment_count = comment_count + 1 where news_id=?";
        contactBean.executeUpdate(strSql, params);

    }

    /**
     * 文章评论数减1
     * @param news_id
     */
    public void decreaseComment(String news_id){

        Integer[] params = new Integer[]{Integer.parseInt(news_id)};
        String strSql = "update ns_news set comment_count = comment_count - 1 where news_id=?";
        contactBean.executeUpdate(strSql, params);
    }
}
