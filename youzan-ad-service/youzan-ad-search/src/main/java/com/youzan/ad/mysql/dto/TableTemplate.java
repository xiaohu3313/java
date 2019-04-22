package com.youzan.ad.mysql.dto;

import com.youzan.ad.index.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableTemplate {

    private String tableName;
    private String level;

    //mysql  binlog 字段索引（下标） 字段名
    private Map<Integer, String> posMap = new HashMap<>();


    //操作类型-》字段
    private Map<OpType, List<String>> opTypeFieldMap = new HashMap<>();
}
