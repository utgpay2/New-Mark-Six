package com.central.common.exception;

import com.central.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * 异常通用处理
 */
@ResponseBody
@Slf4j
public class DefaultExceptionAdvice {
    /**
     * IllegalArgumentException异常处理返回json
     * 返回状态码:400
     */
    @ResponseStatus(HttpStatus.OK) // HttpStatus.BAD_REQUEST
    @ExceptionHandler({IllegalArgumentException.class})
    public Result badRequestException(IllegalArgumentException e) {
        return defHandler("参数解析失败", e);
    }

    /**
     * AccessDeniedException异常处理返回json
     * 返回状态码:403
     */
    @ResponseStatus(HttpStatus.OK) // HttpStatus.FORBIDDEN
    @ExceptionHandler({AccessDeniedException.class})
    public Result badMethodExpressException(AccessDeniedException e) {
        return defHandler("没有权限请求当前方法", e);
    }

    /**
     * 返回状态码:405
     */
    @ResponseStatus(HttpStatus.OK) // HttpStatus.METHOD_NOT_ALLOWED
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return defHandler("不支持当前请求方法", e);
    }

    /**
     * 返回状态码:415
     */
    @ResponseStatus(HttpStatus.OK) // HttpStatus.UNSUPPORTED_MEDIA_TYPE
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public Result handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return defHandler("不支持当前媒体类型", e);
    }

    /**
     * SQLException sql异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.OK) // HttpStatus.INTERNAL_SERVER_ERROR
    @ExceptionHandler({SQLException.class})
    public Result handleSQLException(SQLException e) {
        return defHandler("服务运行SQLException异常", e);
    }

    /**
     * BusinessException 业务异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.OK) // HttpStatus.INTERNAL_SERVER_ERROR
    @ExceptionHandler(BusinessException.class)
    public Result handleException(BusinessException e) {
        return defHandler("业务异常", e);
    }

    /**
     * IdempotencyException 幂等性异常
     * 返回状态码:200
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IdempotencyException.class)
    public Result handleException(IdempotencyException e) {
        return Result.failed(e.getMessage());
    }

    /**
     * 所有异常统一处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.OK)  // HttpStatus.INTERNAL_SERVER_ERROR
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        return defHandler("未知异常", e);
    }

    // --- 捕捉公共的参数验证错误  @Validated @Valid  Start ---
    /**
     * 参数校验异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException e) {
        return defHandler(e.getAllErrors().get(0).getDefaultMessage(), e);
    }

    /**
     * bean参数校验异常
     * 返回状态码:200
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return defHandler(e.getFieldError().getDefaultMessage(), e);
    }

    /**
     * restful参数校验异常{id}
     * 返回状态码:200
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return defHandler("参数填写错误", e);
    }

    /**
     * restful参数为空校验异常
     * 返回状态码:200
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerException(NullPointerException e) {
        return defHandler("空指针异常", e);
    }

    /**
     * 参数名填写错误
     * 返回状态码:200
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result missingServletRequestParameterException(MissingServletRequestParameterException e) {
        return defHandler("参数名填写错误", e);
    }

    /**
     * 数据库唯一索引异常
     * 返回状态码:200
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        return defHandler("数据库唯一索引异常", e);
    }

    /**
     * 单个参数校验异常
     * ConstraintViolationException ：参数上加@RequestParam或参数加@NotBlank @NotNull等
     * 返回状态码:200
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationException(ConstraintViolationException e) {
        List<String> msgList = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            msgList.add(constraintViolation.getMessage());
        }
        String messages = StringUtils.join(msgList.toArray(), ";");
        return defHandler(messages, e);
    }
    // --- 捕捉公共的参数验证错误  @Validated @Valid  End ---

    protected Result defHandler(String msg, Exception e) {
        log.error(msg, e);
        return Result.failed(msg);
    }
}
