package com.youzan.ad.vo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一返回（响应）对象
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> implements Serializable {
    /**
     * 业务状态码
     * 0：成功
     * -1：失败
     */
    private Integer code;

    /**
     * 业务的描述信息
     */
    private String message;


    /**
     * 返回对象
     */
    private T data;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
