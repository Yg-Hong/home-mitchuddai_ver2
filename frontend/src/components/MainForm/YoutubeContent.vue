<script setup>
import YouTube from "vue3-youtube";

const props = defineProps(["src"]);

const onReady = () => {};
</script>

<template>
  <YouTube :src="props.src" @ready="onReady" ref="youtube" />
</template>

<style scoped></style>
