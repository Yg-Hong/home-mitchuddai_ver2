<script setup>
import { reactive, toRaw, ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import "@vueup/vue-quill/dist/vue-quill.snow.css";
import QnaEditForm from "@/components/QnaForm/QnaEditForm.vue";
import QnaAPI from "@/api/QnaAPI";

const router = useRouter();
const route = useRoute();

const formState = ref({
  id: "",
  title: "",
  authorId: "",
  content: "",
});

// const getQnaDetail = (id, success, fail) => {
//   QnaAPI.get(`/posts/${id}`).then(success).catch(fail);
// };

const getQnaDetail = () => {
  console.log(route.params.id);

  QnaAPI.getQnaDetail(
    route.params.id,
    ({ data }) => {
      console.log(data);
      formState.value = data;
    },
    (error) => {
      console.log(error);
    }
  );
};

const readOnlyFlag = ref(true);

// const updateQna = (id, qna, success, fail) => {
//   QnaAPI.patch(`/posts/${id}`, qna).then(success).catch(fail);
// };

const onSave = () => {
  console.log("submit!", toRaw(formState));
  console.log("key no : " + route.params.id);

  QnaAPI.updateQna(
    route.params.id,
    formState.value,
    (response) => {
      console.log(response);
      router.go(-1);
    },
    (error) => {
      console.log(error);
    }
  );
};

const wrapperCol = {
  span: 14,
};

const updateContent = (value) => {
  console.log(value);
  formState.value.content = value;
};

const onModify = () => {
  readOnlyFlag.value = !readOnlyFlag.value;
  console.log(readOnlyFlag.value);
};

const onDelete = () => {
  console.log("delete!");

  QnaAPI.deleteQna(
    route.params.id,
    (response) => {
      console.log(response);
      router.go(-1);
    },
    (error) => {
      console.log(error);
    }
  );
};

const onCancel = () => {
  console.log("cancel!");
  router.go(-1);
};

getQnaDetail();
</script>

<template>
  <a-form :model="formState" :label-col="labelCol" :wrapper-col="wrapperCol">
    <a-row justify="center">
      <a-col :span="24">
        <a-row justify="center">
          <a-col :span="18">
            <a-row class="rowForInputBox">
              <a-col :span="6">
                <label class="NexonFootballGothicLight" for="inputBoxForTitle"
                  >제목</label
                >
              </a-col>
              <a-col :span="18">
                <a-input
                  id="inputBoxForTitle"
                  v-model:value="formState.title"
                  :disabled="readOnlyFlag"
                />
              </a-col>
            </a-row>
            <a-row class="rowForInputBox">
              <a-col :span="6">
                <label class="NexonFootballGothicLight" for="inputBoxForUserId"
                  >사용자 아이디</label
                >
              </a-col>
              <a-col :span="6">
                <a-input
                  id="inputBoxForUserId"
                  v-model:value="formState.authorId"
                  disabled
                />
              </a-col>
            </a-row>

            <QnaEditForm
              @updateContent="updateContent"
              :readOnlyFlag="readOnlyFlag"
              :prevContent="formState.content"
            />
          </a-col>
        </a-row>
      </a-col>
    </a-row>

    <a-form-item>
      <a-row justify="start">
        <a-col :span="5"> </a-col>
        <a-col :span="18">
          <a-button class="actionBtnBox" @click="onModify">수정하기</a-button>
          <a-button class="actionBtnBox" @click="onSave">저장하기</a-button>
          <a-button class="actionBtnBox" @click="onReply"
            >답변 입력하기</a-button
          >

          <a-button style="margin-left: 10px" @click="onDelete">
            삭제하기
          </a-button>
          <a-button style="margin-left: 10px" @click="onCancel">
            뒤로 가기
          </a-button>
        </a-col>
      </a-row>
    </a-form-item>
  </a-form>
</template>

<style scoped>
.rowForInputBox {
  margin-bottom: 10px;
}

.actionBtnBox {
  background-color: #002266;
  color: white;
}
</style>
