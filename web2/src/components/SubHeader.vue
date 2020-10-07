<template>
    <section id="sub-header">
        <div class="project-info">
            <img :src="project.avatar_uri" alt="Project Logo">
            <span>{{project.name}}</span>
        </div>
        <div class="tab">
            <div v-for="(submenu, index) in activatedSubmenus" 
                :key="index" 
                v-on:click="switchActivate(index)"
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
    props: {
        //id:int
    },
    data: function() {
        return {
            project: {
                avatar_uri: '',
                name: '',
            },
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
    methods: {
        switchActivate(index) {
            /*
            console.log(this.$router.history.current)
            console.log(this.$router.history.pending)
            */
            this.submenus.forEach(submenu => {
                submenu.active = this.$router.history.current.path.includes(submenu.path)
            })
        }
    },
    computed: {
        activatedSubmenus() {
            this.submenus.forEach(submenu => {
                submenu.active = this.$router.history.current.path.includes(submenu.path)
            })
            return this.submenus
        }
    },
}
</script>

<style>
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
    width: 35px;
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