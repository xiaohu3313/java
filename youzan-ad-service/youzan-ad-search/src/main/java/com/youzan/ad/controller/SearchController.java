package com.youzan.ad.controller;

import com.youzan.ad.annotation.IgnoreResponseAdvice;
import com.youzan.ad.client.SponeorClient;
import com.youzan.ad.client.vo.AdPlanGetRequest;
import com.youzan.ad.client.vo.AdPlanResponse;
import com.youzan.ad.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SponeorClient sponeorClient;

    @PostMapping
    @IgnoreResponseAdvice
    public CommonResponse<List<AdPlanResponse>> findPlan(
            @RequestBody AdPlanGetRequest planGetRequest){
        return sponeorClient.getPlan(planGetRequest);
    }


}
