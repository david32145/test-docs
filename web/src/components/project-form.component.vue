<template>
  <div class="project-form">
    <main>
      <form action="#" @submit.prevent="send">
        <div
          :class="[
            'input-group',
            'input-group-ligth',
            { 'input-group-error': hasNameError },
          ]"
          id="name-form-group"
        >
          <label for="name">Nome</label>
          <input
            type="text"
            v-model="name"
            id="name"
            placeholder="Uber Technologies Inc."
          />
          <span>{{ textNameError }}</span>
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
            v-model="description"
            id="description"
            placeholder="empresa multinacional americana..."
          />
          <span>{{ textDescriptionError }}</span>
        </div>
        <div
          :class="[
            'input-group',
            'input-group-ligth',
            { 'input-group-error': hasGithubError },
          ]"
          id="github-uri-form-group"
        >
          <label for="github-uri">Github</label>
          <input
            type="text"
            v-model="github_repo_uri"
            id="github-uri"
            placeholder="https://github.com/uber/uber-app"
          />
          <span>{{ textGithubError }}</span>
        </div>
        <div v-on:click="openInputFile" class="file-input-preview">
          <img :src="urlPreview" alt="Project placeholder" />
          <input @change="changeFile" ref="file" type="file" hidden="true" />
        </div>
        <button type="submit" class="btn-form">
          {{ buttonTitle }}
        </button>
      </form>
    </main>
    <Dialog />
  </div>
</template>

<script>
import Dialog from "@/components/Dialog.vue";
import axios from "axios";
import projectPlaceholder from "@/assets/project-placeholder.png";
import httpClient from "../providers/http-client";

const GITHUB_URI_REGEX = /^https:\/\/github.com\/(.)*\/(.)*/;

export default {
  name: "project-form-component",
  components: {
    Dialog,
  },
  props: ["onFormSubmit", "buttonTitle", "project"],
  data() {
    return {
      name: this.project.name,
      description: this.project.description,
      github_repo_uri: this.project.githubUri,
      file: null,
      urlPreview: this.project.imageUri || projectPlaceholder,
      hasNameError: false,
      hasDescriptionError: false,
      hasGithubError: false,
      textNameError: "",
      textDescriptionError: "",
      textGithubError: "",
    };
  },
  methods: {
    send() {
      if (this.fieldsValidation()) {
        this.onFormSubmit({
          ...this.project,
          name: this.name,
          description: this.description,
          githubUri: this.github_repo_uri,
        });
      }
    },
    changeFile(e) {
      this.file = e.target.files[0];
      URL.revokeObjectURL(this.urlPreview);
      this.urlPreview = URL.createObjectURL(this.file);
      const projectId = this.$route.params.project_id;
      const formData = new FormData();
      formData.append("image", this.file);
      axios
        .post("http://localhost:8080/api/project/" + projectId + "/imageUri", formData, {
          headers: {
            "Content-Type": `multipart/form-data; boundary=${formData._boundary}`,
          },
        })
        .then((response) => {
          this.urlPreview = response.data;
        })
        .catch(() => {
          alert("Error ao salvar imagem");
        });
    },
    openInputFile() {
      this.$refs["file"].click();
    },
    fieldsValidation() {
      this.hasNameError = false;
      this.hasDescriptionError = false;
      this.hasGithubError = false;

      if (!this.name || this.name.length < 5) {
        this.hasNameError = true;
        this.textNameError = !this.name
          ? "O nome é obrigatório"
          : "O nome deve ter pelo menos 5 caracteres";
      }

      if (!this.description || this.description.length < 10) {
        this.hasDescriptionError = true;
        this.textDescriptionError = !this.description
          ? "A descrição é obrigatória"
          : "A descrição deve ter pelo menos 10 caracteres";
      }

      if (
        !this.github_repo_uri ||
        !GITHUB_URI_REGEX.test(this.github_repo_uri)
      ) {
        this.hasGithubError = true;
        this.textGithubError = !this.github_repo_uri
          ? "A url do github é obrigatória"
          : "Url inválida";
      }
      return !(
        this.hasNameError ||
        this.hasDescriptionError ||
        this.hasGithubError
      );
    },
  },
};
</script>

<style>
.project-form main {
  display: grid;
  grid-template-columns: 1fr 600px 1fr;
  grid-template-areas: "a b c";
  flex: 1;
  background-color: var(--light-green);
  padding: 20px;
}

.project-form main form {
  flex: 1;
  margin-top: 30px;
  grid-area: b;
}

.project-form main form .input-group {
  margin-bottom: 22px;
}

.project-form main form .file-input-preview img {
  width: 100%;
  height: 180px;
  object-fit: fill;
}

.project-form main form .btn-form {
  margin-top: 30px;
}
</style>