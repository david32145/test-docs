<template>
  <main class="project-members">
    <div class="project-members-header">
      <h1>{{ members.length }} Integrantes</h1>
      <div>
        <input
          v-model="searched_member"
          type="text"
          placeholder="buscar integrante"
          v-on:keyup.enter="addMember"
        />
      </div>
    </div>
    <ul class="members-list">
      <li
        v-for="(member, index) in members"
        :key="member.id"
        class="members-list-item"
      >
        <div class="member-data">
          <img :src="member.avatarUri" alt="Avatar" />
          <div class="member-info">
            <div class="member-name_and_type">
              <p class="member-name">{{ member.name }}</p>

              <span
                v-if="ownerId === member.id"
                class="type-member owner-member"
                >dono</span
              >
              <span v-else class="type-member dev-member">dev</span>
            </div>
            <p class="member-email">{{ member.email }}</p>
          </div>
        </div>
        <button
          v-if="canDelete(member.id)"
          @click="removeMember(index, member.id)"
        >
          Remover
        </button>
      </li>
    </ul>
  </main>
</template>

<script>
import httpClient from "../providers/http-client";

export default {
  name: "project-members-tab",
  props: ["project"],
  data: function () {
    return {
      searched_member: "",
      members: [],
      ownerId: parseInt(
        JSON.parse(localStorage.getItem("@testdocs/user")).id,
        10
      ),
    };
  },
  methods: {
    addMember() {
      const projectId = this.$route.params.project_id;
      httpClient
        .post(`/projects/${projectId}/members`, {
          userEmail: this.searched_member,
        })
        .then((response) => {
          this.members.push(response.data);
        })
        .catch((err) => {
          alert(err.response.data.message || "Ocorreu um error inesperado");
        });
    },
    removeMember(index, removedMemberId) {
      const projectId = this.$route.params.project_id;
      httpClient
        .delete(`/projects/${projectId}/members/${removedMemberId}`, {
          userEmail: this.searched_member,
        })
        .then((response) => {
          this.members.splice(index, 1);
        })
        .catch((err) => {
          alert(err.response.data.message || "Ocorreu um error inesperado");
        });
    },
    canDelete(deleteUser) {
      const userId = parseInt(
        JSON.parse(localStorage.getItem("@testdocs/user")).id,
        10
      );
      if (deleteUser === userId) {
        return false;
      }
      return userId === parseInt(this.project.ownerId);
    },
  },
  created() {
    const projectId = this.$route.params.project_id;
    httpClient
      .get(`/projects/${projectId}/members`)
      .then((response) => {
        this.members = response.data;
      })
      .catch((err) => {
        alert(err.response.data.message || "Ocorreu um error inesperado");
      });
  },
};
</script>

<style>
.project-members {
  padding: 25px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
}

.project-members .project-members-header {
  width: 100%;
  max-width: 600px;
  min-width: 450px;
  margin-top: 30px;
  margin-bottom: 30px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.project-members-header h1 {
  font-weight: 500;
  font-size: 2.2rem;
  line-height: 2.6rem;
  color: var(--dark-text);
}

.project-members-header input {
  width: 200px;
  background: transparent;
  border: none;
  border-bottom: 2px solid rgba(49, 54, 56, 0.8);
  font-weight: 300;
  font-size: 1.6rem;
  line-height: 1.9rem;
  text-align: left;
  padding: 10px;
  transition: 1s;
}

.project-members-header input:focus {
  border-bottom: 2px solid var(--bg-blue);
}

.project-members-header .users-list {
  background-color: #ffffff;
  width: 200px;
  position: absolute;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.project-members-header .users-list li {
  width: 100%;
  padding: 8px;
  cursor: pointer;
}

.project-members-header .users-list li:hover {
  background-color: var(--light-green);
}

.project-members-header .users-list li p {
  width: 100%;
  font-size: 1.6rem;
  color: var(--dark-text);
  text-align: justify;
}

.project-members .members-list {
  width: 100%;
  max-width: 600px;
  min-width: 450px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.members-list .members-list-item {
  width: 100%;
  margin-bottom: 20px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}

.members-list-item .member-data {
  display: flex;
  align-items: center;
  justify-content: center;
}

.member-data img {
  width: 45px;
  height: 45px;
  border-radius: 24px;
  margin-right: 15px;
}

.member-data .member-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.member-data .member-info .member-name_and_type {
  display: flex;
  align-items: center;
  justify-content: center;
}

.member-name_and_type .member-name {
  width: 170px;
  margin-right: 20px;
  font-size: 1.8rem;
  line-height: 2.1rem;
  color: var(--dark-text);
}

.member-name_and_type .type-member {
  width: 45px;
  height: 20px;
  padding: 4px 10px;
  border-radius: 12px;
  font-size: 1.2rem;
  line-height: 1.4rem;
  text-align: center;
  color: #ffffff;
}

.member-name_and_type .owner-member {
  background: var(--bg-red);
}

.member-name_and_type .dev-member {
  background: var(--bg-blue);
}

.member-data .member-info .member-email {
  width: 100%;
  font-weight: 300;
  font-size: 1.4rem;
  line-height: 1.6rem;
  color: var(--dark-text);
}

.members-list-item button {
  background: transparent;
  color: var(--bg-red);
  border: none;
  padding: 5px;

  border-radius: 5px;
  font-size: 1.4rem;
  line-height: 1.6rem;
  text-align: center;
  text-transform: uppercase;
  cursor: pointer;
  transition: 0.5s;
}

.members-list-item button:hover {
  color: #ffffff;
  background: var(--bg-red);
  transform: scale(1.05);
}
</style>