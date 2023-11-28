import { localAxios } from "@/utils/http-commons";

const commonAPI = localAxios();

const getNewsContent = (success, fail) => {
  commonAPI.get("/open/news", "부동산").then(success).catch(fail);
};

export default { getNewsContent };
