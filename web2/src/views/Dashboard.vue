<template>
    <div class="page">
        <main>
            <div class="projects-header">
                <h1>Projetos</h1>
                <div class="container">
                    <div class="project-search-container">
                        <img src="@/assets/magnifying-glass.svg" alt="Lupa">
                        <input type="text" v-model="project_search" name="project-search" id="project-search" placeholder="buscar">
                    </div>
                    <router-link to="createProject">
                        <button>+</button>
                    </router-link>
                </div>
            </div>
    
            <ul class="list-project">
                <li v-for="project in filteredProjects" :key="project.id">
                    <img v-if="project.avatar_uri === ''" src="@/assets/avatar.svg" alt="Avatar" class="avatar-project">
                    <img v-else :src="project.avatar_uri" alt="Avatar" class="avatar-project">
                    
                    <p>{{project.name}}</p>
                    
                    <NotificationIcon color='black'/>

                    <p class="members-number">{{project.membersNumber}} integrantes</p>
                    <p class="project-owner">@{{project.nameOwner}}</p>
                    
                    <router-link to='project'>
                        <button>
                            <img src="@/assets/arrow-right.svg" alt="->">
                        </button>
                    </router-link>
                </li>
            </ul>
        </main>
    
        <div class="border-line"></div>

        <Notifications/>
    </div>
</template>

<script>

import NotificationIcon from "@/components/NotificationIcon.vue"
import Notifications from "@/components/Notifications.vue"

export default {
    name:"Dashboard",
    components: {
        NotificationIcon,
        Notifications
    },
    props: {
        //userId:  {type: Number,required: true}
    },
    data: function() {
        return {
            projectOwner: '',
            membersNumber: 0,
            project_search: '',
            projects: [
                {
                    id: 0,
                    avatar_uri: '',
                    membersNumber: 13,
                    name: 'Amazon',
                    nameOwner: 'rafael.vieira'
                },
                {
                    id: 1,
                    avatar_uri: '@/assets/avatar.svg',
                    membersNumber: 13,
                    name: 'Uber',
                    nameOwner: 'rafael.vieira'
                },
                {
                    id: 2,
                    avatar_uri: '@/assets/avatar.svg',
                    membersNumber: 13,
                    name: 'Uber',
                    nameOwner: 'rafael.vieira'
                },
                {
                    id: 3,
                    avatar_uri: '',
                    membersNumber: 13,
                    name: 'Uber',
                    nameOwner: 'rafael.vieira'
                },
            ],
        }
    },
    computed: {
        filteredProjects: function() {
            if(this.project_search === '')
                return this.projects
            return this.projects.filter(project => {
                return project.name.toLowerCase().includes(this.project_search.toLowerCase())
            });
        }
    },
}
</script>

<style>
.page {
    margin: 0 auto;
    padding-top: 40px;

    display: flex;
    align-items: flex-start;
    justify-content: center;
}

.page .border-line {
    margin: 77px 72px 0;
    width: 0px;
    height: 70vh;
    border: 1px solid var(--gray-light);
}

.page main {
    max-width: 800px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

main .projects-header {
    width: 100%;
    margin-bottom: 32px;

    display: flex;
    align-items: center;
    justify-content: space-between;
}

.projects-header h1 {
    font-weight: 500;
    font-size: 2.2rem;
    color: var(--dark-text);
    margin-left: 22px;
}

.projects-header .container {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.container .project-search-container {
    width: 319px;
    height: 45px;
    margin-right: 20px;
    padding: 8px;
    background: rgba(255, 255, 255, 0.8);
    border-radius: 22.5px;

    display: flex;
    align-items: center;
}

.container .project-search-container:focus-within {
    border: 1px solid var(--bg-blue);
}

.container .project-search-container img {
    width: 30px;
    height: 30px;
    margin-left: 12px;
    margin-right: 3px;
}

.container .project-search-container input {
    background: transparent;
    outline: none;
    border: none;
    padding: 0 10px;
    font-weight: 300;
    text-align: left;
    font-size: 1.8rem;
    line-height: 2.1rem;
    letter-spacing: 0.02em;
    color: var(--gray-light);
    transition: 0.3s;
}

.container .project-search-container input:focus {
    color: var(--dark-text);
}

.container button {
    cursor: pointer;
    border: none;
    font-size: 2.5rem;
    width: 45px;
    height: 45px;
    background-color: var(--primary-color);
    color: #FFFFFF;
    border-radius: 25px;
    transition: 0.2s;
    margin-right: 22px;
}

.container button:hover {
    transform: scale(1.1, 1.1);
}

main .list-project {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.list-project li {
    width: 792px;
    min-width: 500px;
    height: 70px;
    cursor: pointer;
    border-radius: 3px 3px 0px 0px;
    display: flex;
    align-items: center;
    justify-content: space-around;
    transition: 0.2s;
    background: #FFFFFF;
}

.list-project li:hover {
    transform: scale(1.03,1.03);
}

.list-project li:nth-child(odd) {
    background: #FFFFFF;
}

.list-project li:nth-child(even) {
    background: #FAFAFB;
}

.list-project li .avatar-project {
    width: 40px;
    border-radius: 20px;
}

.list-project li p {
    min-width: 138px;

    font-weight: 300;
    font-size: 1.8rem;
    line-height: 2.1rem;
    text-align: justify;
    letter-spacing: 0.02em;
    color: var(--dark-text);
}

.list-project li .members-number {
    font-weight: 300;
    font-size: 1.8rem;
    line-height: 2.1rem;
    text-align: justify;
    letter-spacing: 0.02em;
    color: var(--gray);
}

.list-project li .project-owner {
    width: 112px;
    height: 21px;

    font-weight: 300;
    font-size: 1.8rem;
    line-height: 2.1rem;
    text-align: justify;
    letter-spacing: 0.02em;
    color: var(--gray);
}

.list-project li button {
    width: 45px;
    height: 45px;
    background: var(--bg-blue);
    border: none;
    border-radius: 25px;
    cursor: pointer;

    display: flex;
    align-items: center;
    justify-content: center;
    transition-duration: 0.02s;
}

.list-project li button:hover {
    transform: scale(1.08,1.08);
}

.list-project li button img {
    width: 16px;
    height: 16px;
}


@media (max-width: 1350px) {
    .page .border-line {
        margin: 77px 20px 0;
    }
}

@media (max-width: 1200px) {
    .page {    
        display: flex;
        align-items: flex-start;
        justify-content: center;
    }
    
    .page main {
        margin: 0 auto;
    }
    
    .page .border-line {
        display: none;
    }
}

@media (max-width: 850px) {
    
    .container .project-search-container {
        width: 319px;
        height: 45px;
    }

    .list-project li {
        width: 100%;
        min-width: 600px;
        /*width: 792px;*/
        display: flex;
        justify-content: space-between;
    }
    
    .list-project li p {
        width: 38px;
        text-align: center;
    }
    
    .list-project li .project-owner {
        min-width: 100px;
        text-align: center;
    }

    .list-project li .members-number {
        font-size: 1.8rem;
        text-align: center;
    }

    .list-project li a {
        transform: scale(0.85);
    }
}
</style>