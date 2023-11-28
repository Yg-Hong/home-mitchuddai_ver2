import { ref } from "vue";
import { defineStore } from "pinia";

export const useKakaoStore = defineStore("kakao", () => {
  const map = ref(null);
  const mapCenterLatLng = ref([36.35536036402348, 127.29840381673439]);

  return { map, mapCenterLatLng };
});
