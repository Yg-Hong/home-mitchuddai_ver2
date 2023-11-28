<script setup></script>

<template>
  <a-col :span="15">
    <div class="left">
      <div class="background">
        <img src="@/assets/img/LoginImg.jpg" class="background_img" />
      </div>
    </div>
  </a-col>
</template>

<style scoped>
.left {
  width: 100%;
  height: 100%;
}

.background {
  max-width: 1280px;
  height: 620px;
  overflow: hidden;
}

.background_img {
  /* width: 100%; */
  padding-left: 10px;
  height: 620px;
}
</style>
