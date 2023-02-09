import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'
import Home from '../views/Home'
import StuHome from '../views/StuHome'
import ClassSetting from '../views/ClassSetting'
import CoursePlan from '../components/teacherView/CoursePlan'
import Homework from '../components/teacherView/Homework'
import Paper from '../components/teacherView/paper/Paper'
import WorkSch from '../components/teacherView/WorkSch'
import MsgDetil from '../components/studentView/msgdetail/MsgDetil'
import MsgList from '../components/studentView/msgdetail/MsgList'
import SubDetil from '../components/studentView/msgdetail/SubDetil'
import SubWork from '../components/studentView/paper/SubWork'
import Chatti from '../components/chatView/Chatti'
import PerCenter from '../components/PerCenter'
import DetailPage from '../components/teacherView/DetailPage'
import PaperList from '../components/teacherView/paper/PaperList'
import PaperCheck from '../components/teacherView/paper/PaperCheck'
import CheckView from '../components/teacherView/paper/CheckView'
import SubView from '../components/studentView/paper/SubView'
import PushPaper from '../components/studentView/paper/PushPaper'
import InitView from '../components/initclass/InitView'
import ChangePsd from '../components/ChangePsd'
import ForgotPsd from '../views/ForgotPsd'

Vue.use(VueRouter)


const routes = [{
        path: '',
        redirect: '/login'
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/forgotpsd',
        name: 'ForgotPsd',
        component: ForgotPsd
    },
    {
        path: '/home',
        name: 'Home',
        component: Home,
        children: [{
                path: '',
                redirect: '/home/courseplan'
            },
            {
                path: '/home/courseplan',
                name: 'CoursePlan',
                component: CoursePlan,
            },
            {
                path: '/home/homework',
                name: 'Homework',
                component: Homework
            },
            {
                path: '/home/homework/detail',
                name: 'DetailPage',
                component: DetailPage,
            },
            {
                path: '/home/paper',
                name: 'Paper',
                component: Paper,
                children: [{
                        path: '/home/paper/list',
                        name: 'PaperList',
                        component: PaperList,
                    },
                    {
                        path: '/home/paper/check',
                        name: 'PaperCheck',
                        component: PaperCheck,
                    },
                    {
                        path: '/home/paper/view',
                        name: 'CheckView',
                        component: CheckView,
                    }
                ]
            },
            {
                path: '/home/worksch',
                name: 'WorkSch',
                component: WorkSch,
            },
            {
                path: '/home/chatti',
                name: 'Chatti',
                component: Chatti,
            }
        ]
    },
    {
        path: '/stuhome',
        name: 'StuHome',
        component: StuHome,
        children: [{
                path: '',
                redirect: '/stuhome/msgdetil'
            },
            {
                path: '/stuhome/msgdetil',
                name: 'MsgDetil',
                component: MsgDetil,
                children: [{
                        path: '/stuhome/msgdetil',
                        redirect: '/stuhome/msgdetil/list'
                    },
                    {
                        path: '/stuhome/msgdetil/list',
                        name: 'MsgList',
                        component: MsgList,
                    },
                    {
                        path: '/stuhome/msgdetil/sub',
                        name: 'SubDetil',
                        component: SubDetil,
                    }
                ]
            },
            {
                path: '/stuhome/subview',
                name: 'SubView',
                component: SubView,
                children: [{
                        path: '/stuhome/subview/work',
                        name: 'SubWork',
                        component: SubWork,
                    },
                    {
                        path: '/stuhome/subview/push',
                        name: 'PushPaper',
                        component: PushPaper,
                    }
                ]
            },
            {
                path: '/stuhome/chatti',
                name: 'Chatti',
                component: Chatti,
            }
        ]
    },
    {
        path: '/classsetting',
        name: 'ClassSetting',
        component: ClassSetting
    },
    {
        path: '/percenter',
        name: 'PerCenter',
        component: PerCenter
    },
    {
        path: '/changepsd',
        name: 'ChangePsd',
        component: ChangePsd
    },
    {
        path: '/initclass',
        name: 'InitView',
        component: InitView,
    }
]

const router = new VueRouter({
    mode: 'hash',
    routes
})

router.beforeEach((to, from, next) => {
    if (to.path === '/login') return next()
    if (to.path === '/forgotpsd') return next()
        // if(to.path === '/home/profilehome') return next()
    const usName = window.sessionStorage.getItem('USERNAME')
    if (!usName) return next('/login')
    next()
})

export default router