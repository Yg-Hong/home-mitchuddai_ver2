package com.whereismyhome.house.house.service;

import com.whereismyhome.house.house.dto.LocationCodeDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final String urlForLocation = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?";


    @Override
    public List<LocationCodeDto> getSidoList() throws IOException {
        StringBuilder param = new StringBuilder("regcode_pattern=");
        param.append("*00000000");
        param.append("&is_ignore_zero=true");

        JSONObject obj = getJsonObject(param.toString());
        List<LocationCodeDto> result = parseCode(0, 0, obj);

        return result;
    }

    @Override
    public List<LocationCodeDto> getGugunList(String sido) throws IOException {
        StringBuilder param = new StringBuilder("regcode_pattern=");
        StringBuilder paramValue = new StringBuilder(sido);

        paramValue.setLength(2);
        paramValue.append("*00000");

        param.append(paramValue);
        log.info("Gugun param: " + param.toString());

        JSONObject obj = getJsonObject(param.toString());
        List<LocationCodeDto> result = parseCode(1, 1, obj);

        return result;
    }

    @Override
    public List<LocationCodeDto> getDongList(String gugun) throws IOException {
        StringBuilder param = new StringBuilder("regcode_pattern=");
        StringBuilder paramValue = new StringBuilder(gugun);

        paramValue.setLength(5);
        paramValue.append("*");

        param.append(paramValue);
        log.info("Dong param: " + param.toString());

        JSONObject obj = getJsonObject(param.toString());
        List<LocationCodeDto> result = parseCode(1, 2, obj);

        return result;
    }

    private JSONObject getJsonObject(String param) throws IOException  {
        StringBuilder urlBuilder = new StringBuilder(urlForLocation);
        urlBuilder.append(param);

        BufferedReader in = null;
        HttpURLConnection conn = null;
        JSONObject obj = null;
        try {
            // reqParams.put("body_contents1", body_contents1); // body에 들어갈 내용을 담는다.

            URL url = new URL(urlBuilder.toString()); // 호출할 외부 API 를 입력한다.

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true

            // Response
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                sb.append(line);
            }

            obj = new JSONObject(sb.toString()); // json으로 변경 (역직렬화)
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(in).close();
            Objects.requireNonNull(conn).disconnect();
        }

        return obj;
    }

    private List<LocationCodeDto> parseCode(int startIdx, int parsingIdx, JSONObject obj) {
        List<LocationCodeDto> result = new ArrayList<>();
        StringTokenizer st;
        String codeValue;
        String nameValue;
        JSONArray jsonArray = obj.getJSONArray("regcodes");
        for(int i = startIdx; i < jsonArray.length(); i++) {
            JSONObject o = (JSONObject) jsonArray.get(i);

            codeValue = o.getString("code");
            nameValue = "error";

            st = new StringTokenizer(o.getString("name"));
            if(parsingIdx == 0) {
                nameValue = st.nextToken();
            } else if(parsingIdx == 1) {
                st.nextToken();
                nameValue = st.nextToken();
            } else if (parsingIdx == 2) {
                st.nextToken();
                st.nextToken();
                nameValue = st.nextToken();
            }
            result.add(new LocationCodeDto(codeValue, nameValue));
        }
        log.info(result.toString());
        return result;
    }
}
