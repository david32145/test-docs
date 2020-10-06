<template>
    <div id="create-project-page">
        <SubHeaderWithTitle title="Criar novo projeto"/>
        <main>
            <form action="#">
                <div class="input-group input-group-ligth" id="name-form-group">
                    <label for="name">Nome</label>
                    <input type="text" v-model="name" id="name" placeholder="Uber Technologies Inc.">
                    <span>Nome inválido</span>
                </div>
                <div class="input-group input-group-ligth" id="description-form-group">
                    <label for="description">Descrição</label>
                    <input type="text" v-model="description" id="description" placeholder="empresa multinacional americana...">
                    <span>Descrição inválido</span>
                </div>
                <div class="input-group input-group-ligth" id="github-uri-form-group">
                    <label for="github-uri">Github</label>
                    <input type="text" v-model="github_repo_uri" id="github-uri" placeholder="https://github.com/uber/uber-app">
                    <span>Github inválido</span>
                </div>
                <div class="file-input-preview">
                    <img src="@/assets/project-placeholder.png" alt="Project placeholder">
                    <input type="file" hidden="true">
                </div>
                <button type="submit" class="btn-form">Criar projeto</button>
            </form>
        </main>
        
        <Dialog/>
    </div>
</template>

<script>
import Dialog from '@/components/Dialog.vue'
import SubHeaderWithTitle from '@/components/SubHeaderWithTitle.vue'

export default {
    name: 'CreateProject',
    components: {
        Dialog,
        SubHeaderWithTitle
    },
    data: function() {
        return {
            name: '',
            description:'',
            github_repo_uri: '',
            file: ''
        }
    }
}
</script>

<style>
#create-project-page {
    height: 100%;
}

main {
    display: flex;
    flex: 1;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

main form {
    width: 100%;
    max-width: 600px;
    margin-top: 30px;
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