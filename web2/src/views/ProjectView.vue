<template>
    <div>
        <main v-if="display">
            <div class="project-details">
                <div class="info-group">
                    <span>Descrição</span>
                    <p>{{project.description}}</p>
                </div>
                <div class="info-group">
                    <span>Github</span>
                    <p>{{project.github_repo_uri}}</p>
                </div>
                <button 
                    v-if="userId === project.owner_id"
                    @click="display = !display" 
                    type="button"
                    class="btn-form btn-edit"
                >
                    Editar
                </button>
            </div>
        </main>

        <ProjectForm v-else action='update' :opcional='display'/>

    </div>
</template>

<script>
import ProjectForm from '@/components/ProjectForm.vue'
import Dialog from '@/components/Dialog.vue'

const GITHUB_URI_REGEX = /^https:\/\/github.com\/(.)*\/(.)*/

export default {
    name: 'ProjectView',
    components: {
        Dialog,
        ProjectForm
    },
    data: function() {
        return {
            userId: 0,
            project: {},
            display: true,
        }
    },
    beforeRouteEnter(to, from, next) {
        next(vm => {
            vm.project = {
                owner_id: 0,
                description: 'Lorem Ipsum is simply dummy text of the printin.Lorem Ipsum is simply dummy text of the printin.',
                github_repo_uri: 'https://github.com/vuejs/vue'
            }
        })
    },
}
</script>

<style>
main {
    display: grid;
    grid-template-columns: 1fr 600px 1fr;
    grid-template-areas: "a b c";
    flex: 1;
    background-color: var(--light-green);
    padding: 20px;
}

.project-details {
    margin-top: 30px;
    grid-area: b;
    max-width: 600px;
}

.project-details .info-group {
    margin-bottom: 20px;
}

.project-details .info-group span {
    font-family: Roboto;
    font-weight: bold;
    font-size: 1.4rem;
    line-height: 1.9rem;
    letter-spacing: 0.05em;
    text-transform: uppercase;

    color: rgba(49, 54, 56, 0.8);
}

.project-details .info-group p {
    font-family: Roboto;
    font-weight: 300;
    font-size: 1.6rem;
    line-height: 2.2rem;
    letter-spacing: 0.05em;
    margin-top: 5px;
    text-align: justify;
    color: rgba(49, 54, 56, 0.8);
}

.project-details .btn-edit {
    margin-top: 30px;
    width: 100px;
    height: 40px;
    font-size: 1.6rem;
}

main form {
    flex: 1;
    margin-top: 30px;
    grid-area: b;
}
</style>