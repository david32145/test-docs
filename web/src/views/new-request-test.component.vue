<template>
  <div class="request-test">
    <header-component></header-component>
    <subheader-with-title-component title="Criar nova solicitação de teste" />
    <main>
      <form action="#">
        <div
          :class="[
            'input-group',
            'input-group-ligth',
            { 'input-group-error': hasVersionError },
          ]"
          id="version-form-group"
        >
          <label for="version">Versão</label>
          <input type="text" id="version" placeholder="" v-model="versao" />
          <span>{{ textVersionError }}</span>
        </div>
        <div
          :class="[
            'input-group',
            'input-group-ligth',
            { 'input-group-error': hasTitleError },
          ]"
          id="title-form-group"
        >
          <label for="title">Título</label>
          <input type="text" id="title" placeholder="" v-model="titulo" />
          <span>{{ textTitleError }}</span>
        </div>
        <div
          :class="[
            'input-group',
            'input-group-ligth',
            { 'input-group-error': hasDescriptionError },
          ]"
          id="description-form-group"
        >
          <label for="description">Descrição</label>
          <input
            type="text"
            id="description"
            placeholder=""
            v-model="descricao"
          />
          <span>{{ textDescriptionError }}</span>
        </div>
        <div
          :class="[
            'input-group',
            'input-group-ligth',
            { 'input-group-error': hasTesterIdError },
          ]"
          id="testeruserid-form-group"
        >
          <label for="testeruserid">Tester ID:</label>
          <input
            type="text"
            id="testeruserid"
            placeholder=""
            v-model="testerid"
          />
          <span>{{ textTesterIdError }}</span>
        </div>
        <button @click="insertRequestTest" class="btn-form">
          Criar solicitação
        </button>
      </form>
    </main>
    <Dialog></Dialog>
  </div>
</template>

<script>
import HeaderComponent from "@/components/header.component.vue";
import SubheaderWithTitleComponent from "@/components/subheader-with-title.component.vue";
import Dialog from "@/components/Dialog.vue";
import httpClient from "../providers/http-client";

export default {
  name: "new-test-request-view",
  components: {
    HeaderComponent,
    SubheaderWithTitleComponent,
    Dialog,
  },
  data: function () {
    return {
      versao: "",
      titulo: "",
      descricao: "",
      testerid: "",
      baseURI: "",
      hasVersionError: false,
      hasTitleError: false,
      hasDescriptionError: false,
      hasTesterIdError: false,
      textVersionError: "",
      textTitleError: "",
      textDescriptionError: "",
      textTesterIdError: "",
    };
  },
  methods: {
    insertRequestTest(event) {
      event.preventDefault();
      if (!this.fieldsValidation()) return;
      const userId = JSON.parse(localStorage.getItem("@testdocs/user")).id;
      const projectId = this.$route.params.project_id;
      let requestBody = {
        version: this.versao,
        title: this.titulo,
        requestingUser: { id: userId },
        project: { id: projectId },
        testerUser: { id: this.testerid },
      };

      httpClient
        .post("/test_requests", requestBody)
        .then(() => {
          this.$router.push({ path: `/project/${projectId}` });
        })
        .catch((err) => {
          alert(err.response.data.message || "Ocorreu um error inesperado");
        });
    },

    fieldsValidation() {
      this.hasVersionError = false;
      this.hasTitleError = false;
      this.hasDescriptionError = false;
      this.hasTesterIdError = false;

      if (this.versao === "") {
        this.hasVersionError = true;
        this.textVersionError = "A versão do projeto é obrigatória";
      }
      if (!this.titulo || this.titulo.length < 5) {
        this.hasTitleError = true;
        this.textTitleError = !this.titulo
          ? "O título é obrigatório"
          : "O título deve ter pelo menos 5 caracteres";
      }
      if (!this.descricao || this.descricao.length < 10) {
        this.hasDescriptionError = true;
        this.textDescriptionError = !this.descricao
          ? "A descrição é obrigatória"
          : "A descrição deve ter pelo menos 10 caracteres";
      }
      if (this.testerid === "") {
        this.hasTesterIdError = true;
        this.textTesterIdError = "O ID do tester é obrigatório";
      }
      return !(
        this.hasVersionError ||
        this.hasTitleError ||
        this.hasDescriptionError ||
        this.hasTesterIdError
      );
    },
  },
};
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