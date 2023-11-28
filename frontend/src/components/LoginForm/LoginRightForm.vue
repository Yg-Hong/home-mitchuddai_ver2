<script setup>
import { reactive, computed } from "vue";
import {
  UserOutlined,
  LockOutlined,
  CaretRightOutlined,
} from "@ant-design/icons-vue";
import { useUserStore } from "@/stores/userStore.js";
import { useRouter } from "vue-router";
import MemberAPI from "@/api/MemberAPI.js";

const formState = reactive({
  userId: "",
  userPassword: "",
  remeber: true,
});

const userStore = useUserStore();
const router = useRouter();

const disabled = computed(() => {
  return !(formState.userId && formState.userPassword);
});

const Login = () => {
  console.log("로그인 버튼 클릭");

  const user = {
    userId: formState.userId,
    password: formState.userPassword,
  };

  console.log(user);

  MemberAPI.tryLogin(
    user,
    ({ data }) => {
      console.log(
        "로그인 성공 Id: " + data.userId + " | password" + data.password
      );
      userStore.login(data);

      router.push("/");
    },
    () => {
      console.log("로그인 실패");
    }
  );
};
</script>

<template>
  <div class="NexonFootballGothicBold FontL FontColorA">Home Mitchuddai</div>
  <div class="NexonFootballGothicBold FontM FontColorB">로그인 해주세요.</div>
  <a-form
    :model="formState"
    name="normal_login"
    class="login-form"
    @finish="onfinish"
    @finishFailed="onfinishFailed"
  >
    <a-form-item
      name="userId"
      :rules="[{ requried: true, messgae: '아이디를 입력해주세요!' }]"
    >
      <a-input
        v-model:value="formState.userId"
        placeholder="ID"
        class="InputBox"
      >
        <template #prefix>
          <UserOutlined class="site-form-item-icon" />
        </template>
      </a-input>
    </a-form-item>

    <a-form-item
      name="userPassword"
      :rules="[{ required: true, message: '비밀번호를 입력해주세요!' }]"
    >
      <a-input-password
        v-model:value="formState.userPassword"
        placeholder="Password"
        class="InputBox"
      >
        <template #prefix>
          <LockOutlined class="site-form-item-icon" />
        </template>
      </a-input-password>
    </a-form-item>

    <a-form-item>
      <a-row justify="space-between">
        <a-col :span="6">
          <a-form-item name="remember" no-style>
            <a-checkbox
              v-model:checked="formState.remember"
              class="NexonGothicBold FontColorA"
              >아이디 저장
            </a-checkbox>
          </a-form-item>
        </a-col>
        <a-col span="6">
          <a-row justify="end">
            <a class="NexonGothicBold FontColorC" href="">비밀번호 찾기</a>
          </a-row>
        </a-col>
      </a-row>
    </a-form-item>

    <a-row justify="end">
      <a-form-item>
        <a-button
          :disabled="disabled"
          class="login-form-button"
          id="login-form-button"
          @click="Login"
        >
          <span>Log in </span> <CaretRightOutlined />
        </a-button>
      </a-form-item>
    </a-row>
  </a-form>

  <a-flex :vertical="true" class="login-form-footer" justify="end">
    <a-row justify="space-between">
      <a-col :span="12">
        <router-link to="/login/register">
          <span class="NexonGothicBold FontColorC">회원가입 하기</span>
        </router-link>
      </a-col>
    </a-row>
  </a-flex>
</template>

<style scoped>
#components-form-demo-normal-login .login-form {
  max-width: 300px;
}
#components-form-demo-normal-login .login-form-forgot {
  float: right;
}
#components-form-demo-normal-login .login-form-button {
  width: 100px;
}

.InputBox {
  height: 50px;
}

#login-form-button {
  background-color: #002266;
  color: #ffffff;

  height: 40px;
  width: 100px;
}

#login-form-button:hover {
  background-color: #00387e;
}

.login-form-footer {
  margin-top: 80px;

  border-top: 1px solid;
}
</style>
