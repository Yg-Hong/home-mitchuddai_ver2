<script setup>
import { defineProps, onMounted, ref, watchEffect, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useHouseStore } from "@/stores/HouseStore.js";
import { storeToRefs } from "pinia";
import { watchDebounced, useDebounceFn } from "@vueuse/core";
import { useKakaoStore } from "@/stores/KakaoStore.js";
import { useLoading } from "@/composables/loading";

const { vLoading } = useLoading();
const kakaoStore = useKakaoStore();
const { mapCenterLatLng } = storeToRefs(kakaoStore);
const houseStore = useHouseStore();
const { houseList, house } = storeToRefs(houseStore);

const emit = defineEmits(["updateHouseMarkerList"]);

const categories = ref([
  { id: "BK9", name: "은행", order: 0, iconClass: "bank" },
  { id: "MT1", name: "마트", order: 1, iconClass: "mart" },
  { id: "PM9", name: "약국", order: 2, iconClass: "pharmacy" },
  { id: "SC4", name: "학교", order: 3, iconClass: "school" },
  { id: "CE7", name: "카페", order: 4, iconClass: "cafe" },
  { id: "SW8", name: "교통", order: 5, iconClass: "subway" },
  { id: "HP8", name: "병원", order: 6, iconClass: "hosipital" },
  { id: "CS2", name: "편의점", order: 7, iconClass: "convinientStore" },
  { id: "OL7", name: "주유소", order: 8, iconClass: "gasStation" },
  { id: "PK6", name: "주차장", order: 9, iconClass: "parkingLot" },
]);

const props = defineProps(["houseMarkerList"]);
const route = useRoute();
const router = useRouter();

// 스크립트가 로딩되면 지도를 띄워주자!
// 즉, dom이 구성된 후에 실행되어야 하므로 mounted hook(onMounted)에서 실행
// 이 과정에서 kakao라는 객체는 window 객체 즉 전역에 등록
// 따라서, mount가 될 때마다 여러 번 로딩할 필요가 없다
// window 객체에 kakao가 등록되어 있는지 확인하고 없을 때만 loading
onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    loadScript();
  }
});

watch(route.params, () => {
  console.log("KakaoMapComp.vue - route.params 변경됨", route.params);
});

