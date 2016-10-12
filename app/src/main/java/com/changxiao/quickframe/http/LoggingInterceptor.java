package com.changxiao.quickframe.http;

import com.changxiao.quickframe.utils.JsonFormatter;
import com.changxiao.quickframe.utils.ZRStringUtils;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 日志拦截器
 * <p>
 * Created by Chang.Xiao on 2016/10/13.
 *
 * @version 1.0
 */
public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long startTime = System.nanoTime();
        Logger.d(String.format("Sending request %s on %s%n%s%nparams:%s",
                request.url(), chain.connection(), request.headers(), request.body()));

        Response response = chain.proceed(request);

        long endTime = System.nanoTime();
        Logger.d(String.format("Received response for %s in %.1fms%n%s%nresponseObject:%s",
                response.request().url(), (endTime - startTime) / 1e6d, response.headers(),
                JsonFormatter.jsonFormatter(ZRStringUtils.replaceBlank(response.body().string()))));

        return response;
    }
}
