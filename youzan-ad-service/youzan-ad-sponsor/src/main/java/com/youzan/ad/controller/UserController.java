package com.youzan.ad.controller;

import com.alibaba.fastjson.JSON;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.service.IUserService;
import com.youzan.ad.vo.AdUserRequest;
import com.youzan.ad.vo.AdUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public AdUserResponse saveUser(@RequestBody AdUserRequest adUserRequest)
            throws AdException {
        log.info("youzan-ad-sponsor: saveUser->",JSON.toJSONString(adUserRequest));
        return userService.adUser(adUserRequest);
    }

}
