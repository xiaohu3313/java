package com.youzan.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitKeyWordRequest {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitKeyWords {
        private Long unitId;
        private String keyword;
    }

    private List<UnitKeyWords> unitKeyWords;
}
