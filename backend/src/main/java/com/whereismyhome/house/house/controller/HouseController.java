package com.whereismyhome.house.house.controller;

import com.whereismyhome.house.house.VO.DetailResultVO;
import com.whereismyhome.house.house.VO.SearchResultVO;
import com.whereismyhome.house.house.service.HouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name="House API", description = "건물 정보 조회를 위한 API")
@RestController
@RequestMapping("/house")
@Slf4j
public class HouseController {

    private final HouseService houseService;

    HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @Operation(summary = "HouseAPI 연결 test", description = "컨트롤러 연결 test")
    @GetMapping("")
    public void controllerTest() {
        log.trace("연결 완료");
    }

    @Operation(summary = "동 코드 기준으로 건물들 조회하기", description = "지역 정보의 dongCode를 변수로 받아 해당 지역에 해당하는 건물 정보를 조회합니다.")
    @Parameter(name = "dongCode", description = "조회할 지역의 code")
    @GetMapping("/{dongCode}")
    public List<SearchResultVO> getHouseListByDongCode(@PathVariable Long dongCode) {
        log.info("dong : " + dongCode);
        List<SearchResultVO> list = null;

        list = houseService.getHouseListByDongCode(dongCode);

        log.info(list.toString());
        return list;
    }

    @Operation(summary = "동 코드와 건물 코드를 기준으로 거래정보들 조회하기", description = "지역 코드와 건물 코드를 변수로 받아 해당 건물의 거래 정보를 조회합니다.")
    @Parameter(name = "dongCode", description = "조회할 지역의 code")
    @Parameter(name = "aptCode", description = "조회할 건물의 code")
    @GetMapping("/{dongCode}/{aptCode}")
    public DetailResultVO getDealListByDongCode(@PathVariable Long dongCode, @PathVariable Long aptCode) {
        log.info("aptCode : " + aptCode + " | dongCode : " + dongCode);
        DetailResultVO result = null;

        result = houseService.getDealListByDongCode(dongCode, aptCode);

        log.info(result.toString());
        return result;
    }

//    ha, oa, pa, qa
    @Operation(summary = "카카오 지도 좌표 기준 건물 정보 조회", description = "카카오 지도의 4각 위경도 좌표를 기준으로 건물 정보를 조회합니다.")
    @Parameter(name = "ha", description = "지도 좌측 하단 위도")
    @Parameter(name = "qa", description = "지도 좌측 하단 경도")
    @Parameter(name = "oa", description = "지도 우측 상단 위도")
    @Parameter(name = "pa", description = "지도 우측 상단 경도")
    @GetMapping("{ha}/{oa}/{pa}/{qa}")
    public List<SearchResultVO> getHouseListByLatLng(@PathVariable String ha, @PathVariable String oa, @PathVariable String pa, @PathVariable String qa) {
        return houseService.getHouseListByLatLng(ha, oa, pa, qa);
    }
}
