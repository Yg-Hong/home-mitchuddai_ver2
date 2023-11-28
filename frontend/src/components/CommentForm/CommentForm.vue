<script setup>
import dayjs from "dayjs";
import { ref } from "vue";
import relativeTime from "dayjs/plugin/relativeTime";
import {
  LikeFilled,
  LikeOutlined,
  DislikeFilled,
  DislikeOutlined,
  AliwangwangOutlined,
} from "@ant-design/icons-vue";

dayjs.extend(relativeTime);
const likes = ref(0);
const dislikes = ref(0);
const action = ref();
const like = () => {
  likes.value = 1;
  dislikes.value = 0;
  action.value = "liked";
};
const dislike = () => {
  likes.value = 0;
  dislikes.value = 1;
  action.value = "disliked";
};
</script>

<template>
  <a-comment>
    <template #actions>
      <span key="comment-basic-like">
        <a-tooltip title="Like">
          <template v-if="action === 'liked'">
            <LikeFilled @click="like" />
          </template>
          <template v-else>
            <LikeOutlined @click="like" />
          </template>
        </a-tooltip>
        <span style="padding-left: 8px; cursor: auto">
          {{ likes }}
        </span>
      </span>
      <span key="comment-basic-dislike">
        <a-tooltip title="Dislike">
          <template v-if="action === 'disliked'">
            <DislikeFilled @click="dislike" />
          </template>
          <template v-else>
            <DislikeOutlined @click="dislike" />
          </template>
        </a-tooltip>
        <span style="padding-left: 8px; cursor: auto">
          {{ dislikes }}
        </span>
      </span>
      <span key="comment-basic-reply-to">Reply to</span>
    </template>
    <template #author><a>홍윤기</a></template>
    <template #avatar>
      <AliwangwangOutlined alt="Han Solo" />
    </template>
    <template #content>
      <p>살기 좋은 집!!! 민수에게 추천해요~~!!</p>
    </template>
    <template #datetime>
      <a-tooltip :title="dayjs().format('YYYY-MM-DD HH:mm:ss')">
        <span>{{ dayjs().fromNow() }}</span>
      </a-tooltip>
    </template>
  </a-comment>
</template>

<style scoped></style>
