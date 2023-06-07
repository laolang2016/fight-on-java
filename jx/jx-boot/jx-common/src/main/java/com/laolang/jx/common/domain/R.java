package com.laolang.jx.common.domain;

import com.laolang.jx.common.consts.CommonBizCode;
import com.laolang.jx.common.exception.BusinessException;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class R<T> {

    /**
     * 接口请求结果的业务状态吗
     */
    private String code;

    /**
     * 判断接口请求是否成功的唯一标识
     */
    private Boolean success;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据体
     */
    private T body;

    /**
     * 扩充字段,正常情况下此字段为空，当此字段有值时，意味着当前接口结构不稳定，以后会修改,即保持 extra 为空
     */
    private Object extra;

    public static <T> R<T> build(String code, boolean success, String msg, T body) {
        R<T> ajax = new R<>();
        ajax.setCode(code);
        ajax.setSuccess(success);
        ajax.setMsg(msg);
        ajax.setBody(body);
        ajax.setExtra(null);
        return ajax;
    }

    public void setPropFromBusinessException(BusinessException e) {
        setMsg(e.getMsg());
        setCode(e.getCode());
        setSuccess(false);
    }

    public static <T> R<T> ok() {
        return build(CommonBizCode.OK.getCode(), true, CommonBizCode.OK.getMsg(), null);
    }

    public static <T> R<T> ok(String code, String msg) {
        return build(code, true, msg, null);
    }

    public static <T> R<T> ok(String code, String msg, T body) {
        return build(code, true, msg, body);
    }

    public static <T> R<T> ok(T body) {
        return build(CommonBizCode.OK.getCode(), true, CommonBizCode.OK.getMsg(), body);
    }

    public static <T> R<T> failed() {
        return build(CommonBizCode.FAILED.getCode(), false, CommonBizCode.FAILED.getMsg(), null);
    }

    public static <T> R<T> failed(String msg) {
        return build(CommonBizCode.FAILED.getCode(), false, msg, null);
    }

    public static <T> R<T> doOverdue() {
        return build(CommonBizCode.OVERDUE.getCode(), false, CommonBizCode.OVERDUE.getMsg(), null);
    }

    public static <T> R<T> forbid() {
        return build(CommonBizCode.FORBID.getCode(), false, CommonBizCode.FORBID.getMsg(), null);
    }

    public static <T> R<T> error() {
        return build(CommonBizCode.ERROR.getCode(), false, CommonBizCode.ERROR.getMsg(), null);
    }

    public static <T> R<T> error(String msg) {
        return build(CommonBizCode.ERROR.getCode(), false, msg, null);
    }

    public static <T> R<T> error(String code, String msg) {
        return build(code, false, msg, null);
    }

    public static <T> R<T> doFixing() {
        return build(CommonBizCode.FIXING.getCode(), false, CommonBizCode.FIXING.getMsg(), null);
    }

    public static <T> R<T> notFount() {
        return build(CommonBizCode.NOT_FOUND.getCode(), false, CommonBizCode.NOT_FOUND.getMsg(), null);
    }

    public static <T> R<T> badRequest() {
        return build(CommonBizCode.BAD_REQUEST.getCode(), false, CommonBizCode.BAD_REQUEST.getMsg(), null);
    }

}


