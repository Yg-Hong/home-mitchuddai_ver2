package com.whereismyhome.house.house.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DetailResultVO {
    private Long aptCode;
    private int buildYear;
    private String roadName;
    private String apartmentName;
    private String jibun;
    private String lng;
    private String lat;

    private String sidoName;
    private String gugunName;
    private String dongName;

    private List<HouseDealVO> houseDeals;
}
