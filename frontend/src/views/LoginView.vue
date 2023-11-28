<script setup>
import LoginForm from "../components/LoginForm/LoginForm.vue";
</script>

<template>
  <a-row class="container" justify="center">
    <div class="loginForm">
      <LoginForm />
    </div>
  </a-row>
</template>

<style scoped>
.container {
  height: 900px;

  align-items: center;
}
.loginForm {
  width: 1280px;
  height: 100%;

  /* 정가운데 정렬 */
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
