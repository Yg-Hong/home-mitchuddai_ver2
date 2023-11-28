<script setup>
const props = defineProps({
  house: Object,
});

console.log(props.house);
</script>

<template>
  <div class="houseComponent">
    <a-row justify="start" class="contentRow">
      <a-col class="NexonGothicMedium FontMS">{{ house.apartmentName }}</a-col>
    </a-row>
    <a-row justify="start" class="contentRow">
      <a-col class="NexonGothicLight FontS">
        {{ house.sidoName }} {{ house.gugunNAme }} {{ house.dongName }}
        {{ house.roadName }}
      </a-col>
    </a-row>
    <a-row class="contentRow">
      <a-col class="NexonGothicLight FontS">
        건축년도 : {{ house.buildYear }} 년 {{ house.build }}
      </a-col>
    </a-row>
  </div>
</template>

<style scoped>
.houseComponent {
  width: 400px;

  margin-bottom: 10px;

  border-left: 7px solid #002266;
  border-top: 1px solid rgb(226, 226, 226);
  border-bottom: 1px solid rgb(226, 226, 226);
  border-right: 1px solid rgb(226, 226, 226);

  border-radius: 5px;
}

.houseComponent:hover {
  background-color: #f1f1f1;
}

.contentRow {
  padding-bottom: 5px;

  margin-left: 5px;
}
</style>
