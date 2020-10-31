<template>
  <div class="project-details-tab">
    <main v-if="!editFormOpen">
      <div class="project-details">
        <div class="info-group">
          <span>Descrição</span>
          <p>{{ project.description }}</p>
        </div>
        <div class="info-group">
          <span>Github</span>
          <p>{{ project.githubUri }}</p>
        </div>
        <button
          v-if="canEdit"
          @click="editFormOpen = true"
          type="button"
          class="btn-form btn-edit"
        >
          Editar
        </button>
      </div>
    </main>
    <project-form-component
      :project="project"
      v-else
      buttonTitle="Editar Projeto"
      :onFormSubmit="onFormEditSubmit"
    />
  </div>
</template>

<script>
import ProjectFormComponent from "@/components/project-form.component.vue";
import Dialog from "@/components/Dialog.vue";
import httpClient from "../providers/http-client";

const GITHUB_URI_REGEX = /^https:\/\/github.com\/(.)*\/(.)*/;

export default {
  name: "project-details-tab",
  props: ["project"],
  components: {
    Dialog,
    ProjectFormComponent,
  },
  data: function () {
    return {
      editFormOpen: false,
    };
  },
  computed: {
    canEdit() {
      const userId = JSON.parse(localStorage.getItem('@testdocs/user')).id
      if(!this.project.owner) {
        return false
      }
      return parseInt(userId, 10) === this.project.owner.id
    },
  },
  methods: {
    onFormEditSubmit(project) {
      const projectId = this.$route.params.project_id
      httpClient
        .put(`/projects/${projectId}`, project)
        .then((response) => {
          this.project = response.data
        })
        .catch((err) => {
          alert(err.response.data.message || "Ocorreu um error inesperado")
        });
      this.editFormOpen = false;
    },
  },
};
</script>

<style>
.project-details-tab main {
  display: grid;
  grid-template-columns: 1fr 600px 1fr;
  grid-template-areas: "a b c";
  flex: 1;
  background-color: var(--light-green);
  padding: 20px;
}

.project-details-tab .project-details {
  margin-top: 30px;
  grid-area: b;
  max-width: 600px;
}

.project-details-tab .project-details .info-group {
  margin-bottom: 20px;
}

.project-details-tab .project-details .info-group span {
  font-family: Roboto;
  font-weight: bold;
  font-size: 1.4rem;
  line-height: 1.9rem;
  letter-spacing: 0.05em;
  text-transform: uppercase;

  color: rgba(49, 54, 56, 0.8);
}

.project-details-tab .project-details .info-group p {
  font-family: Roboto;
  font-weight: 300;
  font-size: 1.6rem;
  line-height: 2.2rem;
  letter-spacing: 0.05em;
  margin-top: 5px;
  text-align: justify;
  color: rgba(49, 54, 56, 0.8);
}

.project-details-tab .project-details .btn-edit {
  margin-top: 30px;
  width: 100px;
  height: 40px;
  font-size: 1.6rem;
}

.project-details-tab main form {
  flex: 1;
  margin-top: 30px;
  grid-area: b;
}
</style>