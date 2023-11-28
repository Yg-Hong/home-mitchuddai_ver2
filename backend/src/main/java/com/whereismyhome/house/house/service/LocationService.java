package com.whereismyhome.house.house.service;

import com.whereismyhome.house.house.dto.LocationCodeDto;

import java.io.IOException;
import java.util.List;

public interface LocationService {
    List<LocationCodeDto> getSidoList() throws IOException;
    List<LocationCodeDto> getGugunList(String sido) throws IOException;
    List<LocationCodeDto> getDongList(String gugun) throws IOException;
}
