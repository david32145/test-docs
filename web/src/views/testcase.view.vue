<template>
  <div class="test-case">
    <header-component />
    <subheader-with-title-component title="Criar novo caso de teste" />

    <main>
      <form v-on:submit="insertTestCase">
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
            { 'input-group-error': hasTaskError },
          ]"
          id="task-form-group"
        >
          <label for="task">Task ID:</label>
          <input type="text" id="task" placeholder="" v-model="taskid" />
          <span>{{ textTaskError }}</span>
        </div>
        <button type="submit" class="btn-form">Criar caso de teste</button>
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
  name: "testcase-view",
  components: {
    HeaderComponent,
    SubheaderWithTitleComponent,
    Dialog,
  },
  data: function () {
    return {
      titulo: "",
      descricao: "",
      taskid: "",
      baseURI: "",
      hasTitleError: false,
      hasDescriptionError: false,
      hasTaskError: false,
      textTitleError: "",
      textDescriptionError: "",
      textTaskError: "",
    };
  },
  methods: {
    insertTestCase(event) {
      event.preventDefault();
      if (!this.fieldsValidation()) {
        return;
      }
      let requestBody = {
        title: this.titulo,
        description: this.descricao,
        testSuite: { id: this.$route.params.test_suite_id },
      };

      httpClient
        .post("/test_requests/test_suites/test_cases", requestBody)
        .then((response) => {
          this.$router.push({
            path: `/project/${this.$route.params.project_id}`,
          });
        })
        .catch((err) => {
          alert(err.response.data.message || "Ocorreu um error inesperado");
        });
    },
    fieldsValidation() {
      this.hasTitleError = false;
      this.DescriptionError = false;
      this.hasTaskError = false;

      if (this.titulo === "") {
        this.hasTitleError = true;
        this.textTitleError = "O título é obrigatório";
      }
      if (this.descricao === "") {
        this.hasDescriptionError = true;
        this.textDescriptionError = "A descrição é obrigatória";
      }
      if (this.taskid === "") {
        this.hasTaskError = true;
        this.textTaskError = "A task é obrigatória";
      }
      return !(
        this.hasTitleError ||
        this.hasDescriptionError ||
        this.hasTaskError
      );
    },
  },
};
</script>

<style>
.test-case {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.test-case main {
  display: flex;
  flex: 1;
  flex-direction: column;
  align-items: center;
  background-color: var(--light-green);
  padding: 20px;
}

.test-case main form {
  width: 100%;
  max-width: 600px;
  margin-top: 30px;
}

.test-case main form .input-group {
  margin-bottom: 22px;
}

.test-case main form .file-input-preview img {
  width: 100%;
  height: 180px;
  object-fit: fill;
}

.test-case main form .btn-form {
  margin-top: 30px;
}
</style>