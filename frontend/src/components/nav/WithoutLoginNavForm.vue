<script setup></script>

<template>
  <div class="nav">
    <a-row class="nav_row" justify="space-between">
      <a-col :span="3">
        <router-link to="/">
          <a-row
            justify="start"
            class="nav_logo NexonFootballGothicBold FontM FontColorA item_align_center"
          >
            Home
          </a-row>
          <a-row
            justify="center"
            class="nav_logo NexonFootballGothicBold FontM FontColorA item_align_center"
          >
            mitchuddai
          </a-row>
        </router-link>
      </a-col>
      <a-col :span="15">
        <a-row justify="right">
          <a-col :span="18">
            <a-row class="navBarMenu">
              <a-col :span="4" class="navBarItem NexonFootballGothicBold FontMMS FontColorA">
                <router-link to="/">소개</router-link>
              </a-col>
              <a-col :span="4" class="navBarItem NexonFootballGothicBold FontMMS FontColorA">
                <router-link to="/news">뉴스</router-link>
              </a-col>
              <a-col :span="6" class="navBarItem NexonFootballGothicBold FontMMS FontColorA">
                <router-link to="/house">실거래가 조회</router-link>
              </a-col>
              <a-col :span="4" class="navBarItem NexonFootballGothicBold FontMMS FontColorA">
                <router-link to="/notice">공지사항</router-link>
              </a-col>
              <a-col :span="4" class="navBarItem NexonFootballGothicBold FontMMS FontColorA">
                <router-link to="/qna">Q&A</router-link>
              </a-col>
            </a-row>
          </a-col>
          <a-col :span="6">
            <a-row class="navBarMenu">
              <a-col :span="10" class="navBarItem NexonFootballGothicBold FontMS FontColorA">
                <router-link to="/login">로그인</router-link>
              </a-col>
              <a-col :span="10" class="navBarItem NexonFootballGothicBold FontMS FontColorA">
                <router-link to="/">회원가입</router-link>
              </a-col>
            </a-row>
          </a-col>
        </a-row>
      </a-col>
      <!-- <router-link></router-link> -->
    </a-row>
  </div>
</template>

<style scoped>
.nav {
  height: 110px;

  /* 상단 고정 */
  position: fixed;
  top: 0;
  left: 0;
  right: 0;

  z-index: 1;

  /* 하단 테두리 */
  border-bottom: 1px solid;

  /* 배경색 */
  background-color: white;
}

.FontMMS {
  font-size: 30px;
}

.alignCenterContainer {
  display: flex;
  align-items: center;
}

.navBarMenu {
  display: flex;
  justify-content: right;

  margin-top: 30px;
}

.navBarItem {
  display: flex;
  justify-content: center;
}

.navigation_list {
  align-items: center;
}

.margin_left {
  margin-left: 10px;
}

a {
  color: black;
}

a:hover {
  color: #00387e;
}

.nav_row {
  width: 100%;
}
</style>
