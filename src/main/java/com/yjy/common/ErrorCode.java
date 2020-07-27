package com.yjy.common;

/**
 * @author zhangjl
 * @description 错误编码
 * @date 2020-05-26 20:57
 */
public enum ErrorCode {

    //编码定义
    //编码长度 00000
    //前两位标识业务 后三位标识当前业务下的错误代码类型
    //钉钉接口调用失败 10


    //----------------------- 通用 -----------------
    //成功
    SUCCESS(200),
    //系统异常
    ERROR_500(500),
    //未发现资源
    ERROR_404(404),
    /**
     * 没有权限
     */
    ERROR_403(403),
    //参数为空
    ERROR_11001(11001),

    // ---------------------token 服务----------------
    //appKey没有匹配到对应的应用，请先配置
    ERROR_20001(20001),

    //------------------------钉钉接口调用 ---------------------
    //调用钉钉接口失败
    ERROR_10000(10000),
    //token获取失败
    ERROR_10001(10001),
    //企业内部免登录获取用户信息失败
    ERROR_10002(10002),
    //根据userId获取用户详情失败
    ERROR_10003(10003),

    ;


    private int code;


    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
