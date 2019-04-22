package com.youzan.ad.mysql.listenter;

import com.youzan.ad.mysql.dto.BinLogRowData;

public interface Ilistener {

    void register();

    void onEvent(BinLogRowData eventData);
}
