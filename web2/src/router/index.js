import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/views/Main.vue'
import Dashboard from '@/views/Dashboard.vue'

import Project from '@/views/Project.vue'
import CreateProject from '@/views/CreateProject.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/main',
    name: 'Main',
    component: Main,
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: Dashboard
      },
      {
        path: 'project',
        name: 'Project',
        component: Project,
      },
      {
        path: 'createProject',
        name: 'CreateProject',
        component: CreateProject,
      }
      ,
      {
        path: '/*',
        redirect: 'dashboard'
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
