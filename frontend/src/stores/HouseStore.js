import HouseAPI from "@/api/houseAPI.js";

import { defineStore } from "pinia";
import { ref, computed } from "vue";

export const useHouseStore = defineStore("houseStore", () => {
  const houseList = ref([]);
  const house = ref({
    aptCode: "",
    aptName: "",
    buildYear: "",
    code: "",
    dealAmount: "",
    dealYear: "",
    dealMonth: "",
    dealDay: "",
    area: "",
    floor: "",
    jibun: "",
    lat: "",
    lng: "",
    dong: "",
    img: "",
  });

  const distance = ref([]);

  //getter
  const getHouseList = computed(() => houseList.value);
  const getHouse = computed(() => house.value);
  const getDistance = computed(() => distance.value);

  //action
  const clearHouseList = () => {
    houseList.value = [];
  };
  const clearHouse = () => {
    house.value = {
      aptCode: "",
      aptName: "",
      buildYear: "",
      code: "",
      dealAmount: "",
      dealYear: "",
      dealMonth: "",
      dealDay: "",
      area: "",
      floor: "",
      jibun: "",
      lat: "",
      lng: "",
      dong: "",
      img: "",
    };
  };
  const clearDistance = () => {
    distance.value = [];
  };

  const setHouseList1 = (dongCode) => {
    HouseAPI.getHouseListByDongCode(
      dongCode,
      ({ data }) => {
        console.log("[setHouseList1] success : ");
        houseList.value = data;

        console.log(houseList.value);
      },
      () => {
        console.log("[setHouseList1] fail");
      }
    );
  };
  const setHouseList2 = (ha, oa, pa, qa) => {
    HouseAPI.getHouseListByLatLng(
      ha,
      oa,
      pa,
      qa,
      ({ data }) => {
        console.log("[setHouseList2] success : " + data);
        houseList.value = data;
      },
      () => {
        console.log("[setHouseList2] fail");
      }
    );
  };
  const setHouse = (house) => {
    house.value = house;
  };
  const setDistance = (item) => {
    distance.value.push(item);
  };

  return {
    houseList,
    house,
    distance,
    getHouseList,
    getHouse,
    getDistance,
    clearDistance,
    clearHouseList,
    clearHouse,
    setDistance,
    setHouseList1,
    setHouseList2,
    setHouse,
  };
});
