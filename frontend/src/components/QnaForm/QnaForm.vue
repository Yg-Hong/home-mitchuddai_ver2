<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <a-row justify="center" class="QnaBoardTitle">
    <a-col :span="18">
      <a-row justify="center">
        <span class="FontLM NexonFootballGothicBold subTitleBox">
          Qna게시판</span
        >
      </a-row>
    </a-col>
  </a-row>

  <RouterView />
</template>

<style scoped>
.QnaBoardTitle {
  width: 100%;
  padding-top: 110px;
}

.subTitleBox {
  margin-bottom: 20px;
}
</style>
