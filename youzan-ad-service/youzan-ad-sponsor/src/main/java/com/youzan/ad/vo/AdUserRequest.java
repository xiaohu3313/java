package com.youzan.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUserRequest {

    private String username;

    public boolean validate(){
        return !StringUtils.isEmpty(username);
    }
}
