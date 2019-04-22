package com.youzan.ad.service;

import com.youzan.ad.exception.AdException;
import com.youzan.ad.vo.CreativeRequest;
import com.youzan.ad.vo.CreativeResopnse;

public interface IAdCreativeService {

    CreativeResopnse createCreateive (CreativeRequest creativeRequest) throws AdException;
}
