<script setup>
import { useUserStore } from "@/stores/UserStore.js";
import { storeToRefs } from "pinia";
import {useRouter} from "vue-router";

const router = useRouter();
const userStore = useUserStore();
const { userId, userName, email, address, phoneNumber } =
  storeToRefs(userStore);


const onCancel = () => {
  router.go(-1);
}

</script>

<template>
  <div>
    <a-row justify="center" class="MyPageTitle">
      <a-col :span="18">
        <a-row justify="center">
          <span class="FontLM NexonFootballGothicBold subTitleBox">
            마이페이지
          </span>
        </a-row>
      </a-col>
    </a-row>

    <a-row justify="center">
      <a-col :span="24">
        <a-row justify="center">
          <a-col :span="8">
            <a-form
              :model="formState"
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
            >
              <a-row class="rowForInputBox">
                <a-col :span="6">
                  <label
                    class="NexonFootballGothicLight"
                    for="inputBoxForTitle"
                  >
                    이름
                  </label>
                </a-col>
                <a-col :span="18">
                  <a-input id="inputBoxForTitle" v-model:value="userName" />
                </a-col>
              </a-row>
              <a-row class="rowForInputBox">
                <a-col :span="6">
                  <label
                    class="NexonFootballGothicLight"
                    for="inputBoxForUserName"
                    >아이디</label
                  >
                </a-col>
                <a-col :span="18">
                  <a-input id="inputBoxForUserName" v-model:value="userId" />
                </a-col>
              </a-row>
              <a-row class="rowForInputBox">
                <a-col :span="6">
                  <label
                    class="NexonFootballGothicLight"
                    for="inputBoxForUserId"
                    >전화번호</label
                  >
                </a-col>
                <a-col :span="18">
                  <a-input id="inputBoxForUserId" v-model:value="phoneNumber" />
                </a-col>
              </a-row>

              <a-row class="rowForInputBox">
                <a-col :span="6">
                  <label
                    class="NexonFootballGothicLight"
                    for="inputBoxForUserId"
                    >주소</label
                  >
                </a-col>
                <a-col :span="18">
                  <a-input id="inputBoxForUserId" v-model:value="address" />
                </a-col>
              </a-row>

              <a-row class="rowForInputBox">
                <a-col :span="6">
                  <label
                    class="NexonFootballGothicLight"
                    for="inputBoxForUserId"
                    >이메일</label
                  >
                </a-col>
                <a-col :span="18">
                  <a-input id="inputBoxForUserId" v-model:value="email" />
                </a-col>
              </a-row>

              <QnaEditForm @updateContent="updateContent" />

              <a-form-item>
                <a-row justify="start">
                  <a-button class="submitBox" @click="onSubmit">
                    저장하기
                  </a-button>
                  <a-button style="margin-left: 10px" @click="onCancel">
                    뒤로가기
                  </a-button>
                </a-row>
              </a-form-item>
            </a-form>
          </a-col>
        </a-row>
      </a-col>
    </a-row>
  </div>
</template>

<style scoped>
.MyPageTitle {
  width: 100%;
  padding-top: 110px;
}

.subTitleBox {
  margin-bottom: 20px;
}

.rowForInputBox {
  margin-bottom: 30px;

  height: 40px;
}

.submitBox {
  background-color: #002266;
  color: white;
}
</style>
