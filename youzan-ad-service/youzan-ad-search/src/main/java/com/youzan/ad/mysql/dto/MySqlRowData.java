package com.youzan.ad.mysql.dto;

import com.youzan.ad.index.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySqlRowData {

    private String tableName;
    private String level;
    private OpType opType;

    //字段-》值
    private List<Map<String, String>> fieldValueMap = new ArrayList<Map<String,String>>();
}
