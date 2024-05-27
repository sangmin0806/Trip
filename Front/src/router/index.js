import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
// import BoardList from '@/components/board/BoardList.vue';
import BoardView from '@/views/BoardView.vue';
import TripView from '@/views/TripView.vue';
import TripRecommend from '@/components/trip/TripRecommend.vue';
import TripList from '@/components/trip/TripList.vue';
import { useSidebarStore } from '@/stores/sidebar.js';
import MyPage from '@/components/myPage/MyPage.vue';
import UserDetail from '@/components/myPage/UserDetail.vue';
import UserEdit from '@/components/myPage/UserEdit.vue';
import { useAuthStore } from '@/stores/auth.js';
import MyChatt from '@/components/myPage/MyChatt.vue';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView,
        },
        {
            path: '/mychatt',
            name: 'mychatt',
            component: MyChatt,
        },
        {
            path: '/mypage',
            name: 'myPage',
            meta: { requiresAuth: true },
            component: MyPage,
            children: [
                {
                    path: 'detail',
                    name: 'user-detail',
                    component: UserDetail,
                },
                {
                    path: 'edit',
                    name: 'user-edit',
                    component: UserEdit,
                },
            ],
        },
        {
            path: '/board',
            name: 'board',
            component: BoardView,
            redirect: { name: 'board-list' },
            children: [
                {
                    path: 'list',
                    name: 'board-list',
                    component: () => import('@/components/board/BoardList.vue'),
                },
                {
                    path: 'view/:articleno',
                    name: 'article-view',
                    meta: { requiresAuth: true },
                    component: () => import('@/components/board/BoardDetail.vue'),
                },
                // {
                //   path: "view/:articleno",
                //   name: "board-view",
                //   component: () => import("@/components/board/BoardDetail.vue"),
                // },
                {
                    path: 'write',
                    name: 'board-write',
                    meta: { requiresAuth: true },
                    component: () => import('@/components/board/BoardWrite.vue'),
                },
                {
                    path: 'modify/:articleno',
                    name: 'board-modify',
                    component: () => import('@/components/board/BoardModify.vue'),
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
                    beforeEnter: (to, from, next) => {
                        useSidebarStore().clearTrips();
                        useSidebarStore().clearInput();
                        useSidebarStore().tripListActive = false;
                        next();
                    },
                },
            ],
        },
    ],
});

router.beforeEach((to, from, next) => {
    const authStore = useAuthStore();
    if (to.meta.requiresAuth && !authStore.isLoggedIn) {
        alert('로그인 해야 열람할 수 있는 페이지 입니다.');
        next('/');
    } else {
        next();
    }
});

export default router;
