<template>
  <section id="sub-header-with-tile">
    <h1>{{ title }}</h1>
  </section>
</template>

<script>
export default {
  name: "subheader-with-title-component",
  props: {
    title: {
      type: String,
      required: true,
    },
  },
};
</script>

<style scoped>
#sub-header-with-tile {
  min-height: 60px;
  background-color: #ffffff;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}

#sub-header-with-tile h1 {
  font-family: Roboto;
  font-weight: 500;
  font-size: 2.2rem;
  line-height: 2.6rem;
  text-align: center;
  letter-spacing: 0.02em;
  color: var(--dark-text);
}
</style>