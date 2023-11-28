<script setup>
import HouseDetailCardForm from "@/components/HouseForm/HouseDetailCardForm.vue";
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useHouseStore } from "@/stores/HouseStore.js";
import { storeToRefs } from "pinia";

const houseStore = useHouseStore();
const { houseList, house } = storeToRefs(houseStore);
console.log(houseList);

onMounted(() => {
  if (houseList.value.length == 0) {
    console.log("houseList is empty");
    houseStore.setHouseList1(route.params.dongCode);
  }

  console.log(houseStore.getHouseList);
  console.log("mounted");
});

const router = useRouter();
const route = useRoute();

// watch(houseList, () => {
//   console.log("houseList changed");
//   let newArray = [];

//   houseList.value.forEach((house) => {
//     newArray.push({
//       aptCode: house.aptCode,
//       title: house.apartmentName,
//       latlng: [house.lat, house.lng],
//     });
//   });

//   console.log(newArray);

//   emit("changeLatAndLngList", newArray);
// });

const onClickHouseDetailInfo = (aptCode) => {
  let dongCode = route.params.dongCode;
  router.push(`/house/${dongCode}/${aptCode}`);

  for (let i = 0; i < houseList.value.length; i++) {
    if (houseList.value[i].aptCode == aptCode) {
      house.value = houseList.value[i];
    }
  }
};

const current = ref(2);
</script>

<template>
  <div class="HouseListForm">
    <a-row justify="center" class="titleRow">
      <a-typography-title :level="2" class="NexonGothicMedium">
        실거래 건물 목록
      </a-typography-title>
    </a-row>
    <template v-for="(house, index) in houseList" :key="index">
      <a-row justify="center">
        <HouseDetailCardForm v-bind:house="house" @click="onClickHouseDetailInfo(house.aptCode)" />
        <template>
          <a-pagination v-model:current="current" simple :total="10" />
        </template>
      </a-row>
    </template>
  </div>
</template>

<style scoped>
.HouseListForm {
  height: 850px;
}

.titleRow {
  margin-top: 20px;
  margin-bottom: 10px;
}

.contentHeader {
  padding-top: 10px;

  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
}

.title h2 {
  text-align: center;

  text-transform: uppercase;
  font-size: 26px;
  letter-spacing: 1px;

  display: grid;
  grid-template-columns: 1fr auto 1fr;
  grid-template-rows: 16px 0;
  grid-gap: 22px;
}

.title h2:after,
.title h2:before {
  content: " ";
  display: block;
  border-bottom: 2px solid #ccc;
  background-color: #f8f8f8;
}
</style>
