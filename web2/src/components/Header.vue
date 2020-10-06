<template>
    <header>
        <div class="header-container">
            <div class="logo-and-title-container">
                <img src="@/assets/logo.svg" alt="Logo">
                <router-link to="dashboard">
                    <span>Dashboard</span>
                </router-link>
                
            </div>
            <div class="notifications-and-user-container">
                <NotificationIcon color='white'/>
                <img src="@/assets/user_avatar.svg" class="icon-user" alt="Icone de User">
            </div>
        </div>
    </header>
</template>

<script>
import NotificationIcon from "@/components/NotificationIcon.vue"

export default {
    name:"Header",
    components: {
        NotificationIcon
    }
}
</script>

<style>
header {
    width: 100%;
    min-height: 64px;
    background-color: var(--primary-color);

    display: flex;
    align-items: center;
    justify-content: center;
}

header .header-container{
    width: 100%;
    padding: 0 25px;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.header-container .logo-and-title-container {
    width: 200px;
    display: flex;
    align-items: center;
}

.logo-and-title-container img {
    width: 40px;
    height: 40px;
    margin-right: 35px;
}

.logo-and-title-container span {
    color: #FFFFFF;
    font-weight: 500;
    font-size: 2.2rem;
    line-height: 2.6rem;
    text-align: justify;
    letter-spacing: 0.02rem;
    cursor: pointer;
}

.header-container .notifications-and-user-container {
    display: flex;
    align-items: center;
}

.notifications-and-user-container .icon-user {
    width: 33px;
    height: 33px;
    margin-left: 35px;
    cursor: pointer;
}

@media (max-width: 680px) {
    header {
        min-width: 300px;
        height: 10vw;
        min-height: 60px;
    }

    .header-container .logo-and-title-container {
        width: 180px;
        min-width: 160px;
    }

    .logo-and-title-container img {
        width: 44px;
        height: 44px;
    }
    
    .logo-and-title-container span {
        font-size: 2.0rem;
    }

    .header-container .notifications-and-user-container {
        width: 80px;
        min-width: 88px;
    }
}
</style>