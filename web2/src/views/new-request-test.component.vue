<template>
  <div class="request-test">
    <header-component></header-component>
    <subheader-with-title-component title="Criar nova solicitação de teste" />
    <main>
      <form action="#">
        <div class="input-group input-group-ligth" id="version-form-group">
          <label for="version">Versão</label>
          <input type="text" id="version" placeholder="" v-model="versao" />
          <span>Versão inválido</span>
        </div>
        <div class="input-group input-group-ligth" id="title-form-group">
          <label for="title">Título</label>
          <input type="text" id="title" placeholder="" v-model="titulo" />
          <span>Título inválido</span>
        </div>
        <div class="input-group input-group-ligth" id="description-form-group">
          <label for="description">Descrição</label>
          <input
            type="text"
            id="description"
            placeholder=""
            v-model="descricao"
          />
          <span>Descrição inválida</span>
        </div>
        <div class="input-group input-group-ligth" id="testeruserid-form-group">
          <label for="testeruserid">Tester ID:</label>
          <input
            type="text"
            id="testeruserid"
            placeholder=""
            v-model="testerid"
          />
          <span>Tester ID inválido</span>
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
    insertRequestTest: function () {
      let obj = {
        versao: this.versao,
        titulo: this.titulo,
        descricao: this.descricao,
        testerid: this.testerid,
      };

      this.$http.post(this.baseURI, obj).then((result) => {
        if (this.fieldsValidation()) {
          this.$router.push({ name: "RequestTests" });
        }
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
        hasVersionError ||
        hasTitleError ||
        hasDescriptionError ||
        hasTesterIdError
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