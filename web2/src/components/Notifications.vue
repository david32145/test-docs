<template>
    <section class="notifications">
        <div class="notifications-header">
            <h1>Notificações</h1>
        </div>

        <ul class="list-notification">
            <li v-for="(notification, index) in notifications" :key="index">
                <img v-if="notification.type === 'info'" src="@/assets/info.svg" :alt="notification.type">
                <img v-else-if="notification.type === 'approved'" src="@/assets/approved.svg" :alt="notification.type">
                <img v-else src="@/assets/disapprove.svg" :alt="notification.type">
                
                <div class="content-notification">
                    <p>{{notification.projectName}}: {{notification.description}}</p>
                    <span class="version-project">v1.0.0</span>
                </div>
            </li>
        </ul>
    </section>
</template>

<script>
export default {
    name:"Notifications",
    data: function() {
        return {
            notifications: [
                {
                    type: 'info',
                    projectName: 'Uber',
                    description: 'Nova Solicitação de teste',
                    projectVersion: 'v1.0.0'
                },
                {
                    type: 'approved',
                    projectName: 'Uber',
                    description: 'Release Aprovada',
                    projectVersion: 'v1.0.0'
                },
                {
                    type: 'disapprove',
                    projectName: 'Uber',
                    description: 'Release Reprovada',
                    projectVersion: 'v1.0.0'
                },
            ]
        }
    },
};
</script>

<style>
.notifications {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.notifications .notifications-header {
    width: 100%;
    margin-bottom: 32px;

    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.notifications-header h1 {
    font-weight: 500;
    font-size: 2.2rem;
    color: var(--dark-text);
}

.notifications .list-notification {
    margin-bottom: 15px;
    display: flex;
    cursor: pointer;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.notifications .list-notification li {
    width: 340px;
    height: 66px;
    margin-bottom: 15px;
    background-color: #FFFFFF;

    display: flex;
    align-items: center;
    justify-content: center;
    transition: 0.2s;
}

.notifications .list-notification li:hover {
    transform: scale(1.05,1.05);
}

.list-notification li img {
    width: 40px;
    height: 40px;
    margin-right: 14px;
}

.list-notification li .content-notification {
    width: 246px;
    display: flex;
    flex-direction: column;
}

.list-notification li .content-notification p {
    font-size: 1.6rem;
    line-height: 1.9rem;
    text-align: justify;
    color: var(--dark-text);
}

.list-notification li .content-notification .version-project{
    font-weight: bold;
    font-size: 1.4rem;
    line-height: 1.6rem;
    color: var(--bg-blue);

    display: flex;
    align-items: center;
    justify-content: flex-end;
    text-align: justify;
    text-decoration-line: underline;
}

@media (max-width: 1200px) {
    .notifications {
        display: none;
    }
}
</style>