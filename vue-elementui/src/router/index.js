import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Index from '../views/index'
import UserManage from '../views/UserManage'
import addUser from '../views/addUser'
import PageThree from '../views/PageThree'
import PageFour from '../views/PageFour'


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: '用户管理',
        show : true,//用于v-if="",设置是否被显示
        component: Index,
        redirect:"/UserManage",
        children: [
            {
                path: '/UserManage',
                name: '用户信息',
                component: UserManage
            }, {
                path: '/addUser',
                name: '添加用户',
                component: addUser,
            },
        ],
    },
    {
        path: '/navigation',
        name: '导航二',
        component: Index,
        children: [
            {
                path: '/PageThree',
                name: '页面三',
                component: PageThree
            }, {
                path: '/PageFour',
                name: '页面四',
                component: PageFour
            },
        ]
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
