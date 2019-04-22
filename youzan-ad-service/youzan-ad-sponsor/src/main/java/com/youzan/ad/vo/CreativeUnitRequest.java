package com.youzan.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreativeUnitRequest {
    private List<CreativeUnits> creativeUnits;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class CreativeUnits {

        private Long creativeId;
        private Long unitId;

    }
}
