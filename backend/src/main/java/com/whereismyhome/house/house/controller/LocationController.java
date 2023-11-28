package com.whereismyhome.house.house.controller;

import com.whereismyhome.house.house.dto.LocationCodeDto;
import com.whereismyhome.house.house.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name="Location API", description = "지역 정보 조회를 위한 API")
@Slf4j
@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @Operation(summary = "시도 코드 조회하기", description = "시도 코드와 시도 이름을 조회합니다.")
    @GetMapping(value = "/getSido")
    public List<LocationCodeDto> getSidoList() throws IOException {
        return locationService.getSidoList();
    }

    @Operation(summary = "시도 코드를 기준으로 구군 코드 조회", description = "시도 코드를 기준으로 구군 코드와 구군 이름을 조회합니다.")
    @Parameter(name = "sido", description = "조회할 지역의 sidoCode")
    @GetMapping(value = "/getGugun/{sido}")
    public List<LocationCodeDto> getGugunList(@PathVariable String sido) throws IOException {
        return locationService.getGugunList(sido);
    }

    @Operation(summary = "구군 코드를 기준으로 동 코드 조회", description = "구군 코드를 기준으로 동 코드와 동 이름을 조회합니다.")
    @Parameter(name = "gugun", description = "조회할 지역의 gugunCode")
    @GetMapping(value = "/getDong/{gugun}")
    public List<LocationCodeDto> getDongList(@PathVariable String gugun) throws IOException {
        return locationService.getDongList(gugun);
    }
}
