package com.youzan.ad.service.impl;

import com.youzan.ad.constant.Constants;
import com.youzan.ad.dao.AdUserRepository;
import com.youzan.ad.entity.AdUser;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.service.IUserService;
import com.youzan.ad.utils.CommonUtils;
import com.youzan.ad.vo.AdUserResponse;
import com.youzan.ad.vo.AdUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AdUserRepository adUserRepository;

    @Override
    @Transactional
    public AdUserResponse adUser(AdUserRequest request) throws AdException {

        //验证用户名是否为空
        if(!request.validate()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_USER);
        }
        //验证用户是否唯一
        AdUser adUser = adUserRepository.findByUsername(request.getUsername());
        if(adUser!=null){
            throw new AdException(Constants.ErrorMsg.USER_REPETITION_ERROR);
        }
        //保存
        AdUser newUser = adUserRepository.save(new AdUser(request.getUsername(),
                CommonUtils.md5(request.getUsername())));
        //返回当前添加数据
        return new AdUserResponse(newUser.getId(),
                newUser.getUsername(),newUser.getToken(),
                newUser.getCreateTime(),newUser.getUpdateTime());
    }
}
