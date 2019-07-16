import Vue from "vue";
import VueRouter from 'vue-router';

Vue.use(VueRouter);

import MainPage from './page/main';

const routes = [
    { path: '/', component: MainPage },
];

const router = new VueRouter({
    routes
});

export default router;
