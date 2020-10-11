<template>
  <div id="create-project-page">
    <header-component />
    <subheader-with-title-component title="Criar novo projeto" />
    <project-form-component
      project="{}"
      buttonTitle="Criar Projeto"
      :onFormSubmit="onFormCreateSubmit"
    />

    <Dialog />
  </div>
</template>

<script>
import HeaderComponent from "@/components/header.component.vue";
import SubheaderWithTitleComponent from "@/components/subheader-with-title.component.vue";
import Dialog from "@/components/Dialog.vue";
import ProjectFormComponent from "@/components/project-form.component.vue";
import httpClient from "../providers/http-client";

export default {
  name: "new-project-view",
  components: {
    HeaderComponent,
    SubheaderWithTitleComponent,
    Dialog,
    ProjectFormComponent,
  },
  methods: {
    onFormCreateSubmit(project) {
      httpClient
        .post("/projects", {
          ...project,
          imageUri: "http://api.adorable.io/avatars/256/abott@adorable.png",
        })
        .then(() => {
          this.$router.push({ name: "Dashboard" });
        })
        .catch((err) => {
          alert(err.response.data.message || "Ocorreu um error inesperado");
        });
    },
  },
};
</script>

<style>
</style>