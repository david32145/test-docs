import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: function () {
      return import('../views/login.view.vue')
    }
  },
  {
    path: '/signup',
    name: 'Signup',
    component: function () {
      return import('../views/signup.view.vue')
    }
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: function () {
      return import('../views/dashboard.view.vue')
    }
  },
  {
    path: '/project/new',
    name: 'NewProject',
    component: function () {
      return import('../views/new-project.view.vue')
    }
  },
  {
    path: '/project/:project_id',
    name: 'Project',
    component: function () {
      return import('../views/view-project.view.vue')
    }
  },
  {
    path: '/project/:project_id/testrequest/new',
    name: 'NewTestRequest',
    component: function () {
      return import('../views/new-request-test.component.vue')
    }
  },
  {
    path: '/project/:project_id/testrequest/:test_request_id/testsuite/new',
    name: 'NewTestSuite',
    component: function() {
      return import('../views/testsuite.view.vue')
    }
  },
  {
    path: '/project/:project_id/testrequest/:test_request_id/testsuite/:test_suite_id/testcase/new',
    name: 'NewTestSuite',
    component: function() {
      return import('../views/testcase.view.vue')
    }
  },
  {
    path: '*',
    redirect: '/login'
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
