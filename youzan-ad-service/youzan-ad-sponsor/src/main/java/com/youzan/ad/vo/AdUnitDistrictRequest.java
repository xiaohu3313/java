package com.youzan.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitDistrictRequest {
    private List<UnitDistricts> unitDistricts;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class UnitDistricts {
        private Long unitId;
        private String province;
        private String city;
    }
}
