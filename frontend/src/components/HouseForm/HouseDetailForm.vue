<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref, watch } from "vue";
import CommentForm from "@/components/CommentForm/CommentForm.vue";
import CommentInputForm from "@/components/CommentForm/CommentInputForm.vue";
import DealCardForm from "@/components/HouseForm/DealCardForm.vue";
import HouseAPI from "@/api/HouseAPI.js";
import { useHouseStore } from "@/stores/HouseStore.js";
import { storeToRefs } from "pinia";
import { MenuFoldOutlined, CaretDownOutlined } from "@ant-design/icons-vue";

const houseStore = useHouseStore();
const { house } = storeToRefs(houseStore);

const route = useRoute();
const router = useRouter();

// const store = useHouseDetailInfoStore();
// const { houseDetailInfo } = storeToRefs(store);

const houseDetailInfo = ref({
  apartmentName: "",
  sidoName: "",
  gugunName: "",
  dongName: "",
  roadName: "",
  roadNameBonbun: "",
  buildYear: "",
  houseDeals: [],
});

const distanceList = houseStore.getDistance;

// const houseDetailInfo = ref({});
watch(route.params, () => {
  getHouseDetail();
});
const getHouseDetail = () => {
  HouseAPI.getHouseDetail(
    route.params.dongCode,
    route.params.aptCode,
    ({ data }) => {
      console.log(data);
      houseDetailInfo.value = data;
    },
    () => {
      console.log("실거래 상세정보 조회에 실패하였습니다.");
    }
  );
};
getHouseDetail();

const onClickTobackToTheList = () => {
  console.log("뒤로가기");
  router.push(`/house/${route.params.dongCode}`);

  //선택된 house 초기화
  house.value = {};
};

const activeKey = ref([]);
watch(activeKey, (val) => {
  console.log(val);
});
</script>

