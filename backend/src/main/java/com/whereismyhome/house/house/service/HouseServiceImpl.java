package com.whereismyhome.house.house.service;

import java.util.HashMap;
import java.util.List;

import com.whereismyhome.house.house.VO.DetailResultVO;
import com.whereismyhome.house.house.VO.SearchResultVO;
import com.whereismyhome.house.house.dao.HouseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HouseServiceImpl implements HouseService {

	private final HouseMapper houseMapper;

	public HouseServiceImpl(HouseMapper houseMapper) {
		this.houseMapper = houseMapper;
	}

	public List<SearchResultVO> getHouseListByDongCode(Long dongCode) {
		return houseMapper.selectHousesByDongCode(dongCode);
	}

	public DetailResultVO getDealListByDongCode(Long dongCode, Long aptCode) {
		HashMap<String, Long> params = new HashMap<>();
		params.put("dongCode", dongCode);
		params.put("aptCode", aptCode);

		return houseMapper.selectDealsByAptCode(params);
	}

	public List<SearchResultVO> getHouseListByLatLng(String ha, String oa, String pa, String qa){
		HashMap<String, String> params = new HashMap<>();
		params.put("ha", ha);
		params.put("oa", oa);
		params.put("pa", pa);
		params.put("qa", qa);

		log.info(params.toString());

		return houseMapper.selectHousesByLatLng(params);
	}
}
