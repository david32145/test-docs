<template>
    <div class="request-test">
    <Header></Header>
    <SubHeaderWithTitle title="Criar nova solicitação de teste"/>

    <main>
        <form action="#">
            <div class="input-group input-group-ligth" id="version-form-group">
                <label for="version">Versão</label>
                <input type="text" id="version" placeholder="" v-model="versao">
                <span>Versão inválido</span>
            </div>
            <div class="input-group input-group-ligth" id="title-form-group">
                <label for="title">Título</label>
                <input type="text" id="title" placeholder="" v-model="titulo">
                <span>Título inválido</span>
            </div>
            <div class="input-group input-group-ligth" id="description-form-group">
                <label for="description">Descrição</label>
                <input type="text" id="description" placeholder="" v-model="descricao">
                <span>Descrição inválida</span>
            </div>
            <div class="input-group input-group-ligth" id="testeruserid-form-group">
                <label for="testeruserid">Tester ID:</label>
                <input type="text" id="testeruserid" placeholder="" v-model="testerid">
                <span>Tester ID inválido</span>
            </div>
            <button @click="insertRequestTest" class="btn-form">Criar solicitação</button>
        </form>
    </main>
    <Dialog></Dialog>
</div>
</template>

<script>
import Header from '@/components/Header.vue'
import SubHeaderWithTitle from '@/components/SubHeaderWithTitle.vue'
import Dialog from '@/components/Dialog.vue'

export default {
    name: "RequestTest",
    components: {
        Header,
        SubHeaderWithTitle,
        Dialog
    },
    data: function() {
        return {
        versao: "",
        titulo: "",
        descricao: "",
        testerid: "",    
        baseURI: "",
        };
    },
    methods: {
        insertRequestTest: function() {
        let obj = {
            versao: this.versao,
            titulo: this.titulo,
            descricao: this.descricao,
            testerid: this.testerid,
        };
        
        const versionFormGroup = document.querySelector("#version-form-group")
        const versionError = versionFormGroup.querySelector("span")

        const titleFormGroup = document.querySelector("#title-form-group")
        const titleError = titleFormGroup.querySelector("span")

        const descriptionFormGroup = document.querySelector("#description-form-group")
        const descriptionError = descriptionFormGroup.querySelector("span")

        const testeridFormGroup = document.querySelector("#testeruserid-form-group")
        const testeridError = testeridFormGroup.querySelector("span")


        this.$http.post(this.baseURI, obj).then((result) => {
            let hasVersionError = false
            let hasTitleError = false
            let hasDescriptionError = false
            let hasTesterIdError = false

            if(obj.versao === "") {
                hasVersionError = true
                versionError.innerText = "A versão do projeto é obrigatória"
            }
            if(!obj.titulo || obj.titulo.length < 5) {
                hasTitleError = true
                if(!obj.titulo) {
                titleError.innerText = "O título é obrigatório"
                } else {
                titleError.innerText = "O título deve ter pelo menos 5 caracteres"
                }
            }
            if(!obj.descricao || obj.descricao.length < 10) {
            hasDescriptionError = true
            if(!obj.descricao) {
                descriptionError.innerText = "A descrição é obrigatória"
            } else {
                descriptionError.innerText = "A descrição deve ter pelo menos 10 caracteres"
            }
            }
            if(obj.testerid === "") {
                hasTesterIdError = true
                testeridError.innerText = "O ID do tester é obrigatório"
            }
            if(hasVersionError) {
            versionFormGroup.classList.add("input-group-error")
            } else {
            versionFormGroup.classList.remove("input-group-error")
            }
            if(hasTitleError) {
                titleFormGroup.classList.add("input-group-error")
            } else {
                titleFormGroup.classList.remove("input-group-error")
            }
            if(hasDescriptionError) {
            descriptionFormGroup.classList.add("input-group-error")
            } else {
            descriptionFormGroup.classList.remove("input-group-error")
            }
            if(hasTesterIdError) {
                testeridFormGroup.classList.add("input-group-error")
            } else {
                testeridFormGroup.classList.remove("input-group-error")
            }
            if (!(hasVersionError || hasTitleError || hasDescriptionError || hasTesterIdError)) {
            this.$router.push({ name: 'RequestTests'});
            } 
        });
        },
    },
}
</script>

<style>

.request-test {
    height: 85%;
    background-color: var(--light-green);
    display: flex;
    flex-direction: column;
}

.request-test main {
    height: 100%;
    display: flex;
    flex: 1;
    flex-direction: column;
    align-items: center;
    background-color: var(--light-green);
    padding: 20px;
}

.request-test main form {
    width: 100%;
    max-width: 600px;
    margin-top: 30px;
}

.request-test main form .input-group {
    margin-bottom: 22px;
}

.request-test main form .file-input-preview img {
    width: 100%;
    height: 180px;
    object-fit: fill;
}

.request-test main form .btn-form {
    margin-top: 30px;
}
</style>