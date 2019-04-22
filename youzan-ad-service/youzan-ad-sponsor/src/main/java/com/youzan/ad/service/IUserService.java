package com.youzan.ad.service;

import com.youzan.ad.exception.AdException;
import com.youzan.ad.vo.AdUserResponse;
import com.youzan.ad.vo.AdUserRequest;

public interface IUserService {


    /**
     * 创建广告主用户
     * @param resquest
     * @return
     * @throws AdException
     */
    AdUserResponse adUser(AdUserRequest resquest) throws AdException;
}
