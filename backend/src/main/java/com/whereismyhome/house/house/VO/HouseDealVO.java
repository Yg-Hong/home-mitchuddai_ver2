package com.whereismyhome.house.house.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HouseDealVO {
    private Long no;
    private String dealAmount;
    private int dealYear;
    private int dealMonth;
    private String area;
    private String floor;

    private Long aptCode;
}
