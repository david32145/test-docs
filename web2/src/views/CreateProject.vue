<template>
    <div id="create-project-page">
        <SubHeaderWithTitle title="Criar novo projeto"/>
        
        <FormProject objective='create'/>

        <Dialog/>
    </div>
</template>

<script>
import SubHeaderWithTitle from '@/components/SubHeaderWithTitle.vue'
import Dialog from '@/components/Dialog.vue'
import FormProject from '@/components/FormProject.vue'

export default {
    name: 'CreateProject',
    components: {
        SubHeaderWithTitle,
        Dialog,
        FormProject,
    },
}
</script>

<style>
</style>