const loadScript = () => {
  const key = import.meta.env.VITE_KAKAO_MAP_API_KEY;
  const script = document.createElement("script");
  /* global kakao */

  // 동적 로딩을 위한 autoload=false 추가
  script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&libraries=services&appkey=${key}`;
  // services 라이브러리 불러오기

  // kakaomap script loading 후 initMap 실행
  script.onload = () => kakao.maps.load(initMap);
  document.head.appendChild(script);
};

const map = ref(null);

const placeOverlay = ref([]);
const contentNode = ref([]);
const houseMarkers = ref([]);
const categoryMarkers = ref([]);
const currCategory = ref([]);
const ps = ref([]);
const bound = ref({});

const initMap = () => {
  const container = document.getElementById("map");
  const lat = 37.50117248464235;
  const lng = 127.0398477503283;

  const options = {
    // center: new kakao.maps.LatLng(...centerLatLng),
    center: new kakao.maps.LatLng(lat, lng),
    level: 3,
  };

  map.value = new kakao.maps.Map(container, options);

  kakao.maps.event.addListener(map.value, "tilesloaded", test);

  var mapTypeControl = new kakao.maps.MapTypeControl();
  map.value.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  // 지도 확대 축소를 제어할 수 있는 줌 컨트롤을 생성합니다
  var zoomControl = new kakao.maps.ZoomControl();
  map.value.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

  placeOverlay.value = new kakao.maps.CustomOverlay({ zIndex: 1 });
  contentNode.value = document.createElement("div"); // 커스텀 오버레이의 컨텐츠 엘리먼트
  houseMarkers.value = []; // 건물 마커를 담을 배열입니다
  categoryMarkers.value = []; // 카테고리 마커를 담을 배열입니다
  currCategory.value = ""; // 현재 선택된 카테고리를 가지고 있을 변수

  // 장소 검색 객체를 생성합니다
  ps.value = new kakao.maps.services.Places(map.value);

  // 지도에 idle 이벤트를 등록합니다
  kakao.maps.event.addListener(map.value, "idle", searchPlaces); // idle event 등록
  // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
  contentNode.value.classList.add("placeinfo_wrap");

  addEventHandle(contentNode.value, "mousedown", kakao.maps.event.preventMap);
  addEventHandle(contentNode.value, "touchstart", kakao.maps.event.preventMap);

  // 커스텀 오버레이 컨텐츠를 설정합니다
  placeOverlay.value.setContent(contentNode.value);
  // 각 카테고리에 클릭 이벤트를 등록합니다
  addCategoryClickEvent(); // 카테고리에 addEvent등록

  // 중심좌표 추적 이벤트
  kakao.maps.event.addListener(
    map.value,
    "center_changed",
    useDebounceFn(() => {
      // if (house.value) return;

      vLoading();
      bound.value = map.value.getBounds();
      // mapCenterLatLng.value = [latlng.getLat(), latlng.getLng()];
      console.log(bound.value);
    }, 500)
  );
};

const displayHouseMarkers = (positions) => {
  console.log(positions);

  // 1. 현재 표시되어있는 마커를 모두 제거
  if (houseMarkers.value.length > 0) {
    houseMarkers.value.forEach((marker) => {
      marker.setMap(null);
    });
  }

  // 3. 마커 표시하기
  positions.forEach((position) => {
    // infoWindow 생성
    let infowindow = new kakao.maps.InfoWindow({
      removable: true,
      content: `<div style="padding:5px;font-size:12px;">${position.title}</div>`,
    });

    let marker = new kakao.maps.Marker({
      map: map.value,
      position: new kakao.maps.LatLng(position.latlng[0], position.latlng[1]),
    });

    // 이벤트 등록
    kakao.maps.event.addListener(marker, "click", function () {
      console.log(position);
      map.value.setCenter(
        new kakao.maps.LatLng(position.latlng[0], position.latlng[1])
      );
      houseStore.clearDistance();

      for (let i = 0; i < categories.value.length; i++) {
        // console.log(categories.value[i]);

        calcDistanceFromPlace(
          categories.value[i].id,
          positions[0].latlng[0],
          positions[0].latlng[1]
        );
      }

      map.value.setLevel(2);
      router.push(`/house/${route.params.dongCode}/${position.aptCode}`);

      console.log(positions);
      console.log(houseStore.getDistance);
    });
    kakao.maps.event.addListener(marker, "mouseover", function () {
      infowindow.open(map.value, marker);
    });
    kakao.maps.event.addListener(marker, "mouseout", function () {
      infowindow.close();
    });

    houseMarkers.value.push(marker);
  });

  // 4. 지도 중심 좌표 이동시켜주기
  map.value.setCenter(
    new kakao.maps.LatLng(positions[0].latlng[0], positions[0].latlng[1])
  );
};

const calcDistanceFromPlace = (category, lat, lng) => {
  // 장소 검색 객체를 통해 카테고리로 장소검색을 요청합니다
  ps.value.categorySearch(
    category,
    (data) => {
      // console.log(data[0]);
      // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
      houseStore.setDistance({
        category: data[0].category_group_code,
        distance: data[0].distance,
        timeOnFoot:
          Math.round((data[0].distance / 1000 / 4) * 60) == 0
            ? 1
            : Math.round((data[0].distance / 1000 / 4) * 60),
        x: data[0].x,
        y: data[0].y,
      });
    },
    {
      location: new kakao.maps.LatLng(lat, lng),
      size: 1,
      sort: kakao.maps.services.SortBy.DISTANCE,
    }
  );
};

setTimeout(function () {
  //houseList를 markerList로 변환
  const houseMarkerList = houseList.value.map((house) => {
    return {
      aptCode: house.aptCode,
      title: house.apartmentName,
      latlng: [house.lat, house.lng],
    };
  });

  displayHouseMarkers(houseMarkerList);
}, 100);

watch(house, () => {
  if (house.value) {
    //marker 로 변환
    const houseMarker = [
      {
        aptCode: house.value.aptCode,
        title: house.value.apartmentName,
        latlng: [house.value.lat, house.value.lng],
      },
    ];

    for (let i = 0; i < categories.value.length; i++) {
      // console.log(categories.value[i]);

      calcDistanceFromPlace(
        categories.value[i].id,
        houseMarker[0].latlng[0],
        houseMarker[0].latlng[1]
      );
    }

    displayHouseMarkers(houseMarker);
    map.value.setCenter(
      new kakao.maps.LatLng(house.value.lat, house.value.lng),
      2
    );
  } else {
    // houseList를 markerList로 변환
    const houseMarkerList = houseList.value.map((house) => {
      return {
        aptCode: house.aptCode,
        title: house.apartmentName,
        latlng: [house.lat, house.lng],
      };
    });

    displayHouseMarkers(houseMarkerList);
    map.value.setCenter(
      new kakao.maps.LatLng(houseList.value[0].lat, houseList.value[0].lng),
      3
    );
  }
});

const test = () => {
  // findHouse();
};

// 중심좌표 추적 -> 마커 데이터 api call
watchDebounced(
  bound,
  async (value) => {
    console.log(value);
    const { ha, oa, pa, qa } = value;
    await displayHouseMarkers2(ha, oa, pa, qa);
  },
  { debounce: 500, maxWait: 5000 }
);

const displayHouseMarkers2 = async (ha, oa, pa, qa) => {
  await houseStore.setHouseList2(ha, oa, pa, qa);

  console.log(houseList.value);
  // houseList를 markerList로 변환
  const houseMarkerList = houseList.value.map((house) => {
    return {
      aptCode: house.aptCode,
      title: house.apartmentName,
      latlng: [house.lat, house.lng],
    };
  });

  // 1. 현재 표시되어있는 마커를 모두 제거
  if (houseMarkers.value.length > 0) {
    houseMarkers.value.forEach((marker) => {
      marker.setMap(null);
    });
  }

  const positions = houseMarkerList;

  // 3. 마커 표시하기
  positions.forEach((position) => {
    // infoWindow 생성
    let infowindow = new kakao.maps.InfoWindow({
      removable: true,
      content: `<div style="padding:5px;font-size:12px;">${position.title}</div>`,
    });

    let marker = new kakao.maps.Marker({
      map: map.value,
      position: new kakao.maps.LatLng(position.latlng[0], position.latlng[1]),
    });

    // 이벤트 등록
    kakao.maps.event.addListener(marker, "click", function () {
      console.log(position);
      map.value.setCenter(
        new kakao.maps.LatLng(position.latlng[0], position.latlng[1])
      );
      houseStore.clearDistance();

      for (let i = 0; i < categories.value.length; i++) {
        // console.log(categories.value[i]);

        calcDistanceFromPlace(
          categories.value[i].id,
          positions[0].latlng[0],
          positions[0].latlng[1]
        );
      }

      map.value.setLevel(2);
      router.push(`/house/${route.params.dongCode}/${position.aptCode}`);

      console.log(positions);
      console.log(houseStore.getDistance);
    });
    kakao.maps.event.addListener(marker, "mouseover", function () {
      infowindow.open(map.value, marker);
    });
    kakao.maps.event.addListener(marker, "mouseout", function () {
      infowindow.close();
    });

    houseMarkers.value.push(marker);
  });
};

// 카테고리별 장소 검색하기 ---------------------------
// 엘리먼트에 이벤트 핸들러를 등록하는 함수
const addEventHandle = (target, type, callback) => {
  if (target.addEventListener) {
    target.addEventListener(type, callback);
  } else {
    target.attachEvent("on" + type, callback);
  }
};

const searchPlaces = () => {
  if (!currCategory.value) {
    return;
  }

  // 커스텀 오버레이를 숨깁니다
  placeOverlay.value.setMap(null);

  // 지도에 표시되고 있는 마커를 제거합니다
  removeCategoryMarker();

  ps.value.categorySearch(currCategory.value, placesSearchCB, {
    useMapBounds: true,
  });

  console.log(ps.value.valueOf());
};

// 지도에 마커를 표출하는 함수입니다
const displayPlaces = (places) => {
  // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
  // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다

  console.log(currCategory.value);

  var order = document
    .getElementById(currCategory.value)
    .getAttribute("data-order");

  for (var i = 0; i < places.length; i++) {
    // 마커를 생성하고 지도에 표시합니다
    var marker = addMarker(
      new kakao.maps.LatLng(places[i].y, places[i].x),
      order
    );
    // 마커와 검색결과 항목을 클릭 했을 때
    // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
    (function (marker, place) {
      kakao.maps.event.addListener(marker, "click", function () {
        displayplaceinfo(place);
      });
    })(marker, places[i]);
  }
};

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
const addMarker = (position, order) => {
  var imageSrc = `../../src/assets/img/category${order}.png`,
    imageSize = new kakao.maps.Size(40, 40), // 마커 이미지의 크기
    imgOptions = {
      // spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
      // spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
      offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
    },
    markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
    marker = new kakao.maps.Marker({
      position, // 마커의 위치
      image: markerImage,
    });

  marker.setMap(map.value); // 지도 위에 마커를 표출합니다
  categoryMarkers.value.push(marker); // 배열에 생성된 마커를 추가합니다

  return marker;
};

const removeCategoryMarker = () => {
  for (var i = 0; i < categoryMarkers.value.length; i++) {
    categoryMarkers.value[i].setMap(null);
  }
  categoryMarkers.value = [];
};

// 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
const displayplaceinfo = (place) => {
  var content =
    '<div class="placeinfo">' +
    '   <a class="title" href="' +
    place.place_url +
    '" target="_blank" title="' +
    place.place_name +
    '">' +
    place.place_name +
    "</a><br />";

  if (place.road_address_name) {
    content +=
      '    <span title="' +
      place.road_address_name +
      '">' +
      place.road_address_name +
      "</span>" +
      '  <span class="jibun" title="' +
      place.address_name +
      '">(지번 : ' +
      place.address_name +
      ")</span><br />";
  } else {
    content +=
      '    <span title="' +
      place.address_name +
      '">' +
      place.address_name +
      "</span><br />";
  }

  content +=
    '    <span class="tel">' +
    place.phone +
    "</span>" +
    "</div>" +
    '<div class="after"></div>';

  contentNode.value.innerHTML = content;
  placeOverlay.value.setPosition(new kakao.maps.LatLng(place.y, place.x));
  placeOverlay.value.setMap(map.value);
};

const placesSearchCB = (data, status) => {
  if (status === kakao.maps.services.Status.OK) {
    // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
    displayPlaces(data);
  } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
    // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
  } else if (status === kakao.maps.services.Status.ERROR) {
    // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
  }
};

// 각 카테고리에 클릭 이벤트를 등록합니다
const addCategoryClickEvent = () => {
  var category = document.getElementById("category"),
    children = category.children;
  for (let i = 0; i < children.length; i++) {
    children[i].onclick = () => {
      console.log(children.className);
      onClickCategory(children[i].id, children[i].className);
    };
  }
};

// 카테고리를 클릭했을 때 호출되는 함수입니다
const onClickCategory = (id, className) => {
  // var id = this.id,
  //   className = this.className;
  placeOverlay.value.setMap(null);
  // console.log(className);
  if (className === "on") {
    currCategory.value = "";
    changeCategoryClass();
    removeCategoryMarker();
  } else {
    currCategory.value = id;
    console.log(currCategory.value);
    if (id == "BK9") {
      changeCategoryClass(document.getElementById("category").children.BK9);
    } else if (id == "CE7") {
      changeCategoryClass(document.getElementById("category").children.CE7);
    } else if (id == "CS2") {
      changeCategoryClass(document.getElementById("category").children.CS2);
    } else if (id == "MT1") {
      changeCategoryClass(document.getElementById("category").children.MT1);
    } else if (id == "OL7") {
      changeCategoryClass(document.getElementById("category").children.OL7);
    } else if (id == "PM9") {
      changeCategoryClass(document.getElementById("category").children.PM9);
    } else if (id == "SC4") {
      changeCategoryClass(document.getElementById("category").children.SC4);
    } else if (id == "SW8") {
      changeCategoryClass(document.getElementById("category").children.SW8);
    } else if (id == "HP8") {
      changeCategoryClass(document.getElementById("category").children.HP8);
    }

    console.log(currCategory.value);
    searchPlaces();
  }
};

// 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
const changeCategoryClass = (el) => {
  var category = document.getElementById("category"),
    children = category.children,
    i;

  for (i = 0; i < children.length; i++) {
    children[i].className = "";
  }

  if (el) {
    el.className = "on";
  }
};

watchEffect(props.houseMarkerList, (newVal) => {
  console.log("KakaoMapComp.vue - HouseList 변경됨", newVal);

  // 상위 컴포넌트에 변경된 값 emit으로 넘기기
  emit("updateHouseMarkerList", newVal);
});
</script>

<template>
  <div>
    <div id="map"></div>
    <div id="category">
      <div
        v-for="category in categories"
        :key="category.id"
        :id="category.id"
        :data-order="category.order"
        @click="onClickCategory(category.id, category.className)"
      >
        <span :class="['category_bg', category.iconClass]"></span>
        {{ category.name }}
      </div>
    </div>
  </div>
</template>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 850px;
}

.overlay {
  position: relative;
  bottom: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.overlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.overlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.overlay .title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.overlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.box1 {
  height: 32px;
  width: 60px;
  border: 1px solid #1876d6;
  text-align: center;
  color: white;
  font-size: 16px;
  font-weight: bold;
  position: relative;
  border-radius: 5px 5px 0 0;
  background-color: #1876d6;
  line-height: 35px;
}

.box2 {
  height: 20px;
  width: 60px;
  border: 1px solid #1876d6;
  text-align: center;
  color: #007bff;
  font-size: 12px;
  font-weight: bold;
  position: relative;
  border-radius: 0 0 5px 5px;
  background-color: white;
}
/* speech bubble 9 */

.sb9:before {
  content: "";
  width: 0px;
  height: 0px;
  position: absolute;
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  border-top: 5px solid #1876d6;
  border-bottom: 5px solid transparent;
  right: 40%;
  bottom: -10px;
}

.sb9:after {
  content: "";
  width: 0px;
  height: 0px;
  position: absolute;
  border-left: 4px solid transparent;
  border-right: 4px solid transparent;
  border-top: 4px solid white;
  border-bottom: 3.5px solid transparent;
  right: 41%;
  bottom: -7px;
}

#category {
  position: absolute;
  top: 250px;
  right: 5px;
  border-radius: 3px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 1;
  font-size: 14px;
}

#category div.on {
  background: rgb(237, 237, 255);
}
#category div:hover {
  background: #eee;
  cursor: pointer;
}
#category div {
  padding: 7px 6px;
  border-bottom: 1px solid #d2d2d2;
}

.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #ff8f27;
  background: #ff8f27
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
