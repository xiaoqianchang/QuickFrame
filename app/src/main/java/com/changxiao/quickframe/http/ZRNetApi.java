package com.changxiao.quickframe.http;

import java.util.Map;

import retrofit2.Callback;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Net api
 * <p>
 * Created by Chang.Xiao on 2016/5/30.
 *
 * @version 1.0
 */
public interface ZRNetApi {

    /**
     * 1.App绑定 /appbind?sid=&did=&rid=
     * @param sid
     * @param did
     * @param rid
     * @return
     */
    @GET("appbind")
    void get(@Query("sid") String sid, @Query("did") String did, @Query("rid") String rid, Callback<Object> response);

    /**
     * 4.发送聊天消息 /pushmsg  POST
     * Sid, did, rid, cid, userid, content  // 如果是新会话, cid=-1
     * 返回：（正常返回, 以下同。 含sid, rid, optype, msg, code），result 包含msgid, cid
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pushmsg")
    Observable<Object> pushMsg(@FieldMap Map<String, Object> params);

}
