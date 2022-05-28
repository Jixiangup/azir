package com.bnyte.azir.api.advice;

import com.bnyte.azir.common.enums.ECookie;
import com.bnyte.azir.common.exception.RdosDefineException;
import com.bnyte.azir.common.util.CookieUtils;
import com.bnyte.azir.common.web.response.Code;
import com.bnyte.azir.common.web.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author bnyte
 * @since 2022/5/27 18:22
 */
@RestControllerAdvice(basePackages = "com.bnyte.azir.api.controller")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    CookieUtils cookieUtils;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return body;
    }

    /**
     * 参数检验异常处理
     * @param e 异常响应
     * @return 响应结果
     */
    @ExceptionHandler
    R<Void> handleValidationException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringJoiner message = new StringJoiner(",");
        fieldErrors.forEach(error -> message.add(error.getDefaultMessage()));
        return R.fail(message.toString(), Code.VALIDATION_ERROR);
    }

    /**
     * 自定义异常处理
     * @param e 异常响应
     * @return 响应结果
     */
    @ExceptionHandler
    R<Void> handleRdosDefineException(RdosDefineException e) {
        if (e.getCode().getCode() > 9 || e.getCode().getCode() < 101) {
            cookieUtils.remove(ECookie.X_ACCESS_TOKEN.getKey());
            cookieUtils.remove(ECookie.USERNAME.getKey());
        }
        return R.fail(e.getCode());
    }


}
