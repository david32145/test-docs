<template>
<div class="home">
  <Login/>
</div>
</template>

<script>
import Login from '@/components/Login.vue'

export default {
  name: 'Home',
  components: {
    Login
  }
}
</script>

<style>
.home {
  height:100%;
}
</style>