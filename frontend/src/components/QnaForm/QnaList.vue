<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import QnaAPI from "@/api/QnaAPI.js";

const qnaList = ref([]);
const totalPage = ref(0);

const router = useRouter();

const columns = [
  {
    title: "no",
    dataIndex: "id",
    width: "10%",
  },
  {
    title: "작성자",
    dataIndex: "authorId",
    width: "15%",
  },
  {
    title: "제목",
    dataIndex: "title",
    width: "55%",
  },
  {
    title: "작성일자",
    dataIndex: "createdAt",
  },
];

// const getQnaList = (pageInfo, success, fail) => {
//   QnaAPI.get(`/posts`, pageInfo).then(success).catch(fail);
// };

const getQnaList = (pageInfo) => {
  console.log("getQnaList");
  console.log(pageInfo);

  QnaAPI.getQnaList(
    pageInfo,
    ({ data }) => {
      console.log(data);

      // qnaList.value = data.result;
      totalPage.value = data.total;
      qnaList.value = data.Result;
    },
    (error) => {
      console.log(error);
    }
  );
};

const customRow = (record) => {
  return {
    onClick: () => {
      console.log("click row", record);
      router.push(`/qna/${record.id}`);
    },
  };
};

const onChangePageInfo = (page, size) => {
  console.log("pageInfo changed");

  const pageInfo = {
    page,
    size,
  };

  console.log(pageInfo);

  getQnaList(pageInfo);
};

// init
getQnaList({ page: 1, size: 10 });
</script>

<template>
  <a-row justify="center">
    <a-col :span="24">
      <a-row justify="center">
        <a-col :span="18">
          <a-table
            :customRow="customRow"
            :pagination="false"
            :columns="columns"
            :data-source="qnaList"
          >
            <template #bodyCell="{ column, text }">
              <template v-if="column.dataIndex === 'title'">
                <a>{{ text }}</a>
              </template>
            </template>
          </a-table>
        </a-col>
      </a-row>
    </a-col>
    <a-row class="paginationButtonRow">
      <a-pagination
        v-model:current="current"
        :total="totalPage*10"
        show-less-items
        @change="onChangePageInfo"
      />
    </a-row>
  </a-row>
  <a-row justify="end">
    <a-col :span="3">
      <a-row justify="end">
        <a-button
          class="margin_top writeBtn"
          @click="router.push('/qna/write')"
        >
          글쓰기
        </a-button>
      </a-row>
    </a-col>
    <a-col :span="3"></a-col>
  </a-row>
</template>

<style scoped>
.writeBtn {
  background-color: #002266;
  color: white;
}

.writeBtn:hover {
  background-color: #00387e;
  color: white;
}

.paginationButtonRow {
  margin-top: 20px;
}
</style>
