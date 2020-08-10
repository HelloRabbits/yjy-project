package com.yjy.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author zhangjl
 * @description
 * @date 2020-07-27 10:52
 */
public class JsonUtils {


    public static Gson build() {
        return new GsonBuilder().create();
    }

}
