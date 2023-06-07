package com.laolang.jx.web;

import com.laolang.jx.common.domain.R;
import com.laolang.jx.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<R<Void>> businessExceptionHandler(BusinessException e) {
        R<Void> r = R.failed();
        r.setPropFromBusinessException(e);
        log.error("请求出错:{}", r.getMsg());
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<R<Void>> accessDeniedExceptionHandler(AccessDeniedException e){
//        R<Void> r = R.forbid();
//        log.error("不允许访问:{}", r.getMsg());
//        return ResponseEntity.status(HttpStatus.OK).body(r);
//    }


    /**
     * 兜底异常处理
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<R<Void>> exceptionHandler(Exception e) {
        R<Void> r = R.error();
        log.error("未知异常");
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(r);
    }
}
