<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const boardList = ref([
  {
    id: 1,
    title: "home mitchuddai(홈미쳐따이) 첫 개장",
    description: "(간장게장 아님 주의)",
    pubDate: "2021-08-20 00:00:00",
  },
]);

const router = useRouter();

const onClickBoards = () => {
  //   router.push(`/board/1`);
};
</script>

<template>
  <div>
    <a-row justify="center">
      <a-col :span="16">
        <a-card>
          <a-list :item-layout="horizontal" :dataSource="boardList" :renderItem="item">
            <template #renderItem="{ item }">
              <a-list-item>
                <a-list-item-meta
                  :title="item.title + '\n\n' + item.description.slice(0, 40) + '...'"
                  :description="item.pubDate"
                  @Click="onClickBoards(item.id)"
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
