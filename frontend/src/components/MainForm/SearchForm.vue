<script setup>
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
import LocationAPI from "@/api/LocationAPI.js";

const router = useRouter();

const sidoCode = ref("");
const gugunCode = ref("");
const dongCode = ref("");

const sidoList = ref([]);
const gugunList = ref([]);
const dongList = ref([]);

const getSidoList = () => {
  LocationAPI.getSidoList(
    ({ data }) => {
      sidoList.value = data;
    },
    () => {
      console.log("시도정보 조회에 실패하였습니다.");
    }
  );
};

const getGugunList = () => {
  LocationAPI.getGugunList(
    sidoCode.value,
    ({ data }) => {
      gugunList.value = data;
    },
    () => {
      console.log("구군정보 조회에 실패하였습니다.");
    }
  );
};

const getDongList = () => {
  LocationAPI.getDongList(
    gugunCode.value,
    ({ data }) => {
      dongList.value = data;
    },
    () => {
      console.log("동정보 조회에 실패하였습니다.");
    }
  );
};

const onSearchByDongCode = () => {
  console.log(dongCode.value);
  router.push(`/house/${dongCode.value}`);
};

getSidoList();

watch(sidoCode, async () => {
  getGugunList();
});

watch(gugunCode, async () => {
  getDongList();
});
</script>

<template>
  <a-row class="content">
    <a-row class="contentRow">
      <a-col :span="24">
        <a-row justify="center">
          <a-col :span="6">
            <a-row justify="center">
              <a-select
                ref="select"
                v-model:value="sidoCode"
                class="select_box"
                show-search
                placeholder="시도선택"
                @focus="focus"
                @change="handleChange"
              >
                <a-select-option v-for="sido in sidoList" :key="sido.code">{{
                  sido.name
                }}</a-select-option>
              </a-select>
            </a-row>
          </a-col>
          <a-col :span="6">
            <a-row justify="center">
              <a-select
                ref="select"
                v-model:value="gugunCode"
                class="select_box"
                show-search
                placeholder="구군선택"
                @focus="focus"
                @change="handleChange"
              >
                <a-select-option v-for="gugun in gugunList" :key="gugun.code">{{
                  gugun.name
                }}</a-select-option>
              </a-select>
            </a-row>
          </a-col>
          <a-col :span="6">
            <a-row justify="center">
              <a-select
                ref="select"
                v-model:value="dongCode"
                class="select_box"
                show-search
                placeholder="동선택"
                @focus="focus"
                @change="handleChange"
              >
                <a-select-option v-for="dong in dongList" :key="dong.code">{{
                  dong.name
                }}</a-select-option>
              </a-select>
            </a-row>
          </a-col>
        </a-row>
      </a-col>
      <a-col :span="24">
        <a-row justify="center">
          <button class="search_button" @click="onSearchByDongCode">
            <span class="NexonGothicMedium">검색하기</span>
          </button>
        </a-row>
      </a-col>
    </a-row>
  </a-row>
</template>

<style scoped>
.content {
  width: 100%;
  height: 500px;
  /* background-color: blanchedalmond; */
  align-items: center;
  display: flex;
  justify-content: center;

  background-image: url("@/assets/img/SearchFormBackgroundImg.jpg");
  background-size: cover;
}

.radio_input {
  display: none;
}

.upper_row {
  /* 사이즈 */
  height: 35px;
  width: 600px;

  /* 패딩 */
  margin-bottom: 20px;
}

.radio-label {
  /* 사이즈 */
  height: 35px;

  /* 정렬 */
  display: flex;
  justify-content: center;
  align-items: center;

  /* 테두리 */
  border: 1px solid black;
  border-radius: 3px;

  /* 마진 */
  margin-left: 3px;
  margin-right: 3px;
}

.radio-label:hover {
  background-color: black;
}

.select_box {
  width: 160px;
}

.search_button {
  /* 마진 */
  margin-top: 20px;

  /* 테두리 */
  border-radius: 5px;
  border: 1px solid black;

  background-color: #f7e1cc;

  /* 사이즈 */
  height: 38px;
  width: 560px;
}

.search_button:hover {
  background-color: #f7d8b8;
}

.search_button > span {
  font-size: 20px;
}

.contentRow {
  width: 800px;
}
</style>
