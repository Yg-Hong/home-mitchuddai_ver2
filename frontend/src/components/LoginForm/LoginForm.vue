<script setup>
import { RouterView } from "vue-router";
import LoginLeftForm from "./LoginLeftForm.vue";
</script>

<template>
  <a-row justify="space-between">
    <LoginLeftForm />

    <a-col :span="8">
      <div class="right">
        <RouterView />
      </div>
    </a-col>
  </a-row>
  <!-- <div class="whole_frame"></div> -->
</template>

<style scoped>
/* .left {
  width: 453px;
  height: 575px;
} */

.right {
  width: 100%;
  height: 100%;

  flex-direction: column;
  align-content: center;
}
</style>
