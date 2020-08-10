package com.yjy.config;

import com.yjy.common.enums.ErrorCodeEnum;
import com.yjy.common.exception.QuestionException;
import com.yjy.common.Response;
import com.taobao.api.ApiException;
import com.yjy.common.exception.UnPermissionException;
import com.yjy.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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
    public Response<String> handleException(Exception e){
        log.error("接口异常：{}", e);
        //自定义抛出的异常
        if (e instanceof QuestionException) {
          return Response.fail(((QuestionException) e).getCode(), e.getMessage());
        } else if (e instanceof ApiException) {
            return Response.fail_500("请求钉钉接口异常");
        } else if (e instanceof UnPermissionException){
            return Response.fail(ErrorCodeEnum.ERROR_403.getCode(), "没有权限");
        } else if(e instanceof UnknownAccountException){
            return Response.fail(ErrorCodeEnum.ERROR_404.getCode(), e.getMessage());
        } else if (e instanceof LockedAccountException) {
            return Response.fail(ErrorCodeEnum.ERROR_30001.getCode(), "账号已锁定");
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException ) e;
            Map<String, String> errors = new HashMap<>();
            exception.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return Response.fail(ErrorCodeEnum.ERROR_11002.getCode(), JsonUtils.build().toJson(errors));
        } else if ( e instanceof MissingServletRequestParameterException){
            return Response.fail(ErrorCodeEnum.ERROR_11002.getCode(), e.getMessage());
        }
        else {
            return Response.fail_500(e.getMessage());
        }
    }
}
