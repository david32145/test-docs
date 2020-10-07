<template>
    <div id="project-page">
        <SubHeader/>
        <router-view/>
    </div>
</template>

<script>
import SubHeader from "@/components/SubHeader.vue"

export default {
    name: 'Project',
    components:  {
        SubHeader
    }
}
</script>

<style>
#project-page {
    background-color: var(--light-green);
}
</style>