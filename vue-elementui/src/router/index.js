import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import App from '../App'
import PageOne from '../views/PageOne'
import PageTwo from '../views/PageTwo'
import PageThree from '../views/PageThree'
import PageFour from '../views/PageFour'


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: '导航一',
        component: App,
        children: [
            {
                path: '/PageOne',
                name: '页面一',
                component: PageOne
            }, {
                path: '/PageTwo',
                name: '页面二',
                component: PageTwo,
            },
        ],
    },
    {
        path: '/navigation',
        name: '导航二',
        component: App,
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
