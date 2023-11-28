<script setup>
import WithoutLoginNavForm from "@/components/nav/WithoutLoginNavForm.vue";
import WithLoginNavForm from "../components/nav/WithLoginNavForm.vue";
import { RouterView } from "vue-router";
import { useUserStore } from "@/stores/UserStore.js";
import { storeToRefs } from "pinia";

const userStore = useUserStore();
const loginState = storeToRefs(userStore.loginState);

console.log("로그인 여부 : " + loginState.value);
</script>

<template>
  <WithLoginNavForm />
  <!-- <WithoutLoginNavForm /> -->

  <!-- 메인 폼 -->
  <div class="contents">
    <router-view />
  </div>
</template>

<style scoped>
.contents {
  width: 100%;
  padding-top: 110px;
}
</style>
