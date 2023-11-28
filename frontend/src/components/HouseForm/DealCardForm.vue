<script setup>
import { defineProps } from "vue";
const props = defineProps({
  deal: Object,
});

console.log(props.deal);
</script>

<template>
  <div class="dealCardComponent">
    <a-row>
      <a-col :span="8" class="NexonGothicMedium">거래일 :</a-col>
      <a-col :span="12" class="NexonGothicMedium">
        {{ props.deal.dealYear }}년 {{ props.deal.dealMonth }}월
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="8" class="NexonGothicMedium">거래금액 : </a-col>
      <a-col :span="12" class="NexonGothicMedium"> {{ props.deal.dealAmount }} </a-col>
    </a-row>
    <a-row>
      <a-col :span="8" class="NexonGothicMedium">전용면적 : </a-col>
      <a-col :span="12" class="NexonGothicMedium"> {{ props.deal.area }} m2 </a-col>
    </a-row>
    <a-row>
      <a-col :span="8" class="NexonGothicMedium">층 : </a-col>
      <a-col :span="12" class="NexonGothicMedium"> {{ props.deal.floor }} </a-col>
    </a-row>
  </div>
</template>

<style scoped>
.dealCardComponent {
  width: 400px;

  margin-bottom: 5px;

  padding-left: 10px;

  border-left: 7px solid #002266;
  border-top: 1px solid rgb(226, 226, 226);
  border-bottom: 1px solid rgb(226, 226, 226);
  border-right: 1px solid rgb(226, 226, 226);

  border-radius: 5px;
}

.dealCardComponent:hover {
  background-color: #f1f1f1;
}
</style>
