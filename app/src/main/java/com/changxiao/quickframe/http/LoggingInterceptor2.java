package com.changxiao.quickframe.http;

import com.changxiao.quickframe.utils.JsonFormatter;
import com.changxiao.quickframe.utils.ZRLog;
import com.changxiao.quickframe.utils.ZRStringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpEngine;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 日志拦截器
 * <p>
 * Created by Chang.Xiao on 2016/10/13.
 *
 * @version 1.0
 */
public final class LoggingInterceptor2 implements Interceptor {

    private static final String TAG = "OkHttp";
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        /*Request request = chain.request();

        long startTime = System.nanoTime();
        ZRLog.d(TAG, String.format("Sending request %s on %s%n%s%nparams:%s",
                request.url(), chain.connection(), request.headers(), request.body().toString()));

        Response response = chain.proceed(request);

        long endTime = System.nanoTime();
        String jsonStr = response.body().source().toString();
        ZRLog.d(TAG, String.format("Received response for %s in %.1fms%n%s%nresponseObject:%s",
                response.request().url(), (endTime - startTime) / 1e6d, response.headers(),
                StringUtils.jsonFormatter(jsonStr)));*/

        Request request = chain.request();

        boolean logBody = true;
        boolean logHeaders = true;

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String requestStartMessage =
                "\n\n--> " + request.method() + " Sending request " + request.url() + ' ' + protocol(protocol);
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        ZRLog.d(TAG, requestStartMessage);

        if (logHeaders) {
            if (hasRequestBody) {
                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                if (requestBody.contentType() != null) {
                    ZRLog.d(TAG, "Content-Type: " + requestBody.contentType());
                }
                if (requestBody.contentLength() != -1) {
                    ZRLog.d(TAG, "Content-Length: " + requestBody.contentLength());
                }
            }

            Headers headers = request.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    ZRLog.d(TAG, name + ": " + headers.value(i));
                }
            }

            if (!logBody || !hasRequestBody) {
                ZRLog.d(TAG, "--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
                ZRLog.d(TAG, "--> END " + request.method() + " (encoded body omitted)");
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                ZRLog.d(TAG, "");
                String paramStr = buffer.readString(charset);
                StringBuilder sb = new StringBuilder();
                String[] params = paramStr.split("&");
                for (String param : params) {
                    sb.append("  " + param + ",\n");
                }
                ZRLog.d(TAG, "params:{\n" + sb.toString() + "}");

                ZRLog.d(TAG, "--> END " + request.method()
                        + " (" + requestBody.contentLength() + "-byte body)");
            }
        }

        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        ZRLog.d(TAG, "<-- " + response.code() + ' ' + response.message() + ' '
                + "Received response for " + response.request().url() + " (" + tookMs + "ms" + (!logHeaders ? ", "
                + bodySize + " body" : "") + ')');

        if (logHeaders) {
            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                ZRLog.d(TAG, headers.name(i) + ": " + headers.value(i));
            }

            if (!logBody || !HttpEngine.hasBody(response)) {
                ZRLog.d(TAG, "<-- END HTTP");
            } else if (bodyEncoded(response.headers())) {
                ZRLog.d(TAG, "<-- END HTTP (encoded body omitted)");
            } else {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                if (contentLength != 0) {
                    ZRLog.d(TAG, "");
                    ZRLog.d(TAG, JsonFormatter.jsonFormatter(buffer.clone().readString(charset)));
                }

                ZRLog.d(TAG, "<-- END HTTP (" + buffer.size() + "-byte body)\n");
            }
        }

        return response;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    private static String protocol(Protocol protocol) {
        return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
    }
}
