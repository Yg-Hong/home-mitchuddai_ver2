package com.whereismyhome.house.house.service;

import com.whereismyhome.house.house.VO.DetailResultVO;
import com.whereismyhome.house.house.VO.SearchResultVO;
import com.whereismyhome.house.house.dto.HouseDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface HouseService {

    public List<SearchResultVO> getHouseListByDongCode(Long dongCode);
    public DetailResultVO getDealListByDongCode(Long dongCode, Long aptCode);
    public List<SearchResultVO> getHouseListByLatLng(String ha, String oa, String pa, String qa);
}
