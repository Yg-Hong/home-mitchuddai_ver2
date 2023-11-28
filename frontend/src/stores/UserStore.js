import { defineStore } from "pinia";
import { ref, computed } from "vue";
import VueCookies from "vue-cookies";

export const useUserStore = defineStore("userStore", () => {
  const loginState = ref(false);
  const userId = ref("");
  const userName = ref("");
  const phoneNumber = ref("");
  const address = ref("");
  const email = ref("");

  //getter
  const getUserId = computed(() => {
    return userId.value;
  });
  const getUserName = computed(() => {
    return userName.value;
  });
  const getPhoneNumber = computed(() => {
    return phoneNumber.value;
  });
  const getAddress = computed(() => {
    return address.value;
  });
  const getEmail = computed(() => {
    return email.value;
  });

  const isLogin = () => {
    //pinia에서 관리하는 객체가 있고, 값이 true이면 로그인 상태
    if (loginState.value == true) {
      return true;
    }

    // 새로고침 같은 이유로 pinia 에는 없지만,
    // 쿠키에 로그인 정보가 있는 경우
    return VueCookies.get() !== null;
  };

  const showMenu = computed(() => {
    // 로그인이 된 경우
    if (isLogin()) {
      return routerMenu;
    }

    // 로그인이 안된 경우
    return routerMenu.filter((menu) => menu.authorize === false);
  });

  const login = (member) => {
    // 로그인 요청 시 loginState 값을 true로 바꿔주기
    // 새로고침 시에도 반영되도록, 쿠키에 저장해 줌
    console.log(
      "유저정보 저장 시도 | id : " + member.userId + " & name : " + name + ""
    );

    loginState.value = true;

    userId.value = member.userId;
    email.value = member.email;
    userName.value = member.name;
    phoneNumber.value = member.phoneNumber;
    address.value = member.address;

    console.log(loginState.value + " " + userId.value + " " + userName.value);
    VueCookies.set("userId", Date.now(), "1d");
    console.log("유저정보 저장 성공");
  };

  const logout = () => {
    loginState.value = false;

    userId.value = "";
    userName.value = "";
    phoneNumber.value = "";
    address.value = "";
    email.value = "";

    VueCookies.remove("userId");
  };

  return {
    login,
    logout,
    isLogin,
    loginState,
    getUserId,
    getUserName,
    getPhoneNumber,
    getAddress,
    getEmail,
    userId,
    userName,
    phoneNumber,
    address,
    email,
  };
});
