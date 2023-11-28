<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <a-row justify="center" class="NoticeTitle">
    <a-col :span="18">
      <a-row justify="center">
        <span class="FontLM NexonFootballGothicBold subTitleBox">
          공지사항</span
        >
      </a-row>
    </a-col>
  </a-row>

  <RouterView />
</template>

<style scoped>
.NoticeTitle {
  width: 100%;
  padding-top: 110px;
}

.subTitleBox {
  margin-bottom: 20px;
}
</style>
