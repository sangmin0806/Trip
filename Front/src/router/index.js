import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
// import BoardList from '@/components/board/BoardList.vue';
import BoardView from '@/views/BoardView.vue';
import TripView from '@/views/TripView.vue';
import TripRecommend from '@/components/trip/TripRecommend.vue';
import TripList from '@/components/trip/TripList.vue';
import UserFrom from '@/components/user/UserForm.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/mypage',
      name: 'my-page',
      component: UserFrom,
    },
    {
      path: "/board",
      name: "board",
      component: BoardView,
      redirect: { name: "board-list" },
      children: [
        {
          path: "list",
          name: "board-list",
          component: () => import("@/components/board/BoardList.vue"),
        },
        {
          path: "view/:articleno",
          name: "article-view",
          component: () => import("@/components/board/BoardDetail.vue"),
        },
        // {
        //   path: "view/:articleno",
        //   name: "board-view",
        //   component: () => import("@/components/board/BoardDetail.vue"),
        // },
        {
          path: "write",
          name: "board-write",
          component: () => import("@/components/board/BoardWrite.vue"),
        },
        {
          path: "modify/:articleno",
          name: "board-modify",
          component: () => import("@/components/board/BoardModify.vue"),
        },
      ],
    },
    {
      path: '/trip',
      name: 'trip',
      component: TripView,
      redirect: { name: 'trip-list' },
      children: [
        {
          path: 'recommend',
          name: 'trip-recommend',
          component: TripRecommend,
        },
        {
          path: 'list',
          name: 'trip-list',
          component: TripList,
        },
      ],
    },
  ],
});

export default router;
