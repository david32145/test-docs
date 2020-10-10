<template>
  <div id="create-project-page">
    <header-component />
    <subheader-with-title-component title="Criar novo projeto" />
    <project-form-component buttonTitle="Criar Projeto" :onFormSubmit="onFormCreateSubmit" />

    <Dialog />
  </div>
</template>

<script>
import HeaderComponent from "@/components/header.component.vue";
import SubheaderWithTitleComponent from "@/components/subheader-with-title.component.vue";
import Dialog from "@/components/Dialog.vue";
import ProjectFormComponent from "@/components/project-form.component.vue";

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

    }
  }
};
</script>

<style>
</style>