<template>
  <div class="view-project-view">
    <header-component></header-component>
    <subheader-component
      :projectName="project.name"
      :activeTab="activeTab"
      :imageLogo="project.imageUri"
      :changedActiveTab="changedActiveTab"
    ></subheader-component>
    <div v-show="activeTab == 1">
      <project-details-tab :project="project"></project-details-tab>
    </div>
    <div v-show="activeTab == 2">
      <project-members-tab :project="project"></project-members-tab>
    </div>
    <div v-show="activeTab == 3">
      <project-releases-tab></project-releases-tab>
    </div>
  </div>
</template>

<script>
import SubheaderComponent from "@/components/subheader.component.vue";
import HeaderComponent from "@/components/header.component.vue";
import ProjectDetailsTab from "@/views/project-details.tab.vue";
import ProjectMembersTab from "@/views/project-members.tab.vue";
import ProjectReleasesTab from "@/views/project-releases.tab.vue";
import httpClient from "../providers/http-client";

export default {
  name: "view-project-view",
  components: {
    SubheaderComponent,
    HeaderComponent,
    ProjectDetailsTab,
    ProjectMembersTab,
    ProjectReleasesTab,
  },
  data() {
    return {
      activeTab: 1,
      project: {
        name: "Uber Technologies",
        description:
          "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to.",
        githubUri: "https://github.com/vuejs/vue",
      },
    };
  },
  methods: {
    changedActiveTab(tabNumber) {
      this.activeTab = tabNumber;
    },
  },
  created() {
    const projectId = this.$route.params.project_id;
    httpClient
      .get(`/projects/${projectId}`)
      .then((response) => {
        this.project = response.data;
      })
      .catch(() => {
        this.$router.push({ name: "Dashboard" });
      });
  },
};
</script>

<style>
.view-project-view {
  background-color: var(--light-green);
  height: 100%;
  display: flex;
  flex-direction: column;
}
</style>