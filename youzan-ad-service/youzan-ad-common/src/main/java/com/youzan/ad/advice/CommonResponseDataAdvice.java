package com.youzan.ad.advice;

import com.youzan.ad.annotation.IgnoreResponseAdvice;
import com.youzan.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        if(methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        CommonResponse commonResponse = new CommonResponse(0, "");
        if(null==body){
            return commonResponse;
        }else if(body instanceof CommonResponse){
            commonResponse = (CommonResponse<Object>) body;
        }else {
            commonResponse.setData(body);
        }
        return commonResponse;
    }
}
