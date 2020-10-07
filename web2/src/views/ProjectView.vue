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
                    v-if="owner_project_id === project.owner_id"
                    v-on:click="display = !display" 
                    type="button"
                    class="btn-form btn-edit"
                >
                    Editar
                </button>
            </div>
        </main>

        <FormProject v-else objective='update'/>

    </div>
</template>

<script>
import FormProject from '@/components/FormProject.vue'
import Dialog from '@/components/Dialog.vue'

const GITHUB_URI_REGEX = /^https:\/\/github.com\/(.)*\/(.)*/

export default {
    name: 'ProjectView',
    components: {
        Dialog,
        FormProject
    },
    data: function() {
        return {
            owner_project_id: 0,
            project: {
                owner_id: 0,
                description: 'Lorem Ipsum is simply dummy text of the printin.Lorem Ipsum is simply dummy text of the printin.',
                github_repo_uri: 'https://github.com/vuejs/vue'
            },
            display: true,
        }
    },
}
</script>

<style>
body {
    display: flex;
    flex-direction: column;
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