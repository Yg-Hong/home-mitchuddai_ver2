<script setup>
import { reactive, toRaw } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/UserStore.js";
import QnaAPI from "../../api/QnaAPI";
import "@vueup/vue-quill/dist/vue-quill.snow.css";
import QnaEditForm from "@/components/QnaForm/QnaEditForm.vue";

const router = useRouter();
const userStore = useUserStore();

const formState = reactive({
  title: "",
  content: "",
  authorId: userStore.getUserId,
  authorName: userStore.getUserName,
});

const wrapperCol = {
  span: 14,
};

const updateContent = (value) => {
  console.log(value);
  formState.content = value;
};

const onSubmit = () => {
  console.log("submit!", toRaw(formState));
  QnaAPI.writeQna(
    formState,
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
</script>

<template>
  <a-row justify="center">
    <a-col :span="24">
      <a-row justify="center">
        <a-col :span="18">
          <a-form
            :model="formState"
            :label-col="labelCol"
            :wrapper-col="wrapperCol"
          >
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
                />
              </a-col>
            </a-row>
            <a-row class="rowForInputBox">
              <a-col :span="6">
                <label
                  class="NexonFootballGothicLight"
                  for="inputBoxForUserName"
                  >사용자 이름</label
                >
              </a-col>
              <a-col :span="6">
                <a-input
                  id="inputBoxForUserName"
                  v-model:value="formState.userName"
                  disabled
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
                  v-model:value="formState.userId"
                  disabled
                />
              </a-col>
            </a-row>

            <QnaEditForm @updateContent="updateContent" />

            <a-form-item>
              <a-row justify="start">
                <a-button class="submitBox" @click="onSubmit">
                  저장하기
                </a-button>
                <a-button style="margin-left: 10px" @click="onCancel">
                  취소하기
                </a-button>
              </a-row>
            </a-form-item>
          </a-form>
        </a-col>
      </a-row>
    </a-col>
  </a-row>
</template>

<style scoped>
.rowForInputBox {
  margin-bottom: 10px;
}

.submitBox {
  background-color: #002266;
  color: white;
}
</style>
