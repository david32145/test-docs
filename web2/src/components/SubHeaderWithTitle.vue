<template>
    <section id="sub-header">
        <h1>{{title}}</h1>
    </section>
</template>

<script>
export default {
    name:'SubHeader',
    props: {
        title: {
            type: String,
            required: true
        }
    },
}
</script>

<style scoped>
#sub-header {
    min-height: 60px;
    background-color: #FFFFFF;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
}

#sub-header h1 {
    font-family: Roboto;
    font-weight: 500;
    font-size: 2.2rem;
    line-height: 2.6rem;
    text-align: center;
    letter-spacing: 0.02em;
    color: var(--dark-text);
}
</style>