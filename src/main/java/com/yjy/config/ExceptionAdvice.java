package com.yjy.config;

import com.yjy.common.ErrorCode;
import com.yjy.common.QuestionException;
import com.yjy.common.ResultResponse;
import com.taobao.api.ApiException;
import com.yjy.common.UnPermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhangjl
 * @description
 * @date 2020-05-26 12:21
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 全局异常处理
     *
     * @param e 异常处理
     * @return
     */
    @ExceptionHandler(value={Exception.class})
    public ResultResponse<String> handleException(Exception e){
        //自定义抛出的异常
        if (e instanceof QuestionException) {
          return ResultResponse.fail(((QuestionException) e).getCode(), e.getMessage());
        } else if (e instanceof ApiException) {
            log.error("请求钉钉接口异常：{}", e);
            return ResultResponse.fail_500("请求钉钉接口异常");
        } else if (e instanceof UnPermissionException){
            return ResultResponse.fail(ErrorCode.ERROR_403.getCode(), "没有权限");
        }
        else {
            log.error("系统异常:{}", e);
            return ResultResponse.fail_500(e.getMessage());
        }
    }
}
