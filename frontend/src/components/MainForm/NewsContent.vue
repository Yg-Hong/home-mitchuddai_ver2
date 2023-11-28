<script setup>
import CommonAPI from "@/api/CommonAPI.js";
import { ref } from "vue";
import { toReactive } from "@vueuse/core";

const originNewsList = ref([]);
const headerNews = ref({});
const newsList = ref([]);

const getNewsContent = () => {
  CommonAPI.getNewsContent(
    ({ data }) => {
      originNewsList.value = data.items;

      newsList.value = toReactive(originNewsList.value.slice(0, 5));

      console.log(headerNews.value);
      console.log(newsList.value);
    },
    () => {
      console.log("뉴스정보 조회에 실패하였습니다.");
    }
  );
};
getNewsContent();

// const newsList = originNewsList;

const onClickNews = (url) => {
  window.open(url);
};
</script>

<template>
  <div>
    <a-row justify="center">
      <a-col :span="16">
        <a-card>
          <a-list :item-layout="horizontal" :dataSource="newsList" :renderItem="item">
            <template #renderItem="{ item }">
              <a-list-item>
                <a-list-item-meta
                  :title="item.title + '\n\n' + item.description.slice(0, 40) + '...'"
                  :description="item.pubDate"
                  @click="onClickNews(item.link)"
                ></a-list-item-meta>
              </a-list-item>
            </template>
          </a-list>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<style scoped></style>
