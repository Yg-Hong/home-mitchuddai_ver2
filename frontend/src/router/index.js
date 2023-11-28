import { createRouter, createWebHistory } from "vue-router";
import { VueCookies } from "vue-cookies";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("@/views/MainView.vue"),
      children: [
        {
          path: "",
          component: () => import("@/components/MainForm/MainForm.vue"),
        },
        {
          path: "qna",
          component: () => import("@/components/QnaForm/QnaForm.vue"),
          children: [
            {
              path: "",
              component: () => import("@/components/QnaForm/QnaList.vue"),
            },
            {
              path: ":id",
              component: () => import("@/components/QnaForm/QnaDetail.vue"),
            },
            {
              path: "write",
              component: () => import("@/components/QnaForm/QnaWrite.vue"),
            },
          ],
        },
        {
          path: "notice",
          component: () => import("@/components/NoticeForm/NoticeForm.vue"),
          children: [
            {
              path: "",
              component: () => import("@/components/NoticeForm/NoticeList.vue"),
            },
            {
              path: ":id",
              component: () =>
                import("@/components/NoticeForm/NoticeDetail.vue"),
            },
          ],
        },
        {
          path: "house/:dongCode",
          component: () => import("@/components/HouseForm/HouseForm.vue"),
          children: [
            {
              path: "",
              component: () =>
                import("@/components/HouseForm/HouseListForm.vue"),
            },
            {
              path: ":aptCode",
              component: () =>
                import("@/components/HouseForm/HouseDetailForm.vue"),
            },
          ],
        },
        {
          path: "mypage",
          component: () => import("@/components/MyPageForm/MyPageForm.vue"),
        },
      ],
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/AboutView.vue"),
    },
    {
      path: "/login",
      name: "login",
      component: () => import("@/views/LoginView.vue"),
      children: [
        {
          path: "",
          component: () => import("@/components/LoginForm/LoginRightForm.vue"),
        },
        {
          path: "register",
          component: () => import("@/components/LoginForm/RegisterForm.vue"),
        },
        {
          path: "findPw",
          component: () => import("@/components/LoginForm/findPwForm.vue"),
        },
      ],
    },
  ],
});

router.beforeEach((to) => {
  // 권한이 있는 페이지의 경우
  if (to.meta.authorized) {
    // 쿠키에서 권한을 확인 한다. // pinia 라우터에서 사용 불가능 하므로.
    const id = VueCookies.get("id");
    // 아이디가 없는 경우 로그인 페이지로 이동
    if (!id) {
      return "/login";
    }
  }
});

export default router;
