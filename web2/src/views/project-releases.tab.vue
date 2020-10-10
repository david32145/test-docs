<template>
  <div class="main-container">
    <div class="release-list-container">
      <div class="release-header">
        <h2>{{ releases_tests.length }} releases</h2>
        <router-link
          :to="'/project/' + $route.params.project_id + '/testrequest/new'"
        >
          <button class="btn-form add-new-release">+</button>
        </router-link>
      </div>
      <ul class="release-list">
        <li
          v-for="(release_test, index) in releases_tests"
          :key="release_test.id"
          class="release-list-item"
        >
          <img
            v-if="release_test.status === 'ACCEPTED'"
            src="@/assets/check-dark.svg"
            alt="Icon checked dark"
          />
          <img
            v-else-if="release_test.status === 'REQUESTED'"
            src="@/assets/clock-dark.svg"
            alt="Icon clock dark"
          />
          <img v-else src="@/assets/close-dark.svg" alt="Icon close dark" />

          <div class="release-item-info">
            <span>{{ release_test.version }}</span>
            <p>Liberado por {{ "David" }}</p>
          </div>
          <button
            v-if="release_test.status === 'REQUESTED'"
            @click="removeRelease(index, release_test.id)"
            class="btn-delete-release"
          >
            Remover
          </button>
          <span v-else class="status-text">{{ release_test.description }}</span>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import httpClient from "../providers/http-client";
export default {
  name: "project-releases-tab",
  data: function () {
    return {
      releases_tests: [],
    };
  },
  methods: {
    removeRelease(index, removedReleaseId) {
      httpClient
        .delete(`/test_requests/${removedReleaseId}`)
        .then((response) => {
          this.releases_tests.splice(index, 1);
        })
        .catch((err) => {
          alert(err.response.data.message || "Ocorreu um error inesperado");
        });
    },
  },
  created() {
    const projectId = this.$route.params.project_id;
    httpClient
      .get(`/test_requests/projects/${projectId}`)
      .then((response) => {
        this.releases_tests = response.data;
      })
      .catch((err) => {
        alert(err.response.data.message || "Ocorreu um error inesperado");
      });
  },
};
</script>

<style>
.main-container {
  display: flex;
  flex: 1;
  flex-direction: row;
  justify-content: center;
  padding: 20px;
}

.release-list-container {
  display: flex;
  grid-area: b;
  flex-direction: column;
  width: 600px;
}

.release-list-container .release-header {
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}

.release-list-container .release-header h2 {
  font-family: Roboto;
  font-weight: 500;
  font-size: 2.2rem;
  line-height: 2.6rem;
  letter-spacing: 0.02em;
  color: var(--dark-text);
}

.release-list-container .release-header button {
  width: 45px;
  height: 45px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 2.5rem;
}

.release-list-container .release-list {
  margin-top: 30px;
}

.release-list-container .release-list .release-list-item {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-bottom: 25px;
}

.release-list .release-list-item .release-item-info {
  display: flex;
  flex-direction: column;
  height: 100%;
  margin-left: 15px;
}

.release-list-item .release-item-info span {
  font-family: Roboto;
  font-weight: 400;
  font-size: 1.8rem;
  letter-spacing: 0.02em;
  color: var(--dark-text);
  margin-bottom: 12px;
  cursor: pointer;
}

.release-list-item .release-item-info p {
  font-family: Roboto;
  font-weight: 300;
  margin-top: 6px;
  font-size: 1.3rem;
  letter-spacing: 0.02em;
  color: var(--dark-text);
}

.release-list-item .status-text {
  margin-left: auto;
  font-family: Roboto;
  font-weight: 300;
  font-size: 1rem;
  letter-spacing: 0.02em;
  color: var(--dark-text);
}

.release-list-item .btn-delete-release {
  margin-left: auto;
  font-family: Roboto;
  font-size: 1.2rem;
  align-items: center;
  letter-spacing: 0.02em;
  text-transform: uppercase;
  color: var(--bg-red);
  background: transparent;
  cursor: pointer;
  border: none;
}
</style>