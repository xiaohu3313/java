package com.youzan.ad.mysql.constant;

import com.github.shyiko.mysql.binlog.event.EventType;

public enum OpType {

    ADD,
    UPDATE,
    DELETE,
    OTHER;

    /**
     * 将binlpg中的事件类型转换成自己定义的时间类型
     * ps:根据开发者mysql的版本取值
     *
     */
    public static OpType to (EventType type){
        switch (type){
            case EXT_WRITE_ROWS:
                return ADD;
            case EXT_UPDATE_ROWS:
                return UPDATE;
            case EXT_DELETE_ROWS:
                return DELETE;
            default:
                return OTHER;
        }
    }
}