<template>
  <br />

  <a-row justify="start">
    <a-typography-title :level="4" class="NexonGothicMedium backToHouseListButton">
      <span @click="onClickTobackToTheList"> <MenuFoldOutlined /> 목록으로 </span>
    </a-typography-title>
  </a-row>
  <a-row justify="center">
    <div class="title">
      <a-typography-title :level="1" class="NexonGothicMedium">
        실거래 상세 자료
      </a-typography-title>
    </div>
  </a-row>
  <div class="detailInfoBox">
    <a-row class="subTitle">
      <a-col :span="1"></a-col>
      <a-col :span="22">
        <a-typography-title :level="3" class="NexonGothicLight">
          <CaretDownOutlined />기본정보
        </a-typography-title>
      </a-col>
    </a-row>
    <a-row justity="space-between">
      <a-col :span="2"></a-col>
      <a-col :span="6"
        ><span class="NexonGothicLight FontCustom">건 &nbsp &nbsp &nbsp 물 : </span>
      </a-col>
      <a-col :span="12">
        <span class="NexonGothicLight FontCustom">{{ houseDetailInfo.apartmentName }}</span>
      </a-col>
    </a-row>
    <a-row justity="space-between">
      <a-col :span="2"></a-col>
      <a-col :span="6"
        ><span class="NexonGothicLight FontCustom"> 주 &nbsp &nbsp &nbsp 소 : </span></a-col
      >
      <a-col :span="12">
        <span class="NexonGothicLight FontCustom">
          {{ houseDetailInfo.sidoName }} {{ houseDetailInfo.gugunName }}
          {{ houseDetailInfo.dongName }}
        </span>
      </a-col>
    </a-row>
    <a-row justity="space-between">
      <a-col :span="2"></a-col>
      <a-col :span="6"><span class="NexonGothicLight FontCustom"> 상세주소 :</span></a-col>
      <a-col :span="12">
        <span class="NexonGothicLight FontCustom">
          {{ houseDetailInfo.roadName }} {{ houseDetailInfo.roadNameBonbun }}
        </span>
      </a-col>
    </a-row>
    <a-row justity="space-between">
      <a-col :span="2"></a-col>
      <a-col :span="6"><span class="NexonGothicLight FontCustom"> 건축년도 : </span></a-col>
      <a-col :span="12">
        <span class="NexonGothicLight FontCustom">{{ houseDetailInfo.buildYear }} 년 </span>
      </a-col>
    </a-row>
  </div>

  <div>
    <a-collapse v-model:activeKey="activeKey" accordion>
      <a-collapse-panel key="1" header="실거래 목록">
        <div class="dealList">
          <template v-for="(deal, index) in houseDetailInfo.houseDeals" :key="index">
            <a-row justify="center">
              <DealCardForm :deal="deal" />
            </a-row>
          </template>
        </div>
      </a-collapse-panel>
      <a-collapse-panel key="3" header="주변 편의시설">
        <a-row class="space_evenly_box">
          <a-col :span="12">
            <a-row justify="center">
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS">편의점</a-row>
              </a-col>
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS">
                  {{ distanceList[7].timeOnFoot }} 분
                </a-row>
              </a-col>
            </a-row>
          </a-col>
          <a-col :span="12">
            <a-row justify="center">
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS"> 지하철 </a-row>
              </a-col>
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS">
                  {{ distanceList[4].timeOnFoot }} 분
                </a-row>
              </a-col>
            </a-row>
          </a-col>
        </a-row>
        <a-row class="space_evenly_box">
          <a-col :span="12">
            <a-row justify="center">
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS">은행</a-row>
              </a-col>
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS">
                  {{ distanceList[1].timeOnFoot }} 분
                </a-row>
              </a-col>
            </a-row>
          </a-col>
          <a-col :span="12">
            <a-row justify="center">
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS"> 병원 </a-row>
              </a-col>
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS">
                  {{ distanceList[6].timeOnFoot }} 분
                </a-row>
              </a-col>
            </a-row>
          </a-col>
        </a-row>
        <a-row class="space_evenly_box">
          <a-col :span="12">
            <a-row justify="center">
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS"> 학교 </a-row>
              </a-col>
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS">
                  {{ distanceList[2].timeOnFoot }} 분
                </a-row>
              </a-col>
            </a-row>
          </a-col>
          <a-col :span="12">
            <a-row justify="center">
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS"> 약국 </a-row>
              </a-col>
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS">
                  {{ distanceList[3].timeOnFoot }} 분
                </a-row>
              </a-col>
            </a-row>
          </a-col>
        </a-row>
        <a-row class="space_evenly_box">
          <a-col :span="12">
            <a-row justify="center">
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS"> 카페 </a-row>
              </a-col>
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS">
                  {{ distanceList[5].timeOnFoot }} 분
                </a-row>
              </a-col>
            </a-row>
          </a-col>
          <a-col :span="12">
            <a-row justify="center">
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS"> 마트 </a-row>
              </a-col>
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS">
                  {{ distanceList[0].timeOnFoot }} 분
                </a-row>
              </a-col>
            </a-row>
          </a-col>
        </a-row>
        <a-row class="space_evenly_box">
          <a-col :span="12">
            <a-row justify="center">
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS"> 주차장 </a-row>
              </a-col>
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS">
                  {{ distanceList[8].timeOnFoot }} 분
                </a-row>
              </a-col>
            </a-row>
          </a-col>
          <a-col :span="12">
            <a-row justify="center">
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS"> 주유소 </a-row>
              </a-col>
              <a-col :span="10">
                <a-row justify="center" class="NexonGothicLight FontMMS">
                  {{ distanceList[9].timeOnFoot }} 분
                </a-row>
              </a-col>
            </a-row>
          </a-col>
        </a-row>
      </a-collapse-panel>
      <a-collapse-panel key="4" header="댓글">
        <CommentForm />
        <CommentInputForm />
      </a-collapse-panel>
    </a-collapse>
  </div>
</template>

<style scoped>
.subTitle {
  margin-top: 20px;
}

.space_evenly_box {
  display: flex;
  justify-content: space-evenly;
}

.dealList {
  height: 500px;
  overflow: auto;
}

.houseDealInfo {
  margin-top: 5px;
}

.backToHouseListButton {
  cursor: pointer;
}

.backToHouseListButton:hover {
  background-color: #f0f0f0;
}
.FontCustom {
  font-size: 18px;
}

.ant-collapse-item {
  font-size: 18px;
}

.FontMMS {
  font-size: 15px;
}
</style>
