package com.youzan.ad.mysql.listenter;

import com.youzan.ad.mysql.TemplateHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class AggregationListener {

    private String dbName;

    private String tableName;

    private Map<String, Ilistener> listenerMap = new HashMap<>();

    //private final TemplateHolder templateHolder;


}
