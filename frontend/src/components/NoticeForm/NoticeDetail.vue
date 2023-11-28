<script setup>
import { reactive } from "vue";
import { useRouter } from "vue-router";
import "@vueup/vue-quill/dist/vue-quill.snow.css";
import QnaEditForm from "@/components/QnaForm/QnaEditForm.vue";

const router = useRouter();

const formState = reactive({
  title: "",
  content: "",
});

const wrapperCol = {
  span: 14,
};

const onCancel = () => {
  console.log("cancel!");
  router.go(-1);
};
</script>

<template>
  <a-form :model="formState" :label-col="labelCol" :wrapper-col="wrapperCol">
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
          :disabled="true"
        />
      </a-col>
    </a-row>

    <QnaEditForm
      @updateContent="updateContent"
      :EditableFlag="false"
      :theme="bubble"
    />

    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button style="margin-left: 10px" @click="onCancel">뒤로가기</a-button>
    </a-form-item>
  </a-form>
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
