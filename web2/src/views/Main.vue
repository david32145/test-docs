<template>
    <div id='main-page'>
        <Header/>
        <router-view/>
    </div>
</template>

<script>
import Header from "@/components/Header.vue"

export default {
    name: 'Main',
    components: {
        Header
    } 
}
</script>

<style>
#main-page {
    height: 100%;
    background-color: var(--light-green);
}
</style>