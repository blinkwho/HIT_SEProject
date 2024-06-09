import VueRouter from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'login',
        component: () => import("@/components/Login.vue")
    },
    {
        path:'/index',
        name:'Index',
        component:()=>import("@/components/Index.vue")
    },
    {
        path:'/register',
        name:'register',
        component:()=>import("@/components/register.vue")
    }
]

const router = new VueRouter({
    mode:'history',
    routes
})

export default router;