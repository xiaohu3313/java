package com.youzan.ad.client;

import com.youzan.ad.client.vo.AdPlanGetRequest;
import com.youzan.ad.client.vo.AdPlanResponse;
import com.youzan.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SponeorClientHystrix implements SponeorClient {
    @Override
    public CommonResponse<List<AdPlanResponse>> getPlan(AdPlanGetRequest planGetRequest) {
        return new CommonResponse<>(-1,"网络异常",null);
    }
}
