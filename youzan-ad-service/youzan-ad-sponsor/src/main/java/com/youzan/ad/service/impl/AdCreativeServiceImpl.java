package com.youzan.ad.service.impl;

import com.youzan.ad.dao.AdCreativeRepository;
import com.youzan.ad.entity.AdCreative;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.service.IAdCreativeService;
import com.youzan.ad.vo.CreativeRequest;
import com.youzan.ad.vo.CreativeResopnse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdCreativeServiceImpl implements IAdCreativeService {

    @Autowired
    private AdCreativeRepository adCreativeRepository;

    /**
     * 创建创意
     * @param creativeRequest
     * @return
     * @throws AdException
     */
    @Override
    @Transactional
    public CreativeResopnse createCreateive(CreativeRequest creativeRequest) throws AdException {
        AdCreative adCreative = adCreativeRepository.save(creativeRequest.toEntity());
        return new CreativeResopnse(adCreative.getId(),adCreative.getName());
    }
}
