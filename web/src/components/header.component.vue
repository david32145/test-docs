<template>
  <header class="main-header">
    <div class="header-container">
      <div class="logo-and-title-container">
        <img src="@/assets/logo.svg" alt="Logo" />
        <router-link :to="{ name: 'Dashboard' }">
          <span>Dashboard</span>
        </router-link>
      </div>
      <div class="notifications-and-user-container">
        <NotificationIcon color="white" />
        <img
          src="@/assets/user_avatar.svg"
          class="icon-user"
          alt="Icone de User"
          v-on:click="logout"
        />
      </div>
    </div>
  </header>
</template>

<script>
import NotificationIcon from "@/components/NotificationIcon.vue";

export default {
  name: "header-component",
  components: {
    NotificationIcon,
  },
  methods: {
    logout() {
      localStorage.removeItem('@testdocs/user')
      this.$router.push({name: 'Login'})
    }
  }
};
</script>

<style>
.main-header {
  width: 100%;
  min-height: 64px;
  background-color: var(--primary-color);

  display: flex;
  align-items: center;
  justify-content: center;
}

.main-header .header-container {
  width: 100%;
  padding: 0 25px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.main-header .header-container .logo-and-title-container {
  width: 200px;
  display: flex;
  align-items: center;
}

.main-header .logo-and-title-container img {
  width: 40px;
  height: 40px;
  margin-right: 20px;
}

.main-header .logo-and-title-container span {
  color: #ffffff;
  font-weight: 500;
  font-size: 2.2rem;
  line-height: 2.6rem;
  text-align: justify;
  letter-spacing: 0.02rem;
  cursor: pointer;
}

.main-header .header-container .notifications-and-user-container {
  display: flex;
  align-items: center;
}

.main-header .notifications-and-user-container .icon-user {
  width: 33px;
  height: 33px;
  margin-left: 35px;
  cursor: pointer;
}

@media (max-width: 680px) {
  .main-header {
    min-width: 300px;
    height: 10vw;
    min-height: 60px;
  }

  .main-header .header-container .logo-and-title-container {
    width: 180px;
    min-width: 160px;
  }

  .main-header .logo-and-title-container img {
    width: 44px;
    height: 44px;
  }

  .main-header .logo-and-title-container span {
    font-size: 2rem;
  }

  .main-header .header-container .notifications-and-user-container {
    width: 80px;
    min-width: 88px;
  }
}
</style>