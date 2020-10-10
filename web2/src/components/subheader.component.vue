<template>
  <section class="sub-header">
    <div class="project-info">
      <img src="@/assets/avatar.svg" alt="Project Logo" />
      <span>{{ projectName }}</span>
    </div>
    <div class="tab">
      <div
        v-for="(tab, index) in tabs"
        :key="index"
        @click="changedActiveTab(tab.number)"
        :class="{ 'tab-active': tab.number === activeTab }"
        class="tab-item"
      >
        <div class="tab-item-label">
          <label>
            {{ tab.title }}
          </label>
          <img
            v-if="tab.title === 'Membros'"
            src="@/assets/users_icon.svg"
            alt="Users icon"
          />
        </div>
        <div class="tab-active-indicator"></div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "subheader-component",
  props: ["changedActiveTab", "activeTab", "projectName"],
  data: function () {
    return {
      projectId: 0,
      tabs: [
        {
          number: 1,
          title: "Informações",
          path: "view",
        },
        {
          number: 2,
          title: "Membros",
          path: "members",
        },
        {
          number: 3,
          title: "Releases",
          path: "releases",
        },
      ],
    };
  },
};
</script>

<style>
.sub-header {
  min-height: 60px;
  background-color: #ffffff;
  display: grid;
  grid-template-columns: 1fr 600px 1fr;
}

.sub-header .project-info {
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 0 28px;
}

.sub-header .project-info span {
  font-family: Roboto;
  font-weight: 500;
  margin-left: 35px;
  font-size: 2rem;
  text-align: center;
  letter-spacing: 0.02em;
  color: var(--dark-text);
}

.sub-header .project-info img {
  width: 40px;
  border-radius: 25px;
}

.sub-header .tab {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.sub-header .tab-item {
  max-width: 120px;
  height: 100%;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.sub-header .tab-item + .tab-item {
  margin-left: 50px;
  height: 100%;
}

.sub-header .tab-item .tab-item-label {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.sub-header .tab-item .tab-item-label label {
  font-family: Roboto;
  font-weight: 300;
  font-size: 1.6rem;
  letter-spacing: 0.02em;
  cursor: pointer;
  color: rgba(49, 54, 56, 0.8);
  margin-right: 5px;
}

.sub-header .tab-item .tab-active-indicator {
  width: 100%;
  border-bottom: 2px solid var(--dark-text);
  position: absolute;
  visibility: hidden;
  left: 0;
  bottom: 0;
}

.sub-header .tab-item.tab-active .tab-item-label label {
  font-weight: 400;
  color: var(--dark-text);
}

.sub-header .tab-item.tab-active .tab-active-indicator {
  visibility: visible;
}
</style>