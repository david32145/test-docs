<template>
    <div>
        <main>
            <form action="#" @submit.prevent="send">
                <div :class="['input-group', 'input-group-ligth', {'input-group-error': hasNameError}]" id="name-form-group">
                    <label for="name">Nome</label>
                    <input type="text" v-model="name" id="name" placeholder="Uber Technologies Inc.">
                    <span>{{textNameError}}</span>
                </div>
                <div :class="['input-group', 'input-group-ligth', {'input-group-error': hasDescriptionError}]" id="description-form-group">
                    <label for="description">Descrição</label>
                    <input type="text" v-model="description" id="description" placeholder="empresa multinacional americana...">
                    <span>{{textDescriptionError}}</span>
                </div>
                <div :class="['input-group', 'input-group-ligth', {'input-group-error': hasGithubError}]" id="github-uri-form-group">
                    <label for="github-uri">Github</label>
                    <input type="text" v-model="github_repo_uri" id="github-uri" placeholder="https://github.com/uber/uber-app">
                    <span>{{textGithubError}}</span>
                </div>
                <div class="file-input-preview">
                    <img src="@/assets/project-placeholder.png" alt="Project placeholder">
                    <input type="file" hidden="true">
                </div>
                <button type="submit" class="btn-form"> 
                    {{(objective==='create') ? 'Criar' : 'Editar'}} Projeto
                </button>
            </form>
        </main>
        <Dialog/>
    </div>
</template>

<script>
import Dialog from '@/components/Dialog.vue'

const GITHUB_URI_REGEX = /^https:\/\/github.com\/(.)*\/(.)*/

export default {
    name: 'ProjectForm',
    components: {
        Dialog
    },
    props: {
        objective: {   //o form deve fazer: create ou update ?
            type: String,
            required: true
        }, 
    },
    data: function() {
        return {
            name: '',
            description:'',
            github_repo_uri: '',
            file: '',
            hasNameError: false,
            hasDescriptionError: false,
            hasGithubError: false,
            textNameError: '',
            textDescriptionError: '',
            textGithubError: '',
        }
    },
    methods: {
        send() {
            if(this.fieldsValidation()) {
                if(this.objective === 'create') {
                    this.$router.push({name: 'ProjectView'})
                    //POST
                } else {
                    //PUT
                    this.$router.push({name: 'ProjectReleases'})
                    //this.$router.refresh()
                }
            }
        },
        fieldsValidation() {
            this.hasNameError = false
            this.hasDescriptionError = false
            this.hasGithubError = false

            if(!this.name || this.name.length < 5) {
                this.hasNameError = true
                this.textNameError = (!this.name) ? "O nome é obrigatório" : "O nome deve ter pelo menos 5 caracteres"
            }

            if(!this.description || this.description.length < 10) {
                this.hasDescriptionError = true
                this.textDescriptionError = (!this.description) ? "A descrição é obrigatória" : "A descrição deve ter pelo menos 10 caracteres"
            }

            if(!this.github_repo_uri || !GITHUB_URI_REGEX.test(this.github_repo_uri)) {
                this.hasGithubError = true
                this.textGithubError = (!this.github_repo_uri) ? "A url do github é obrigatória" : "Url inválida"
            }
            return !(this.hasNameError || this.hasDescriptionError || this.hasGithubError)
        },
    }
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

main form {
    flex: 1;
    margin-top: 30px;
    grid-area: b;
}

main form .input-group {
    margin-bottom: 22px;
}

main form .file-input-preview img {
    width: 100%;
    height: 180px;
    object-fit: fill;
}

main form .btn-form {
    margin-top: 30px;
}
</style>