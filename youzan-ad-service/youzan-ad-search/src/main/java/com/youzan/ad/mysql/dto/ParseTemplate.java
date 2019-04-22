package com.youzan.ad.mysql.dto;


import com.youzan.ad.index.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParseTemplate {

    private String database;

    private Map<String, TableTemplate> tableTemplateMap = new HashMap<>();

    public static ParseTemplate parse(Template template){
        ParseTemplate parseTemplate = new ParseTemplate();

        String database = template.getDatabase();

        parseTemplate.setDatabase(database);

        for (JsonTable table:template.getTableList()){
            String tableName = table.getTableName();
            Integer level = table.getLevel();

            TableTemplate tableTemplate = new TableTemplate();
            tableTemplate.setTableName(tableName);
            tableTemplate.setLevel(level.toString());

            parseTemplate.tableTemplateMap.put(tableName, tableTemplate);

            Map<OpType, List<String>> opTypeFieldMap = tableTemplate.getOpTypeFieldMap();
            for (JsonTable.Column column:table.getInsert()){
                getColumn(
                        OpType.ADD,
                        opTypeFieldMap,
                        ArrayList::new
                ).add(column.getColumn());
            }

            for (JsonTable.Column column:table.getDelete()){
                getColumn(
                        OpType.DELETE,
                        opTypeFieldMap,
                        ArrayList::new
                ).add(column.getColumn());
            }

            for (JsonTable.Column column:table.getUpdate()){
                getColumn(
                        OpType.DELETE,
                        opTypeFieldMap,
                        ArrayList::new
                ).add(column.getColumn());
            }
        }
        return parseTemplate;
    }

    private static <O, L> L getColumn(O key, Map<O, L> map, Supplier<L> factory){
        return map.computeIfAbsent(key, k->factory.get());
    }

}
