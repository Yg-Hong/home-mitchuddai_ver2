import { localAxios } from "@/utils/http-commons";

const HouseAPI = localAxios();

const getHouseListByDongCode = (dongCode, success, fail) => {
  HouseAPI.get(`house/${dongCode}`).then(success).catch(fail);
};

// const getHouseList = (dongCode, success, fail) => {
//   HouseAPI.get(`house/${dongCode}`).then(success).catch(fail);
// };

const getHouseListByLatLng = (ha, oa, pa, qa, success, fail) => {
  HouseAPI.get(`house/${ha}/${oa}/${pa}/${qa}`).then(success).catch(fail);
};

const getHouseDetail = (dongCode, aptCode, success, fail) => {
  HouseAPI.get(`house/${dongCode}/${aptCode}`).then(success).catch(fail);
};

export default { getHouseListByDongCode, getHouseDetail, getHouseListByLatLng };
