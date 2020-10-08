import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/views/Main.vue'
import Dashboard from '@/views/Dashboard.vue'

import Project from '@/views/Project.vue'
import ProjectCreate from '@/views/ProjectCreate.vue'
import ProjectView from '@/views/ProjectView.vue'
import ProjectMembers from '@/views/ProjectMembers.vue'
import ProjectReleases from '@/views/ProjectReleases.vue'

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
        path: 'project/:projectId(\\d+)',
        name: 'Project',
        component: Project,
        children: [
          {
            path: 'view',
            name: 'ProjectView',
            component: ProjectView
          },
          {
            path: 'members',
            name: 'ProjectMembers',
            component: ProjectMembers
          },
          {
            path: 'releases',
            name: 'ProjectReleases',
            component: ProjectReleases
          },
          {
            path: '*',
            redirect: 'view'
          },
          {
            path: '',
            redirect: 'view'
          },
        ]
      },
      {
        path: 'projectCreate',
        name: 'ProjectCreate',
        component: ProjectCreate,
      },
      {
        path: '',
        redirect: 'dashboard'
      },
      {
        path: '*',
        redirect: 'dashboard'
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
