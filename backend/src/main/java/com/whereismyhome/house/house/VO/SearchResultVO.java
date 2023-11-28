package com.whereismyhome.house.house.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchResultVO {
    private Long aptCode;
    private int buildYear;
    private String roadName;
    private String roadNameBonbun;
    private String roadNameBubun;

    private String dongCode;
    private String apartmentName;

    private String lng;
    private String lat;

    private String sidoName;
    private String gugunName;
    private String dongName;
}
