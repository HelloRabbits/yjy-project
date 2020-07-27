package com.yjy.utils;

import com.alibaba.fastjson.JSON;
import com.yjy.common.QuestionException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangjl
 * @description
 * @date 2020-05-26 17:28
 */
@Slf4j
public class HttpUtils {

    private final static OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.SECONDS)
            .build();

    private final static int retryTimes = 3;

    /**
     * okHttp post请求
     * @param url 请求链接
     * @param param 请求参数
     * @param tClass 返回对象
     * @param <T> 类型
     * @return
     * @throws QuestionException
     */
    public static <T> T postJson(String url, Object param, Class<T> tClass) throws QuestionException {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType, JSON.toJSONString(param)))
                .build();
        String result = execute(request);
        return result != null ? JSON.parseObject(result, tClass) : null;
    }

    /**
     * okHttp get请求
     * @param url 请求链接
     * @param tClass 返回对象
     * @param <T> 类型
     * @return
     * @throws QuestionException
     */
    public static <T> T get(String url, Class<T> tClass) throws QuestionException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        String result = execute(request);
        return result != null ? JSON.parseObject(result, tClass) : null;
    }


    private static String execute(Request request) throws QuestionException {
        return execute(request, 1);
    }

    private static String execute(Request request, int reqCount) throws QuestionException {
        try {

            log.debug("发起请求 当前第 {} 次 / {} 次 {}", reqCount, retryTimes, reqCount > 1 ? "[重试请求]" : "");

            return executeInternal(request);

        } catch (SocketTimeoutException e) {

            if (reqCount >= retryTimes) {
                throw new QuestionException(500, "请求失效,请检查你的网络状态");
            }
            // 执行重试
            return execute(request, reqCount++);
        } catch (QuestionException e) {
            throw e;
        } catch (Exception e) {
            throw new QuestionException(500, "");
        }
    }

    private static String executeInternal(Request request) throws Exception {
        Response response = HTTP_CLIENT.newCall(request).execute();
        if (response == null || response.body() == null) {
            log.warn("http请求结果为 null");
            return null;
        }
        return response.body().string();
    }

}
