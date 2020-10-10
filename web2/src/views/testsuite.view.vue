<template>
  <div class="test-suite">
    <header-component />
    <subheader-with-title-component title="Criar novo suíte de teste" />

    <main>
      <form action="#">
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
        <button @click="insertTestSuite" class="btn-form">Criar suíte</button>
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
  name: "UserInsert",
  components: {
    HeaderComponent,
    SubheaderWithTitleComponent,
    Dialog,
  },
  data: function () {
    return {
      titulo: "",
      descricao: "",
      baseURI: "",
      hasTitleError: false,
      hasDescriptionError: false,
      textTitleError: "",
      textDescriptionError: "",
    };
  },

  methods: {
    insertTestSuite: function () {
      let obj = {
        titulo: this.titulo,
        descricao: this.descricao,
      };
      this.$http.post(this.baseURI, obj).then((result) => {
        if (this.fieldsValidation()) {
          this.$router.push({ name: "TestSuites" });
        }
      });
    },
    fieldsValidation() {
      this.hasTitleError = false;
      this.hasDescriptionError = false;
      if (this.titulo === "") {
        this.hasTitleError = true;
        this.textTitleError = "O título é obrigatório";
      }
      if (this.descricao === "") {
        this.hasDescriptionError = true;
        this.textDescriptionError = "A descrição é obrigatória";
      }
      return !(hasTitleError || hasDescriptionError);
    },
  },
};
</script>

<style>
.test-suite {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.test-suite main {
  display: flex;
  flex: 1;
  flex-direction: column;
  align-items: center;
  background-color: var(--light-green);
  padding: 20px;
}

.test-suite main form {
  width: 100%;
  max-width: 600px;
  margin-top: 30px;
}

.test-suite main form .input-group {
  margin-bottom: 22px;
}

.test-suite main form .file-input-preview img {
  width: 100%;
  height: 180px;
  object-fit: fill;
}

.test-suite main form .btn-form {
  margin-top: 30px;
}
</style>