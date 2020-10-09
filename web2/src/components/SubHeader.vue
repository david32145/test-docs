<template>
    <section id="sub-header">
        <div class="project-info">
            <img :src="project.avatar_uri" alt="Project Logo">
            <span>{{project.name}}</span>
        </div>
        <div class="tab">
            <div v-for="(submenu, index) in activatedSubmenus" 
                :key="index" 
                @click="switchActivate(index)"
                :class="{'tab-active': submenu.active}" 
                class="tab-item"
            >
                <div class="tab-item-label">
                    <router-link :to="`${submenu.path}`">
                        <label>
                            {{submenu.title}}
                        </label>
                    </router-link>
                    <img 
                        v-if="submenu.title==='Membros'" 
                        src="@/assets/users_icon.svg" 
                        alt="Users icon"
                    >
                </div>
                <div class="tab-active-indicator"></div>
            </div>
        </div>
    </section>
</template>

<script>
export default {
    name:'SubHeader',
    data: function() {
        return {
            projectId: 0,
            project: {},
            submenus: [
                {
                    title: 'Informações',
                    path: 'view',
                    active: false
                },
                {
                    title: 'Membros',
                    path: 'members',
                    active: false
                },
                {
                    title: 'Releases',
                    path: 'releases',
                    active: false
                },
            ]
        }
    },
    created: function() {
        this.getInfoProject()
    },
    methods: {
        getInfoProject() {
            //GET - no projeto, para obter suas informações atualizadas
            this.project.avatar_uri = 'https://d3i4yxtzktqr9n.cloudfront.net/uber-sites/f452c7aefd72a6f52b36705c8015464e.jpg'
            this.project.name = 'Uber'
        },
        switchActivate(index) {
            const currentPath = this.$router.currentRoute.path
            this.submenus.forEach(submenu => {
                submenu.active = currentPath.includes(submenu.path)
            })
            this.getInfoProject()
        }
    },
    computed: {
        activatedSubmenus() {
            const currentPath = this.$router.currentRoute.path
            this.submenus.forEach(submenu => {
                submenu.active = currentPath.includes(submenu.path)
            })
            this.getInfoProject()
            return this.submenus
        }
    },
}
</script>

<style scoped>
#sub-header {
    min-height: 60px;
    background-color: #FFFFFF;
    display: grid;
    grid-template-columns: 1fr 600px 1fr;
}

.project-info {
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 0 28px;
}

.project-info span {
    font-family: Roboto;
    font-weight: 500;
    margin-left: 35px;
    font-size: 2.0rem;
    text-align: center;
    letter-spacing: 0.02em;
    color: var(--dark-text);
}

.project-info img {
    width: 40px;
    border-radius:25px;
}

.tab {
    display: flex;
    flex-direction: row;
    align-items: center;
}

.tab-item {
    max-width: 120px;
    height: 100%;
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.tab-item + .tab-item {
    margin-left: 50px;
    height: 100%;
}

.tab-item .tab-item-label{
    display: flex;
    flex-direction: row;
    align-items: center;
}

.tab-item .tab-item-label label {
    font-family: Roboto;
    font-weight: 300;
    font-size: 1.6rem;
    letter-spacing: 0.02em;
    cursor: pointer;
    color: rgba(49, 54, 56, 0.8);
    margin-right: 5px;
}

.tab-item .tab-active-indicator {
    width: 100%;
    border-bottom: 2px solid var(--dark-text);
    position: absolute;
    visibility: hidden;
    left: 0;
    bottom: 0;
}

.tab-item.tab-active .tab-item-label label {
    font-weight: 400;
    color: var(--dark-text);
}

.tab-item.tab-active .tab-active-indicator {
    visibility: visible;
}
</style>