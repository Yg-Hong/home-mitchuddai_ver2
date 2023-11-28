<script setup>
import NewsContent from "@/components/MainForm/NewsContent.vue";
import BoardContent from "@/components/MainForm/BoardContent.vue";
import YoutubeContent from "./YoutubeContent.vue";
import { LeftCircleOutlined, RightCircleOutlined } from "@ant-design/icons-vue";
</script>

<template>
  <div class="LowerContent">
    <a-carousel arrows>
      <template #prevArrow>
        <div class="custom-slick-arrow" style="left: 10px; z-index: 1">
          <left-circle-outlined />
        </div>
      </template>
      <template #nextArrow>
        <div class="custom-slick-arrow" style="right: 10px">
          <right-circle-outlined />
        </div>
      </template>
      <div>
        <a-row justify="center">
          <div class="ContentSubtitle">
            <a-typography-title :level="3" class="NexonGothicBold">뉴스</a-typography-title>
          </div>
        </a-row>
        <NewsContent />
      </div>
      <div>
        <a-row justify="center">
          <div class="ContentSubtitle">
            <a-typography-title :level="3" class="NexonGothicBold">유튜브</a-typography-title>
          </div>
        </a-row>
        <a-row justify="center">
          <a-col :span="21">
            <a-row justify="center">
              <YoutubeContent src="https://www.youtube.com/watch?v=PCXOh-LhkaA" />
            </a-row>
          </a-col>
        </a-row>
      </div>
      <div>
        <a-row justify="center">
          <div class="ContentSubtitle">
            <a-typography-title :level="3" class="NexonGothicBold">공지사항</a-typography-title>
          </div>
        </a-row>
        <BoardContent />
      </div>
    </a-carousel>
  </div>
</template>

<style scoped>
.LowerContent {
  height: 600px;
}

/* For demo */
:deep(.slick-slide) {
  text-align: center;
  height: 550px;
  line-height: 160px;
  background: #364d79;
  overflow: hidden;
}

:deep(.slick-arrow.custom-slick-arrow) {
  width: 25px;
  height: 25px;
  font-size: 25px;
  color: #fff;
  background-color: rgba(31, 45, 61, 0.11);
  transition: ease all 0.3s;
  opacity: 0.3;
  z-index: 1;
}
:deep(.slick-arrow.custom-slick-arrow:before) {
  display: none;
}
:deep(.slick-arrow.custom-slick-arrow:hover) {
  color: #fff;
  opacity: 0.5;
}

:deep(.slick-slide h3) {
  color: #fff;
}

.ContentSubtitle {
  margin-top: 20px;
  width: 280px;
}

.ContentSubtitle h3 {
  text-align: center;
  font-size: 30px;
  font-weight: 300;
  color: white;
  letter-spacing: 1px;
  text-transform: uppercase;

  display: grid;
  grid-template-columns: 1fr max-content 1fr;
  grid-template-rows: 27px 0;
  grid-gap: 20px;
  align-items: center;
}

.ContentSubtitle h3:after,
.ContentSubtitle h3:before {
  content: " ";
  display: block;
  border-bottom: 1.5px solid #f7e1cc;
  border-top: 1.5px solid #f7e1cc;
  height: 5px;
  background-color: #364d79;
}
</style>
