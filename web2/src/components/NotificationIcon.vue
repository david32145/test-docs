<template>
    <div class="icon-notifications-container">
        <img v-if="color === 'white'" class="notification-light" src="@/assets/notification-light.svg" alt="Icone de Notificações">
        <img v-else class="notification-dark"  src="@/assets/notification-dark.svg" alt="Icone de Notificações">
        <span class="notifications-number">10</span><br>
    </div>
</template>

<script>
export default {
    name: 'NotificationIcon',
    props: {
        color: String
    },
}
</script>

<style>

.icon-notifications-container {
    width: 31px;
    height: 31px;
    position: relative;
    cursor: pointer;

    display: flex;
    align-items: center;
    justify-content: center;
}

.notification-light {
    width: 22px;
    height: 22px;
}

.notification-dark {
    width: 25px;
    height: 25px;
}

.icon-notifications-container .notifications-number {
    width: 18px;
    height: 18px;
    background-color: var(--bg-blue);
    color: #FFFFFF;
    border-radius: 8px;

    font-weight: bold;
    font-size: 1.0rem;
    line-height: 1.2rem;
    text-align: center;
    
    position: absolute;
    display: flex;
    align-items: center;
    justify-content: center;

    top: 0;
    left: 0;
    transform: translate(90%, -40%);
}
</style>