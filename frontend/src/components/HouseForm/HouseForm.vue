<script setup>
import KakaoMapForm from "./KakaoMapForm.vue";
import { RouterView } from "vue-router";

import { ref } from "vue";

const houseMarkerList = ref([]);

const changeLatAndLngList = (newList) => {
  console.log("HouseForm: " + newList);
  houseMarkerList.value = newList;
  console.log(houseMarkerList.value);
};

const updateHouseMarkerList = (newList) => {
  console.log("HouseForm: " + newList);
  houseMarkerList.value = newList;
  console.log(houseMarkerList.value);
};
</script>

<template>
  <a-row justify="start">
    <a-col :span="6" class="LeftForm">
      <!-- <HouseListForm @changeLatAndLngList="changeLatAndLngList" /> -->
      <RouterView @changeLatAndLngList="changeLatAndLngList" />
    </a-col>
    <a-col :span="18">
      <KakaoMapForm
        :houseMarkerList="houseMarkerList"
        @updateHouseMarkerList="updateHouseMarkerList"
      />
    </a-col>
  </a-row>
</template>

<style scoped>
.LeftForm {
  padding-right: 30px;
  padding-left: 30px;
  height: 850px;

  border-right: 1px solid black;

  overflow: auto;
}

.LeftForm::-webkit-scrollbar {
  width: 5px;
}
.LeftForm::-webkit-scrollbar-thumb {
  background-color: #2f3542;
  border-radius: 5px;
}
.LeftForm::-webkit-scrollbar-track {
  background-color: rgb(202, 201, 201);
  border-radius: 5px;
  box-shadow: inset 0px 0px 5px white;
}
</style>
