import { localAxios } from "@/utils/http-commons";

const LocationAPI = localAxios();

const getSidoList = (success, fail) => {
  LocationAPI.get("/location/getSido").then(success).catch(fail);
};

const getGugunList = (sido, success, fail) => {
  LocationAPI.get(`/location/getGugun/${sido}`).then(success).catch(fail);
};

const getDongList = (gugun, success, fail) => {
  LocationAPI.get(`/location/getDong/${gugun}`).then(success).catch(fail);
};

export default { getSidoList, getGugunList, getDongList };
